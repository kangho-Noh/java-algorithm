package day07.Articulation.Point.p11400;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static BufferedReader br;
	static BufferedWriter bw;

	static int V, E;
	static ArrayList<Integer>[] adjList; //인접 리스트
	static int[] searchOrder; //각 정점의 탐색 순서
	static boolean[] isArticulation; // 단절점인지 담는 배열
	static int order; //방문 순서

	/**
	 * 단절점 찾기 문제
	 * 1) root일 경우
	 *    2 <= child, A는 단절점
	 *    2 > child, A는 단절점이 아님
	 *    child는 정점의 자식 트리 수
	 * 2) root가 아닐 경우
	 *    order <= low, A는 단절점
	 *    order > low, A는 단절점이 아님 (우회로가 있다)
	 *    order는 정점을 방문한 순서,
	 *    low는 해당 정점 이후에 방문한 정점 중에 V를 거치지 않고 갈 수 있는 모든 정점의 min order
	 *    low의 초기값은 자신의 order
	 */

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day07/Articulation/Point/p11400/input.txt"));

		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		adjList = new ArrayList[V + 1];
		for (int i = 0; i < V + 1; i++) {
			adjList[i] = new ArrayList<>();
		}
		searchOrder = new int[V + 1];
		isArticulation = new boolean[V + 1];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			adjList[a].add(b);
			adjList[b].add(a);
		}

		order = 0;

		findArticulationPoint();

		int answer = 0;
		for (int i = 1; i < V + 1; i++) {
			if (isArticulation[i]) {
				answer++;
			}
		}
		bw.write( answer + "\n");
		for (int i = 1; i < V + 1; i++) {
			if (isArticulation[i]) {
				bw.write(i + " ");
			}
		}
		bw.write("\n");

		bw.flush();
		bw.close();
		br.close();
	}

	private static void findArticulationPoint() {
		for (int i = 1; i <= V; i++) {
			if (searchOrder[i] == 0) {
				dfs(i, true);
			}
		}
	}

	private static int dfs(int now, boolean isRoot) {
		searchOrder[now] = ++order;
		int ret = order; // 상위 노드에게 리턴할 low 값
		int child = 0; //자식 트리 수

		for (int next : adjList[now]) {
			if (searchOrder[next] == 0) {
				child++;
				int low = dfs(next, false);

				if (!isRoot && searchOrder[now] <= low) {
					isArticulation[now] = true;
				}
				ret = Math.min(ret, low);
			} else {
				ret = Math.min(ret, searchOrder[next]);
			}
		}

		if (isRoot && child >= 2) {
			isArticulation[now] = true;
		}

		return ret;
	}
}
