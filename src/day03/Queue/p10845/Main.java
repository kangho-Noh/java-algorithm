package day03.Queue.p10845;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static Deque<Integer> queue = new ArrayDeque<>();
	static StringTokenizer st;
	static BufferedReader br;
	static BufferedWriter bw;

	public static void main(String[] args) throws Exception {

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
		if (command.equals("push")) {
			int num = Integer.parseInt(st.nextToken());
			queue.add(num);
		} else if (command.equals("pop")) {
			if (queue.isEmpty()) {
				bw.write(-1 + "\n");
			} else {
				bw.write(queue.poll() + "\n");
			}
		} else if (command.equals("size")) {
			bw.write(queue.size() + "\n");

		} else if (command.equals("empty")) {
			if (queue.isEmpty()) {
				bw.write(1 + "\n");
			} else {
				bw.write(0 + "\n");
			}

		} else if (command.equals("front")) {
			if (queue.isEmpty()) {
				bw.write(-1 + "\n");
			} else {
				bw.write(queue.getFirst() + "\n");
			}
		} else if (command.equals("back")) {
			if (queue.isEmpty()) {
				bw.write(-1 + "\n");
			} else {
				bw.write(queue.getLast() + "\n");
			}
		}
	}
}
