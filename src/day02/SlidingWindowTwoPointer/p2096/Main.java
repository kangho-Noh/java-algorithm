package day02.SlidingWindowTwoPointer.p2096;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day02/SlidingWindowTwoPointer/p2096/input2.txt"));
		//

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());

		int Max[] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int Min[] = new int[3];
		Min[0] = Max[0];
		Min[1] = Max[1];
		Min[2] = Max[2];

		for (int i = 1; i < N; i++) {

			int input[] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int temp[] = Min.clone();
			Min[0] = Math.min(temp[0], temp[1]) + input[0];
			Min[1] = Math.min(Math.min(temp[0], temp[1]), temp[2]) + input[1];
			Min[2] = Math.min(temp[1], temp[2]) + input[2];

			temp = Max.clone();
			Max[0] = Math.max(temp[0], temp[1]) + input[0];
			Max[1] = Math.max(Math.max(temp[0], temp[1]), temp[2]) + input[1];
			Max[2] = Math.max(temp[1], temp[2]) + input[2];

		}

		bw.write(Math.max(Math.max(Max[0], Max[1]), Max[2]) +" " + Math.min(Math.min(Min[0], Min[1]), Min[2]) + "\n");
		bw.flush();
		bw.close();
	}
}
