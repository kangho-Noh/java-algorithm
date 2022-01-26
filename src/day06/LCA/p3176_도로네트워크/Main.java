package day06.LCA.p3176_도로네트워크;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
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

	static final int INF = Integer.MAX_VALUE;
	static int N, logN;
	static ArrayList<Edge>[] adj;
	static int[][] parent;
	//parent[][]까지의 최소, 최대 간선 크기를 저장하는 배열 두 개
	static int[][] minWeight;
	static int[][] maxWeight;

	static int[] depth;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day06/LCA/p3176_도로네트워크/input.txt"));
		//
		//1. logN을 구한다
		//2. depth[N], parent[logN][N], adj[N] 만든다
		//3. bfs 또는 dfs(스택)을 사용하여 root부터 차례대로 depth, parent[0][i] 채우기
		//4. sparse table을 만든다. parent[n][k] = parent[n-1][parent[n-1][k]]
		//5. LCA(a,b) 구현
		//  - 깊이는 depth[a]>depth[b]로 설정
		//  - 둘이 깊이 맞춤 -> 비트연산
		//  - a==b면 둘 중하나 리턴 후 종료
		//  - 반복문 수행 -> 조상 바로 아래 도착
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		logN = getLog(N);

		initListData();

		bfs(1);

		makeSparseTable();

		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int D = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			solve(D, E);
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static void initListData() throws IOException {
		adj = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; i++) {
			adj[i] = new ArrayList<>();
		}
		depth = new int[N + 1];
		parent = new int[logN + 1][N + 1];
		minWeight = new int[logN + 1][N + 1];
		for (int i = 0; i < logN + 1; i++) {
			for (int j = 0; j < N + 1; j++) {
				minWeight[i][j] = INF;
			}
		}
		maxWeight = new int[logN + 1][N + 1];
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			adj[a].add(new Edge(b, c));
			adj[b].add(new Edge(a, c));
		}
	}

	private static void makeSparseTable() {
		for (int i = 1; i <= logN; i++) {
			for (int j = 1; j <= N; j++) {
				parent[i][j] = parent[i - 1][parent[i - 1][j]];
				minWeight[i][j] =
					Math.min(minWeight[i - 1][j], minWeight[i - 1][parent[i - 1][j]]);
				maxWeight[i][j] =
					Math.max(maxWeight[i - 1][j], maxWeight[i - 1][parent[i - 1][j]]);
			}
		}
	}

	private static void bfs(int node) {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(node);
		depth[node] = 1; // 깊이 초기화 (원래는 0이지만 상대적인 값이므로 편의상 1로 초기화)
		parent[0][node] = 1; // 첫번째 조상
		while (!q.isEmpty()) {
			int now = q.poll();

			for (Edge edge : adj[now]) {
				int childNode = edge.node;
				int weight = edge.weight;
				if (depth[childNode] == 0) {
					depth[childNode] = depth[now] + 1;
					parent[0][childNode] = now;
					minWeight[0][childNode] = weight;
					maxWeight[0][childNode] = weight;
					q.add(childNode);
				}
			}
		}
	}

	private static void solve(int a, int b) throws IOException {
		if (a == b) {
			bw.write("0 0\n");
			return;
		}
		//높이 대소관계 맞추기 depth[a]>depth[b]
		if (depth[a] < depth[b]) {
			solve(b, a);
			return;
		}
		int min = INF;
		int max = -1;
		//높이 맞추기 -> 비트연산
		int cha = depth[a] - depth[b];
		for (int i = 0; i < logN; i++) {
			if ((cha & (1 << i)) != 0) {
				min = Math.min(min, minWeight[i][a]);
				max = Math.max(max, maxWeight[i][a]);
				a = parent[i][a];
			}
		}

		//둘이 같으면 min, max가 그대로 답이 된다. (-1이면 0, INF면 0)
		if (a != b){
			for (int i = logN; i >= 0; i--) {
				if (parent[i][a] != parent[i][b]) {
					min = Math.min(min,
						Math.min(minWeight[i][a], minWeight[i][b]));
					max = Math.max(max,
						Math.max(maxWeight[i][a], maxWeight[i][b]));
					a = parent[i][a];
					b = parent[i][b];
				}
			}
			//LCA 바로 아래로 도착
			min = Math.min(min,
				Math.min(minWeight[0][a], minWeight[0][b]));
			max = Math.max(max,
				Math.max(maxWeight[0][a], maxWeight[0][b]));

		}
		if (min == INF) {
			bw.write("0 ");
		} else {
			bw.write(min + " ");
		}
		if (max == -1) {
			bw.write("0\n");
		} else {
			bw.write(max + "\n");
		}

	}

	private static int getLog(int n) {
		int ret = 0;
		for (int k = 1; k < n; k *= 2) {
			ret++;
		}
		return ret;
	}

	private static class Edge {
		int node;
		int weight;

		Edge(int a, int b) {
			node = a;
			weight = b;
		}
	}
}
