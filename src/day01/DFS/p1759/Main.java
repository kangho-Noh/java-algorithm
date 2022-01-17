package day01.DFS.p1759;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int L, C;
	static char[] arr;
	static StringBuffer ans = new StringBuffer();

	public static void main(String[] args) throws IOException {

		//입력 간편하게 받는 방법
		// System.in을 파일로 변경하는 것임.
		// 제출할 때는 이 줄만 지워서 제출
		System.setIn(new FileInputStream("src/day01/DFS/p1759/input.txt"));

		//입력 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// br.readLine() -> 한 줄 전체 읽기
		// st.nextToken() -> \t\r\n을 기준으로 다음 값 string 으로 읽어오기
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		arr = new char[C];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++)
			arr[i] = st.nextToken().charAt(0);
		Arrays.sort(arr);

		// 재귀함수 호출
		for (int i = 0; i < C; i++)
			dfs(i, 0, "", 0);

		// 값 출력
		System.out.println(ans);
	}

	/**
	 * 1. 체크인
	 * 2. 목적지인가 - 길이, 자음 모음 개수
	 * 3. 연결된 곳을 순회 - 나보다 높은 알파벳
	 * 4. 갈 수 있는지 - 생략 가능
	 * 5. 간다 -
	 * 6. 체크아웃
	 */
	public static void dfs(int now, int depth, String s, int collectionCnt) {
		char nowchar = arr[now];

		//체크인
		++depth;
		if (isCollection(nowchar)) {
			++collectionCnt;
		}
		s += nowchar;
		//목적지인가
		if (depth == L) {
			if (collectionCnt > 0 && L - collectionCnt > 1) {
				ans.append(s);
				ans.append("\n");
			}
			return;
		}
		//연결된 곳을 순회
		for (int i = now + 1; i < C; i++) {
			dfs(i, depth, s, collectionCnt);
		}
	}

	public static boolean isCollection(char c) {
		return (c == 'a') || (c == 'e') || (c == 'i') || (c == 'o') || (c == 'u');
	}
}
