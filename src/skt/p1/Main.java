package skt.p1;

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
	static int n;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/skt/p1/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());

		bw.flush();
		bw.close();
		br.close();
	}

	public int solution(int money, int[] costs) {
		int answer = 0;
		int[] coins = {1, 5, 10, 50, 100, 500};
		//이차원 배열
		int[][] values = new int[6][6]; //values[i][j] i원단위를 j원으로 만들었을 때의 단가
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j <= i; j++) {
				if (i == j) {
					values[i][j] = costs[i];
				} else {
					int gop = coins[i] / coins[j];
					values[i][j] = costs[j] * gop;
				}
			}
		}
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				System.out.print(values[i][j] + " ");
			}
			System.out.println("");
		}

		for (int i = 5; i >= 0; i--) {

			//coins[i] 단위를 사용하기 위해 사용할 coins을 골라야 함
			int ind = -1;
			int min = 987654321;
			for (int j = 0; j <= i; j++) {
				if (values[i][j] < min) {
					ind = j;
					min = values[i][j];
				}
			}
			answer += values[i][ind] * (money/coins[i]);
			money -= coins[i] * (money/coins[i]);
		}
		return answer;
	}
}
