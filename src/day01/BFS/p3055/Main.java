package day01.BFS.p3055;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair {
	int x, y;
	char type;

	Pair(int y, int x, char type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}

	@Override
	public String toString() {
		return "[y=" + y + ", x=" + x + ", type=" + type + "]";
	}
}

public class Main {

	static final int DY[] = {-1, 0, 1, 0};
	static final int DX[] = {0, -1, 0, 1};

	public static void main(String[] args) throws Exception {

		// 제출 시 지움
		System.setIn(new FileInputStream("src/day01/BFS/p3055/input4.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		char[][] arr = new char[R][C];
		int[][] cnt = new int[R][C];

		Pair sprite, goal, water;
		goal = null;
		sprite = new Pair(0, 0, ' ');
		Queue<Pair> queue = new LinkedList<>();
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			for (int j = 0; j < C; j++) {
				arr[i][j] = s.charAt(j);
				if (arr[i][j] == '*') {
					water = new Pair(i, j, '*');
					queue.add(water);
				} else if (arr[i][j] == 'S') {
					sprite = new Pair(i, j, 'S');
				} else if (arr[i][j] == 'D') {
					goal = new Pair(i, j, 'D');
				}
			}
		}
		queue.add(sprite);
		while (!queue.isEmpty()) {
			Pair pair = queue.remove();

			/** 물 BFS
			 * 1. 큐에서 꺼내옴
			 * 2. 목적지인가? - 목적지 없으므로 생략
			 * 3. 연결된 곳을 순회 - 상하좌우
			 * 4.	갈 수 있는가?
			 * 		- 맵 영역안의 '.'만 갈 수 있다
			 * 5. 		체크인
			 * 			- 입력때 만든 char[][]에다가 '.'을 '*'로 바꿔줌
			 * 6. 		큐에 넣음
			 */

			/** 고슴도치 BFS
			 * 1. 큐에서 꺼내옴
			 * 2. 목적지인가? - D (비버굴)
			 * 3. 연결된 곳을 순회 - 상하좌우
			 * 4. 	갈 수 있는가?
			 * 		- 맵 영역 안,
			 * 		- 'D','.'인데, 그 주변에 '*'이 인접하면 안됨. -> 줄일 수 있음. 물부터 움직이면 if문 하나 줄일 수 있다
			 * 		- 방문하지 않은 곳.
			 * 5.		체크인
			 * 			- 지도와 같은 사이즈의 int 배열을 만들어서, 고슴도치가 몇 분에 그곳에 도착할 수 있을지를 기록
			 * 6.		큐에 넣음
			 * 			- 고슴도치가 가는 곳을 큐에 기록
			 */
			if (pair.type == '*') { // 물
				int y = pair.y;
				int x = pair.x;
				for (int i = 0; i < 4; i++) {
					int nexty = y + DY[i];
					int nextx = x + DX[i];
					if (nexty < 0 || nextx < 0 || nexty >= R || nextx >= C)
						continue;
					if (arr[nexty][nextx] != '.')
						continue;
					arr[nexty][nextx] = '*';
					queue.add(new Pair(nexty, nextx, '*'));
				}
			} else if (pair.type == 'S') { // 고슴도치
				int y = pair.y;
				int x = pair.x;
				for (int i = 0; i < 4; i++) {
					int nexty = y + DY[i];
					int nextx = x + DX[i];
					if (nexty < 0 || nextx < 0 || nexty >= R || nextx >= C)
						continue;
					if (arr[nexty][nextx] != '.' && arr[nexty][nextx] != 'D')
						continue;
					if(cnt[nexty][nextx] != 0)
						continue;
					cnt[nexty][nextx] = cnt[y][x] + 1;
					queue.add(new Pair(nexty, nextx, 'S'));
				}
			}
		}

		int ans = cnt[goal.y][goal.x];
		if (ans == 0) {
			System.out.println("KAKTUS");
		}else{
			System.out.println(ans);
		}

		// System.out.println("R = " + R);
		// System.out.println("C = " + C);
		// for (int i = 0; i < R; i++) {
		// 	for (int j = 0; j < C; j++) {
		// 		System.out.print(arr[i][j]);
		// 	}
		// 	System.out.println();
		// }
	}

}
