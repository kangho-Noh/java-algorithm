package kakao.lv2;

import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	public static void main(String[] args) {
		Solution sol = new Solution();
		int[] solution = sol.solution(6, 4,
			new int[][] {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}});
		System.out.println(solution[0] + " "+ solution[1]);
	}
}

class Solution {

	int[] dy = {-1, 0, 1, 0};
	int[] dx = {0, 1, 0, -1};

	public int[] solution(int m, int n, int[][] picture) {
		int numberOfArea = 0;
		int maxSizeOfOneArea = 0;
		//bitmask -> 원소의 개수
		int bitmask = 0;
		//bitmask&원소 == 0이면 bitmask|원소 해주고 numberOfArea++;
		//maxSizeOfOneArea -> dfs 나 bfs 사용해서 세주면 됨
		boolean[][] visited = new boolean[m][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				int color = picture[i][j];
				if(color==0 )continue;
				if (((1 << color) & bitmask) == 0) {
					bitmask |= (1 << color);
				}
				if (!visited[i][j]) {
					numberOfArea++;
					maxSizeOfOneArea = Math.max(maxSizeOfOneArea,bfs(i, j, visited, m, n, picture));
				}
			}
		}

		int[] answer = new int[2];
		answer[0] = numberOfArea;
		answer[1] = maxSizeOfOneArea;
		return answer;
	}

	private int bfs(int i, int j, boolean[][] visited, int m, int n, int[][] picture) {
		int cnt = 0;
		Queue<yx> queue = new ArrayDeque<>();
		queue.add(new yx(i, j));
		visited[i][j] = true;
		while (!queue.isEmpty()) {
			yx poll = queue.poll();
			cnt++;
			int nowy = poll.y;
			int nowx = poll.x;
			for (int k = 0; k < 4; k++) {
				int nexty = nowy + dy[k];
				int nextx = nowx + dx[k];
				if (nexty < 0 || nexty >= m || nextx < 0 || nextx >= n)
					continue;
				if (picture[i][j] == picture[nexty][nextx] && !visited[nexty][nextx]) {
					visited[nexty][nextx] = true;
					queue.add(new yx(nexty, nextx));
				}
			}
		}
		return cnt;
	}

	private class yx {
		int y;
		int x;

		yx(int yy, int xx) {
			y = yy;
			x = xx;
		}
	}
}