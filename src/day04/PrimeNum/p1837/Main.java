package day04.PrimeNum.p1837;

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

	static boolean[] isNotPrime;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day04/PrimeNum/p1837/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());

		String P = st.nextToken();
		int K = Integer.parseInt(st.nextToken());

		//소수 구하기
		isNotPrime = new boolean[K + 1];
		isNotPrime[0] = isNotPrime[1] = true;

		for (int i = 2; i <= Math.sqrt(K); i++) {
			int j = 2;
			while (i * j < K + 1) {
				isNotPrime[i * j] = true;
				j++;
			}
		}

		String ans = "GOOD";
		for (int i = 2; i < K; i++) {
			if (isNotPrime[i])
				continue;
			//소수면 계산해보고 나머지가 0이면 BAD
			if (modular(P, i) == 0) {
				ans = "BAD " + i;
				break;
			}
		}

		bw.write(ans + "\n");

		bw.flush();
		bw.close();
		br.close();
	}

	private static int modular(String p, int q) {
		int num = 0;
		for (int i = 0; i < p.length(); i++) {
			num = num * 10 + p.charAt(i) - '0';
			num %= q;
		}

		return num;
	}
}
