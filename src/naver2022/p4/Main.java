package naver2022.p4;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		Solution sol = new Solution();
		Point2D[] arr = new Point2D[] {
				new Point2D(0, 0),
				new Point2D(1, 1),
				new Point2D(2, 2),
				new Point2D(3, 3),
				new Point2D(3, 2),
				new Point2D(4, 2),
				new Point2D(5, 1)
		};
		sol.solution(arr);
	}
}

class Point2D {
	public int x;
	public int y;

	public Point2D(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Solution {
	int[][] comb = new int[40][40]; // comb[n][r] == nCr
	final int MAX = (int)1e8;

	public int solution(Point2D[] A) {
		// write your code in Java SE 8

		int ans = 0;
		int N = A.length;
		boolean[][] check = new boolean[N][N]; // check[i][j] -> i랑 j랑 만든 선분을 체크해봤으면 true

		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				if (check[i][j]) //이미 체크한 점끼리는 패스
					continue;
				//두 점으로 선분 만들기
				int x1 = A[i].x;
				int y1 = A[i].y;
				int x2 = A[j].x;
				int y2 = A[j].y;
				int dy = y2 - y1;
				int dx = x2 - x1;
				int gcd = gcd(dy, dx);
				dy /= gcd;
				dx /= gcd;

				//그 선분 위에 점 개수 구하기
				//걔네들끼리 check 해주기
				List<Integer> indices = new ArrayList<>(); //check = true해주기 위함
				indices.add(i);
				indices.add(j);
				int triplets = 0;
				for (int k = 0; k < N; k++) {
					if (k == i || k == j)
						continue;
					int x3 = A[k].x;
					int y3 = A[k].y;
					int dy2 = y3 - y2;
					int dx2 = x3 - x2;
					int gcd2 = gcd(dy2, dx2);
					dy2 /= gcd2;
					dx2 /= gcd2;

					if ((dy == dy2 && dx == dx2) || (dy == -dy2 && dx == -dx2)) { //한 선분 위에 있을 조건 : dy dx가 같음.
						triplets++;
						indices.add(k);
					}
				}
				for (Integer ind : indices) {
					for (Integer ind2 : indices) {
						check[ind][ind2] = true;
					}
				}
				//(점개수)Combination3으로 조합 구하고 ans에 더하기
				if (triplets == 0) //없을 경우
					continue;
				if (triplets > 38) { //점이 너무 많을 경우 1억 초과, combination에서 seg fault 발생
					ans = MAX + 1;
					break;
				}
				int points = triplets + 2;
				ans += combination(points, 3); //points C 3
			}
			if (ans > MAX) {
				break;
			}
		}

		if (ans > MAX)
			return -1;
		return ans;
	}

	public int combination(int n, int r) { // 조합 구하기
		if (n == r || r == 0) {
			return 1;
		} else if (comb[n][r] != 0) {
			return comb[n][r];
		} else {
			return comb[n][r] =
					Math.min(MAX + 1, combination(n - 1, r - 1) + combination((n - 1), r));
		}
	}

	public int gcd(int a, int b) { //최대공약수
		int r;
		a = Math.abs(a);
		b = Math.abs(b);
		while (b != 0) {
			r = a % b;
			a = b;
			b = r;
		}
		return a;
	}

}

