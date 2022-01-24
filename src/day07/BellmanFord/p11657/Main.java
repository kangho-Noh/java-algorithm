package day07.BellmanFord.p11657;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static BufferedReader br;
	static BufferedWriter bw;

	static final int INF = (int)1e9;
	static int N, M;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day07/BellmanFord/p11657/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Edge[] edges = new Edge[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edges[i] = new Edge(a, b, c);
		}

		//초기화
		long[] ans = new long[N + 1];
		ans[1] = 0;
		for (int i = 2; i <= N; i++) {
			ans[i] = INF;
		}

		//정점개수-1 만큼 loop
		for (int i = 0; i < N - 1; i++) {
			for (Edge edge : edges) {
				int src = edge.src;
				int dst = edge.dst;
				int weight = edge.weight;
				if (ans[src] != INF) {
					ans[dst] = Math.min(ans[dst], ans[src] + weight);
				}
			}
		}
		boolean infinite = false;
		for (Edge edge : edges) {
			int src = edge.src;
			int dst = edge.dst;
			int weight = edge.weight;
			long temp = ans[dst];
			if (ans[src] != INF) {
				ans[dst] = Math.min(ans[dst], ans[src] + weight);
			}
			if (temp != ans[dst]) {
				infinite = true;
			}
		}

		if (infinite)
			bw.write(-1 + "\n");
		else {
			for (int i = 2; i <= N; i++) {
				if (ans[i] != INF)
					bw.write(ans[i] + "\n");
				else
					bw.write(-1 + "\n");
			}
		}
		bw.flush();
		bw.close();
		br.close();
	}

	private static class Edge {
		int src, dst, weight;

		Edge(int a, int b, int c) {
			src = a;
			dst = b;
			weight = c;
		}
	}
}
