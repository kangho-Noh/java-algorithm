package day09.Knapsack.p12865_평번한배낭;

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
	static int N, K;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day09/Knapsack/p12865_평번한배낭/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int[] w = new int[N + 1], v = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			w[i] = Integer.parseInt(st.nextToken());
			v[i] = Integer.parseInt(st.nextToken());
		}
		int[][] dp = new int[N + 1][K + 1];
		//dp[i][j]: i번째 물품까지 담을 때, j무게를 넘기지 않고 담을 수 있는 최고 가치
		for (int i = 1; i < N + 1; i++) {
			for (int j = 0; j < K + 1; j++) {
				dp[i][j] = dp[i - 1][j];
				if (j - w[i] >= 0) {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w[i]] + v[i]);
				}
			}
		}

		int ans = dp[N][K];
		bw.write(ans + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
}
