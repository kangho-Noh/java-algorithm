package day07.Djikstra.p4485;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static BufferedReader br;
	static BufferedWriter bw;

	static final int INF = Integer.MAX_VALUE;
	static int n;
	static ArrayList<Edge>[] adj;
	static final int[] dy = {-1, 0, 1, 0};
	static final int[] dx = {0, 1, 0, -1};
	static int[][] board;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day07/Djikstra/p4485/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int t = 1;
		while (true) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			if (n == 0)
				break;
			adj = new ArrayList[n * n];
			for (int i = 0; i < n * n; i++) {
				adj[i] = new ArrayList<>();
			}
			board = new int[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					int weight = Integer.parseInt(st.nextToken());
					board[i][j] = weight;
					int dstInd = i * n + j;
					for (int k = 0; k < 4; k++) {
						int ny = i + dy[k];
						int nx = j + dx[k];
						if (ny < 0 || nx < 0 || ny >= n || nx >= n)
							continue;
						int srcInd = ny * n + nx;
						adj[srcInd].add(new Edge(dstInd, weight));
					}
				}
			}
			int ans = dijkstra();
			bw.write("Problem " + t + ": " + ans + "\n");
			t++;
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static int dijkstra() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		int[] result = new int[n * n];
		for (int i = 0; i < n * n; i++) {
			result[i] = INF;
		}
		result[0] = board[0][0];
		pq.add(new Edge(0, board[0][0]));
		while (!pq.isEmpty()) {
			Edge now = pq.poll();
			int src = now.node;
			int cost = now.weight;
			if (cost > result[src])
				continue;
			for (Edge next : adj[src]) {
				int dst = next.node;
				int weight = next.weight;
				if (result[dst] > cost + weight) {
					result[dst] = cost + weight;
					pq.add(new Edge(dst, weight + cost));
				}
			}
		}
		return result[n * n - 1];
	}

	private static class Edge implements Comparable<Edge> {
		int node, weight;

		Edge(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			int comp = Integer.compare(weight, o.weight);
			return comp;
		}
	}

}
