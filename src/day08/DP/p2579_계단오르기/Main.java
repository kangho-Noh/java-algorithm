package day08.DP.p2579_계단오르기;

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
		System.setIn(new FileInputStream("src/day08/DP/p2579_계단오르기/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int[] stair = new int[N+1];
		int[][] dp = new int[N+1][2]; // 0은 한칸으로 올라왔을 경우, 1은 두 칸으로 올라왔을 경우
		for (int i = 1; i <= N; i++) {
			stair[i] = Integer.parseInt(br.readLine());
		}
		if(N==1){
			bw.write(stair[1] + "\n");
		} else if (N == 2) {
			bw.write(stair[1] + stair[2] + "\n");
		}
		else {
			dp[1][0] = stair[1]; //한칸단위로 올라옴
			dp[2][1] = stair[2]; //두칸단위로 올라옴
			dp[2][0] = stair[1] + stair[2]; // -> 한칸으로왔으면 다음번에 한칸으로 못감

			for (int i = 3; i <= N; i++) {
				dp[i][1] = Math.max(dp[i-2][0],dp[i-2][1]) + stair[i];
				dp[i][0] = dp[i-1][1] + stair[i];
			}
			bw.write(Math.max(dp[N][0],dp[N][1]) + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
