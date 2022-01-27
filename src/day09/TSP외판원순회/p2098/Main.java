package day09.TSP외판원순회.p2098;

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
	static int N, bitmask;
	static int[][] adj = new int[17][17];
	static int[][] dp;
	// 현재 도시까지 방문한 정보 bit, 현재 도시 넘버 now

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day09/TSP외판원순회/p2098/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		bitmask = 1 << N;
		dp = new int[17][bitmask]; //dp[now][bit]
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				adj[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		bw.write(TSP(0, 1) + "\n");

		bw.flush();
		bw.close();
		br.close();
	}

	private static int TSP(int now, int bit) {
		// 현재 노드 now, 방문 정보 bit
		//갈 수 있는 노드 모두 방문정보 넘겨주면서 순회

		// 종료조건: bit 정보 꽉찼을 때 (모두 순회 완료)
		if (bit == (1 << N) - 1) {
			// 다시 돌아갈 수 없으면 정답안됨
			if (adj[now][0] == 0)
				return (int)1e9;
			else
				return adj[now][0];
		}
		if (dp[now][bit] != 0) {
			//같은 과정으로 온 적 있으면
			return dp[now][bit];
		}
		//계속 순회 필요
		dp[now][bit] = (int)1e9;

		for (int i = 0; i < N; i++) {
			if (adj[now][i] == 0)
				continue; // 갈 수 없으면 패스
			if ((bit & (1 << i)) != 0)
				continue; //방문한적 있으면 패스
			dp[now][bit] = Math.min(dp[now][bit], adj[now][i] + TSP(i, bit | (1 << i)));
		}
		return dp[now][bit];

	}
}
