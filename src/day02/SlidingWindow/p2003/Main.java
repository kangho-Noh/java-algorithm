package day02.SlidingWindow.p2003;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		//int[] arr = new int[N];
		//st = new StringTokenizer(br.readLine());
		// for (int i = 0; i < N; i++) {
		// 	arr[i] = Integer.parseInt(st.nextToken());
		// }
		//윗줄을 아래 한줄로 만들 수 있음.
		int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();



		int low = 0, high = 0;

		//Snm == M, ans++  low++
		//Snm < M, high++
		//Snm > M, low++
		int sum = arr[0];
		int ans = 0;
		while (true) {
			if (sum == M) {
				ans++;
				sum -= arr[low];
				low++;

			} else if (sum < M) {
				high++;
				if (high != N)
					sum += arr[high];
			} else {
				sum -= arr[low];
				low++;
			}
			if (low > high && low != N) {
				high++;
				sum += arr[high];
			}
			if (low == N || high == N)
				break;
		}

		System.out.println(ans);
	}
}
