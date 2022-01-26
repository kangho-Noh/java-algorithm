package day06.DisjointSet.p3830;

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

	static final int INF = Integer.MAX_VALUE;
	static int N, M;
	static int[] parent;
	static long[] relativeWeight;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day06/DisjointSet/p3830/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		while (true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); //샘플의 수
			M = Integer.parseInt(st.nextToken()); //한 일의 수
			if (N == 0 && M == 0) {
				break;
			}

			parent = new int[N + 1];
			relativeWeight = new long[N+1];
			for (int i = 0; i < N + 1; i++) {
				parent[i] = i;
				relativeWeight[i] = 0;
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				char command = st.nextToken().charAt(0);
				if (command == '!') {
					// b가 a보다 w그램 무겁다
					int a = Integer.parseInt(st.nextToken());
					int b = Integer.parseInt(st.nextToken());
					int w = Integer.parseInt(st.nextToken());
					union(a,b,w);
				} else {
					//b가 a보다 얼마나 무거운지 (b-a)
					int a = Integer.parseInt(st.nextToken());
					int b = Integer.parseInt(st.nextToken());
					if(find(a) != find(b)){
						bw.write("UNKNOWN\n");
					}else{
						bw.write(getRelativeWeight(a,b) + "\n");
					}
				}

			}
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static long getRelativeWeight(int a, int b) {
		return relativeWeight[a] - relativeWeight[b];
	}

	private static void union(int a, int b, int w) {
		int pa = find(a);
		int pb = find(b);
		if (pa == pb) {
			// 이미 같으면 리턴
			return;
		}
		//다르면 한쪽을 붙이고 relative weight 저장
		long wa = relativeWeight[a]; // wa = pa -a
		long wb = relativeWeight[b]; // wb = pb -b
		// 우리가 구할 것 ) relativeWeight[pa] = pb - pa = wb+b - (wa+a)
		//  = wb -wa + b -a, 여기서 w = b-a이므로,
		//  = wb - wa + w
		parent[pa] = pb;
		relativeWeight[pa] = wb - wa + w;
	}

	private static int find(int a) {
		if (parent[a] == a)
			return a;
		else {
			int pa = find(parent[a]);
			relativeWeight[a] += relativeWeight[parent[a]];
			return parent[a] = pa;
		}
	}

}
