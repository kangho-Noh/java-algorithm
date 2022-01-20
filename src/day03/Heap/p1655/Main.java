package day03.Heap.p1655;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;

	static int N;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day03/Heap/p1655/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());

		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();

		for (int i = 0; i < N; i++) {
			int a = Integer.parseInt(br.readLine());
			int maxHeapSize = maxHeap.size();
			int minHeapSize = minHeap.size();
			if (maxHeapSize == 0 && minHeapSize == 0) {
				maxHeap.add(a);
				bw.write(a + "\n");
				continue;
			}
			if (minHeapSize == 0) {
				if (a > maxHeap.peek()) {
					minHeap.add(a);
				} else {
					minHeap.add(maxHeap.remove());
					maxHeap.add(a);
				}
				bw.write(maxHeap.peek() + "\n");
				continue;
			}
			//두 힙의 개수를 똑같이 유지해야 함 홀수일 경우 maxHeapSize가 하나 더 크게
			//정답을 출력할 때에는 maxHeap.peek()
			//maxHeapSize> minHeapSize,
			//   a>=maxHeap.peek() -> minHeap.add(a);
			//   a<maxHeap.peek()
			//     -> minHeap.add(maxHeap.remove()), maxHeap.add(a);
			//maxHeapSize == minHeapSize,
			//   a>minHeap.peek()
			//     -> maxHeap.add(minHeap.remove()), minHeap.add(a);
			//   a<=minHeap.peek()
			//     -> maxHeap.add(a);
			if (maxHeapSize > minHeapSize) {
				if (a >= maxHeap.peek()) {
					minHeap.add(a);
				} else {
					minHeap.add(maxHeap.remove());
					maxHeap.add(a);
				}
			} else {
				if (a > minHeap.peek()) {
					maxHeap.add(minHeap.remove());
					minHeap.add(a);
				} else {
					maxHeap.add(a);
				}
			}
			bw.write(maxHeap.peek() + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}
}
