package day01.Sort.p1713;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Answer {

	static int N, K;
	static Person[] people;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day01/Sort/p1713/input1.txt"));
		//
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		K = sc.nextInt();

		people = new Person[101];

		List<Person> list = new ArrayList<>();
		for (int k = 0; k < K; k++) {
			int num = sc.nextInt();
			if (people[num] == null) {
				// 아직 한번도 호출되지 않음
				people[num] = new Person(num, 0, 0, false);
			}
			//사진판에 있는 경우 -> count ++;
			if (people[num].isIn) {
				people[num].count++;
			}
			//사진판에 없는 경우 -> 하나 골라서, 제거 후 새 후보 추가
			else{
				if (list.size() == N) {
					Collections.sort(list);
					Person p = list.remove(0);
					p.isIn = false;
				}
				people[num].count = 1;
				people[num].isIn = true;
				people[num].timestamp = k;
				list.add(people[num]);
			}
		}

		Collections.sort(list, Comparator.comparingInt(o -> o.num));
		for (Person person : list) {
			System.out.print(person.num+ " ");
		}
		System.out.println();
	}
}

class Person implements Comparable<Person>{
	int num;
	int count;
	int timestamp;
	boolean isIn;

	public Person(int num, int count, int timestamp, boolean isIn) {
		this.num = num;
		this.count = count;
		this.timestamp = timestamp;
		this.isIn = isIn;
	}

	@Override
	public String toString() {
		return "{" +
			"num = " + num +
			", count = " + count +
			", timestamp = " + timestamp +
			"}";
	}

	@Override
	public int compareTo(Person o) {
		// if(count<o.count) return 1;
		// if(count == o.count && timestamp < o.timestamp) return 1;
		// return 0;
		int comp1 = Integer.compare(count, o.count);
		if (comp1 == 0) {
			comp1 = Integer.compare(timestamp, o.timestamp);
		}
		return comp1;
	}
}
