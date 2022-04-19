package day01.Practice.p13458;

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
	static int n;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());

		int[] students = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			students[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		int chief = Integer.parseInt(st.nextToken());
		int vice = Integer.parseInt(st.nextToken());

		long ans = 0;
		for (int i = 0; i < n; i++) {
			students[i] -= chief;
			ans+=1;
			if (students[i] > 0) {
				ans += students[i]/vice;
				if (students[i] % vice > 0) {
					ans++;
				}
			}
		}
		bw.write(ans + "\n");

		bw.flush();
		bw.close();
		br.close();
	}
}
