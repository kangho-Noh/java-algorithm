package day04.Gcd.p1735;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static BufferedReader br;
	static BufferedWriter bw;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		int ja1 = Integer.parseInt(st.nextToken());
		int mo1 = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int ja2 = Integer.parseInt(st.nextToken());
		int mo2 = Integer.parseInt(st.nextToken());

		int ja = ja1 * mo2 + ja2 * mo1;
		int mo = mo1 * mo2;

		int gcd = getGcd(ja, mo);
		bw.write(ja / gcd + " " + mo / gcd + "\n");

		bw.flush();
		bw.close();
		br.close();
	}

	private static int getGcd(int ja, int mo) {
		int r = ja % mo;
		while (r != 0) {
			ja = mo;
			mo = r;
			r = ja % mo;
		}
		return mo;
	}
}
