package day05.Binomial.p11051;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static BufferedReader br;
	static BufferedWriter bw;

	static int N;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] dp = new int[1001][1001];

		//dp[N][K]
		dp[1][0] = dp[1][1] = 1;
		for (int i = 2; i <= N; i++) {
			dp[i][0] = 1;
			for (int j = 1; j <= i; j++) {
				dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]) % 10007;
			}
		}

		bw.write(dp[N][K] + "\n");

		bw.flush();
		bw.close();
		br.close();
	}
}
