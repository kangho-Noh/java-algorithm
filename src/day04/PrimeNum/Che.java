package day04.PrimeNum;

import java.util.ArrayList;
import java.util.List;

public class Che {

	static final int MAX = 1000000;
	static int K;

	static boolean[] isNotPrime;
	static List<Integer> primes = new ArrayList<>();

	public static void main(String[] args) {

		initChe();

	}

	private static void initChe() {
		//소수 만들기
		isNotPrime = new boolean[MAX + 1];
		isNotPrime[0] = isNotPrime[1] = true;
		for (int i = 2; i*i < MAX + 1; i++) {
			if (!isNotPrime[i]) {
				primes.add(i);
				for (int j = i * 2; j <= MAX; j += i) {
					isNotPrime[j] = true;
				}
			}
		}
	}
}
