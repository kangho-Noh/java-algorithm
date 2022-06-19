package naver2022.p1;

public class Solution {
	int solution(int N) {
		int largest = N;
		int shift = 0;
		int temp = N;
		for (int i = 1; i < 30; ++i) {
			int index = (temp & 1);//Q[0]번째 숫자
			temp = ((temp >> 1) | (index << 29)); //오른쪽 쉬프트하고, 제일 왼쪽에 숫자 삽입
			if (temp > largest) {
				largest = temp;
				shift = i;
			}
		}
		return shift;
	}
}
