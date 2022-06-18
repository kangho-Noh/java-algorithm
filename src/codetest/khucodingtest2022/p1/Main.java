package codetest.khucodingtest2022.p1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static BufferedReader br;
	static BufferedWriter bw;

	static final int INF = Integer.MAX_VALUE;
	static long n, m;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		n = Long.parseLong(st.nextToken());
		m = Long.parseLong(st.nextToken());

		//0초
		long building = n;
		//일단 Math.min(n-2, m-2)초 동안은 절대 안부셔짐.
		long t = Math.min(n - 2, m - 2); //
		//t초 공격력 t+1
		while (true) {
			t++;
			long attack = t + 1;
			building = Math.min(n, building + m);
			building -= attack;
			if (building <= 0) {
				break;
			}
		}
		bw.write(t + "\n");

		bw.flush();
		bw.close();
		br.close();
	}
}
