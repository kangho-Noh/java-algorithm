package naver2022.example;

import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		int[] A = {-1, 4, 2, 0, 3};
		int[] positive = Arrays.stream(A).filter(a -> a > 0).sorted().toArray();
		int ans = 1;
		for (int i = 0; i < positive.length; i++) {
			if(ans<positive[i]) break;
			else if(ans == positive[i]) ans++;
		}
		System.out.println(ans);
	}

}
