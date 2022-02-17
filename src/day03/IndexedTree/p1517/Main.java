package day03.IndexedTree.p1517;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static BufferedReader br;
	static BufferedWriter bw;

	static final int INF = Integer.MAX_VALUE;
	static int n, s;
	static int[] tree;
	static Node[] nums;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day03/IndexedTree/p1517/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());

		s = 1;
		while (s < n) {
			s *= 2;
		}
		tree = new int[s * 2];
		nums = new Node[n + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			int a = Integer.parseInt(st.nextToken());
			nums[i] = new Node(a, i);
		}
		nums[0] = new Node(-INF,-INF);
		Arrays.sort(nums);

		long ans = 0;
		for (int i = 1; i <= n; i++) {
			int nowInd = nums[i].ind;
			ans += query(1, s, 1, nowInd + 1, n);
			update(nowInd + s - 1, 1);
		}
		bw.write(ans + "\n");

		bw.flush();
		bw.close();
		br.close();
	}

	private static void update(int target, int diff) {
		tree[target] = diff;
		while (target > 0) {
			target /= 2;
			tree[target] += diff;
		}
	}

	private static int query(int left, int right, int node, int qLeft, int qRight) {
		if (left > qRight || right < qLeft) {
			return 0;
		}
		if (qLeft <= left && qRight >= right) {
			return tree[node];
		}
		int mid = (left + right) / 2;
		return query(left, mid, node * 2, qLeft, qRight)
			+ query(mid + 1, right, node * 2 + 1, qLeft, qRight);
	}

	private static class Node implements Comparable<Node> {

		int ind, num;

		Node(int a, int b) {
			num = a;
			ind = b;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(num, o.num);
		}
	}
}
