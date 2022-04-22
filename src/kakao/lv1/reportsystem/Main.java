package kakao.lv1.reportsystem;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/kakao/input.txt"));
	}

	public int[] solution(String[] id_list, String[] report, int k) {
		int[] answer;

		//report[i]는 "신고자 피신고자" 형태
		//중복을 없애려면 Set 사용 가능.
		//또는 stream().distinct() 사용가능
		List<String> list = Arrays.stream(report).distinct().collect(Collectors.toList());
		HashMap<String, Integer> count = new HashMap<>();
		for (String s : list) {
			String target = s.split(" ")[1];
			count.put(target, count.getOrDefault(target, 0) + 1);
			// getOrDefault 있으면 가져오고 없으면 0 +1
		}

		return Arrays.stream(id_list).map(_user -> {
			final String user = _user;
			List<String> reportList = list.stream()
				.filter(s -> s.startsWith(user + " ")) //user가 신고한 목록만 가져옴
				.collect(Collectors.toList());
			return reportList.stream()
				.filter(s -> count.getOrDefault(s.split(" ")[1], 0) >= k)
				.count(); //신고 당한 사람의 count가 k보다 크거나 같은 사람들의 명수
		}).mapToInt(Long::intValue).toArray(); //stream().count()의 default return 은 long
	}
}
