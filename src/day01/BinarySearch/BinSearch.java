package day01.BinarySearch;

public class BinSearch {
	public static void main(String[] args) {
		int[] arr = {0, 5, 19, 22, 25, 30, 300};
		System.out.println(binarySearch(arr, 22)); //3
		System.out.println(binarySearch(arr, 300)); //6
		System.out.println(binarySearch(arr, 0)); //0
		System.out.println(binarySearch(arr, 400)); //-1
		System.out.println(binarySearch(arr, -5000)); //-1
	}

	private static int binarySearch(int[] arr, int target){
		int low = 0, high = arr.length; //[low,high)
		while (low < high) {
			int mid = (low+high) / 2;

			if (arr[mid] == target) {
				return mid;
			} else if (arr[mid] > target) {
				high = mid;
			} else if (arr[mid] < target) {
				low = mid+1;
			}
		}


		return -1; //cant find
	}
}
