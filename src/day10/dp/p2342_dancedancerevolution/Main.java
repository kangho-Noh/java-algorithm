package day10.dp.p2342_dancedancerevolution;

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
		System.setIn(new FileInputStream("src/day10/dp/p2342/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		int[] nums = new int[100001];
		int ind = 1;
		while (true) {
			int a = Integer.parseInt(st.nextToken());
			if (a == 0) {
				break;
			}
			nums[ind++] = a;
		}
		int[][][] dp = new int[100001][5][5];//i번째 버튼을 밟기위해 왼발 j 오른발 k에 위치했을 때의 최소비용
		//전부 다 INF로 초기화. INF일 경우 불가능한 지점
		// INF이 아닌애들 끼리 Math.min()해서 값 넣음
		for (int i = 0; i < 100001; i++) {
			for (int j = 0; j < 5; j++) {
				for (int k = 0; k < 5; k++) {
					dp[i][j][k] = INF;
				}
			}
		}
		dp[0][0][0] = 0; //초기 상태
		for (int i = 1; i <= ind-1; i++) {
			int arrow = nums[i];
			for (int j = 0; j < 5; j++) {
				for (int k = 0; k < 5; k++) {
					if (dp[i - 1][j][k] == INF)
						continue;
					dp[i][arrow][k] =
						Math.min(dp[i][arrow][k], dp[i - 1][j][k] + getCost(arrow, j));
					dp[i][j][arrow] =
						Math.min(dp[i][j][arrow], dp[i - 1][j][k] + getCost(arrow, k));
				}
			}
		}
		int ans = INF;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++)
				ans = Math.min(ans, dp[ind-1][i][j]);
		}
		bw.write(ans + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	private static int getCost(int num, int j) {
		int absint = Math.abs(num - j);
		if(j==0) return 2;
		if (absint == 0) {
			return 1;
		} else if (absint == 2) {
			return 4;
		}  else {
			return 3;
		}
	}
}
