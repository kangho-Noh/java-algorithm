package day04.PrimeNum.p2960;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static BufferedReader br;
	static BufferedWriter bw;

	static int N, K;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		boolean[] visited = new boolean[N + 1];
		int cnt = 0;
		int i,j = 1;
		for (i = 2; i <= N; i++) {
			j = 1;
			while (i * j <= N) {
				if (!visited[i*j]) {
					cnt++;
					if(cnt == K) break;
					visited[i*j] = true;
				}
				j++;
			}
			if(cnt == K) break;
		}
		bw.write(i*j + "\n");

		bw.flush();
		bw.close();
		br.close();
	}
}
