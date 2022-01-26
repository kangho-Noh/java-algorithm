package day08.DP.p11659_구간합구하기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static BufferedReader br;
	static BufferedWriter bw;

	static final int INF = Integer.MAX_VALUE;
	static int N, M;
	static int[] sum;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day08/DP/p11659_구간합구하기/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N + 1];
		sum = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if(i>0)
				sum[i] = sum[i-1] + arr[i];
			else
				sum[i] = arr[i];
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			bw.write(hap(a,b)+"\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}

	private static int hap(int a, int b) {
		return sum[b]-sum[a-1];
	}
}
