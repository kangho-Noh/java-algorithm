package day01.BFS.p1260;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static BufferedReader br;
	static BufferedWriter bw;

	static final int INF = Integer.MAX_VALUE;
	static int N, M, K;
	static int[][] adj;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day01/BFS/p1260/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		adj = new int[N + 1][N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj[a][b] = adj[b][a] = 1;
		}

		dfs(K);
		bfs(K);

		bw.flush();
		bw.close();
		br.close();
	}

	private static void bfs(int start) throws IOException {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(start);
		boolean[] visited = new boolean[N + 1];
		visited[start] = true;
		while (!q.isEmpty()) {
			int now = q.poll();
			bw.write(now + " ");
			for (int i = 1; i <= N; i++) {
				if (now != i && !visited[i] && adj[now][i] == 1) {
					q.add(i);
					visited[i] = true;
				}
			}
		}
		bw.write("\n");
	}

	private static void dfs(int start) throws IOException {
		Stack<Integer> s = new Stack<>();
		s.add(start);
		boolean[] visited= new boolean[N+1];
		boolean[] printed = new boolean[N + 1];
		visited[start] = true;

		while (!s.empty()) {
			int now = s.peek();
			if(!printed[now]){
				bw.write(now + " ");
				printed[now] = true;
			}
			for (int i = 1; i <= N; i++) {
				if (i == now || visited[i]) {
					continue;
				}
				if (adj[now][i] == 1) {
					//탐색할 자식이 있으면 now 위에 자식 넣고 break;
					s.add(i);
					visited[i] = true;
					break;
				}
			}
			if (s.peek() == now) {
				//자식이 없으면 그대로 now가 peek
				s.pop();
			}
		}
		bw.write('\n');
	}
}
