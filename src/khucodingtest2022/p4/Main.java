package khucodingtest2022.p4;

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

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String L = st.nextToken();
			String R = st.nextToken();
			long ans = 0;
			for (Long j = Long.parseLong(L); j <= Long.parseLong(R); j++) {
				String s = j.toString();
				int cnt = 0;
				for (int k = 0; k < s.length(); k++) {
					char c = s.charAt(k);
					if(c=='0' || c=='6' || c=='9') cnt++;
					if(c=='8') cnt+=2;
				}
				if(cnt>5) ans++;
			}
			bw.write(ans + "\n");
		}


		bw.flush();
		bw.close();
		br.close();
	}
}
