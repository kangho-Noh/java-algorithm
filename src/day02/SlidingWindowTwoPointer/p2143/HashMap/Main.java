package day02.SlidingWindowTwoPointer.p2143.HashMap;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		HashMap<Long, Long> mapA = new HashMap<Long, Long>();
		for (int i = 1; i <= N; i++) {
			long temp = 0;
			for (int j = i; j <= N; j++) {
				temp += A[j];
				if (mapA.containsKey(temp)) {
					long value = mapA.get(temp);
					mapA.replace(temp, value, value + 1);
				} else {
					mapA.put(temp, 1L);
				}
			}
		}

		int M = Integer.parseInt(br.readLine());
		int[] B = new int[M + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
		HashMap<Long, Long> mapB = new HashMap<Long, Long>();
		for (int i = 1; i <= M; i++) {
			long temp = 0;
			for (int j = i; j <= M; j++) {
				temp += B[j];
				if (mapB.containsKey(temp)) {
					long value = mapB.get(temp);
					mapB.replace(temp, value, value + 1);
				} else {
					mapB.put(temp, 1L);
				}
			}
		}

		long answer = 0;
		for (Entry<Long, Long> entry : mapA.entrySet()) {
			long key = entry.getKey();
			long remain = T - key;
			long bValue = 0;
			if (mapB.containsKey(remain)) {
				bValue = mapB.get(remain);
			}
			long aValue = entry.getValue();
			answer += aValue * bValue;
		}

		bw.write(answer + "");

		bw.flush();
		bw.close();
		br.close();
	}

}