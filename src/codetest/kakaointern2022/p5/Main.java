package codetest.kakaointern2022.p5;

public class Main {
	public static void main(String[] args) {
		Solution sol = new Solution();
		System.out.println(sol.solution(
			new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
			new String[]{"Rotate", "ShiftRow"}
		));
	}
}

class Solution {

	public int[][] solution(int[][] rc, String[] operations) {
		int row = rc.length;
		int col = rc[0].length;
		int[][] answer = new int[row][col];
		int[] y = new int[] {0, row - 1, row - 1, 0}; //왼위, 왼아, 오아, 오위
		//shiftrow -> 전체적으로 [0]을 ++ % row

		//효율성 -> 인덱스로 해야할듯
		for (String operation : operations) {
			if (operation.charAt(0) == 'R') {
				//Rotate
				//첫번째열 -> 왼위 시작, 왼아꺼를 복사
				int firstNode = rc[y[0]][0];
				//첫번째 열
				for (int i = y[0]; i < y[0] + row - 1; i++) {
					int yy = i % row;
					rc[yy][0] = rc[(yy + 1) % row][0];
				}
				//마지막 행
				for (int i = 0; i < col - 1; i++) {
					rc[y[1]][i] = rc[y[1]][(i + 1) % col];
				}
				//마지막 열
				for (int i = y[2] + row; i > y[2]; i--) {
					int yy = i % row;
					if (yy - 1 < 0)
						rc[0][col - 1] = rc[row - 1][col - 1];
					else
						rc[yy][col - 1] = rc[yy - 1][col - 1];
				}
				//첫번째 행
				for (int i = col - 1; i > 0; i--) {
					rc[y[3]][i] = rc[y[3]][i - 1];
				}
				rc[y[0]][1] = firstNode;
			} else if (operation.charAt(0) == 'S') {
				//ShiftRow
				//y 전체적으로 ++ 하고 %= row
				for (int i = 0; i < 4; i++) {
					y[i]+=row;
					y[i]--;
					y[i] %= row;
				}
			}
		}
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				int yy = (y[0]+i)%row;
				answer[i][j] = rc[yy][j];
			}
		}
		return answer;
	}
}