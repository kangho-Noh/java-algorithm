package day06.MinimumSpanningTree.p1922.kruskal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static BufferedReader br;
	static BufferedWriter bw;

	static int N, M;
	static int[] disjointSet;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day06/MinimumSpanningTree/p1922/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());

		//disjointSet
		disjointSet = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			disjointSet[i] = i;
		}
		int ans = 0;

		List<Edge> edges = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			Edge edge = new Edge(a, b, c);
			edges.add(edge);
		}
		Collections.sort(edges);

		// 가중치 오름차순으로 간선을 돌며 사이클이 만들어지지 않을 경우 추가하며 진행 O(M)
		for (Edge edge : edges) {
			int v1 = edge.src;
			int v2 = edge.dst;
			int weight = edge.weight;
			if (find(v1) == find(v2)) {
				//사이클 존재
				continue;
			} else {
				ans += weight;
				union(v1, v2);
			}
		}

		bw.write(ans + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	private static void union(int v1, int v2) {
		int p1 = find(v1);
		int p2 = find(v2);
		disjointSet[p1] = p2;
	}

	private static int find(int v) {
		if (disjointSet[v] == v)
			return v;
		else
			return disjointSet[v] = find(disjointSet[v]);
	}

	private static class Edge implements Comparable<Edge> {
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
