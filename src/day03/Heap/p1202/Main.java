package day03.Heap.p1202;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static BufferedReader br;
	static BufferedWriter bw;

	static int N, K;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day03/Heap/p1202/input2.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		PriorityQueue<Integer> bags = new PriorityQueue<>(); //가방 작은 순
		PriorityQueue<Jewelry> pq = new PriorityQueue<>(); //보석 가격 내림차 순
		List<Jewelry> jewelries = new ArrayList<>(); //보석 무게 오름차순

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			Jewelry jewelry = new Jewelry(M, V);
			jewelries.add(jewelry);
		}

		Collections.sort(jewelries,
			Comparator.comparing(Jewelry::getWeight)); //무게 오름차순 정렬

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int C = Integer.parseInt(st.nextToken());
			bags.add(C);
		}

		long ans = 0;
		int jewelInd = 0;
		//가장 작은 가방 선택
		//가능한 보석 중 가장 비싼 것 넣기
		while (!bags.isEmpty()) {
			int bag = bags.remove();
			while (jewelInd < N) {
				Jewelry jewel = jewelries.get(jewelInd);
				int jewelWeight = jewel.weight;
				if (bag >= jewelWeight) {
					pq.add(jewel);
					jewelInd++;
				} else {
					break;
				}
			}
			if(!pq.isEmpty()){
				ans += pq.remove().price;
			}
		}

		System.out.println(ans);

	}
}

class Jewelry implements Comparable<Jewelry> {

	int weight;
	int price;

	public int getWeight() {
		return weight;
	}

	public int getPrice() {
		return price;
	}

	Jewelry(int weight, int price) {
		this.weight = weight;
		this.price = price;
	}

	// 비싼 가격 순 (가격 내림차 순)
	@Override
	public int compareTo(Jewelry o) {
		int comp1 = Integer.compare(o.price, price);
		return comp1;
	}
}
