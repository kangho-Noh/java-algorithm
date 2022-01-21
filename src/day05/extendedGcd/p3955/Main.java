package day05.extendedGcd.p3955;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static BufferedReader br;
	static BufferedWriter bw;

	static int T;
	static long A, B;

	public static void main(String[] args) throws Exception {
		// A*X + 1 = B*Y
		// A : 파티에 참여하는 인원 (input K)
		// B : 봉지 하나 당 들어있는 사탕 수 (input C)
		// X : 인당 나눠줄 사탕의 수
		// Y : 사탕 봉지의 수
		// A * x + 1 = B * y
		// Ax + By = C 의 형태로 변환.
		// -Ax + By = 1
		// A(-x) + By = 1 의 형태로 변환.

		// 1. 해 검증
		// D = gcd(A, B) = egcd(A, B).r
		//     -> 확장 유클리드를 갖다 쓰면 됨. egcd(A, B)의 r값
		// Ax + By = C 일 때, C % D == 0 이어야 해를 가질 수 있음 : 베주 항등식
		// 즉, C % D != 0 -> 해가 없음

		// 2. 초기 해 구하기
		// x0 = s *C /D
		// y0 = t * C / D

		// 3. 일반 해 구하기
		// x = x0 + B / D * k
		// y = y0 - A / D * k
		//여기서 D는 1이므로 아래 식에서 생략

		// 4. k의 범위
		// x < 0 (위 식에서 음수로 만들었으니)
		// x0 + B * k < 0
		// k < (-x0) / B

		// 0 < y <= 1e9
		// 0 < y0 - A / k <= 1e9
		// ( y0 - 1e9 ) / A <= k < y0 / A

		// ( y0 - 1e9 ) / A <= k < y0 / A
		//                     k < (-x0) / B
		// 참고) < 일 때, floor가 아닌, ceil 후 -1해야 답이 나옴(최대)
		// <=일떄는 floor쓰면 답 나옴

		//5. 만족하는 k가 있는지 찾고 k를 통해 y를 구한다.

		//
		System.setIn(new FileInputStream("src/day05/extendedGcd/p3955/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			A = Long.parseLong(st.nextToken());
			B = Long.parseLong(st.nextToken());

			// A : 파티에 참여하는 인원 (input K)
			// B : 봉지 하나 당 들어있는 사탕 수 (input C)
			// X : 인당 나눠줄 사탕의 수
			// Y : 사탕 봉지의 수
			// A * x + 1 = B * y
			// Ax + By = C 의 형태로 변환.
			// -Ax + By = 1
			// A(-x) + By = 1 의 형태로 변환.

			// 1. 해 검증
			// D = gcd(A, B) = egcd(A, B).r
			//     -> 확장 유클리드를 갖다 쓰면 됨. egcd(A, B)의 r값
			// Ax + By = C 일 때, C % D == 0 이어야 해를 가질 수 있음 : 베주 항등식
			// 즉, C % D != 0 -> 해가 없음
			// 이 문제에서 C는 1, 따라서 만족하는 D도 1밖에 없다.
			long C = 1;
			EGResult egResult = extendedGcd(A, B);
			long D = egResult.r;
			if (1 % D != 0) {
				bw.write("IMPOSSIBLE\n");
				continue;
			}

			// 2. 초기 해 구하기
			// x0 = s * C / D
			// y0 = t * C / D
			long x0 = egResult.s * C / D;
			long y0 = egResult.t * C / D;

			// 3. 일반 해 구하기
			// x = x0 + B / D * k
			// y = y0 - A / D * k
			//여기서 D는 1이므로 아래 식에서 생략
			// 4. k의 범위
			// x < 0 (위 식에서 음수로 만들었으니)
			// x0 + B * k < 0
			// k < (-x0) / B

			// 0 < y <= 1e9
			// 0 < y0 - A * k <= 1e9
			// ( y0 - 1e9 ) / A <= k < y0 / A

			// ( y0 - 1e9 ) / A <= k < y0 / A
			//                     k < (-x0) / B
			// 참고) < 일 때, floor가 아닌, ceil 후 -1해야 답이 나옴(최대)
			// <=일떄는 floor쓰면 답 나옴

			long k = (long)(Math.ceil(Math.min((double)y0 / A, (double)(-x0) / B)) - 1);
			long ans = y0 - A * k;
			if (k < (y0 - 1e9) || ans > 1e9) {
				bw.write("IMPOSSIBLE\n");
			} else {
				bw.write(y0 - A * k + "\n");
			}
		}

		bw.flush();
		bw.close();
		br.close();
	}

	static EGResult extendedGcd(long a, long b) {
		long s0 = 1, t0 = 0, r0 = a; //표에서 위에꺼. 나눠지는 수
		long s1 = 0, t1 = 1, r1 = b; //표에서 아래꺼

		long temp;
		while (r1 != 0) {
			long q = r0 / r1;

			//r에 대한 연산
			temp = r0 - q * r1; // = r0 % r1
			r0 = r1;
			r1 = temp;

			//그대로 s로 확장
			temp = s0 - q * s1;
			s0 = s1;
			s1 = temp;

			//t로 확장
			temp = t0 - q * t1;
			t0 = t1;
			t1 = temp;
		}//while이 끝나면 r0에 gcd가 남아있다.
		return new EGResult(s0, t0, r0);
	}
}

class EGResult {
	long s;
	long t;
	long r;

	public EGResult(long s, long t, long r) {
		super();
		this.s = s;
		this.t = t;
		this.r = r;
	}

	@Override
	public String toString() {
		return "ExtendedGcdResult: [s=" + s + ", t=" + t + ", gcd=" + r + "]";
	}
}