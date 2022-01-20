package day03.BinaryTree.p5639;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static BufferedReader br;
	static BufferedWriter bw;

	static int N;
	static Node root;

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day03/BinaryTree/p5639/input.txt"));
		//
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String s = br.readLine();
		int prev = 0;
		if (s != null) {
			root = new Node(Integer.parseInt(s));
		}
		while ((s = br.readLine()) != null) {

			int input = Integer.parseInt(s);

			// 입력 받을 때마다 차례대로 추가하면 될듯
			// root부터 다시 내려가는 방식으로
			makeTree(root, input);
		}
		//결과는 후위 순회한 결과 출력 postorder
		postorder(root);

		bw.flush();
		bw.close();
		br.close();
	}

	private static void makeTree(Node now, int data) {

		if (data < now.data) {
			if (now.leftChild == null) {
				now.leftChild = new Node(data);
			}else{
				makeTree(now.leftChild, data);
			}
		}else{
			if (now.rightChild == null) {
				now.rightChild = new Node(data);
			}else{
				makeTree(now.rightChild, data);
			}
		}
	}

	private static void postorder(Node root) throws IOException {
		if (root == null)
			return;
		if (root.leftChild != null)
			postorder(root.leftChild);
		if (root.rightChild != null)
			postorder(root.rightChild);

		bw.write(root.data + "\n");

	}
}

class Node {
	int data;
	Node leftChild;
	Node rightChild;

	public Node(int data) {
		this.data = data;
	}
}
