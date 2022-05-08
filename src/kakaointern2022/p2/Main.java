package kakaointern2022.p2;

public class Main {
	public static void main(String[] args) {
		Solution sol = new Solution();
		System.out.println(sol.solution(new int[] {4, 6, 5, 1}, new int[] {3, 2, 7, 2}));
	}
}

class Solution {
	public int solution(int[] queue1, int[] queue2) {
		int answer = -1;
		int ind1 = 0, ind2 = 0;
		long sum1 = 0, sum2 = 0;

		for (int i = 0; i < queue1.length; i++) {
			sum1 += queue1[i];
			sum2 += queue2[i];
		}
		long allSum = sum1 + sum2;
		long targetSum = allSum / 2;

		while (ind2 < queue2.length) {
			if (sum1 == targetSum) {
				answer = ind1 + ind2;
				break;
			} else if (sum1 < targetSum) {
				sum1 += queue2[ind2];
				ind2++;
			} else {
				sum1 -= queue1[ind1];
				ind1++;
			}
		}
		return answer;
	}
}