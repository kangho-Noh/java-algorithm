package day01.BruteForce.p1065;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {


	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int input = Integer.parseInt(br.readLine());
		int ans = 0;
		for(int i=1;i<=input;i++){
			String s = Integer.toString(i);
			boolean hansu = true;
			if(s.length()<3){
				++ans;
				continue;
			}
			int cha = s.charAt(1) - s.charAt(0);
			for (int j = 1; j < s.length()-1; j++) {
				if (cha != s.charAt(j + 1) - s.charAt(j)) {
					hansu = false;
				}
			}
			if (hansu) {
				++ans;
			}
		}
		System.out.println(ans);
	}
}
