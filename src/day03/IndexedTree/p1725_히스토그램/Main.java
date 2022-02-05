package day03.IndexedTree.p1725_히스토그램;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static BufferedWriter bw;
	static BufferedReader br;
	static StringTokenizer st;

	static int n, s;
	static int[] tree;
	static int[] nums;
	static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		//
		System.setIn(new FileInputStream("src/day03/IndexedTree/p1725_히스토그램/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		n = Integer.parseInt(br.readLine());
		s = 1;
		while (s < n) {
			s *= 2;
		}
		nums = new int[n + 1];
		nums[0] = INF;
		tree = new int[s * 2]; // 최소 높이를 가진 nums의 인덱스를 저장

		for (int i = 1; i <= n; i++) {
			nums[i] = Integer.parseInt(br.readLine());
			tree[s + i - 1] = i;
		}

		initTree();
		bw.write(solve(1, n) + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	private static int solve(int left, int right) {
		if (left > right) {
			return 0;
		}
		int minIndex = query(1, s, 1, left, right);
		int ret = nums[minIndex] * (right - left + 1);
		ret = Math.max(ret, solve(minIndex + 1, right));
		ret = Math.max(ret, solve(left, minIndex - 1));
		return ret;
	}

	private static int query(int left, int right, int now, int qLeft, int qRight) {
		if (left > qRight || right < qLeft) {
			return 0;
		}
		if (left >= qLeft && right <= qRight) {
			return tree[now];
		}
		int mid = (left + right) / 2;
		int leftInd = query(left, mid, now * 2, qLeft, qRight);
		int rightInd = query(mid + 1, right, now * 2 + 1, qLeft, qRight);
		if (nums[leftInd] > nums[rightInd]) {
			return rightInd;
		} else {
			return leftInd;
		}
	}

	private static void initTree() {
		for (int i = 1; i < s; i++) {
			int now = s - i;
			int left = now * 2;
			int right = now * 2 + 1;
			if (nums[tree[left]] < nums[tree[right]]) {
				tree[now] = tree[left];
			} else {
				tree[now] = tree[right];
			}
		}
	}
}
