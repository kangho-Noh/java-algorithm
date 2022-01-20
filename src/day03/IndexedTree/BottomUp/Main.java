package day03.IndexedTree.BottomUp;

public class Main {

	static int N, M, K, S;
	static long[] nums;
	static long[] tree;

	public static void main(String[] args) throws Exception {

	}

	static void initBU() {
		//Leaf에 값 반영
		for (int i = 0; i < N; i++) {
			tree[S + i] = nums[i + 1];
		}
		//내부 노드 채움
		for (int i = 1; i < S; i++) {
			tree[S - i] = tree[2 * (S - i)] + tree[2 * (S - i) + 1];
		}
	}

	static long queryBU(int queryLeft, int queryRight) {
		//쿼리 바텀업

		//Leaf에서 left, right 찾기
		int left = S + queryLeft - 1;
		int right = S + queryRight - 1;
		long sum = 0;
		while (left <= right) {
			//좌측 노드가 홀수면 현재 노드 값 사용하고 한칸 뒤로
			if (left % 2 == 1) {
				sum += tree[left++];
			}
			//우측 노드가 짝수면 현재 노드 값 사용하고 한칸 앞으로
			if (right % 2 == 0) {
				sum += tree[right--];
			}
			//좌측, 우측 모두 부모로 이동
			left /= 2;
			right /= 2;
		}
		return sum;
	}

	static void updateBU(int target, long value) {
		//Leaf에서 target을 찾음
		int node = S + target - 1;
		//value 반영
		tree[node] = value;
		//root에 도달할 때까지 부모에 값 반영
		node /= 2;
		while (node > 0) {
			tree[node] = tree[node * 2] + tree[node * 2 + 1];
			node /= 2;
		}
	}

	static void update(int node, int diff) {
		int idx = node + S - 1;
		while (idx > 0) {
			tree[idx] += diff;
			idx /= 2;
		}
	}
}
