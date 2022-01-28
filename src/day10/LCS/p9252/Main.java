package day10.LCS.p9252;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static BufferedReader br;
	static BufferedWriter bw;

	static final int INF = Integer.MAX_VALUE;
	static String s1, s2;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day10/LCS/p9252/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		s1 = br.readLine();
		s2 = br.readLine();
		int l1 = s1.length();
		int l2 = s2.length();
		int[][] dp = new int[l1 + 1][l2 + 1]; // s1[i] s2[j]까지 봤을 때 최장 증가 문자열의 길이
		//dp[i][j] = max(dp[i][j-1], dp[i-1][j], dp[i-1][j-1]+1)
		int[][] direction = new int[l1 + 1][l2 + 1]; // dp[i][j]에 영향을 준 칸이 어느쪽인지 0 왼 1 위 2 대각선
		int ans = 0;
		int si = -1, sj = -1;
		for (int i = 1; i <= l1; i++) {
			for (int j = 1; j <= l2; j++) {
				char c1 = s1.charAt(i - 1);
				char c2 = s2.charAt(j - 1);
				if (c1 == c2) {
					if (dp[i - 1][j - 1] + 1 > dp[i][j - 1] && dp[i - 1][j - 1] + 1 > dp[i - 1][j]) {
						dp[i][j] = dp[i - 1][j - 1] + 1;
						direction[i][j] = 2;
					} else if (dp[i - 1][j] >= dp[i][j - 1]) {
						dp[i][j] = dp[i - 1][j];
						direction[i][j] = 1;
					} else {
						dp[i][j] = dp[i][j - 1];
						direction[i][j] = 0;
					}
				} else {
					if (dp[i - 1][j] >= dp[i][j - 1]) {
						dp[i][j] = dp[i - 1][j];
						direction[i][j] = 1;
					} else {
						dp[i][j] = dp[i][j - 1];
						direction[i][j] = 0;
					}
				}
				if (ans < dp[i][j]) {
					ans = dp[i][j];
					si = i;
					sj = j;
				}
			}
		}
		String ansstr = "";
		while (si > 0 && sj > 0) {
			if (direction[si][sj] == 2) {
				ansstr += s1.charAt(si-1);
				si--;
				sj--;
			} else if (direction[si][sj] == 1) {
				si--;
			} else {
				sj--;
			}
		}
		StringBuilder sb = new StringBuilder(ansstr);
		if (ans == 0) {
			bw.write("0\n");
		} else {
			bw.write(ans + "\n");
			bw.write(sb.reverse().toString() + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}
}
