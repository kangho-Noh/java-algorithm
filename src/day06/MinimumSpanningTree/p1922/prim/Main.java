package day06.MinimumSpanningTree.p1922.prim;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static BufferedReader br;
	static BufferedWriter bw;

	static int N, M;
	static boolean[] visited;

	static ArrayList<Edge>[] edges;
	static PriorityQueue<Edge> pq;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day06/MinimumSpanningTree/p1922/input.txt"));
		//
		initInputData();
		int start = 1; //시작 정점
		int ans = prim(start);

		bw.write(ans + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	private static void initInputData() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		// 간선을 저장하기 위한 배열
		edges = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		//정점이 추가되었는지 확인하기 위한 배열
		visited = new boolean[N + 1];
		//프림 알고리즘 - 최소힙
		pq = new PriorityQueue<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edges[a].add(new Edge(b, c));
			edges[b].add(new Edge(a, c));
		}
	}

	private static int prim(int start) {
		int ans = 0;
		visited[start] = true;
		pq.addAll(edges[start]);

		while (!pq.isEmpty()) {
			Edge minEdges = pq.poll();
			int dst = minEdges.dst;
			int weight = minEdges.weight;
			if (visited[dst])
				continue;
			visited[dst] = true;
			ans += weight;

			//dst에 대한 정점 추가
			pq.addAll(edges[dst]);
		}
		return ans;
	}

	private static class Edge implements Comparable<Edge> {
		int dst, weight;

		Edge(int b, int c) {
			dst = b;
			weight = c;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(weight, o.weight);
		}
	}
}
