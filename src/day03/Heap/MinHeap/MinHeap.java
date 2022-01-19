package day03.Heap.MinHeap;

import java.util.ArrayList;
import java.util.List;

public class MinHeap {
	List<Integer> list;

	public MinHeap() {
		list = new ArrayList<>();
		list.add(0);
	}

	public void insert(int val) {
		//1. leaf 마지막에 삽입
		list.add(val);

		int current = list.size() - 1;
		int parent = current / 2;

		//2. 부모와 비교 후 조건에 맞지 않으면 Swap
		//3. 조건이 만족되거나 root까지 반복
		while (true) {
			if (parent == 0 || list.get(parent) <= list.get(current)) {
				break;
			}

			int temp = list.get(parent);
			list.set(parent, list.get(current));
			list.set(current, temp);

			current = parent;
			parent = current / 2;

		}

	}

	public int delete() {

		if (list.size() == 1) {
			return 0;
		}

		//1. root에 leaf 마지막 데이터를 가져옴
		int top = list.get(1);
		list.set(1, list.get(list.size() - 1));
		list.remove(list.size() - 1);

		//2. 자식과 비교 후 조건이 맞지 않으면 swap
		//3. 조건이 만족되거나 leaf까지 반복
		int currentPosition = 1;
		while (true) {
			int leftPosition = currentPosition * 2;
			int rightPosition = currentPosition * 2 + 1;

			//leaf node 찾기
			//왼쪽 자식이 없는 경우 내가 leaf
			if (leftPosition >= list.size()) {
				break;
			}
			//왼쪽 자식이 존재. 오른쪽도 확인하는 과정
			int minValue = list.get(leftPosition);
			int minPos = leftPosition;

			//오른쪽 자식이 존재하고, 왼쪽 자식보다 작은 값이라면 오른쪽 값으로 치환
			if (rightPosition < list.size() && list.get(rightPosition) < minValue) {
				minValue = list.get(rightPosition);
				minPos = rightPosition;
			}

			//swap
			if (list.get(currentPosition) > minValue) {
				int temp = list.get(currentPosition);
				list.set(currentPosition, list.get(minPos));
				list.set(minPos, temp);
				currentPosition = minPos;
			} else { //더 내려갈 필요 없음
				break;
			}

		}

		return top;

	}
}
