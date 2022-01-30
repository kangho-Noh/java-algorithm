package day03.Stack.p3425;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Stack;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;

	static final int ABS_INF = Math.abs((int)1e9); //절댓값 최대 넘어가면 에러
	static int N;
	static boolean error;
	static Stack<Integer> stack = new Stack<>();
	static ArrayList<String> commands = new ArrayList<>();
	static ArrayList<Integer> inputs = new ArrayList<>();

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day01/Stack/p3425/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		while (true) {
			//프로그램 명령 입력
			commands.clear();
			while (true) {
				String command = br.readLine();
				if (command.equals("END")) {
					break;
				}
				if (command.equals("QUIT")) {
					bw.flush();
					bw.close();
					br.close();
					return;
				}
				commands.add(command);
			}

			// 입력값 입력
			inputs.clear();
			N = Integer.parseInt(br.readLine());
			for (int i = 0; i < N; i++) {
				inputs.add(Integer.parseInt(br.readLine()));
			}
			br.readLine();

			for (int input : inputs) {
				stack.clear();
				error = false;
				stack.push(input);
				for (String command : commands) {
					switch (command) {
						case "POP":
							doPop();
							break;
						case "INV":
							doInv();
							break;
						case "DUP":
							doDup();
							break;
						case "SWP":
							doSwp();
							break;
						case "ADD":
							doAdd();
							break;
						case "SUB":
							doSub();
							break;
						case "MUL":
							doMul();
							break;
						case "DIV":
							doDiv();
							break;
						case "MOD":
							doMod();
							break;
						default:
							doNum(command);
					}
					if (error)
						break;
				}
				if (error || stack.size() != 1) {
					bw.write("ERROR\n");
				} else {
					bw.write(stack.pop() + "\n");
				}
			}
			bw.write("\n");

		}
	}

	private static void doNum(String command) {
		StringBuilder sb = new StringBuilder(command);
		int a = Integer.parseInt(sb.delete(0, 4).toString());
		stack.push(a);
	}

	private static void doMod() {
		if (stack.size() < 2) {
			error = true;
		} else {
			int a = stack.pop();
			int b = stack.pop();
			//b%a
			if (a == 0) {
				error = true;
				return;
			}
			int result = Math.abs(b) % Math.abs(a);
			if (b < 0) {
				result = -result;
			}
			stack.push(result);
		}
	}

	private static void doDiv() {
		if (stack.size() < 2) {
			error = true;
		} else {
			int a = stack.pop();
			int b = stack.pop();
			//b/a
			if (a == 0) {
				error = true;
				return;
			}
			int result = Math.abs(b / a);
			if ((b < 0 && a > 0) || (b > 0 && a < 0)) {
				result = -result;
			}
			stack.push(result);
		}
	}

	private static void doMul() {
		if (stack.size() < 2) {
			error = true;
		} else {
			int a = stack.pop();
			int b = stack.pop();
			long result = (long)b * a;
			if (Math.abs(result) > ABS_INF) {
				error = true;
			} else
				stack.push((int)result);
		}
	}

	private static void doSub() {
		if (stack.size() < 2) {
			error = true;
		} else {
			int a = stack.pop();
			int b = stack.pop();
			int result = b - a;
			if (Math.abs(result) > ABS_INF) {
				error = true;
				return;
			}
			stack.push(result);
		}
	}

	private static void doAdd() {
		if (stack.size() < 2) {
			error = true;
		} else {
			int a = stack.pop();
			int b = stack.pop();
			int result = a + b;
			if (Math.abs(result) > ABS_INF) {
				error = true;
				return;
			}
			stack.push(result);
		}
	}

	private static void doSwp() {
		if (stack.size() < 2) {
			error = true;
		} else {
			int a = stack.pop();
			int b = stack.pop();
			stack.push(a);
			stack.push(b);
		}
	}

	private static void doDup() {
		if (stack.empty()) {
			error = true;
		} else {
			stack.push(stack.peek());
		}
	}

	private static void doInv() {
		if (stack.empty()) {
			error = true;
		} else {
			stack.push(-stack.pop());
		}
	}

	private static void doPop() {
		if (stack.empty()) {
			error = true;
		} else {
			stack.pop();
		}
	}
}
