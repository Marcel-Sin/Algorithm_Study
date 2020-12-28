import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE / 4;
	static final int INF = Integer.MAX_VALUE / 4;
	static final int MAX_SIZE = 1001;
	static Random rand = new Random();
	static int N,K;
	static Treap btree;
	
	public static void main(String[] args) throws IOException {
		Init();
		System.out.println(btree.Kth(btree, K).key);
		
	}

	static void Init() throws IOException {
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		N = nextInt(stk);
		K = nextInt(stk);
		
		stk = new StringTokenizer(io.inputStr());
		btree = new Treap(nextInt(stk));
		for (int i = 1; i < N; i++) {
			btree = btree.Insert(btree, new Treap(nextInt(stk)));
		}

	}
	
	static void LMR(Treap node) {
		if(node == null) return;
		
		if(node.left != null) LMR(node.left);
		System.out.println(node.key);
		if(node.right != null) LMR(node.right);
		
	}
	
	static class Treap {
		public int key,priority,size;
		public Treap left,right;
		public Treap(int key) {
			super();
			this.key = key;
			this.priority = rand.nextInt();
			this.size = 1;
			this.left = null;
			this.right = null;
		}
		
		public int CalcSize() {
			size = 1;
			if(left != null) size += left.size;
			if(right != null) size += right.size;
			return size;
		}
		public void SetLeft(Treap node) {
			left = node;
			CalcSize();
		}
		public void SetRight(Treap node) {
			right = node;
			CalcSize();
		}
		
		
		public Pair Split(Treap root,int key) {
			if(root == null) return new Pair(null,null);
			
			if(root.key < key) {
				Pair rs = Split(root.right,key);
				root.SetRight(rs.a);
				return new Pair(root,rs.b);
			}
			else {
				Pair ls = Split(root.left,key);
				root.SetLeft(ls.b);
				return new Pair(ls.a,root);
			}
		}
		
		public Treap Insert(Treap root, Treap node) {
			if(root == null) return node;
			if(root.priority < node.priority) {
				Pair sub = Split(root,node.key);
				node.SetLeft(sub.a);
				node.SetRight(sub.b);
				return node;
			}
			else {
				if(root.key < node.key) root.SetRight(Insert(root.right,node));
				else root.SetLeft(Insert(root.left,node));
				return root;
			}
		}
		
		public Treap Kth(Treap root, int k) {
			if(root == null) return null;
			int leftSize = 1;
			if(root.left != null) leftSize += root.left.size;
			if(leftSize == k) return root;
			else if(leftSize < k) return Kth(root.right,k-leftSize);
			else return Kth(root.left,k);
		}
		
	}
	static class Pair {
		public Treap a,b;

		public Pair(Treap a, Treap b) {
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
		// System.out.println("요소갯수 : " + arr.length);
		for (int i = 0; i < limit; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
	static void Display(int[][] arr, int limit) {
		System.out.println("요소갯수 : " + (arr.length * arr[0].length));
		for (int i = 0; i < limit; i++) {
			System.out.print("[" + i + "] : ");
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