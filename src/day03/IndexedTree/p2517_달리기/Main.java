package day03.IndexedTree.p2517_달리기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static BufferedReader br;
	static BufferedWriter bw;

	static final int INF = Integer.MAX_VALUE;
	static int N, S;
	static int[] tree, arr;
	static HashMap<Integer, Integer> origin;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day03/IndexedTree/p2517_달리기/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		int[] arr = new int[N + 1];
		S = 1;
		while (S < N) {
			S *= 2;
		}
		tree = new int[S * 2];
		origin = new HashMap<>();
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(st.nextToken());
			origin.put(arr[i], i); //15 : 8 이런식
		}
		Arrays.sort(arr);
		int[] ans = new int[N + 1];
		//실력이 안좋은 선수부터 (실력 오름차순)
		for (int i = 1; i <= N; i++) {
			int now = arr[i]; //현재 선수의 실력
			int originalScore = origin.get(now); //원래 등수

			int qResult = query(1, S, 1, 1, originalScore - 1);
			//qResult는 내가 앞지를 수 있는 사람의 수가 나올 거임
			//내 순위는 origin - qResult가 될 것이다. 단, 1보다 작으면 1로 바꿔줌
			int result = originalScore - qResult;
			if (result < 1)
				result = 1;
			ans[originalScore] = result;

			update(originalScore, 1);
		}

		for (int i = 1; i <= N; i++) {
			bw.write(ans[i] + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static void update(int target, int value) {
		int treeInd = target + S - 1;
		while (treeInd > 0) {
			tree[treeInd] += 1;
			treeInd /= 2;
		}
	}

	private static int query(int left, int right, int now, int qLeft, int qRight) {
		//범위 밖이면 return 0;
		if (right < qLeft || left > qRight) {
			return 0;
		}
		//left>=qLeft, right<=qRight 이면 return tree[now]
		else if (left >= qLeft && right <= qRight) {
			return tree[now];
		}
		//겹치는 범위면 자식에게 물어보기
		else {
			int mid = (left + right) / 2;
			//왼쪽 자식
			int lVal = query(left, mid, now * 2, qLeft, qRight);
			//오른쪽 자식
			int rVal = query(mid + 1, right, now * 2 + 1, qLeft, qRight);
			return lVal + rVal;
		}
	}
}
