package naver2022.p3;

import java.util.Arrays;

public class Main {
	public static void main(String[] args) {

	}

}

class Solution {
	public int solution(int N, int[] A, int[] B) {
		// write your code in Java SE 8
		int M = A.length;
		int[] degree = new int[N + 1];

		for (int i = 0; i < M; i++) {
			int a = A[i];
			int b = B[i];
			degree[a]++;
			degree[b]++;
		}

		Arrays.sort(degree);

		int sum = 0;
		for (int i = 1; i <= N; i++) {
			sum += degree[i] * i;
		}
		return sum;
	}
}