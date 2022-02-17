package day03.IndexedTree.p1395;

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
	static int n, m, s = 1;
	static int[] tree, lazy, buttons;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day03/IndexedTree/p1395/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		while (s < n) {
			s *= 2;
		}
		buttons = new int[n + 1];
		tree = new int[s * 2];
		lazy = new int[s * 2];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			if (cmd == 0) {
				//스위치 반전
				update(1, s, 1, l, r);
			} else {
				//켜진 스위치 개수
				bw.write(query(1, s, 1, l, r) + "\n");
			}
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static int query(int left, int right, int node, int qLeft, int qRight) {
		lazyUpdate(left, right, node);

		if (left > qRight || right < qLeft) {
			return 0;
		}
		if (left >= qLeft && right <= qRight) {
			return tree[node];
		}
		int mid = (left + right) / 2;
		return query(left, mid, node * 2, qLeft, qRight)
			+ query(mid + 1, right, node * 2 + 1, qLeft, qRight);
	}

	private static void update(int left, int right, int node, int uLeft, int uRight) {
		lazyUpdate(left, right, node);
		if (left > uRight || right < uLeft) {
			return;
		}
		if (left >= uLeft && right <= uRight) {
			int switchNum = right - left + 1;
			tree[node] = switchNum - tree[node];
			if (left != right) {
				lazy[node * 2] = lazy[node * 2] == 0 ? 1 : 0;
				lazy[node * 2 + 1] = lazy[node * 2 + 1] == 0 ? 1 : 0;
			}
			return;
		}
		int mid = (left + right) / 2;
		update(left, mid, node * 2, uLeft, uRight);
		update(mid + 1, right, node * 2 + 1, uLeft, uRight);
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}

	private static void lazyUpdate(int left, int right, int node) {
		if (lazy[node] != 0) { //lazy[node] == 1 -> 반전 필요
			tree[node] = right - left + 1 - tree[node];
			if (left != right) {
				lazy[node * 2] = lazy[node * 2] == 0 ? 1 : 0;
				lazy[node * 2 + 1] = lazy[node * 2 + 1] == 0 ? 1 : 0;
			}
		}
		lazy[node] = 0;
	}
}
