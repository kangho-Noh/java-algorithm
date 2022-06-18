package codetest.kakao.lv2.문자열압축;

public class Main {
	public static void main(String[] args) {

		Solution solution = new Solution();
		System.out.println(solution.solution("ababcdcdababcdcd"));
	}
}

class Solution {
	public int solution(String s) {
		int answer = s.length();
		int length = s.length();
		for (int i = 1; i <= length / 2; i++) {
			//i = 자르는 단위
			int temp = length;
			for (int j = 0; j < length - i; j+=i) {
				//j = 비교 시작 인덱스
				int cnt = getSameSubstrCnt(s, i, j)+1 ; //같은 스트링 개수
				if(cnt>1){
					//같은 쌍이 있는 경우 숫자+문자열하나 로 압축  i *(cnt+1) 압축전 -> num+i 압축후
					int num = Integer.toString(cnt).length();
					temp = temp-(i*cnt) + (num+i);
					j+=i*(cnt-1);
				}
			}
			answer = Math.min(answer, temp);
		}
		return answer;
	}

	private int getSameSubstrCnt(String s, int len, int startInd) {
		int ret = 0;
		String substr = s.substring(startInd, startInd + len);
		for (int i = startInd+len; i < s.length()-len+1; i+=len) {
			String now = s.substring(i, i + len);
			if (substr.equals(now)) {
				ret++;
			}else{
				break;
			}
		}

		return ret;
	}
}