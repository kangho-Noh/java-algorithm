package day07.FloydWarshall.p11404;

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

	static int N, M;
	static int[][] distance;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day07/FloydWarshall/p11404/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());

		//인접행렬 사용
		distance = new int[N + 1][N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			if (distance[u][v] == 0 || distance[u][v] > w) {
				distance[u][v] = w;
			}
		}

		//플로이드와샬 (j->k direct) vs (i를 경유한 j->k)
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				for (int k = 1; k <= N; k++) {
					if(j==k) continue;
					if(distance[j][i]==0 || distance[i][k] ==0) continue;
					if (distance[j][k] ==0
						|| distance[j][i] + distance[i][k] < distance[j][k]) {
						distance[j][k] = distance[j][i] + distance[i][k];
					}
				}
			}
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				bw.write(distance[i][j] + " ");
			}
			bw.write("\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

}
