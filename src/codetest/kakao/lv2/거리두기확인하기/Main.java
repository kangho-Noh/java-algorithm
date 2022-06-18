package codetest.kakao.lv2.거리두기확인하기;

import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	int[] dy = {-1, 0, 1, 0};
	int[] dx = {0, 1, 0, -1};

	public int[] solution(String[][] places) {
		int[] answer = {0, 0, 0, 0, 0};
		for (int k = 0; k < 5; k++) {
			String[] place = places[k];
			Queue<Node> queue = new ArrayDeque<>();
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if (place[i].charAt(j) == 'P') {
						queue.add(new Node(i, j, 0, i, j));
					}
				}
			}
			answer[k] = getAnswer(place, queue);
		}
		return answer;
	}

	private int getAnswer(String[] place, Queue<Node> queue) {
		while (!queue.isEmpty()) {
			Node now = queue.poll();
			for (int i = 0; i < 4; i++) {
				int ny = now.y + dy[i];
				int nx = now.x + dx[i];
				if (ny < 0 || ny >= 5 || nx < 0 || nx >= 5)
					continue;
				if(ny==now.sy && nx == now.sx) continue;
				if (place[ny].charAt(nx) == 'P') {
					return 0;
				}
				if (now.depth < 1 && place[ny].charAt(nx) == 'O') {
					queue.add(new Node(ny, nx, now.depth + 1, now.sy, now.sx));
				}
			}
		}
		return 1;
	}

	private class Node {
		int y, x, depth;
		int sy,sx;
		Node(int y, int x, int depth, int sy, int sx) {
			this.y = y;
			this.x = x;
			this.depth = depth;
			this.sy = sy;
			this.sx = sx;
		}
	}
}
