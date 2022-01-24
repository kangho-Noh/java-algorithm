package day06.DisjointSet.p1717;

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
	static int[] set;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day06/DisjointSet/p1717/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		initSet(N);
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int C = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (C == 0) {
				union(a, b);
			} else {
				if (find(a) == find(b)) {
					bw.write("YES\n");
				}else{
					bw.write("NO\n");
				}
			}
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static void union(int a, int b) {
		int parentA = find(a);
		int parentB = find(b);
		set[parentA] = parentB;
	}

	private static int find(int a) {
		if(set[a] == a) return a;
		else return set[a] = find(set[a]);
	}

	private static void initSet(int n) {
		set = new int[N + 1];
		for (int i = 1; i <= n; i++) {
			set[i] = i;
		}
	}
}
