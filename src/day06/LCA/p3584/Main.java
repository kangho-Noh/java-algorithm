package day06.LCA.p3584;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static BufferedReader br;
	static BufferedWriter bw;

	static final int INF = Integer.MAX_VALUE;
	static int t, n, logn;
	static int[][] parent;
	static int[] depth;
	static ArrayList<Integer>[] adj;
	static boolean[] parentExist;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day06/LCA/p3584/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		t = Integer.parseInt(st.nextToken());

		for (int tt = 0; tt < t; tt++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			getLogN();
			parent = new int[logn + 1][n + 1];
			depth = new int[n + 1];
			adj = new ArrayList[n + 1];
			parentExist = new boolean[n + 1];
			for (int i = 1; i <= n; i++) {
				adj[i] = new ArrayList<>();
			}
			for (int i = 0; i < n - 1; i++) {
				st = new StringTokenizer(br.readLine());
				int p = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				parent[0][c] = p;
				adj[p].add(c);
				parentExist[c] = true;
			}
			int root = -1;
			for (int i = 1; i <= n; i++) {
				if (parentExist[i] == false)
					root = i;
			}

			doBfs(root);
			setSparseTable();

			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			bw.write(findLCA(a, b) + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static void setSparseTable() {
		//parent[i][j] = parent[i-1][parent[i-1][j]]
		for (int i = 1; i < logn + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				parent[i][j] = parent[i - 1][parent[i - 1][j]];
			}
		}
	}

	private static void doBfs(int root) {
		//bfs하면서 depth랑 바로 위 부모 설정
		Queue<Integer> q = new ArrayDeque<>();
		q.add(root);
		depth[root] = 0;
		while (!q.isEmpty()) {
			int now = q.poll();
			for (int i = 0; i < adj[now].size(); i++) {
				int child = adj[now].get(i);
				depth[child] = depth[now] + 1;
				parent[0][child] = now;
				q.add(child);
			}
		}
	}

	private static int findLCA(int a, int b) {
		if (depth[a] < depth[b])
			return findLCA(b, a);

		for (int i = 0; i <= logn; i++) {
			if (((depth[a] - depth[b]) & (1 << i)) > 0) {
				a = parent[i][a];
			}
		}
		if(a == b) return a;

		for (int i = logn; i >= 0; i--) {
			if(parent[i][a] != parent[i][b]){
				a = parent[i][a];
				b = parent[i][b];
			}
		}
		return parent[0][a];
	}

	private static void getLogN() {
		logn = 0;
		for (int s = 1; s < n; s *= 2) {
			logn++;
		}
	}
}
