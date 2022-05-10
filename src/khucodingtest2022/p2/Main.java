package khucodingtest2022.p2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static BufferedReader br;
	static BufferedWriter bw;

	static final int INF = Integer.MAX_VALUE;
	static int K;
	static String S;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		S = br.readLine();
		K = Integer.parseInt(br.readLine());

		HashMap<String, Boolean> exist = new HashMap<>();
		List<String> list = new ArrayList<>();
		boolean[] alpha = new boolean[26];
		for (int i = 0; i < S.length(); i++) {
			char c = S.charAt(i);
			alpha[c-'a'] = true;
		}

		for (int i = 0; i < 26; i++) {
			if (alpha[i]) {
				for (int j = 0; j < S.length(); j++) {
					if (S.charAt(j)-'a' == i) {
						for (int k = j + 1; k < S.length()+1; k++) {
							String substring = S.substring(j, k);
							if(!exist.containsKey(substring)) {
								list.add(substring);
								exist.put(substring, true);
							}
						}
					}
				}
			}
			if (list.size() > K) {
				break;
			}
		}
		Collections.sort(list);
		bw.write(list.get(K - 1));
		bw.flush();
		bw.close();
		br.close();
	}
}
