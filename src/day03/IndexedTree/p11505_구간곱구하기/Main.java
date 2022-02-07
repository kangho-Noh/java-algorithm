package day03.IndexedTree.p11505_구간곱구하기;

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
	static final int mod = 1000000007;
	static int n, m, k, s;
	static long[] tree;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day03/IndexedTree/p11505_구간곱구하기/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		s = 1;
		while (s < n) {
			s *= 2;
		}
		tree = new long[s * 2];

		for (int i = 1; i <= n; i++) {
			tree[s + i - 1] = Long.parseLong(br.readLine());
		}
		initTree();
		for (int i = 0; i < m + k; i++) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			if (cmd == 1) {
				int b = Integer.parseInt(st.nextToken());
				long c = Integer.parseInt(st.nextToken());
				update(b, c);
			} else {
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				bw.write(query(1, s, 1, b, c) + "\n");
			}
		}
		bw.flush();
		bw.close();
		br.close();
	}

	private static long query(int left, int right, int node, int qLeft, int qRight) {

		if (left > qRight || right < qLeft) {
			return 1;
		} else if (left >= qLeft && right <= qRight) {
			return tree[node];
		}
		int mid = (left + right) / 2;
		return (query(left, mid, node * 2, qLeft, qRight)
			* query(mid + 1, right, node * 2 + 1, qLeft, qRight)) % mod;
	}

	private static void update(int target, long num) {
		int treeInd = target+s-1;
		tree[treeInd] = num;
		while (treeInd > 0) {
			int sibling;
			if (treeInd % 2 == 1) {
				sibling = treeInd - 1;
			} else {
				sibling = treeInd+1;
			}
			tree[treeInd / 2] = (tree[treeInd] * tree[sibling]) % mod;
			treeInd/=2;
		}
	}

	private static void initTree() {

		for (int i = 1; i < s; i++) {
			tree[s - i] = (tree[(s - i) * 2] * tree[(s - i) * 2 + 1]) % mod;
		}
	}
}
