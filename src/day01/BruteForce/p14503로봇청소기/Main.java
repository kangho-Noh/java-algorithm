package day01.BruteForce.p14503로봇청소기;

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

	static final int NORTH = 0; //북, 동, 남, 서
	static final int EAST = 1; //북, 동, 남, 서
	static final int SOUTH = 2; //북, 동, 남, 서
	static final int WEST = 3; //북, 동, 남, 서
	static final int[] LEFT = {WEST, NORTH, EAST, SOUTH}; //LEFT[NORTH] -> 북쪽의 오른쪽인 WEST
	static final int[] dy = {-1, 0, 1, 0}; //dy[i] i방향으로 이동 y 북 동 남 서
	static final int[] dx = {0, 1, 0, -1};
	static final int WALL = 1;
	static final int BLANK = 0;
	static int n, m;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int[][] board = new int[n + 2][m + 2];
		for (int i = 0; i < n + 2; i++) {
			board[i][0] = WALL;
			board[i][m + 1] = WALL;
		}
		for (int i = 0; i < m + 2; i++) {
			board[0][i] = WALL;
			board[n + 1][i] = WALL;
		}

		int sy, sx, d;
		st = new StringTokenizer(br.readLine());
		sy = Integer.parseInt(st.nextToken()) + 1;
		sx = Integer.parseInt(st.nextToken()) + 1;
		d = Integer.parseInt(st.nextToken());

		for (int i = 1; i < n + 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < m + 1; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int ans = 1;
		while (true) {
			//1 현재 위치 청소
			board[sy][sx] = -1;

			//나가기 조건: 주변에 청소할수 없는데, 뒤에 벽일 경우
			boolean end = true;
			for (int i = 0; i < 4; i++) {
				if (board[sy + dy[i]][sx + dx[i]] == 0)
					end = false;
			}
			if (end) {
				if (board[sy + dy[(d + 2) % 4]][sx + dx[(d + 2) % 4]] == 1) {
					//뒤가 벽일 경우
					break;
				} else {
					//벽이 아니면 후진
					sy += dy[(d + 2) % 4];
					sx += dx[(d + 2) % 4];
					continue;
				}
			}

			//2a 현재 위치의 바로 왼쪽에 청소안한 빈공간이면, 방향 회전 후 전진
			while (true) {
				d = LEFT[d]; //방향 회전
				if (board[sy + dy[d]][sx + dx[d]] == 0) {
					sy += dy[d];
					sx += dx[d];
					ans++;
					break;
				}
			}
		}
		bw.write(ans + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
}
