package day03.HashMap.p4358;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static BufferedReader br;
	static BufferedWriter bw;

	static int N;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day03/HashMap/p4358/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String s;
		Map<String, Integer> map = new HashMap<>();
		PriorityQueue<String> pq = new PriorityQueue<>();
		int cnt = 0;
		while ((s = br.readLine()) != null) {
			if (map.containsKey(s)) {
				map.replace(s, map.get(s) + 1);
			} else {
				pq.add(s);
				map.put(s, 1);
			}
			cnt++;
		}

		while (!pq.isEmpty()) {
			String key = pq.poll();
			bw.write(String.format("%s %.4f\n", key, 100*map.get(key)/(float)cnt));
		}


		bw.flush();
		bw.close();
		br.close();
	}
}
