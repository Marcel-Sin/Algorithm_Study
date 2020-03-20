import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main {

	public static void main(String[] args) throws IOException {
		IO_Manager io = new IO_Manager();
		HashMap<Integer, Node> tree = new HashMap<Integer, Node>();
		StringTokenizer stk;
		int count = io.inputInt();
		boolean[] check = new boolean[count+1];
		
		for(int i=1; i<=count; i++) {
			tree.put(i,new Node(tree,i));
		}
		
		for(int i=1; i<count; i++) {
			stk = new StringTokenizer(io.inputStr());
			Join(tree,nextInt(stk),nextInt(stk));
		}
		BFS(tree,check,1);
		for(int i=2; i<=count; i++) {
			io.write(tree.get(i).Action()+"\n");
		}
		io.close();
	
	}

	public static void Join(HashMap<Integer, Node> tree, int a, int b) {
		tree.get(a).link.add(b);
		tree.get(b).link.add(a);
	}
	
	public static int nextInt(StringTokenizer stk) {
		return Integer.parseInt(stk.nextToken());
	}
	
	public static void BFS(HashMap<Integer, Node> tree,boolean[] check, int start) {
		Queue<Node> nodeQueue = new LinkedList<Node>();
		nodeQueue.add(tree.get(start));
		check[start] = true;
		while(nodeQueue.isEmpty() == false) {
			Node parent = nodeQueue.poll();
			for(int nextKey : parent.link) {
				if(check[nextKey] == false) {
					Node nextNode = tree.get(nextKey);
					nextNode.parent = parent.content;
					check[nextKey] = true;
					nodeQueue.add(nextNode);
				}
			}
		}
		
	}
	
}

class Node {
	
	public HashMap<Integer, Node> tree;
	public int content;
	public int parent;
	public HashSet<Integer> link;
	
	public Node(HashMap<Integer, Node> tree, int content) {
		super();
		this.tree = tree;
		this.content = content;
		this.parent = -1;
		link = new HashSet<Integer>();
	}
	
	public int Action() {
		return parent;
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
