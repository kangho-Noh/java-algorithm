package codetest.ncsoft.p2;

public class Main {

	public static void main(String[] args) {
		Solution sol = new Solution();
		int solution = sol.solution(new String[] {
			"1899-13-31", "19001231", "2001-09-04", "1900-02-29",
			"2021-5-31", "1950-11-30", "1996-02-29", "1999-11-31", "2000-02-29"
		});
		System.out.println(solution);
	}

}

class Solution {
	private int[] monthsDay = {
		0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
	};

	public int solution(String[] birth) {
		int answer = 0;
		String year, month, day;
		int y, m, d;
		// if (text.matches("[0-9]+") && text.length() ==  4) {
		// 	int i = Integer.parseInt(text);
		// 	System.out.println(i);
		// }
		for (String s : birth) {
			String[] split = s.split("-");
			if (split.length != 3)
				continue;
			year = split[0];
			month = split[1];
			day = split[2];
			if (checkValidDate(year, month, day)) {
				y = Integer.parseInt(year);
				m = Integer.parseInt(month);
				d = Integer.parseInt(day);
			} else {
				continue;
			}
			System.out.println(y + " " + m + " " + d);

			//year
			if (!(1900 <= y && y <= 2021)) {
				continue;
			}
			boolean leap = false; //윤년
			if (y % 400 == 0 || (y % 100 != 0 && y % 4 == 0)) {
				leap = true;
			}

			//month
			if (!(1 <= m && m <= 12)) {
				continue;
			}

			//day
			if (m == 2 && leap) {
				if (1 <= d && d <= 29)
					answer++;
			} else {
				if (1 <= d && d <= monthsDay[m])
					answer++;
			}
		}
		return answer;
	}

	private boolean checkValidDate(String year, String month, String day) {

		boolean ret = checkValid(year, 4);
		if (!checkValid(month, 2))
			ret = false;
		if (!checkValid(day, 2))
			ret = false;
		return ret;
	}

	private boolean checkValid(String year, int len) {
		String regex = "[0-9]+"; //숫자만
		return year.matches(regex) && year.length() == len;
	}
}
