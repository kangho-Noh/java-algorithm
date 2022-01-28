package day09.Knapsack.p7579;

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
		System.setIn(new FileInputStream("src/day09/Knapsack/p7579/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int[] memory = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			memory[i] = Integer.parseInt(st.nextToken());
		}

		int[] cost = new int[N + 1];
		int totalCost = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
			totalCost += cost[i];
		}
		//dp[i][j] : 총 cost가 j였을 때, i번째 앱까지 살펴본 결과 가장 큰 메모리양
		int dp[][] = new int[N + 1][totalCost + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j <= totalCost; j++) {
				dp[i][j] = dp[i - 1][j]; //초기값 w[i]가 0일 수 있으므로
				if (j - cost[i] >= 0) {
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - cost[i]] + memory[i]);
				}
			}
		}
		int ans = 0;
		for (int i = 1; i <= totalCost; i++) {
			if (dp[N][i] >= M) {
				ans = i;
				break;
			}
		}

		bw.write(ans + "\n");

		bw.flush();
		bw.close();
		br.close();
	}
}
