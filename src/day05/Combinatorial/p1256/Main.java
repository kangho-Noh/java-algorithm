package day05.Combinatorial.p1256;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static BufferedReader br;
	static BufferedWriter bw;

	static int N, M, K, total;
	static int[][] pascalTriangle;

	static int[][] dp;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day05/Combinatorial/p1256/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //'a'
		M = Integer.parseInt(st.nextToken()); //'z'
		K = Integer.parseInt(st.nextToken()); //K번째 문자열

		total = N + M;
		dp = new int[total+1][total+1];
		int allCount = combination(N+M,N);
//전체 문자열 개수 N+M C N개
		if (K <= allCount)
			query(N, M, K);
		else
			bw.write("-1");
		bw.write('\n');
		bw.flush();
		bw.close();
		br.close();
	}

	private static void query(int n, int m, int k) throws IOException {
		//단어 끝에 도달
		if (n + m == 0) {
			return;
		}
		//z만 남은 경우
		if (n == 0) {
			bw.write('z');
			query(n, m - 1, k);
		}
		//a 만 남은 경우
		else if (m == 0) {
			bw.write('a');
			query(n - 1, m, k);
		}
		//a, z 둘다 남은 경우
		else {
			int leftVal = combination(n + m - 1, m); //첫번째 칸을 'a'로 확정했을 때
			if (leftVal >= k) {
				//정답이 왼쪽에 있다
				bw.write('a');
				query(n - 1, m, k);
			} else {
				//정답이 오른쪽에 있다
				bw.write('z');
				query(n, m - 1, k - leftVal); //왼쪽 가짓수를 줄여줘야 함
			}

		}
	}

	public static int combination(int n, int r) {
		if (n == r || r == 0) {
			return 1;
		} else if (dp[n][r] != 0) {
			return dp[n][r];
		} else {
			return dp[n][r] =
				Math.min((int)1e9,
					combination(n - 1, r - 1) + combination((n - 1), r));
		}
	}
}
