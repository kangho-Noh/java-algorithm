package kakao.lv3.추석트래픽;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main {
	public static void main(String[] args) {
		Solution sol = new Solution();
		//System.out.println(sol.solution());
	}
}

class Solution {
	public int solution(String[] lines) throws ParseException {
		int answer = 0;
		String startTime, endTime;
		Date date1, date2;
		float diffTime;
		long startTimeMillis, endTimeMillis, diffTimeMillis;
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");
		long perSecond = 1000; //1000ms 간격

		ArrayList<Long> end = new ArrayList<>();
		ArrayList<Long> start = new ArrayList<>();

		for (String line : lines) {
			String[] s = line.split(" ");

			endTime = s[1];
			date2 = format.parse(endTime);
			diffTime = Float.parseFloat(s[2].substring(0, s[2].length() - 1));
			diffTimeMillis = (long)(diffTime * 1000);

			//시작시간 구하기
			startTimeMillis = date2.getTime() - diffTimeMillis;
			startTimeMillis += 1;
			date1 = new Date(startTimeMillis);

			end.add(date2.getTime());
			start.add(date1.getTime());

		}
		int size = lines.length;
		for (int i = 0; i < size; i++) {
			long nowS = end.get(i);
			int cnt = 1;
			for (int j = i + 1; j < size; j++) {
				long Sk = start.get(j);
				if (Sk <= nowS + 999) {
					cnt++;
				}
			}
			answer = Math.max(cnt, answer);
		}
		return answer;
	}
}