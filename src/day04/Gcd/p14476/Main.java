package day04.Gcd.p14476;

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

	static int N;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day04/Gcd/p14476/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		int[] LRgcd = new int[N];
		int[] RLgcd = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		LRgcd[0] = arr[0];
		RLgcd[N - 1] = arr[N - 1];
		for (int i = 1; i < N; i++) {
			LRgcd[i] = gcd(LRgcd[i - 1], arr[i]);
			RLgcd[N - i - 1] = gcd(RLgcd[N - i], arr[N - i - 1]);
		}

		int maxGcd = 0;
		int ansK = -1;

		for (int k = 0; k < N; k++) {
			int K = arr[k];
			int left = k - 1;
			int right = k + 1;
			int gcdWithoutK;
			if(0<=left && right<N) {
				gcdWithoutK = gcd(LRgcd[left], RLgcd[right]);
			}else if (left<0){
				gcdWithoutK = RLgcd[right];
			}else{
				gcdWithoutK = LRgcd[left];
			}
			if (K % gcdWithoutK != 0) {
				if(maxGcd<gcdWithoutK){
					maxGcd = gcdWithoutK;
					ansK = K;
				}
			}
		}

		if (ansK == -1)
			bw.write(-1 + "\n");
		else
			bw.write(maxGcd + " " + ansK + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	public static int gcd(int a, int b) {
		int r;
		while (b != 0) {
			r = a % b;
			a = b;
			b = r;
		}
		return a;
	}
}
