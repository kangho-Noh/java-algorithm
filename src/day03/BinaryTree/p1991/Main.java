package day03.BinaryTree.p1991;

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

	public static void main(String[] args) throws Exception {

		//
		System.setIn(new FileInputStream("src/day03/BinaryTree/p1991/input.txt"));


		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());

		BinaryTree binaryTree = new BinaryTree();

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			char parent = st.nextToken().charAt(0);
			char leftChild = st.nextToken().charAt(0);
			char rightChild = st.nextToken().charAt(0);
			binaryTree.createNode(parent, leftChild, rightChild);
		}

		binaryTree.preorder(binaryTree.root);
		System.out.println();
		binaryTree.inorder(binaryTree.root);
		System.out.println();
		binaryTree.postorder(binaryTree.root);
	}

}

class Node {
	char data;
	Node lchild;
	Node rchild;

	Node(char data) {
		this.data = data;
	}
}

class BinaryTree {
	Node root;

	public void createNode(char data, char lchild, char rchild) {
		if(root == null){
			root= new Node(data);

			if (lchild != '.') {
				root.lchild = new Node(lchild);
			}if (rchild != '.') {
				root.rchild = new Node(rchild);
			}
		}else{
			searchNode(root, data, lchild, rchild);
		}
	}

	private void searchNode(Node now, char data, char lchild, char rchild) {
		if(now == null) return;
		if(now.data == data){
			if (lchild != '.') {
				now.lchild = new Node(lchild);
			}if (rchild != '.') {
				now.rchild = new Node(rchild);
			}
			return;
		}
		searchNode(now.lchild, data, lchild, rchild);
		searchNode(now.rchild, data, lchild, rchild);

	}

	public void preorder(Node now){
		System.out.print(now.data);
		if (now.lchild != null) {
			preorder(now.lchild);
		}
		if (now.rchild != null) {
			preorder(now.rchild);
		}
	}
	public void inorder(Node now){
		if (now.lchild != null) {
			inorder(now.lchild);
		}
		System.out.print(now.data);
		if (now.rchild != null) {
			inorder(now.rchild);
		}
	}
	public void postorder(Node now){
		if (now.lchild != null) {
			postorder(now.lchild);
		}
		if (now.rchild != null) {
			postorder(now.rchild);
		}
		System.out.print(now.data);
	}


}