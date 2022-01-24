package day06.DAG.p2252;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static StringTokenizer st;
	static BufferedReader br;
	static BufferedWriter bw;

	static int N, M;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day06/DAG/p2252/input2.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 인접 리스트
		LinkedList<Integer>[] list = new LinkedList[N + 1];
		for (int i = 1; i <= N; i++) {
			list[i] = new LinkedList<>();
		}
		// indegree
		int[] indegree = new int[N+1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			indegree[b]++;
		}

		// 처리할 정점이 들어갈 큐
		Queue<Integer> queue = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) {
				queue.add(i);
			}
		}

		while (!queue.isEmpty()) {
			int now = queue.poll();
			bw.write(now+" ");
			for (int next : list[now]) {
				indegree[next]--;
				if (indegree[next] == 0) {
					queue.add(next);
				}
			}
		}
		bw.write("\n");


		bw.flush();
		bw.close();
		br.close();
	}
}
