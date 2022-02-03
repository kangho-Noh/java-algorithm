package day03.IndexedTree.p2357_최솟값과최댓값;

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
	static int N, M, S = 1;
	static int[] minTree, maxTree;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day03/IndexedTree/p2357_최솟값과최댓값/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		while (S < N) {
			S *= 2;
		}
		minTree = new int[S * 2];
		for (int i = 0; i < S * 2; i++) {
			minTree[i] = INF;
		}
		maxTree = new int[S * 2];
		M = Integer.parseInt(st.nextToken());
		int[] nums = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
			update(i, nums[i]);
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int queryLeft = Integer.parseInt(st.nextToken());
			int queryRight = Integer.parseInt(st.nextToken());
			bw.write(minQuery(1, 1, S, queryLeft, queryRight) + " "
				+ maxQuery(1, 1, S, queryLeft, queryRight) + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static int minQuery(int now, int left, int right, int queryLeft, int queryRight) {
		if (left > queryRight || right < queryLeft) {
			return INF;
		} else if (left >= queryLeft && right <= queryRight) {
			return minTree[now];
		}
		else{
			int mid = (left+right)/2;
			int leftValue = minQuery(now * 2, left, mid, queryLeft, queryRight);
			int rightValue = minQuery(now * 2 + 1, mid + 1, right, queryLeft, queryRight);
			return Math.min(leftValue, rightValue);
		}
	}

	private static int maxQuery(int now, int left, int right, int queryLeft, int queryRight) {
		if (left > queryRight || right < queryLeft) {
			return 0;
		} else if (left >= queryLeft && right <= queryRight) {
			return maxTree[now];
		}
		else{
			int mid = (left+right)/2;
			int leftValue = maxQuery(now * 2, left, mid, queryLeft, queryRight);
			int rightValue = maxQuery(now * 2 + 1, mid + 1, right, queryLeft, queryRight);
			return Math.max(leftValue, rightValue);
		}
	}

	private static void update(int target, int value) {
		int treeInd = target + S - 1;
		minTree[treeInd] = value;
		maxTree[treeInd] = value;
		while (treeInd / 2 > 0) {
			if (treeInd % 2 == 0) {
				minTree[treeInd / 2] = Math.min(minTree[treeInd], minTree[treeInd + 1]);
				maxTree[treeInd / 2] = Math.max(maxTree[treeInd], maxTree[treeInd + 1]);
			} else {
				minTree[treeInd / 2] = Math.min(minTree[treeInd], minTree[treeInd - 1]);
				maxTree[treeInd / 2] = Math.max(maxTree[treeInd], maxTree[treeInd - 1]);
			}
			treeInd /= 2;
		}
	}
}
