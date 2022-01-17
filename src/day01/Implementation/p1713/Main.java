package day01.Implementation.p1713;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day01/Implementation/p1713/input2.txt"));
		//

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); //사진 틀의 개수
		int M = Integer.parseInt(br.readLine()); // 총 추천 횟수

		int[] photos = new int[N]; // 사진틀
		int[] star = new int[101]; // 추천수
		int[] old = new int[101]; // 사진틀에 등재된 시간

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int now = Integer.parseInt(st.nextToken()); //지금 추천된 사람

			//비어있는 사진틀이 있는지, 이미 사진틀에 있는 사람인지
			boolean cont = false;
			for (int j = 0; j < N; j++) {
				if (now == photos[j]) {
					//이미 사진틀에 있음
					star[now]++;
					cont = true;
					break;
				} else if (photos[j] == 0) {
					//비어있는 사진틀
					photos[j] = now;
					star[now] = 1;
					old[now] = i;
					cont = true;
					break;
				}
			}
			if (cont)
				continue;
			// 추천수가 적고 가장 오래된 누군가를 대체해서 새로 등재해야 할 때
			int stars = 1001;
			int changePhotoIndex = -1; //바뀔 사진틀의 인덱스
			int howOld = 1001;
			for (int j = 0; j < N; j++) {
				int here = photos[j];
				if (stars > star[here]) {
					changePhotoIndex = j;
					stars = star[here];
					howOld = old[here];
				}
				if (stars == star[here]) {
					if (old[here] < howOld) { //추천수가 같지만 더 오래됨
						changePhotoIndex = j;
						stars = star[here];
						howOld = old[here];
					}
				}
			}
			int target = photos[changePhotoIndex];
			star[target] = 0;
			photos[changePhotoIndex] = now;//후보교체
			star[now] = 1;
			old[now] = i;

		}

		Arrays.sort(photos);
		for (int i = 0; i < photos.length; i++) {

			int photo = photos[i];
			if (photo == 0)
				continue;
			System.out.print(photo);
			System.out.print(' ');
		}
		System.out.println();
	}
}
