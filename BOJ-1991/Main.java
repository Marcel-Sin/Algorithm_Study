import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main {

	public static void main(String[] args) throws IOException {
		IO_Manager io = new IO_Manager();
		HashMap<Character,Node> btree = new HashMap<Character,Node>();
		StringTokenizer stk;
		int count = io.inputInt();
		for(int i=0; i<count; i++) {
			stk = new StringTokenizer(io.inputStr());
			char nodeContent = stk.nextToken().charAt(0);
			char leftNode = stk.nextToken().charAt(0);
			char rightNode = stk.nextToken().charAt(0);
			btree.put(nodeContent, new Node(btree,nodeContent,leftNode,rightNode));
		}

		
		Preorder(btree,btree.get('A'));
		System.out.println();
		inorder(btree,btree.get('A'));
		System.out.println();
		postorder(btree,btree.get('A'));
		
	
	}
	
	static void Preorder(HashMap<Character,Node> btree, Node root) {
		if (root == null) return;
		System.out.print(root.content);
		Preorder(btree,root.left());
		Preorder(btree,root.right());
	}
	static void inorder(HashMap<Character,Node> btree, Node root) {
		if (root == null) return;
		inorder(btree,root.left());
		System.out.print(root.content);
		inorder(btree,root.right());
	}
	static void postorder(HashMap<Character,Node> btree, Node root) {
		if (root == null) return;
		postorder(btree,root.left());
		postorder(btree,root.right());
		System.out.print(root.content);
	}
	
	
	static int nextInt(StringTokenizer stk) {
		return Integer.parseInt(stk.nextToken());
	}
	
}

class Node {
	public HashMap<Character,Node> graph;
	public char content;
	public char leftKey;
	public char rightKey;
	public Node(HashMap<Character,Node> graph,int content, int left, int right) {
		super();
		this.graph = graph;
		this.content = (char)content;
		this.leftKey = (char)left;
		this.rightKey = (char)right;
	}

	public Node left() {
		if (leftKey == '.') return null;
		return graph.get(leftKey);
	}
	
	public Node right() {
		if (rightKey == '.') return null;
		return graph.get(rightKey);
	}
	
}



// ************************************** //
// *-------------IO_Manager--------------* //
// ************************************** //
class IO_Manager {
	public BufferedReader br;
	public BufferedWriter bw;

	public IO_Manager() {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
	}
	public Integer inputInt() throws IOException {
		return Integer.parseInt(br.readLine());
	}
	public Long inputLong() throws IOException {
		return Long.parseLong(br.readLine());
	}
	public String inputStr() throws IOException {
		return br.readLine();
	}
	public void write(String str) throws IOException {
		bw.write(str);
	}
	public void close() throws IOException {
		br.close();
		bw.close();
	}
}
