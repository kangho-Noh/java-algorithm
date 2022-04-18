package day01.BFS.p13460;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static BufferedReader br;
	static BufferedWriter bw;

	static final int INF = Integer.MAX_VALUE;
	static int n, m;
	static char[][] board;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1}; //위오아왼

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day01/BFS/p13460/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		board = new char[n][m];
		int by = 0, bx = 0, ry = 0, rx = 0, hy = 0, hx = 0;
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < m; j++) {
				board[i][j] = s.charAt(j);
				if (board[i][j] == 'B') {
					by = i;
					bx = j;
				} else if (board[i][j] == 'R') {
					ry = i;
					rx = j;
				} else if (board[i][j] == 'O') {
					hy = i;
					hx = j;
				}
			}
		}

		int ans = 11;

		//굴렸을 때 변화없으면 큐에 넣지않음
		//굴렸던 방향, 굴린 후 반대방향은 넣지 않음
		//굴린 후 두 구슬의 위치가 같으면 원래 위치랑 비교해서 더 많이 이동한 놈을 하나 덜 이동시키도록.
		//큐에 넣을 정보: 공 좌표, 몇번째인지 카운트, 움직일 방향
		Queue<Node> queue = new ArrayDeque<>();
		for (int i = 0; i < 4; i++) {
			queue.add(new Node(ry, rx, by, bx, i, 1));
		}

		while(!queue.isEmpty()){
			Node now = queue.poll();
			int[] redNext = findWall(now.redy, now.redx, now.direction);
			int[] blueNext = findWall(now.bluey, now.bluex, now.direction);
			//실패한 경우 (blue구슬 도착)
			if(blueNext[0] == -1 && blueNext[1] == -1) continue;
			else{
				//성공한 경우
				if(redNext[0] == -1 && redNext[1] == -1){
					ans = Math.min(ans, now.count);
					continue;
				}
			}
			moveIfSame(now, redNext, blueNext);
			//큐에 넣음 이동방향, 반대방향 제외한 두 방향
			//count가 10을 넘지 않을때까지
			//만약 이동 후와 전이 같으면 할필요 없음
			if(now.count==10) continue;
			if(redNext[0] == now.redy && redNext[1] == now.redx
				&& blueNext[0] == now.bluey && blueNext[1] == now.bluex) continue;
			queue.add(new Node(redNext[0], redNext[1], blueNext[0], blueNext[1],
				(now.direction+1)%4, now.count+1));
			queue.add(new Node(redNext[0], redNext[1], blueNext[0], blueNext[1],
				(now.direction+3)%4, now.count+1));
		}
		if(ans==11){
			bw.write(-1+"\n");
		}else{
			bw.write(ans+"\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}

	private static void moveIfSame(Node now, int[] redNext, int[] blueNext) {
		if(redNext[0] == blueNext[0] && redNext[1] == blueNext[1]){
			//둘이 같은 선상
			if(now.direction ==0){
				//위쪽 방향이면, 원래 y가 더 큰 구슬이 nexty+=1
				if(now.redy> now.bluey){
					redNext[0]+=1;
				}else{
					blueNext[0]+=1;
				}
			}else if(now.direction == 1){
				//원래 x가 더 작은 구슬이 nextx-=1
				if(now.redx< now.bluex){
					redNext[1]-=1;
				}else{
					blueNext[1]-=1;
				}
			}else if(now.direction == 2){
				//원래 y가 더 작은 구슬이 nexty-=1
				if(now.redy < now.bluey){
					redNext[0]-=1;
				}else{
					blueNext[0]-=1;
				}
			}else if(now.direction == 3){
				//원래 x가 더 큰 구슬이 nextx+=1
				if(now.redx> now.bluex){
					redNext[1]+=1;
				}else{
					blueNext[1]+=1;
				}
			}
		}
	}

	private static class Node {
		int redy, redx, bluey, bluex, direction, count;

		Node(int ry, int rx, int by, int bx, int d, int c) {
			redy = ry;
			redx = rx;
			bluey = by;
			bluex = bx;
			direction = d;
			count = c;
		}
	}

	private static int[] findWall(int y, int x, int d) {
		int ny = y, nx = x;
		while (true) {
			if (board[ny + dy[d]][nx + dx[d]] != '.'
				&& board[ny + dy[d]][nx + dx[d]] != 'R' && board[ny + dy[d]][nx + dx[d]] != 'B') {
				if(board[ny + dy[d]][nx + dx[d]] == 'O'){
					//골인
					return new int[] {-1,-1};
				}
				break;
			}
			ny += dy[d];
			nx += dx[d];
		}
		return new int[] {ny, nx};
	}
}
