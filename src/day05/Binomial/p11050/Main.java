package day05.Binomial.p11050;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static BufferedReader br;
	static BufferedWriter bw;

	static int N, M;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		bw.write(combination(N, M) + "\n");

		bw.flush();
		bw.close();
		br.close();
	}

	private static long combination(int N, int M) {
		if (M == 0 || N == M)
			return 1;
		return combination(N - 1, M - 1)  + combination(N - 1, M);
	}
}
