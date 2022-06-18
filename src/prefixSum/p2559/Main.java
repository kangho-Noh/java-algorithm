package prefixSum.p2559;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static BufferedReader br;
	static BufferedWriter bw;

	static final int INF = Integer.MIN_VALUE;
	static int n, k;
	static int[] arr, sum;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		arr = new int[n + 1];
		sum = new int[n + 1];

		st = new StringTokenizer(br.readLine());
		sum[0] = 0;
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sum[i] = arr[i] + sum[i - 1];
		}
		//arr[i] = sum[i]-sum[i-1]
		//sum(i+1~i+k) = sum[i+k]-sum[i]
		int ans = INF;
		for (int i = 0; i + k <= n; i++) {
			ans = Math.max(ans, sum[i + k] - sum[i]);
		}

		bw.write(ans + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
}
