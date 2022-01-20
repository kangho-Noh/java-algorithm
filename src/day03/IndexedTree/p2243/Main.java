package day03.IndexedTree.p2243;

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

	static int N, S;
	static long[] tree;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day03/IndexedTree/p2243/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());

		S = 1;
		while (S < 1000000) {
			S *= 2;
		}
		tree = new long[S * 2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (cmd == 1) {
				//사탕을 꺼내는 경우
				int index = query(1, S, 1, b);
				update(index, -1);
				bw.write(index+"\n");
			} else {
				//사탕을 넣는 경우
				long c = Long.parseLong(st.nextToken());
				update(b, c);
			}
		}
		bw.flush();
		bw.close();
	}

	//return 맛 (인덱스)
	static int query(int left, int right, int node, long candy) {
		//왼쪽 >= Query -> 왼쪽으로 이동, 쿼리 유지
		//왼쪽 < Query -> 오른쪽으로 이동, 쿼리 -= leftValue
		if (left != right) {
			int mid = (left + right) / 2;
			int leftNode = node * 2;
			int rightNode = node * 2 + 1;
			if (tree[leftNode] >= candy) {
				return query(left, mid, leftNode, candy);
			} else {
				return query(mid + 1, right, rightNode, candy - tree[leftNode]);
			}
		} else {
			// 리프노드 (정답 도착)
			return left;
		}
	}

	static void update(int node, long diff) {
		int idx = node + S - 1;
		while (idx > 0) {
			tree[idx] += diff;
			idx /= 2;
		}
	}
}
