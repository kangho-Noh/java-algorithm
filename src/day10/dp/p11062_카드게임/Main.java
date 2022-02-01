package day10.dp.p11062_카드게임;

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
	static int T, N;
	static int[][][] dp;
	static int[] cards;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day10/dp/p11062_카드게임/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			cards = new int[N];
			for (int i = 0; i < N; i++) {
				cards[i] = Integer.parseInt(st.nextToken());
			}
			dp = new int[2][N][N];
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < N; j++) {
					for (int k = 0; k < N; k++) {
						dp[i][j][k] = -1;
					}
				}
			}
			bw.write(recursive(0, 0, N - 1) + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	//최선의 플레이
	private static int recursive(int turn, int left, int right) {
		if (dp[turn][left][right] != -1) {
			return dp[turn][left][right];
		}
		//기저사례: left ==right
		if (left == right) {
			if (turn == 1) {
				return 0;
			} else {
				return cards[left];
			}
		}


		//Math.max(왼쪽 카드를 골랐을 때, 오른쪽 카드를 골랐을 때)
		int ret;
		dp[turn][left][right] = 0;
		if (turn == 0) {
			ret = Math.max(recursive(1, left + 1, right) + cards[left],
				recursive(1, left, right - 1) + cards[right]);
		} else {
			ret = Math.min(recursive(0, left + 1, right) ,
				recursive(0, left, right - 1) );
		}
		dp[turn][left][right] = ret;
		return ret;
	}
}
