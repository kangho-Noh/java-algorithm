package day06.Graph.p11375_열혈강호;

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

	static final int INF = Integer.MAX_VALUE;
	static int N, M;
	static ArrayList<Integer>[] works, people;
	static int[] workCount;
	static int[] cnt;
	static boolean[] matched, working;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day06.Graph.p11375_열혈강호/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //사람의수
		M = Integer.parseInt(st.nextToken()); //일의개수
		works = new ArrayList[M + 1]; //works[i] i번 일을 해결할 수 있는 사람들 리스트
		for (int i = 0; i < M + 1; i++) {
			works[i] = new ArrayList<>();
		}
		people = new ArrayList[N + 1]; //people[i] i사람이 해결할 수 있는 일들 리스트
		for (int i = 0; i < N + 1; i++) {
			people[i] = new ArrayList<>();
		}
		workCount = new int[N + 1]; //workCount[i] i사람이 해결할 수 있는 일의 개수
		cnt = new int[M + 1]; //cnt[i] i번 일을 해결할 수 있는 사람의 수
		matched = new boolean[M + 1]; //matched[i] true면 i번 일은 매칭됨 또는 더이상 매칭불가상태
		working = new boolean[N + 1]; //working[i] i번 사람이 매칭이 완료되었으면 true
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			workCount[i] = n;
			for (int j = 1; j <= n; j++) {
				int work = Integer.parseInt(st.nextToken());
				people[i].add(work);
				works[work].add(i);
				cnt[work]++;
			}
		}
		int ans = 0;
		/**
		 * 1. 모든 일 중에 해결할 수 있는 사람이 가장 적은 일을 뽑는다 (0명 제외)
		 * 2. 그 일을 해결할 수 있는 사람들 중에 해결할 수 있는 일 수가 가장 적은 사람을 뽑는다
		 * 3. 둘을 매칭 시킨다
		 * 4. 더이상 매칭이 불가능할 때까지 반복한다.
		 * 시간초과 날 줄 알았는데,, 맞았습니다 떠서 떨떠름함
		 */
		while (true) {
			int nowWork = -1;
			int minCnt = INF;
			for (int i = 1; i <= M; i++) {
				if (!matched[i] && cnt[i] != 0 && minCnt > cnt[i]) {
					minCnt = cnt[i];
					nowWork = i;
				}
			}
			if (nowWork == -1)
				break;

			int nowPerson = -1;
			int minCnt2 = INF;
			for (int person : works[nowWork]) {
				if (working[person])
					continue;
				if (minCnt2 > workCount[person]) {
					minCnt2 = workCount[person];
					nowPerson = person;
				}
			}

			matched[nowWork] = true;
			working[nowPerson] = true;
			for (int work : people[nowPerson]) {
				cnt[work]--;
			}
			for (int person : works[nowWork]) {
				workCount[person]--;
			}
			ans++;
		}
		bw.write(ans + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
}
