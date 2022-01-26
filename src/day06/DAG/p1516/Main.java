package day06.DAG.p1516;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static BufferedReader br;
	static BufferedWriter bw;

	static int N;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day06/DisjointSet/p1516/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		Structure[] structures = new Structure[N + 1];
		for (int i = 1; i <= N; i++) { // 객체 생성 안해주면 Nullptr Exception 터짐
			structures[i] = new Structure(0);
		}
		int[] indegree = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			structures[i].buildTime = Integer.parseInt(st.nextToken());

			while (true) {
				int a = Integer.parseInt(st.nextToken());
				if (a == -1) {
					break;
				}
				structures[a].add(i);
				indegree[i]++;
			}
		}
		Queue<Integer> queue = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) {
				queue.add(i);
			}
		}

		while (!queue.isEmpty()) {
			Integer now = queue.poll(); //지금 지을 수 있음
			Structure structure = structures[now];
			structure.minBuildTime += structure.buildTime;
			List<Integer> buildNext = structure.buildNext;
			for (Integer next : buildNext) {
				indegree[next]--;
				structures[next].timePass(structure.minBuildTime);
				if (indegree[next] == 0) {
					queue.add(next);
				}
			}
		}

		for (int i = 1; i <= N; i++) {
			bw.write(structures[i].minBuildTime + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static class Structure {
		int buildTime;
		List<Integer> buildNext;
		int minBuildTime;

		Structure(int a) {
			buildTime = a;
			buildNext = new ArrayList<>();
			minBuildTime = 0;
		}

		void add(int a) {
			buildNext.add(a);
		}

		void timePass(int timepassed) {
			this.minBuildTime = Math.max(timepassed, this.minBuildTime);
		}
	}
}
