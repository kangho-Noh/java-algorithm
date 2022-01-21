package day05.Combinatorial.p1722;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static BufferedReader br;
	static BufferedWriter bw;

	static int N;
	static List<Integer> list = new ArrayList<>();
	static long[] dpFactorial = new long[21];
	static int[] nums;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day05/Combinatorial/p1722/input1.txt"));
		// System.setIn(new FileInputStream("src/day05/Combinatorial/p1722/input2.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		for (int i = 1; i <= N; i++) { // n==4면 {1,2,3,4}
			list.add(i);
		}

		st = new StringTokenizer(br.readLine());
		int C = Integer.parseInt(st.nextToken());
		if (C == 1) {
			long k = Long.parseLong(st.nextToken());
			//k번째 순열을 찾아서 출력
			//가장 앞에 하나를 확정시킴
			//제외한 공간에서 나머지 순열의 개수를 구함
			//그 개수가 k보다 크거나 같으면 들어가고, 아니면 가장 앞의 것을 다음 것으로 바꿈
			findAnswer(k, N);
		} else {
			nums = new int[N];
			for (int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			long count = 0;
			findK(count);
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static void findK(long count) throws IOException {

		//nums[0]부터 [N-1]까지 확인하면서
		//list에서 nums[i]를 찾을 때까지 거친 숫자 개수 * 그 칸 제외 팩토리얼 + 해주면 될듯

		for (int i = 0; i < nums.length; i++) {
			int curInt = nums[i];
			for (int j = 0; j < list.size(); j++) {
				Integer curListInt = list.get(j);
				if (curListInt == curInt) {
					count += j * factorial(list.size() - 1);
					list.remove(j);
					break;
				}
			}
		}
		bw.write(count + 1 + "\n");
	}

	private static void findAnswer(long k, int space) throws IOException {
		if (list.size() == 1) {
			bw.write(list.get(0) + "\n");
			return;
		}
		for (int i = 0; i < list.size(); i++) {
			//앞에 nowInt 하나를 확정시킨 상태
			Integer nowInt = list.get(i);
			long cases = factorial(space - 1) * (i + 1);
			if (cases >= k) {
				//숫자 확정 후 다음 칸으로 이동
				list.remove(i);
				bw.write(nowInt + " ");
				// 1이 맞으면 k
				// 2가 맞으면 k-cases
				// 3이 맞으면 k-cases*2 ...
				findAnswer(k - factorial(space - 1) * i, space - 1);
				break;
			}
		}
	}

	private static long factorial(int param) {
		if (param == 0)
			return 1;
		if (dpFactorial[param] != 0)
			return dpFactorial[param];
		return (dpFactorial[param] = param * factorial(param - 1));
	}
}
