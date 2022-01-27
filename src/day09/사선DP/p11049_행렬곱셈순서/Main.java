package day09.사선DP.p11049_행렬곱셈순서;

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
		//연산횟수 (A X B) = rA * cA * cB = rA * rB * cB
		//
		System.setIn(new FileInputStream("src/day09/사선DP/p11049_행렬곱셈순서/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		int[] r = new int[N + 1];
		int[] c = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			r[i] = Integer.parseInt(st.nextToken());
			c[i] = Integer.parseInt(st.nextToken());
		}
		int[][] dp = new int[N + 1][N + 1];
		for (int i = 1; i < N; i++) {
			dp[i][i] = 0;
		}
		//1,2 2,3 3,4 4,5 1,3 2,4 ...
		for (int i = 1; i < N; i++) {
			for (int j = 1; j + i <= N; j++) {
				int a = j;
				int b = j + i;
				dp[a][b] = INF;
				for (int k = a; k+1 <= b; k++) { //자르는 점 k까지가 왼쪽, k+1부터 오른쪽
					dp[a][b] = Math.min(
						dp[a][b], dp[a][k] + dp[k+1][b] + r[a] * c[k] * c[b]);
				}
			}
		}

		bw.write(dp[1][N] + "\n");

		bw.flush();
		bw.close();
		br.close();
	}
}
