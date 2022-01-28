package day09.Knapsack.p12865_평번한배낭.anotherVersion;

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

		//1차원 dp배열을 사용하여 무게를 큰쪽부터 작은쪽으로 순서대로 채우는 방식. (더빠름)
		int[] dp = new int[K + 1]; //dp[i] 무게제한이 i일 때 최대 가치
		for (int i = 1; i <= N; i++) {
			for (int j = K; j >= 0; j--) {
				if (j - w[i] >= 0)
					dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
			}
		}
		bw.write(dp[K] + "\n");

		bw.flush();
		bw.close();
		br.close();
	}
}
