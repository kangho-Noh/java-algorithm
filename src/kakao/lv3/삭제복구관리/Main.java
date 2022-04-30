package kakao.lv3.삭제복구관리;

import java.util.Arrays;
import java.util.Stack;

class Solution {

	static int cursor;
	static int lastIndex;
	static int size;
	static int[] next, prev;
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
		next = new int[n];
		prev = new int[n];
		for (int i = 0; i < n; i++) {
			next[i] = i + 1; //if next[i]==n이면 lastindex
			prev[i] = i - 1; //if prev[i]==-1이면 Lastindex
		}

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
		for (int i = 0; i < n; i++) {
			cursor = prev[cursor];
		}
	}

	//커서를 아래로 내림 (index++)
	private void cursorDown(int n) {
		for (int i = 0; i < n; i++) {
			cursor = next[cursor];
		}
	}

	private void undo() {
		//trashcan에서 꺼냄
		int target = trashcan.pop();
		answer.setCharAt(target, 'O');
		if(prev[target]>=0)
			next[prev[target]] = target;
		if(next[target]<size)
			prev[next[target]] = target;
	}

	private void delete() {
		answer.setCharAt(cursor, 'X');
		trashcan.add(cursor);
		// 주변 next, prev 바꿈
		if(prev[cursor]>=0)
			next[prev[cursor]] = next[cursor];
		if(next[cursor]<size)
			prev[next[cursor]] = prev[cursor];
		//삭제 후 커서 내림. 만약에 내릴 수 없으면(마지막 인덱스이면) 올림
		if (next[cursor] == size) {
			cursorUp(1);
		} else {
			cursorDown(1);
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