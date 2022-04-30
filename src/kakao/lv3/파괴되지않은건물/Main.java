package kakao.lv3.파괴되지않은건물;

public class Main {
	public static void main(String[] args) {
		Solution sol = new Solution();
		System.out.println(sol.solution(
			new int[][] {{1,2,3},{4,5,6},{7,8,9}},
			new int[][] {{1,1,1,2,2,4},{1,0,0,1,1,2},{2,2,0,2,0,100}}
		));
	}
}

class Solution {
	int[][] imos;

	public int solution(int[][] board, int[][] skill) {
		int n = board.length;
		int m = board[0].length;
		imos = new int[n + 1][m + 1];

		for (int[] sk : skill) {
			int type = sk[0];
			int r1 = sk[1];
			int c1 = sk[2];
			int r2 = sk[3];
			int c2 = sk[4];
			int degree = sk[5];
			if (type == 1) {
				//공격
				degree = -degree;
			}
			imos[r1][c1] += degree;
			imos[r1][c2 + 1] -= degree;
			imos[r2 + 1][c1] -= degree;
			imos[r2 + 1][c2 + 1] += degree;
		}

		for (int i = 0; i < n; i++) {
			int deg = 0;
			for (int j = 0; j < m; j++) {
				deg += imos[i][j];
				imos[i][j] = deg;
			}
		}
		for (int i = 0; i < m; i++) {
			int deg = 0;
			for (int j = 0; j < n; j++) {
				deg += imos[j][i];
				imos[j][i] = deg;
			}
		}

		int ans = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (board[i][j] + imos[i][j] > 0)
					ans++;
			}
		}
		return ans;
	}
}
