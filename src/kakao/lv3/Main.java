package kakao.lv3;

import java.util.Arrays;
import java.util.Stack;

class Solution {

	static int cursor;
	static int lastIndex;
	static int size;
	static StringBuilder answer;
	static Stack<Integer> trashcan;

	public String solution(int n, int k, String[] cmd) {
		answer = new StringBuilder();
		for (int i = 0; i < n; i++) {
			answer.append('O');
		}
		cursor = k;
		trashcan = new Stack<>();
		lastIndex = n - 1;
		size = n;

		Arrays.stream(cmd).forEach(this::commandController);

		return answer.toString();
	}

	private void commandController(String s) {
		char command = s.charAt(0);
		if (command == 'Z') {
			undo();
		} else if (command == 'U') {
			cursorUp(Integer.parseInt(s.substring(2)));
		} else if (command == 'D') {
			cursorDown(Integer.parseInt(s.substring(2)));
		} else if (command == 'C') {
			delete();
		}
	}

	//커서를 위로 올림 (index--)
	private void cursorUp(int n) {
		int moved = 0;
		while (moved != n) {
			cursor--;
			if (cursor < 0) {
				cursor = 0;
				break;
			}
			char now = answer.charAt(cursor);
			if (now == 'O') {
				moved++;
			}
		}
	}

	//커서를 아래로 내림 (index++)
	private void cursorDown(int n) {
		int moved = 0;
		while (moved != n) {
			cursor++;
			if (cursor >= lastIndex) {
				cursor = lastIndex;
				break;
			}
			char now = answer.charAt(cursor);
			if (now == 'O') {
				moved++;
			}
		}
	}

	private void undo() {
		//trashcan에서 꺼냄
		Integer target = trashcan.pop();
		answer.setCharAt(target, 'O');
		size++;
		//cursor는 그대로, lastIndex 갱신
		if (size == 1) {
			lastIndex = target;
			cursor = target;
		} else if (lastIndex < target) {
			lastIndex = target;
		}
	}

	private void delete() {
		answer.setCharAt(cursor, 'X');
		trashcan.add(cursor);
		size--;
		//만약 마지막 원소면 커서 위로
		//마지막 원소가 아니면 커서 아래로
		if (size == 0) {
			//사이즈 0이면 커서 움직이지 않음.
		} else {
			if (cursor == lastIndex) {
				cursorUp(1);
				lastIndex = cursor;
			} else if (cursor != lastIndex) {
				cursorDown(1);
			}
		}
	}
}

public class Main {
	public static void main(String[] args) {
		Solution sol = new Solution();
		String ans = sol.solution(8, 2, new String[] {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"});
		System.out.println(ans);
	}
}