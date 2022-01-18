package day02.BinarySerach.p2805;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] arr;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day02/p2805/input2.txt"));
		//

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		arr = new int[N];
		int maxtree = 0;
		for (int i = 0; i < N; i++) {
			int now = Integer.parseInt(st.nextToken());
			arr[i] = now;
			maxtree = Math.max(maxtree, now);
		}
		int low = 0, high = maxtree;
		int half, ans = 0;
		//half는 절단기의 높이. 가능할 경우 답에 저장하고 높이를 높임
		//답이 가능하지 않으면 절단기의 높이를 낮춤.
		while (low < high) {
			half = (low+high)/2;
			if(isPossible(half)){
				ans = half;
				low = half+1;
			}else{
				high = half;
			}
		}

		System.out.println(ans);
	}

	private static boolean isPossible(int half) {
		long hap = 0;
		for(int i=0;i<N;i++){
			long cha = arr[i] - half;
			if(cha>0){
				hap += cha;
			}
		}
		if(hap>=M) return true;
		return false;
	}
}
