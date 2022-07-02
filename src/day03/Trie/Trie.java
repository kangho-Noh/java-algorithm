package day03.Trie;

public class Trie {

	static TrieNode root = new TrieNode();

	public static void main(String[] args) {

	}

	public static void insertTrieNode(String s) {
		TrieNode current = root;
		for (int i = 0; i < s.length(); i++) {
			TrieNode nextNode = current.children[s.charAt(i) - 'A'];
			if (nextNode == null) {
				nextNode = new TrieNode();
				current.children[s.charAt(i)-'A'] = nextNode;
			}
			current = nextNode;
		}
		current.isEnd = true;
	}
}

class TrieNode {
	TrieNode[] children = new TrieNode[26];
	boolean isEnd = false;

	public boolean hasChild(char c) {
		return (children[c-'A'] != null);
	}
}
