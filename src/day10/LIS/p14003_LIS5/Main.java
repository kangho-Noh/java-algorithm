package day10.LIS.p14003_LIS5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static BufferedReader br;
	static BufferedWriter bw;

	static final int INF = Integer.MAX_VALUE;
	static int N;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day10/LIS/p14003_LIS5/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int[] nums = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		List<Integer> LIS = new ArrayList<>(); //LIS가 들어갈 배열
		int[] dp = new int[N + 1]; // 인덱스가 들어갈 배열

		int ans = 1;
		LIS.add(nums[1]);
		dp[1] = 1;
		for (int i = 2; i <= N; i++) {
			if (LIS.get(LIS.size() - 1) < nums[i]) {
				LIS.add(nums[i]);
				dp[i] = LIS.size();
			} else {
				int index = binarySearch(LIS, nums[i]);
				LIS.set(index, nums[i]);
				dp[i] = index + 1;
			}
			ans = Math.max(ans, dp[i]);
		}

		LIS.clear();
		int ind = ans;
		for (int i = N; i > 0; i--) {
			if (ind == dp[i]) {
				LIS.add(nums[i]);
				ind--;
			}
		}
		Collections.reverse(LIS);

		bw.write(ans + "\n");
		for (Integer li : LIS) {
			bw.write(li + " ");
		}
		bw.write("\n");

		bw.flush();
		bw.close();
		br.close();
	}

	private static int binarySearch(List<Integer> lis, int num) {
		int l = 0, r = lis.size();
		while (l < r) {
			int mid = (l + r) / 2;
			if (lis.get(mid) < num) {
				l = mid + 1;
			} else {
				r = mid;
			}
		}
		return l;
	}
}
