package day03.Stack.p10828;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static Stack<Integer> stack = new Stack<>();
	static StringTokenizer st;
	static BufferedReader br;
	static BufferedWriter bw;


	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day03/Stack/p10828/input1.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			doCommand();
		}
		bw.flush();
		bw.close();
	}

	private static void doCommand() throws IOException {
		String command = st.nextToken();
		if(command.equals("push")){
			int num = Integer.parseInt(st.nextToken());
			stack.push(num);
		}else if(command.equals("pop")){
			if (stack.empty()) {
				bw.write(-1 +"\n");
			}else{
				bw.write(stack.pop() +"\n");
			}
		}else if(command.equals("size")){
			bw.write(stack.size() +"\n");

		}else if(command.equals("empty")){
			if(stack.empty()){
				bw.write(1 +"\n");
			}else{
				bw.write(0 +"\n");
			}

		}else if(command.equals("top")){
			if (stack.empty()) {
				bw.write(-1 +"\n");
			}else{
				bw.write(stack.peek() +"\n");
			}
		}
	}
}
