package day01.BruteForce.p14890경사로;

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
	static int[][] check;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());

		int[][] board = new int[n][n];
		check = new int[n][n]; // 경사로 놓은 곳 체크
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				check[i][j] = 0;
			}
		}
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		//가다가 경사로 발견 -> L만큼 앞 또는 뒤로 이동 후 체크. 통과되면 앞으로 간경우는 원래 자리로, 뒤로 간 경우는 뒤부터
		//가로방향
		int sx;
		int sy;
		int ans = 0;
		//가로경로
		for (int i = 0; i < n; i++) {
			sy = i; //고정
			sx = 0;
			int prev, now;
			prev = board[sy][sx];
			boolean fail = false;
			for (int j = 1; j < n; j++) {
				now = board[i][j];
				if (prev + 1 == now) {
					//이전보다 한단계 위 -> L만큼 앞으로 이동 후 다 prev인지 확인
					fail = checkForward(l, board, i, prev, j);
				} else if (prev - 1 == now) {
					//이전보다 한단계 아래 -> L-1만큼 뒤로 이동 후 다 now인지 확인
					fail = checkBackward(l, board, i, now, j);
					if (!fail) {
						j += l - 1;
					}
				} else if (prev == now) {
					//아무 일도 없음
				} else {
					fail = true;
				}

				if (fail)
					break;
				prev = now;
			}
			if (!fail) {
				//bw.write("sy: " + sy + ", sx: " + sx + "\n");
				ans += 1;
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				check[i][j] = 0;
			}
		}
		//세로경로
		for (int j = 0; j < n; j++) {
			sy = 0; //고정
			sx = j; //고정
			int prev, now;
			prev = board[sy][sx];
			boolean fail = false;
			for (int i = 1; i < n; i++) {
				now = board[i][j];
				if (prev + 1 == now) {
					//이전보다 한단계 위 -> L만큼 앞으로 이동 후 다 prev인지 확인
					fail = checkForward2(l, board, i, prev, j);
				} else if (prev - 1 == now) {
					//이전보다 한단계 아래 -> L-1만큼 뒤로 이동 후 다 now인지 확인
					fail = checkBackward2(l, board, i, now, j);
					if (!fail) {
						i += l - 1;
					}
				} else if (prev == now) {
					//아무 일도 없음
				} else {
					fail = true;
				}

				if (fail)
					break;
				prev = now;
			}
			if (!fail) {
				//bw.write("sy: " + sy + ", sx: " + sx + "\n");
				ans += 1;
			}
		}
		bw.write(ans + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	private static boolean checkBackward(int l, int[][] board, int i, int now, int j) {
		boolean fail = false;
		for (int k = l - 1; k > 0; k--) {
			if (k + j >= n) {
				fail = true;
				break;
			}
			if (check[i][j + k] == 1 || board[i][j + k] != now) {
				fail = true;
				break;
			}
			check[i][j + k] = 1;
		}
		check[i][j] = 1;
		return fail;
	}

	private static boolean checkBackward2(int l, int[][] board, int i, int now, int j) {
		boolean fail = false;
		for (int k = l - 1; k > 0; k--) {
			if (k + i >= n) {
				fail = true;
				break;
			}
			if (check[i + k][j] == 1 || board[i + k][j] != now) {
				fail = true;
				break;
			}
			check[i + k][j] = 1;
		}
		check[i][j] = 1;
		return fail;
	}

	private static boolean checkForward(int l, int[][] board, int i, int prev, int j) {
		boolean fail = false;
		for (int k = l; k > 0; k--) {
			if (j - k < 0) {
				fail = true;
				break;
			}
			if (check[i][j - k] == 1 || board[i][j - k] != prev) {
				fail = true;
				break;
			}
			check[i][j - k] = 1;
		}
		return fail;
	}

	private static boolean checkForward2(int l, int[][] board, int i, int prev, int j) {
		boolean fail = false;
		for (int k = l; k > 0; k--) {
			if (i - k < 0) {
				fail = true;
				break;
			}
			if (check[i-k][j] == 1 || board[i-k][j] != prev) {
				fail = true;
				break;
			}
			check[i-k][j] = 1;
		}
		return fail;
	}
}
