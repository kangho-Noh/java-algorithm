package day06.LCA.p11438;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static BufferedReader br;
	static BufferedWriter bw;

	static int N, M;
	static int logN;
	static int[][] parent; //parent[K][V], 정점 V의 2^K번째 조상
	static int[] depth; //노드의 깊이를 저장
	// parent[K][V] = parent[K-1][parent[K-1][V]]
	//인접리스트
	static ArrayList<Integer>[] adj;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day06/LCA/p11438/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		//logN 구하는 과정
		//N=2, logN = 1
		//N=8, logN = 3
		//N=10, logN = 4
		for (int k = 1; k < N; k *= 2) {
			logN++;
		}

		depth = new int[N + 1];
		parent = new int[logN + 1][N + 1];
		adj = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj[a].add(b);
			adj[b].add(a);
		}

		//1. dfs또는 bfs로 트리 만들기. depth설정.
		//이 때, dfs의 재귀방식을 사용하면 최대 10만번으로 stack overflow발생. 따라서 스택을 사용하거나 bfs사용
		doBfs(1);

		//2. sparse table만들기
		makeSparseTable();

		//3. LCA 수행
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			bw.write(findLCA(a, b) + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static void doBfs(int root) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(root);

		depth[root] = 0;
		queue.add(root);

		while (!queue.isEmpty()) {
			int now = queue.poll();
			for (int next : adj[now]) {
				if (next != root && depth[next] == 0) {
					depth[next] = depth[now] + 1;
					parent[0][next] = now;
					queue.add(next);
				}
			}
		}
	}

	private static void makeSparseTable() {
		//점화식 사용
		for (int i = 1; i <= logN; i++) {
			for (int j = 1; j <= N; j++) {
				parent[i][j] = parent[i - 1][parent[i - 1][j]];
			}
		}
	}

	private static int findLCA(int a, int b) {
		// 깊이를 a>b로 맞춤
		if (depth[a] < depth[b]) {
			return findLCA(b, a);
		}

		// 깊이 맞추기
		for (int i = 0; i <= logN; i++) {
			if (((depth[a] - depth[b]) & (1 << i)) >= 1) {
				a = parent[i][a];
			}
		}

		// 깊이 맞췄으면 같은지 확인
		if (a == b) {
			return a;
		}

		// 공통조상이 아닐 때까지 부모를 따라 올라간다
		// 최종적으로는 LCA 바로 밑칸까지만 올라간다.
		// 무조건 올라가므로 for문 조건 이렇게 설정해도 됨
		for (int i = logN; i >= 0; i--) {
			if (parent[i][a] != parent[i][b]) {
				a = parent[i][a];
				b = parent[i][b];
			}
		}
		return parent[0][a];
	}
}
