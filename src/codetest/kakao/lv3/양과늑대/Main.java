package codetest.kakao.lv3.양과늑대;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		Solution sol = new Solution();
		System.out.println(sol.solution(new int[] {0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1},
			new int[][] {{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}}));
	}
}

class Solution {
	int[] leftChild, rightChild, sheepChildCount;
	int answer;

	public int solution(int[] info, int[][] edges) {
		answer = 0;
		leftChild = new int[info.length];
		rightChild = new int[info.length];
		sheepChildCount = new int[info.length];
		for (int i = 0; i < edges.length; i++) {
			int parent = edges[i][0];
			int child = edges[i][1];
			if (info[child] == 0) {
				sheepChildCount[parent]++;
			}
			if (leftChild[parent] == 0) {
				leftChild[parent] = child;
			} else {
				rightChild[parent] = child;
			}
		}
		List<Integer> list = new ArrayList<>();
		list.add(0);
		dfs(0, 0, 0, list, info);
		return answer;
	}

	private void dfs(int sheep, int wolf, int now, List<Integer> tempList, int[] info) {
		List<Integer> list = new ArrayList<>(tempList);
		if (info[now] == 0) {
			sheep++;
		} else {
			wolf++;
		}
		if (sheep == wolf)
			return;
		list.remove(Integer.valueOf(now));
		if (leftChild[now] != 0) {
			list.add(leftChild[now]);
		}
		if (rightChild[now] != 0) {
			list.add(rightChild[now]);
		}
		for (Integer next : list) {
			if (next == now)
				continue;
			dfs(sheep, wolf, next, list, info);
		}

		answer = Math.max(answer, sheep);
	}
}