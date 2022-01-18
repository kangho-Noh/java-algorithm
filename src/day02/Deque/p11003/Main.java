package day02.Deque.p11003;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

import javafx.util.Pair;

public class Main {

	static int N, L;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine(), " ");

		int data[] = new int[N];

		Deque<Integer> dq = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			data[i] = Integer.parseInt(st.nextToken());
			while (!dq.isEmpty() && data[dq.getLast()] > data[i])
				dq.removeLast();
			dq.addLast(i);
			if (!dq.isEmpty() && dq.getFirst() <= i - L)
				dq.removeFirst();
			sb.append(data[dq.getFirst()]).append(" ");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
