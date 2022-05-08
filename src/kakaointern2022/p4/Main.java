package kakaointern2022.p4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		Solution sol = new Solution();
		System.out.println(sol.solution(
			6,
			new int[][]{{1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4}, {3, 4, 4}, {4, 5, 3}, {4, 6, 1}, {5, 6, 1}},
			new int[]{1,3},
			new int[]{5}
		));
	}
}

class Solution {

	int[] disjointSet;
	List<Edge> edges;
	ArrayList<Edge>[] adj;//adj는 최소스패닝 트리의 간선
	boolean[] isGate, isSummit, visited;
	final int MAX = 10000001;

	public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
		int[] answer = new int[]{0, MAX};
		initInputData(n, paths.length, paths);
		Collections.sort(edges);

		//간선정보 최소스패닝 트리로 업데이트
		kruskal(edges, n);

		isSummit = new boolean[n + 1];
		for (int summit : summits) {
			isSummit[summit] = true;
		}
		isGate = new boolean[n + 1];
		for (int gate : gates) {
			isGate[gate] = true;
		}

		for (int root : gates) {
			visited = new boolean[n + 1];
			dfs(root, answer,0);
		}

		return answer;
	}

	private void dfs(int node, int[] answer, int intensity) {
		//탈출조건
		//산봉우리일 경우
		visited[node] = true;
		if (isSummit[node]) {
			if (intensity < answer[1]) {
				answer[1] = intensity;
				answer[0] = node;
			} else if (intensity == answer[1]) {
				answer[0] = Math.min(answer[0], node);
			}
			return;
		}

		for (Edge next : adj[node]) {
			if(!isGate[next.dst] && !visited[next.dst]) {
				dfs(next.dst, answer, Math.max(intensity, next.weight));
			}
		}
		visited[node] = false;
	}

	private void initInputData(int N, int M, int[][] path) {
		//disjointSet
		disjointSet = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			disjointSet[i] = i;
		}

		edges = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			int a = path[i][0];
			int b = path[i][1];
			int c = path[i][2];
			edges.add(new Edge(a, b, c));
		}
	}

	private void kruskal(List<Edge> edges, int n) {
		// 가중치 오름차순으로 간선을 돌며 사이클이 만들어지지 않을 경우 추가하며 진행 O(M)
		adj = new ArrayList[n + 1];
		for (int i = 0; i < n + 1; i++) {
			adj[i] = new ArrayList<>();
		}
		for (Edge edge : edges) {
			int v1 = edge.src;
			int v2 = edge.dst;
			int weight = edge.weight;
			if (find(v1) == find(v2)) {
				//사이클 존재
				continue;
			} else {
				adj[v1].add(new Edge(v1, v2, weight));
				adj[v2].add(new Edge(v2, v1, weight));
				union(v1, v2);
			}
		}
	}

	private void union(int v1, int v2) {
		int p1 = find(v1);
		int p2 = find(v2);
		disjointSet[p1] = p2;
	}

	private int find(int v) {
		if (disjointSet[v] == v)
			return v;
		else
			return disjointSet[v] = find(disjointSet[v]);
	}

	private class Edge implements Comparable<Edge> {
		int src, dst, weight;

		Edge(int a, int b, int c) {
			src = a;
			dst = b;
			weight = c;
		}

		@Override
		public int compareTo(Edge o) {
			int comp = Integer.compare(weight, o.weight);
			return comp;
		}
	}
}