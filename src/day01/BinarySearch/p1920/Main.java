package day01.BinarySearch.p1920;

import static java.util.Arrays.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day01/BinarySearch/p1920/input2.txt"));
		//

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);

		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			int now = Integer.parseInt(st.nextToken());
			int i1 = Arrays.binarySearch(arr, now);
			if (i1 < 0) {
				System.out.println(0);
			} else {
				System.out.println(1);
			}
		}
	}
}
