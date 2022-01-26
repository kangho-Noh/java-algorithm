package day08.DP.p1932_정수삼각형;

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
	static int N;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day08/DP/p1932_정수삼각형/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		int[][] arr = new int[N + 1][N + 1];
		int[][] dp = new int[N + 1][N + 1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < i + 1; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//dp[i][j] = arr[i][j] + max(dp[i-1][j-1],dp[i-1][j])
		dp[0][0] = arr[0][0];
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < i + 1; j++) {
				if (j == 0)
					dp[i][j] = arr[i][j] + dp[i - 1][j];
				else if (j == i)
					dp[i][j] = arr[i][j] + dp[i - 1][j - 1];
				else
					dp[i][j] = arr[i][j] + Math.max(dp[i - 1][j - 1], dp[i - 1][j]);
			}
		}
		int ans = 0;
		for (int i = 0; i < N; i++) {
			ans = Math.max(ans, dp[N-1][i]);
		}

		bw.write(ans+"\n");

		bw.flush();
		bw.close();
		br.close();
	}
}
