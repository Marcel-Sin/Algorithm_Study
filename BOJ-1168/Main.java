import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE / 4;
	static final int INF = Integer.MAX_VALUE;

	static int N,K;
	static Random rand = new Random();
	static Treap tree;
	
	public static void main(String[] args) throws IOException {
		Init();
		Solve();
	}
	static void Init() throws IOException{
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		N = nextInt(stk);
		K = nextInt(stk)-1;
		tree = new Treap(1);
		for (int i = 2; i <= N; i++) {
			tree = tree.Insert(tree, new Treap(i));
		}
	}
	
	static void Solve() {
		
		int pos = 0;
		int mod = N;
		int key = 0;
		ArrayList<Integer> answer = new ArrayList<Integer>();
		
		
		for (int i = 0; i < N; i++) {
			pos = (pos + K) % mod;
			key = tree.Kth(tree, pos+1).key;
			answer.add(key);
			tree = tree.Delete(tree, key);
			mod--;
		}
		
		
		
		StringBuilder sb = new StringBuilder();
		sb.append('<');
		for (int i = 0; i < answer.size()-1; i++) {
			sb.append(answer.get(i));
			sb.append(',');
			sb.append(' ');
		}
		sb.append(answer.get(answer.size()-1));
		sb.append('>');
		System.out.println(sb.toString());
	}
	
	static class Treap {
		int key,priority,size;
		Treap left,right;
		public Treap(int key) {
			super();
			this.key = key;
			this.priority = rand.nextInt();
			this.size = 1;
		}
		public int Calc() {
			size = 1;
			if(left != null) size += left.size;
			if(right != null) size += right.size;
			return size;
		}
		public void SetLeft(Treap node) {
			this.left = node;
			Calc();
		}
		public void SetRight(Treap node) {
			this.right = node;
			Calc();
		}
		
		public Pair Split(Treap root, int key) {
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
				Pair pair = Split(root, node.key);
				node.SetLeft(pair.a);
				node.SetRight(pair.b);
				return node;
			}
			else {
				if(root.key < node.key) root.SetRight(Insert(root.right,node));
				else root.SetLeft(Insert(root.left,node));
				return root;
			}
		}
		
		public Treap Merge(Treap a, Treap b) {
			if(a == null) return b;
			else if(b == null) return a;
			
			if(a.priority < b.priority) {
				b.SetLeft(Merge(a,b.left));
				return b;
			}
			else {
				a.SetRight(Merge(a.right,b));
				return a;
			}
		}
		
		public Treap Delete(Treap root, int key) {
			if(root == null) return root;
			if(root.key == key)  {
				root = Merge(root.left,root.right);
				return root; 
			}
			else if(root.key < key) root.SetRight(Delete(root.right,key));
			else root.SetLeft(Delete(root.left,key));
			return root;
		}
		
		public Treap Kth(Treap root, int k) {
			if(root == null) return null;
			int leftSize = 1;
			if(root.left != null) leftSize += root.left.size;
			if(k == leftSize) return root;
			else if(k < leftSize) return Kth(root.left,k);
			else return Kth(root.right,k-leftSize);
		}
		
	}
	static class Pair {
		public Treap a,b;

		public Pair(Treap a, Treap b) {
			this.a = a;
			this.b = b;
		}
	}
	

	// ============================================================
	// ============================================================
	// ============================================================
	// ============================================================
	// ============================================================
	// ============================================================
	// ============================================================
	// ===================== functions for PS =====================
	// ============================================================
	// ============================================================
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