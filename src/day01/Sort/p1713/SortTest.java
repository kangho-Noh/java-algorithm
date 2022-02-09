package day01.Sort.p1713;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortTest {
	public static void main(String[] args) {
		Item item1 = new Item(3,3,1);
		Item item2 = new Item(1,2,3);
		Item item3 = new Item(1,1,2);

		List<Item> list = new ArrayList<>();

		list.add(item1);
		list.add(item2);
		list.add(item3);

		System.out.println("list = " + list);

		// 1. @Override한 compareTo에 의해
		Collections.sort(list);

		System.out.println("list = " + list);

		// 2. Comparator.comparing 함수에 의해
		Collections.sort(list, Comparator.comparingInt(o -> o.c));

		System.out.println("list = " + list);

		// 3. getter를 사용하여 소팅
		Collections.sort(list,
			Comparator.comparing(Item::getB).reversed().thenComparing(Item::getA));

		System.out.println("list = " + list);

	}

}

class Item implements Comparable<Item> {


	int a;
	int b;
	int c;

	public int getA() {
		return a;
	}

	public int getB() {
		return b;
	}

	public int getC() {
		return c;
	}
	public Item(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	@Override
	public String toString() {
		return "( a = " + a + ", b = " + b + ", c = " + c+" )";
	}

	// 음수 : -
	// 양수 : Swap
	// 0 : -
	@Override
	public int compareTo(Item o) {
		// 오름차순
		return Integer.compare(a, o.a);
		//아래와 같다.
		// if(a < o.a){
		// 	return -1;
		// } else if(a == o.a){
		// 	return 0;
		// } else {
		// 	return 1;
		// }

		// 내림차순
		// return Integer.compare(o.a, a);

	}
}
