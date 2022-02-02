package day07.FloydWarshall.p2458;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static BufferedReader br;
	static BufferedWriter bw;

	static final int INF = Integer.MAX_VALUE;
	static int N, M;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day07/FloydWarshall/p2458/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		ArrayList<Integer>[] adj = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; i++) {
			adj[i] = new ArrayList<>();
		}
		int[][] edge = new int[N + 1][N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			//작 -> 큰
			adj[a].add(b);
			edge[a][b] = 1;
		}
		for (int i = 0; i < N + 1; i++) {
			for (int j = 0; j < N + 1; j++) {
				if (i == j)
					edge[i][j] = 1;
			}
		}
		int[] smallerCnt = new int[N + 1]; //나보다 작은 사람 수
		int[] tallerCnt = new int[N + 1]; //나보다 큰 사람 수
		int[][] visited = new int[N + 1][N + 1];
		//i를 경유한 j->k
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				for (int k = 1; k < N + 1; k++) {
					if (j == k)
						continue;
					if (edge[j][i] == 0 || edge[i][k] == 0)
						continue;
					if (visited[j][k] == 1)
						continue;
					smallerCnt[k]++;
					tallerCnt[j]++;
					visited[j][k] = 1;
					edge[j][k]=1;
				}
			}
		}
		int ans = 0;
		for (int i = 1; i <= N; i++) {
			if (smallerCnt[i] + tallerCnt[i] == N - 1) {
				ans++;
			}
		}
		bw.write(ans + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
}
