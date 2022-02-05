package day03.IndexedTree.lazy_propagation.p10999_구간합구하기2;

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

	static int N, M, K, S;
	static long[] tree;
	static long[] lazy;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day03/IndexedTree/lazy_propagation/p10999_구간합구하기2/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		S = 1;
		while (S < N) {
			S *= 2;
		}
		tree = new long[S * 2];
		lazy = new long[S * 2];
		for (int i = 0; i < N; i++) {
			int treeInd = i + S;
			tree[treeInd] = Long.parseLong(br.readLine());
		}
		initTree();

		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			if (cmd == 1) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				long diff = Long.parseLong(st.nextToken());
				update(1, S, 1, a, b, diff);
			} else {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				bw.write(query(1, S, 1, a, b) + "\n");
			}
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static void initTree() {
		//내부 노드 채우기
		for (int i = 1; i < S; i++) {
			tree[S - i] = tree[2 * (S - i)] + tree[2 * (S - i) + 1];
		}
	}

	private static void updateLazy(int left, int right, int now) {
		if (lazy[now] != 0) {
			//만약 lazy값이 있다면
			tree[now] += (lazy[now] * (right - left + 1)); //현재 노드 업데이트
			//자식 노드가 있다면 lazy값 전파
			if (left != right) {
				lazy[now * 2] += lazy[now];
				lazy[now * 2 + 1] += lazy[now];
			}
			lazy[now] = 0; //반영 했으므로 lazy초기화
		}
	}

	private static void update(int left, int right, int now, int tLeft, int tRight, long diff) {
		updateLazy(left, right, now);

		if (left > tRight || right < tLeft) {
			return;
		}
		if (tLeft <= left && tRight >= right) {
			//범위가 겹치면 현재 노드 바로 업데이트 후 자식 lazy값 넣어주고 리턴
			tree[now] += diff * (right - left + 1);

			if (left != right) {
				lazy[now * 2] += diff;
				lazy[now * 2 + 1] += diff;
			}
			return;
		}
		//범위가 한쪽이 걸친 상태면 자식 노드 업데이트 후 현재 노드에 반영
		int mid = (left + right) / 2;
		update(left, mid, now * 2, tLeft, tRight, diff);
		update(mid + 1, right, now * 2 + 1, tLeft, tRight, diff);
		tree[now] = tree[now * 2] + tree[now * 2 + 1];
	}

	private static long query(int left, int right, int now, int qleft, int qright) {
		//구간합을 물을 때에는 현재 노드가 lazy 상태인지 확인을 꼭 해야함
		updateLazy(left, right, now);

		if (qleft > right || qright < left) {
			return 0;
		} else if (qleft <= left && qright >= right) {
			return tree[now];
		}
		int mid = (left + right) / 2;
		long lValue = query(left, mid, now * 2, qleft, qright);
		long rValue = query(mid + 1, right, now * 2 + 1, qleft, qright);
		return lValue + rValue;
	}
}
