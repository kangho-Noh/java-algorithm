package day03.Heap.MinHeap.p1927;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static StringTokenizer st;
	static BufferedReader br;
	static BufferedWriter bw;
	static PriorityQueue<Integer> pq = new PriorityQueue<>();

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			if (num == 0) {
				if (pq.isEmpty()) {
					bw.write(0 + "\n");
				}else{
					bw.write(pq.peek() + "\n");
					pq.poll();
				}
			}else{
				pq.add(num);
			}
		}

		bw.flush();
		bw.close();
	}
}
