package codetest.skt.p2;

public class Main {
	public int[][] solution(int n, boolean clockwise) {
		int[][] answer = new int[n][n];
		int[] ny = {0, 0, n - 1, n - 1};
		int[] nx = {0, n - 1, 0, n - 1};
		if (clockwise) {
			// 오른쪽으로 꺾기
			boolean done = false;
			int diry[] = {0, 1, -1, 0};
			int dirx[] = {1, 0, 0, -1};
			int num = 1;
			while (!done) {
				int cnt = 0;
				for (int i = 0; i < 4; i++) {
					answer[ny[i]][nx[i]] = num;
					if (answer[ny[i] + diry[i]][nx[i] + dirx[i]] > 0) {
						cnt++;
						//이미 채워 졌으면 방향을 오른쪽으로 튼다
						if(diry[i] == 0 && dirx[i] == 1){
							diry[i] = 1;
							dirx[i] = 0;
						}else if(diry[i] == 0 && dirx[i] == -1){
							diry[i] = -1;
							dirx[i] = 0;
						}else if(diry[i] == 1 && dirx[i] == 0){
							diry[i] = 0;
							dirx[i] = -1;
						}else if(diry[i] == -1 && dirx[i] == 0){
							diry[i] = 0;
							dirx[i] = 1;
						}
					}
					ny[i] += diry[i];
					nx[i] += dirx[i];
				}
				num++;
				done = true;
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						if(answer[i][j]==0) {
							done=false;
							break;
						}
					}
				}
			}

		} else {
			//왼쪽으로 꺾기
			boolean done = false;
			int diry[] = {1, 0, 0, -1};
			int dirx[] = {0, -1, 1, 0};
			int num = 1;
			while (!done) {
				int cnt = 0;
				for (int i = 0; i < 4; i++) {
					answer[ny[i]][nx[i]] = num;
					if (answer[ny[i] + diry[i]][nx[i] + dirx[i]] > 0) {
						cnt++;
						//이미 채워 졌으면 방향을 오른쪽으로 튼다
						if(diry[i] == 0 && dirx[i] == 1){
							diry[i] = -1;
							dirx[i] = 0;
						}else if(diry[i] == 0 && dirx[i] == -1){
							diry[i] = 1;
							dirx[i] = 0;
						}else if(diry[i] == 1 && dirx[i] == 0){
							diry[i] = 0;
							dirx[i] = 1;
						}else if(diry[i] == -1 && dirx[i] == 0){
							diry[i] = 0;
							dirx[i] = -1;
						}
					}
					ny[i] += diry[i];
					nx[i] += dirx[i];
				}
				num++;
				done = true;
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						if(answer[i][j]==0) {
							done=false;
							break;
						}
					}
				}
			}


		}

		return answer;
	}
}
