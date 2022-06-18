package codetest.skt.p3;

public class Main {
	public int solution(int width, int height, int[][] diagonals) {
		int answer = 0;
		int modNum = 10000019;
		long[][] dp = new long[height + 1][width + 1];
		for (int i = 0; i < height + 1; i++) {
			dp[i][0] = 1;
		}
		for (int i = 0; i < width + 1; i++) {
			dp[0][i] = 1;
		}
		for (int i = 1; i <= height; i++) {
			for (int j = 1; j <= width; j++) {
				dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % modNum;
			}
		}
		for (int i = 0; i < diagonals.length; i++) {
			int[] dia = diagonals[i];
			int diay = dia[1];
			int diax = dia[0];
			//왼위 -> 오아
			answer += dp[diay][diax - 1] * dp[height - (diay - 1)][width - diax] % modNum;
			//오아 -> 왼위
			answer += dp[diay - 1][diax] * dp[height - diay][width - (diax - 1)] % modNum;
		}
		return answer;
	}
}
