package day03.Stack.p2504;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;

	static boolean correctAnswer = true;
	static Stack<Character> brace;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String input = br.readLine();
		brace = new Stack<>();
		int ans = 0;
		int cnt = 1;
		for (int i = 0; i < input.length(); i++) {
			char now = input.charAt(i);
			if (now == '(') {
				brace.push(now);
				cnt *= 2;
			} else if (now == '[') {
				brace.push(now);
				cnt *= 3;
			} else {
				if (now == ')') {
					if (brace.isEmpty() || brace.peek() != '(') {
						correctAnswer = false;
						break;
					}
					if (input.charAt(i - 1) == '(') {
						ans += cnt;
					}
					brace.pop();
					cnt /= 2;
				} else {
					if (brace.isEmpty() || brace.peek() != '[') {
						correctAnswer = false;
						break;
					}
					if (input.charAt(i - 1) == '[') {
						ans += cnt;
					}
					brace.pop();
					cnt /= 3;

				}
			}
		}

		if (!correctAnswer || !brace.empty()) {
			System.out.println(0);
		} else {
			System.out.println(ans);
		}
	}

}
