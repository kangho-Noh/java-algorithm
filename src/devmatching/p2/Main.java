package devmatching.p2;

public class Main {
	public int solution(String[] grid) {
		//?를 채워서 모든 같은 문자들이 이어져야 하는데, ?에 들어갈 알파벳들의 경우의 수. ?의 최대 개수 9
		int answer = -1;
		//브루트포스?
		int cnt = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length(); j++) {
				char c = grid[i].charAt(j);
				if(c == '?'){
					cnt++;
				}
			}
		}
		//
		return answer;
	}
}
