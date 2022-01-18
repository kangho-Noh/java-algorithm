package day02.SlidingWindowTwoPointer.p1806;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, S, ans = 100001;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N+1];
		arr[0] = 0;
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken()) + arr[i-1];
		}
		int low = 0, high = 1;

		while(high<=N && low <= N){
			int now = arr[high]- arr[low];
			if (now >= S) {
				ans = Math.min(high - low, ans);
				low++;
			} else {
				high++;
			}
		}

		if (ans == 100001)
			System.out.println(0);
		else
			System.out.println(ans);

	}
}
