package day07.Djikstra.p5719_거의최단경로;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static BufferedReader br;
	static BufferedWriter bw;

	static final int INF = Integer.MAX_VALUE;
	static int N, M, S, D;
	static LinkedList<Integer>[] trackingList; //최단거리 형성 시 직전 정점을 저장하는 배열
	// trackingList[n]에는 n 정점으로 가기 '전' 정점들을 넣음
	static ArrayList<Edge>[] adj; //간선 정보를 담음
	static boolean[][] flag; //생략한 간선 정보를 담음 true면 생략

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day07/Djikstra/p5719_거의최단경로/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		while (true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			if (N == 0 && M == 0) {
				break;
			}
			initData();
			//최초 다익스트라 한 번 돌며 trackingList 생성
			dijkstra(S);
			//trackingList 돌면서 최단경로 간선 지우기
			removeAdj(D);
			//한 번 더 다익스트라 돌기
			int[] answer = dijkstra(S);
			//결과 출력
			if (answer[D] == INF) {
				bw.write("-1\n");
			} else {
				bw.write(answer[D] + "\n");
			}
		}
		bw.flush();
		bw.close();
		br.close();
	}

	private static void removeAdj(int dst) {
		for (int src : trackingList[dst]) {
			if (flag[src][dst]) //이미 체크했으면 넘겨야 시간단축
				continue;
			flag[src][dst] = true;
			removeAdj(src);
		}
	}

	private static int[] dijkstra(int s) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(s, 0));
		int[] distance = new int[N];
		Arrays.fill(distance, INF);
		distance[s] = 0;
		while (!pq.isEmpty()) {
			Edge now = pq.poll();
			int nowNode = now.node;
			int nowDistance = now.weight;
			if (distance[nowNode] < nowDistance)
				continue;
			for (Edge next : adj[nowNode]) {
				int nextNode = next.node;
				int nowToNext = next.weight + distance[nowNode];
				if (flag[nowNode][nextNode]) //지워진 간선이라면 생략
					continue;
				if (distance[nextNode] == nowToNext) {
					//같을 경우 trackingList에 추가
					trackingList[nextNode].add(nowNode);
				} else if (distance[nextNode] > nowToNext) {
					//새로운 최단거리일 경우 기존 trackingList 지우고 새로 추가
					trackingList[nextNode].clear();
					trackingList[nextNode].add(nowNode);
					distance[nextNode] = nowToNext;
					pq.add(new Edge(nextNode, nowToNext));
				}
			}
		}
		return distance;
	}

	private static void initData() throws IOException {
		trackingList = new LinkedList[N];
		adj = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			trackingList[i] = new LinkedList<>();
			adj[i] = new ArrayList<>();
		}
		flag = new boolean[N][N];
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u, v, p;
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			p = Integer.parseInt(st.nextToken());
			adj[u].add(new Edge(v, p));
		}
	}

	private static class Edge implements Comparable<Edge> {
		int node, weight;

		Edge(int b, int c) {
			node = b;
			weight = c;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(weight, o.weight);
		}
	}

}
