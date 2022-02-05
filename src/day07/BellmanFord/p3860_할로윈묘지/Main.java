package day07.BellmanFord.p3860_할로윈묘지;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static BufferedReader br;
	static BufferedWriter bw;

	static final int INF = Integer.MAX_VALUE;
	static final int[] dy = {1, 0, -1, 0};
	static final int[] dx = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day07/BellmanFord/p3860_할로윈묘지/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		while (true) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			if (w == 0 && h == 0) {
				break;
			}
			int[][] board = new int[h][w];
			int g = Integer.parseInt(br.readLine());
			for (int i = 0; i < g; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				board[y][x] = -1; //묘비
			}
			int e = Integer.parseInt(br.readLine());
			Hole[] holes = new Hole[e + 1];
			for (int i = 0; i < e + 1; i++) {
				holes[i] = new Hole();
			}
			for (int i = 1; i <= e; i++) {
				st = new StringTokenizer(br.readLine());
				int x1 = Integer.parseInt(st.nextToken());
				int y1 = Integer.parseInt(st.nextToken());
				int x2 = Integer.parseInt(st.nextToken());
				int y2 = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());
				board[y1][x1] = i;
				holes[i].y = y2;
				holes[i].x = x2;
				holes[i].t = t;
			}
			List<Edge> edges = new ArrayList<>();

			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					//i*w+j //정점번호 (y*w + x)
					if (i == h - 1 && j == w - 1)
						continue;
					int src = i * w + j;
					if (board[i][j] == 0) {
						for (int k = 0; k < 4; k++) {
							int ny = dy[k] + i;
							int nx = dx[k] + j;
							if (ny < 0 || nx < 0 || ny >= h || nx >= w)
								continue;
							int dst = ny * w + nx;
							if (board[ny][nx] == -1) {
								continue;
							} else {
								edges.add(new Edge(src, dst, 1));
							}
						}
					} else if (board[i][j] > 0) {
						Hole hole = holes[board[i][j]];
						int dst = hole.y * w + hole.x;
						edges.add(new Edge(src, dst, hole.t));
					}
				}
			}

			//초기화
			long[] ans = new long[w * h + 1];
			ans[0] = 0;
			for (int i = 1; i <= w * h; i++) {
				ans[i] = INF;
			}

			for (int i = 0; i < w * h - 1; i++) {
				for (Edge edge : edges) {
					int src = edge.src;
					int dst = edge.dst;
					int weight = edge.weight;
					if (ans[src] != INF) {
						ans[dst] = Math.min(ans[dst], ans[src] + weight);
					}
				}
			}

			boolean infinite = false;
			for (Edge edge : edges) {
				int src = edge.src;
				int dst = edge.dst;
				int weight = edge.weight;
				long temp = ans[dst];
				if (ans[src] != INF) {
					ans[dst] = Math.min(ans[dst], ans[src] + weight);
				}
				if (temp != ans[dst]) {
					infinite = true;
				}
			}
			int last = w * h - 1;

			if (infinite) {
				bw.write("Never\n");
			} else if (ans[last] == INF) {
				bw.write("Impossible\n");
			}else{
				bw.write(ans[last] + "\n");
			}

		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static class Hole {
		int y, x, t;

		Hole() {
			this.y = 0;
			this.x = 0;
			this.t = 0;
		}

		void set(int y, int x, int t) {
			this.y = y;
			this.x = x;
			this.t = t;
		}
	}

	private static class Edge {
		int src, dst, weight;

		Edge(int src, int dst, int weight) {
			this.src = src;
			this.dst = dst;
			this.weight = weight;
		}

	}
}
