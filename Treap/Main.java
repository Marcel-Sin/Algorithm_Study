import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Random;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE/4;
	static final int INF = Integer.MAX_VALUE/4;

	static int TOTAL;
	
	static Random rand = new Random();
	public static void main(String[] args) throws IOException{
		Node tree = new Node(10);
		tree = tree.Insert(tree, new Node(7));
		tree = tree.Insert(tree, new Node(13));
		tree = tree.Insert(tree, new Node(17));
		tree = tree.Insert(tree, new Node(15));
		tree = tree.Insert(tree, new Node(9));
		tree = tree.Insert(tree, new Node(8));
		tree = tree.Insert(tree, new Node(10));
		tree = tree.Insert(tree, new Node(6));
		tree = tree.Insert(tree, new Node(3));
		tree = tree.Erase(tree, 13);
		LMR(tree);
		
		System.out.println(tree.Kth(tree,1).key);
		System.out.println(tree.Kth(tree,9).key);
	}

	static void LMR(Node root) {
		if(root == null) return;
		
		LMR(root.left);
		System.out.println(root.key);
		LMR(root.right);
	}
	
	static class Node {
		public int key,size,priority;
		public Node left, right;
		
		public Node(int key) {
			super();
			this.key = key;
			this.size = 1;
			left = null;
			right = null;
			priority = rand.nextInt();
		}
		
		public int Calc() {
			size = 1;
			if(left != null) size += left.Calc();
			if(right != null) size += right.Calc();
			return size;
		}
		public void SetLeft(Node node) {
			left = node; Calc();
		}
		public void SetRight(Node node) {
			right = node; Calc();
		}
		
		public Pair Split(Node root, int key) {
			if(root == null) return new Pair(null,null);
			
			if(root.key < key) {
				Pair pair = Split(root.right,key);
				root.SetRight(pair.a);
				return new Pair(root,pair.b);
			}
			else {
				Pair pair = Split(root.left,key);
				root.SetLeft(pair.b);
				return new Pair(pair.a,root);
			}
		}
		public Node Insert(Node root, Node node) {
			if(root == null) return node;
			
			if(root.priority < node.priority) {
				Pair pair = Split(root,node.key);
				node.SetLeft(pair.a);
				node.SetRight(pair.b);
				return node;
			}
			if(root.key < node.key) root.SetRight(Insert(root.right,node));
			else root.SetLeft(Insert(root.left,node));
			return root;
		}
		
		public Node Merge(Node a, Node b) {
			if(a == null) return b;
			else if(b == null) return a;
			
			if(a.priority > b.priority ) {
				a.SetRight(Merge(a.right,b));
				return a;
			} 
			else {
				b.SetLeft(Merge(a,b.left));
				return b;
			}
		}	
		public Node Erase(Node root,int key) {

			if(root == null) return null;
			if(root.key == key) {
				Node ret = Merge(root.left,root.right);
				return ret;
			}
			if(root.key < key) root.SetRight(Erase(root.right,key));
			else root.SetLeft(Erase(root.left,key));
			return root;
		}

		public Node Kth(Node root,int k) {
			int leftSize = 1;
			if(root.left != null) leftSize += root.left.size;
			if(leftSize == k) return root;
			if(leftSize < k) return Kth(root.right,k-leftSize);
			else return Kth(root.left,k);
		}
	}	
	static class Pair {
		public Node a,b;

		public Pair(Node a, Node b) {
			super();
			this.a = a;
			this.b = b;
		}
	}
	
	// ===================== functions for PS =====================
	static int nextInt(StringTokenizer stk) {
		return Integer.parseInt(stk.nextToken());
	}
	static long Min(long a, long b) {
		return (a > b) ? b : a;
	}
	static long Max(long a, long b) {
		return (a > b) ? a : b;
	}
	static int Min(int a, int b) {
		return (a > b) ? b : a;
	}
	static int Max(int a, int b) {
		return (a > b) ? a : b;
	}
	static void Display(int[] arr, int limit) {
		//System.out.println("요소갯수 : " + arr.length);
		for (int i = 0; i < limit; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
	static void Display(int[][] arr, int limit) {
		System.out.println("요소갯수 : " + (arr.length * arr[0].length));
		for (int i = 0; i < limit; i++) {
			System.out.print("["+i+"] : ");
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
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