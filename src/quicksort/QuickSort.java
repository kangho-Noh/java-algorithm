package quicksort;

public class QuickSort {
	private static void quickSort(int[] arr){
		//배열을 받아 재귀함수 호출
		quickSort(arr, 0, arr.length - 1);
	}

	private static void quickSort(int[] arr, int start, int end) {
		int part2 = partition(arr, start, end); // 주어진 범위 내부의 파티션을 나누고, 오른쪽 파티션의 첫번째 인덱스를 리턴으로 받아옴.
		if(start < part2 - 1){ //만약, 오른쪽 파티션의 첫번째 값 part2가 start 바로 다음이면, 왼쪽은 하나라는 의미. 즉 정렬을 할 필요가 없음. 따라서 오른쪽 파티션만 재귀호출
			quickSort(arr, start, part2-1);
		}
		if (part2 < end) { // part2 == end라는 의미는 오른쪽 파티션에 하나만 있다는 거니까, 그 땐 오른쪽 파티션 재귀호출을 하면 안됨.
			quickSort(arr, part2, end);
		}
	}

	private static int partition(int[] arr, int start, int end) {
		int pivot = arr[(start + end) / 2]; //중간값을 피벗으로 설정
		while(start<=end){
			while(arr[start] < pivot) start++;
			while(arr[end] > pivot) end --;
			if(start <= end){
				swap(arr, start, end);
				start++;
				end--;
			}
		}
		return start; //두번째 파티션의 첫번째 인덱스가 들어가게 됨.
	}

	private static void swap(int[] arr, int start, int end) {
		int tmp = arr[start];
		arr[start] = arr[end];
		arr[end] = tmp;
	}

	private static void printArray(int[] arr) {
		for (int data : arr) {
			System.out.print(data+", ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] arr = {3, 9, 4, 7, 5, 0, 1, 6, 8, 2};
		printArray(arr);
		quickSort(arr);
		printArray(arr);
	}
}
