package day01.Greedy.p1092;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static BufferedReader br;
	static BufferedWriter bw;

	static int N, M;
	static List<Integer> cranes, boxes;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day01/Greedy/p1092/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		cranes = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			int crane = Integer.parseInt(st.nextToken());
			cranes.add(crane);
		}

		M = Integer.parseInt(br.readLine());
		boxes = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int box = Integer.parseInt(st.nextToken());
			boxes.add(box);
		}

		cranes.sort(Collections.reverseOrder());
		boxes.sort(Collections.reverseOrder());

		if (cranes.get(0) < boxes.get(0)) {
			bw.write(-1 + "\n");
			bw.flush();
			bw.close();
			return;
		}

		int ans = 0;
		while (!boxes.isEmpty()) {
			for (int i = 0; i < N; i++) {
				int crane = cranes.get(i);
				for (int j = 0; j < boxes.size(); j++) {
					int box = boxes.get(j);
					if (box <= crane) {
						boxes.remove(j);
						break;
					}
				}
			}
			ans++;
		}

		bw.write(ans + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
}
