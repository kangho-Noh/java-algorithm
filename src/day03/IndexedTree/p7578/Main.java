package day03.IndexedTree.p7578;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static BufferedReader br;
	static BufferedWriter bw;

	static int N, S;
	static int A[], B[], tree[];

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day03/IndexedTree/p7578/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		A = new int[N + 1];
		B = new int[N + 1];
		HashMap<Integer, Integer> hashB = new HashMap<>();
		S = 1;
		while (S < N) {
			S *= 2;
		}
		tree = new int[S * 2];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			B[i] = Integer.parseInt(st.nextToken());
			hashB.put(B[i], i);
		}

		//A[]를 차례대로 순회
		//먼저 query로 지금 긋는 선과 크로스 되는 선이 있는지 확인 ans+=query(1,S,1,treenode+1, 끝)
		//그 다음 업데이트. update(treenode,1)
		int ans = 0;
		for (int i = 1; i <= N; i++) {
			int treeInd = hashB.get(A[i]);
			int treeNode = treeInd + S - 1;
			ans += query(1, S, 1, treeNode + 1, S * 2 - 1);
			update(treeNode, 1);
		}

		bw.write(ans + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	private static void update(int node, int value) {

	}

	private static int query(int left, int right, int node, int queryLeft, int queryRight) {
		return 0;
	}
}
