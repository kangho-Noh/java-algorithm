package codetest.devmatching.p1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static BufferedReader br;
	static BufferedWriter bw;

	static final int INF = Integer.MAX_VALUE;
	static int n;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream(""));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());

		bw.flush();
		bw.close();
		br.close();
	}

	public int[][] solution(int[][] dist) {
		int[][] answer = {};

		// 소팅할 값 복사
		List<Item> dist0 = new ArrayList<>();
		for (int i = 0; i < dist[0].length; i++) {
			dist0.add(new Item(i, dist[0][i]));
		}

		Collections.sort(dist0);

		Deque<Integer> deque = new ArrayDeque<>();
		deque.add(0);
		deque.add(dist0.get(1).index);
		int standard = 0;
		int prev = dist0.get(1).index;
		for (int i = 2; i < dist0.size(); i++) {
			int now = dist0.get(i).index;
			if(dist[standard][prev] + dist[standard][now] == dist[now][prev]){
				//반대쪽
				if(deque.getFirst() == prev){
					deque.addLast(now);
				}else{
					deque.addFirst(now);
				}
			}else{
				if(deque.getFirst() == prev){
					deque.addFirst(now);
				}else{
					deque.addLast(now);
				}
			}

			prev = now;
		}
		int[] answer1 = new int[deque.size()];
		int[] answer2 = new int[deque.size()];
		int i=0;
		int j = deque.size()-1;
		for (Integer integer : deque) {
			answer1[i++] = integer;
			answer2[j--] = integer;
		}
		answer = new int[][] {answer1, answer2};

		return answer;
	}
}

class Item implements Comparable<Item> {
	int index;
	int distance;

	public Item(int a, int b) {
		index = a;
		distance = b;
	}

	@Override
	public int compareTo(Item o) {
		int comp = Integer.compare(distance, o.distance);
		return comp;
	}
}
