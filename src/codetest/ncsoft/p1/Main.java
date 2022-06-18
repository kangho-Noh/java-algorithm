package codetest.ncsoft.p1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		Solution sol = new Solution();
		String[] solution = sol.solution(new String[] {
			"spy", "ray", "spy", "room", "once", "ray", "spy", "once", "qqq", "sss", "sss", "once"
		});
		Arrays.stream(solution).forEach(s -> System.out.print(s+" "));
	}
}
class Solution {
	public String[] solution(String[] movie) {
		String[] ans = {};
		HashMap<String, Integer> indexHash = new HashMap<>();
		List<Movie> count = new ArrayList<>();
		int movieSize = 0;
		for (String s : movie) {
			if (indexHash.containsKey(s)) {
				int ind = indexHash.get(s);
				Movie m = count.get(ind);
				m.count++;
			} else {
				indexHash.put(s, movieSize);
				movieSize++;
				count.add(new Movie(s, 1));
			}
		}
		Collections.sort(count);
		ans = new String[movieSize];
		for (int i = 0; i < movieSize; i++) {
			ans[i] = count.get(i).name;
		}
		return ans;
	}

	class Movie implements Comparable<Movie> {
		String name;
		int count;

		Movie(String name, int count) {
			this.name = name;
			this.count = count;
		}

		@Override
		public int compareTo(Movie o) {
			int comp1 = Integer.compare(o.count,count);
			if (comp1 == 0) {
				comp1 = name.compareTo(o.name);
				return comp1;
			}
			return comp1;
		}
	}

}
