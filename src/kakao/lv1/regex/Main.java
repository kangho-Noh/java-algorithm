package kakao.lv1.regex;

import java.util.Locale;

public class Main {
	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(solution.solution("z-+.^."));
	}

	private static class Solution {
		public String solution(String new_id) {
			String answer = new_id;
			//"-_.~!@#$%^&*()=+[{]}:?,<>/"

			//1. lowercase
			answer = answer.toLowerCase(Locale.ROOT);
			//2. 사용불가 문자 제거
			String invalidCharacters = "[^a-z0-9.\\-_]";
			answer = answer.replaceAll(invalidCharacters, "");
			//3. 마침표 여러개 하나로 만들기
			String continuousPeriod = "[.]{2,}";
			answer = answer.replaceAll(continuousPeriod, "."); //.이 2개이상 연속되면 .하나로 치환
			//4. 마침표 처음, 끝 존재 시 제거
			answer = answer.replaceAll("^[.]|[.]$", "");
			//5. 빈 문자열일 경우 'a' 추가
			if (answer.isEmpty()) {
				answer = "a";
			}
			//6. 16자 이상이면, 15자로 줄이고 마지막 . 제거
			if (answer.length() > 15) {
				answer = answer.substring(0, 15);
				answer = answer.replaceAll("[.]$", "");
			}
			//7. 2자 이하면, 마지막 글자 중복해서 3개로 만들기
			if (answer.length() <= 2) {
				while (answer.length() < 3) {
					answer += answer.charAt(answer.length() - 1);
				}
			}
			return answer;
		}
	}
}

