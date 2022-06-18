package codetest.skt.p4;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Main {

	static int[] depth;
	static int[][] parent;
	static ArrayList<Integer>[] adj;
	static int logN;

	public static void main(String[] args) {
		int[][] edges = {{0,1},{0,2},{1,3},{1,4}};
		System.out.println(solution(5,  edges));
	}

	public static long solution(int n, int[][] edges) {
		long answer = 0;
		logN = getLog(n);
		parent = new int[logN + 1][n + 1];
		for (int i = 0; i < parent[0].length; i++) {
			parent[0][i] = i;
		}
		depth = new int[n + 1];
		adj = new ArrayList[n + 1];
		for (int i = 0; i <= n; i++) {
			adj[i] = new ArrayList<>();
		}
		for (int i = 0; i < edges.length; i++) {
			adj[edges[i][0]].add(edges[i][1]);
		}
		for (int i = 0; i < n - 1; i++) {
			parent[0][edges[i][1]] = edges[i][0];
		}
		int root = -1;
		for (int i = 0; i < n; i++) {
			if (parent[0][i] == i)
				root = i;
		}

		bfs(root);
		setSparseTable(n);

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j)
					continue;
				int lca = findLCA(i, j);

				answer += depth[i] + depth[j] - 2L * (depth[lca] + 1) + 1;
			}
		}

		return answer;
	}

	private static void setSparseTable(int n) {
		//parent[i][j] = parent[i-1][parent[i-1][j]]
		for (int i = 1; i < logN + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				parent[i][j] = parent[i - 1][parent[i - 1][j]];
			}
		}
	}

	private static int findLCA(int a, int b) {
		if (depth[a] < depth[b])
			return findLCA(b, a);

		for (int i = 0; i <= logN; i++) {
			if (((depth[a] - depth[b]) & (1 << i)) > 0) {
				a = parent[i][a];
			}
		}
		if (a == b)
			return a;

		for (int i = logN; i >= 0; i--) {
			if (parent[i][a] != parent[i][b]) {
				a = parent[i][a];
				b = parent[i][b];
			}
		}
		return parent[0][a];
	}

	private static void bfs(int root) {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(root);
		depth[root] = 0;
		while (!q.isEmpty()) {
			int now = q.poll();
			for (int i = 0; i < adj[now].size(); i++) {
				int child = adj[now].get(i);
				depth[child] = depth[now] + 1;
				q.add(child);
			}
		}
	}

	private static int getLog(int n) {
		int ret = 0;
		for (int k = 1; k < n; k *= 2) {
			ret++;
		}
		return ret;
	}
}
