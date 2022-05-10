package khucodingtest2022.p3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static final int INF = (int)1e9;

	static StringTokenizer st;
	static BufferedReader br;
	static BufferedWriter bw;

	static int V, E;
	static List<Edge>[] edges;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		//인접리스트
		edges = new ArrayList[V + 1];
		for (int i = 1; i < V + 1; i++) {
			edges[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edges[a].add(new Edge(c, b));
			edges[b].add(new Edge(c, a));
		}
		st = new StringTokenizer(br.readLine());
		int[] start = new int[3];
		for (int i = 0; i < 3; i++) {
			start[i] = Integer.parseInt(st.nextToken());
		}
		int[] ans = djikstra(start[0]);
		int[] ans2 = djikstra(start[1]);
		int distance1 = ans[start[2]]; // 0 <-> 2
		int distance2 = ans[start[1]]; // 0 <-> 1
		int distance3 = ans2[start[2]]; // 1 <-> 2
		int answer = Math.min(distance1 + distance2,
			Math.min(distance2 + distance3, distance1 + distance3)); //0을 거치는 경우, 1을 거치는 경우, 2를 거치는 경우

		if (answer >= INF) {
			bw.write(-1 + "\n");
		} else {
			bw.write(answer + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static int[] djikstra(int start) {
		int[] distance = new int[V + 1];

		Arrays.fill(distance, INF);
		distance[start] = 0;

		boolean[] visited = new boolean[V + 1]; //각 노드당 한 번만 방문

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(0, start));

		while (!pq.isEmpty()) {
			Edge minEdge = pq.poll();
			int now = minEdge.node;
			int nowWeight = minEdge.weight;
			if (nowWeight > distance[now])
				continue;
			visited[now] = true;
			for (Edge edge : edges[now]) {
				int next = edge.node;
				int nowToNext = distance[now] + edge.weight;
				if (!visited[next] && nowToNext < distance[next]) {
					distance[next] = nowToNext;
					pq.add(new Edge(nowToNext, next));
				}
			}
		}
		return distance;
	}

	private static class Edge implements Comparable<Edge> {
		int weight, node;

		Edge(int w, int d) {
			weight = w;
			node = d;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(weight, o.weight);
		}
	}
}