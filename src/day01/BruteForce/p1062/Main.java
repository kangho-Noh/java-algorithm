package day01.BruteForce.p1062;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static BufferedReader br;
	static BufferedWriter bw;

	static final int INF = Integer.MAX_VALUE;
	static int N, K, learned;
	static int ans;
	static final char[] basic = {'a', 'c', 'i', 't', 'n'};
	static List<String> words = new ArrayList<>();

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day01/BruteForce/p1062/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		ans = 0;
		for (int i = 0; i < 5; i++) {
			learned |= 1 << (basic[i] - 'a');
		}
		K -= 5;
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			StringBuilder sb = new StringBuilder(s);
			String substring = sb.substring(4, sb.length() - 4);
			words.add(substring);
		}
		if (K >= 0) {
			dfs(0, learned, 0);
		}
		bw.write(ans + "\n");

		bw.flush();
		bw.close();
		br.close();
	}

	private static void dfs(int depth, int learning, int nowi) {
		if (depth == K) {
			int cnt = 0;
			for (String word : words) {
				boolean canRead = true;
				for (int i = 0; i < word.length(); i++) {
					if ((learning & (1 << (word.charAt(i) - 'a'))) == 0) {
						canRead = false;
					}
				}
				if (canRead)
					cnt++;
			}
			ans = Math.max(ans, cnt);
			return;
		}
		else{
			for (int i = nowi; i < 26; i++) {
				if((learning & (1<<i)) ==0 ){
					dfs(depth+1, learning | (1<<i), i);
				}
			}
		}
	}
}
