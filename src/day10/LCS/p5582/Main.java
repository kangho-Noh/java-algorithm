package day10.LCS.p5582;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;

	static String s1, s2;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day10/LCS/p5582/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		s1 = br.readLine();
		s2 = br.readLine();
		int[][] dp = new int[s1.length() + 1][s2.length() + 1];
		int ans = 0;
		for (int i = 1; i <= s1.length(); i++) {
			for (int j = 1; j <= s2.length(); j++) {
				char c1 = s1.charAt(i - 1);
				char c2 = s2.charAt(j - 1);
				if (c1 == c2) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
					ans = Math.max(ans, dp[i][j]);
				}
			}
		}
		bw.write(ans + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
}
