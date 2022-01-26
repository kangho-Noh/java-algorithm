package day08.DP.p11660_구간합5;

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
	static int N, M;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day08/DP/p11660_구간합5/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] num = new int[N + 1][N + 1];
		long[][] dp = new long[N + 1][N + 1];
		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < N + 1; j++) {
				num[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = dp[i][j - 1] + dp[i - 1][j] - dp[i-1][j-1] + num[i][j];
			}
		}
		//x행 y열이었음;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1, y1, x2, y2;
			x1 = Integer.parseInt(st.nextToken()) - 1;
			y1 = Integer.parseInt(st.nextToken()) - 1;
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			bw.write(dp[x2][y2] - dp[x1][y2] - dp[x2][y1] + dp[x1][y1] + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}
}
