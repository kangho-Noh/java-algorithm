package day03.Trie.p9202;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static final int[] DY = {-1, -1, 0, 1, 1, 1, 0, -1};
	static final int[] DX = {0, 1, 1, 1, 0, -1, -1, -1};
	static final int[] score = {0, 0, 0, 1, 1, 2, 3, 5, 11};

	static StringTokenizer st;
	static BufferedReader br;
	static BufferedWriter bw;

	static int N;
	static char[][] board;
	static boolean[][] visited = new boolean[4][4];
	static Trie trie;

	static int maxScore = 0;
	static String longestAnswer = "";
	static int findCount = 0;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day03/Trie/p9202/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		Node root = new Node();
		trie = new Trie(root);
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			trie.makeTrie(root, s, 0);
		}
		br.readLine();

		int M = Integer.parseInt(br.readLine());
		for (int m = 0; m < M; m++) {
			board = new char[4][4];
			for (int i = 0; i < 4; i++) {
				String s = br.readLine();
				for (int j = 0; j < 4; j++) {
					board[i][j] = s.charAt(j);
				}
			}
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					StringBuilder sb = new StringBuilder();
					char nowchar = board[i][j];
					Node node = trie.root.child[nowchar - 'A'];
					if (node != null) {
						sb.append(nowchar);
						dfs(i, j, node, 1, sb);
					}
				}
			}
			bw.write(maxScore + " " + longestAnswer + " " + findCount + "\n");
			br.readLine();
			trie.initIsHit(trie.root);
			maxScore = 0;
			longestAnswer = "";
			findCount = 0;
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static void dfs(int y, int x, Node node, int depth, StringBuilder sb) {
		visited[y][x] = true;
		//목적지인가
		if (node.isEnd && !node.isHit) {
			node.isHit = true;
			findCount++;
			maxScore += score[sb.length()];
			String s = sb.toString();

			if(compare(longestAnswer, s) <0){
				longestAnswer = s;
			}
		}

		for (int i = 0; i < 8; i++) {
			int ny = y + DY[i];
			int nx = x + DX[i];

			if (ny >= 0 && ny < 4 && nx >= 0 && nx < 4 && !visited[ny][nx]) {
				char nextchar = board[ny][nx];
				Node nextNode = node.child[nextchar - 'A'];
				if (nextNode != null) {
					sb.append(nextchar);
					dfs(ny, nx, nextNode, depth + 1, sb);
					sb.deleteCharAt(sb.length() - 1);
				}
			}
		}
		visited[y][x] = false;
	}

	private static int compare(String s1, String s2) {
		int result = Integer.compare(s1.length(), s2.length());
		if (result == 0) {
			return s2.compareTo(s1);
		} else {
			return result;
		}
	}
}

class Trie {
	Node root;

	Trie(Node node) {
		root = node;
	}

	public void makeTrie(Node node, String s, int ind) {

		char nowchar = s.charAt(ind);
		Node nextNode = node.child[nowchar - 'A'];
		if (nextNode == null) {
			nextNode = new Node();
			node.child[nowchar - 'A'] = nextNode;
		}

		if (ind == s.length() - 1) {
			nextNode.isEnd = true;
			return;
		}

		makeTrie(nextNode, s, ind + 1);
	}

	public void initIsHit(Node node) {
		node.isHit = false;
		for (int i = 0; i < 26; i++) {
			if (node.child[i] != null) {
				initIsHit(node.child[i]);
			}
		}
	}
}

class Node {
	Node[] child;
	boolean isEnd;
	boolean isHit;

	Node() {
		child = new Node[26]; //초기값 null?
		isEnd = false;
		isHit = false;
	}
}