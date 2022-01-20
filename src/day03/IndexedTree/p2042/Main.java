package day03.IndexedTree.p2042;

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
	static long[] nums;
	static long[] tree;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day03/IndexedTree/p2042/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		nums = new long[N + 1];

		for (int i = 1; i <= N; i++) {
			nums[i] = Long.parseLong(br.readLine());
		}

		//S를 구하는 과정
		S = 1;
		while (S < N) {
			S *= 2;
		}
		tree = new long[S * 2];
		init(1, S, 1);

		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			if (cmd == 1) {
				// update
				// long diff = c - nums[b];
				//원래 숫자도 고쳐주기
				// nums[b] = c;
				//아니면 트리로 구하던가
				long diff = c - tree[b+S-1];
				update(1, S, 1, b, diff);
			} else {
				// query
				bw.write(query(1,S,1,b, (int)c) + "\n");
			}
		}
		bw.flush();
		bw.close();
	}

	static long init(int left, int right, int node) {
		//내부 노드일 경우
		int mid = (left + right) / 2;
		if (left != right) {
			tree[node] = init(left, mid, node * 2) + init(mid + 1, right, node * 2 + 1);

		}
		//리프 노드일 경우
		else {
			if (left <= N) // 포화 이진트리로 만들기 위해 비워놓은 노드는 건들면 안됨
				tree[node] = nums[left];
		}
		return tree[node];
	}

	static long query(int left, int right, int node, int queryLeft, int queryRight) {
		// 연관이 없음 -> 결과에 영향이 없는 값 return
		if (queryRight < left || right < queryLeft) {
			return 0;
		}
		//판단 가능. 쿼리 구간에 포함됨 -> 현재 노드 값 return
		if (queryLeft <= left && right <= queryRight) {
			return tree[node];
		}
		//판단 불가, 자식에게 위임, 자식에서 올라온 합을 return
		int mid = (left + right) / 2;
		long leftResult = query(left, mid, node * 2, queryLeft, queryRight);
		long rightResult = query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
		return leftResult + rightResult;
	}

	static void update(int left, int right, int node, int target, long diff) {
		//연관 있음 -> 현재 노드에 diff 반영 후 자식에게 diff 전달
		if (left <= target && target <= right) {
			tree[node] += diff;
			if (left != right) {
				int mid = (left + right) / 2;
				update(left, mid, node * 2, target, diff);
				update(mid + 1, right, node * 2 + 1, target, diff);
			}
		}
	}
}

