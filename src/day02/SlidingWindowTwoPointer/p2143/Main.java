package day02.SlidingWindowTwoPointer.p2143;

import static java.util.Collections.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int T, N, M;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day02/SlidingWindowTwoPointer/p2143/input.txt"));
		//

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		N = Integer.parseInt(br.readLine());
		int[] A = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(st.nextToken()) + A[i - 1];
		}

		M = Integer.parseInt(br.readLine());
		int[] B = new int[M + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			B[i] = Integer.parseInt(st.nextToken()) + B[i - 1];
		}

		List<Integer> subA = new ArrayList<>();
		List<Integer> subB = new ArrayList<>();

		for (int i = 0; i <= N; i++) {
			for (int j = i + 1; j <= N; j++) {
				subA.add(A[j] - A[i]);
			}
		}
		for (int i = 0; i <= M; i++) {
			for (int j = i + 1; j <= M; j++) {
				subB.add(B[j] - B[i]);
			}
		}

		sort(subA);
		sort(subB);

		long ans = 0;

		int iA = 0;
		int iB = subB.size() - 1;
		while (iA < subA.size() && iB >= 0) {
			int a = subA.get(iA);
			int b = subB.get(iB);
			int hap = a + b;
			if (hap == T) {
				int acnt = 0;
				int bcnt = 0;
				while (iA < subA.size() && a == subA.get(iA)) {
					iA++;
					acnt++;
				}
				while (iB >= 0 && b == subB.get(iB)) {
					iB--;
					bcnt++;
				}
				ans += (long)acnt * bcnt;
			} else if (hap < T) {
				iA++;
			} else {
				iB--;
			}
		}

		System.out.println(ans);

	}
}
