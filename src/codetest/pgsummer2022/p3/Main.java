package codetest.pgsummer2022.p3;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		Solution sol = new Solution();
		System.out.println(sol.solution(
			"Q4OYPI"
		));
	}
}

class Solution {

	int lefty = 1, leftx = 0;
	int righty = 1, rightx = 9;
	int[][] keyboard = new int[][] {
		{'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'},
		{'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P'}
	};

	public int[] solution(String line) {
		List<Integer> moves = new ArrayList<>();
		for (int i = 0; i < line.length(); i++) {
			int nowChar = line.charAt(i);
			for (int j = 0; j < keyboard.length; j++) {
				for (int k = 0; k < keyboard[j].length; k++) {
					if (nowChar == keyboard[j][k]) {
						moves.add(moveWhichFinger(j, k));
					}
				}
			}
		}

		int[] answer = new int[moves.size()];
		for (int i = 0; i < moves.size(); i++) {
			answer[i] = moves.get(i);
		}
		return answer;
	}

	private int moveWhichFinger(int keyy, int keyx) {

		int yLeftToKey = Math.abs(keyy - lefty);
		int xLeftToKey = Math.abs(keyx - leftx);
		int yRightToKey = Math.abs(keyy - righty);
		int xRightToKey = Math.abs(keyx - rightx);

		int leftMan = xLeftToKey + yLeftToKey;
		int rightMan = xRightToKey + yRightToKey;

		if (leftMan < rightMan) {
			lefty = keyy;
			leftx = keyx;
			return 0;
		} else if (leftMan > rightMan) {
			righty = keyy;
			rightx = keyx;
			return 1;
		}
		//맨하탄 같은 경우 수평거리 (x)
		if (xLeftToKey < xRightToKey) {
			lefty = keyy;
			leftx = keyx;
			return 0;
		} else if (xLeftToKey > xRightToKey) {
			righty = keyy;
			rightx = keyx;
			return 1;
		}
		//키보드 상의 왼쪽, 오른쪽
		if (keyx < 5) {
			lefty = keyy;
			leftx = keyx;
			return 0;
		} else {
			righty = keyy;
			rightx = keyx;
			return 1;
		}
	}
}