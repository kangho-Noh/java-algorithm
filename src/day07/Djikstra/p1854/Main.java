package day07.Djikstra.p1854;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static StringTokenizer st;
	static BufferedReader br;
	static BufferedWriter bw;

	static int V, E, K;
	static ArrayList<Edge>[] edges;
	static PriorityQueue<Integer>[] distance;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day07/Djikstra/p1854/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		//인접리스트
		edges = new ArrayList[V + 1];
		distance = new PriorityQueue[V + 1];
		for (int i = 1; i < V + 1; i++) {
			edges[i] = new ArrayList<>();
			distance[i] = new PriorityQueue<>(Collections.reverseOrder());
		}

		int a, b, c;
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			edges[a].add(new Edge(c, b));
		}

		dijkstra(1);

		for (int i = 1; i < V + 1; i++) {
			if (distance[i].size() == K) {
				bw.write(distance[i].peek() + "\n");
			} else {
				bw.write("-1\n");
			}
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static void dijkstra(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		distance[start].add(0);
		pq.add(new Edge(0, start));

		while (!pq.isEmpty()) {
			Edge nowEdge = pq.poll();

			int nowNode = nowEdge.node;
			int nowDistance = nowEdge.distance; //지금까지 구한 now정점까지의 최소 거리
			if (nowDistance > distance[nowNode].peek()) {
				continue;
			}

			for (Edge nextEdge : edges[nowNode]) {
				int nextNode = nextEdge.node;
				int nowToNext = nowDistance + nextEdge.distance;
				if (distance[nextNode].size() < K) {
					distance[nextNode].add(nowToNext);
					pq.add(new Edge(nowToNext, nextNode));
				} else if (distance[nextNode].peek() > nowToNext) {
					distance[nextNode].poll();
					distance[nextNode].add(nowToNext);
					pq.add(new Edge(nowToNext, nextNode));
				}
			}
		}
	}

	private static class Edge implements Comparable<Edge> {
		int distance, node;

		Edge(int w, int d) {
			distance = w;
			node = d;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(distance, o.distance);
		}
	}
}
