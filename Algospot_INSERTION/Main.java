import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static int TOTAL;
	static Treap RANK_TREE;
	static int[] PROBLEM = new int[50000];
	static int[] ANSWER = new int[50000];
	static int LEN;
	
	public static void main(String[] args) throws IOException {
		TOTAL = io.inputInt();
		for(int i = 0; i < TOTAL; i++) {
			Init();
			Solve();
		}
	}
	
	static void Solve() {
		Treap tree = new Treap(1);
		for(int num = 2; num <= LEN; num++) tree = tree.Insert(tree, new Treap(num));
		
		for(int curIdx = LEN-1; curIdx >= 0; curIdx--) {
			int rank = tree.size-PROBLEM[curIdx];
			ANSWER[curIdx] = tree.Kth(tree, rank).key;
			tree = tree.Remove(tree, ANSWER[curIdx]);
		}
		Display(ANSWER, LEN);
	}
	
	
	static void Init() throws IOException {
		Arrays.fill(PROBLEM, -1);
		Arrays.fill(ANSWER, -1);
		LEN = io.inputInt();
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		
		for(int i = 0; stk.hasMoreTokens(); i++) PROBLEM[i] = nextInt(stk);
		
	}
	
	static class Pair<T> {
		private T first,second;

		public Pair(T first, T second) {
			super();
			this.first = first;
			this.second = second;
		}
	}
	static class Treap {
		static Random RANDOM = new Random();
		int key,priority,size;
		Treap left,right;
		
		public Treap(int key) {
			super();
			this.key = key;
			this.priority = RANDOM.nextInt();
			this.size = 1;
			this.left = null;
			this.right = null;
		}

		public void RenewSize() {
			size = 1;
			if(left != null) size += left.size;
			if(right != null) size += right.size;
		}	
		public void SetLeft(Treap node) {
			left = node;
			RenewSize();
		}
		public void SetRight(Treap node) {
			right = node;
			RenewSize();
		}
		
		public Pair<Treap> Split(Treap root, int key) {
			if(root == null) return new Pair<Treap>(null,null);
			
			//최소근사점을 찾기 위해 오른쪽 서브로 간다.
			if(root.key < key) {
				Pair<Treap> rs = Split(root.right,key);
				root.SetRight(rs.first);
				return new Pair<Treap>(root, rs.second);
			}
			//최대근사점을 찾기 위해 왼쪽 서브로 간다.
			else {
				Pair<Treap> ls = Split(root.left, key);
				root.SetLeft(ls.second);
				return new Pair<Treap>(ls.first, root);
			}
		}
		public Treap Insert(Treap root, Treap node) {
			if(root == null) return node;
			if(root.priority < node.priority) {
				Pair<Treap> sub = Split(root,node.key);
				node.SetLeft(sub.first);
				node.SetRight(sub.second);
				return node;
			}
			if(root.key < node.key) {
				root.SetRight(Insert(root.right,node));
				return root;
			}
			else {
				root.SetLeft(Insert(root.left,node));
				return root;
			}
		}
		// max(A) < min(B) 이진트리 좌측,우측 가능할 때,
		public Treap Merge(Treap A,Treap B) {
			if(A == null) return B;
			if(B == null) return A;
			
			if(A.priority < B.priority) {
				Treap newLeft = Merge(A,B.left);
				B.SetLeft(newLeft);
				return B;
			}
			else {
				Treap newRight = Merge(A.right,B);
				A.SetRight(newRight);
				return A;
			}
			
		}
		
		public Treap Remove(Treap root, int key) {
			if(root == null) return root;
			if(root.key == key) {
				root = Merge(root.left,root.right);
				return root;
			}
			else if(root.key < key) {
				root.SetRight(Remove(root.right,key));
				return root;
			}
			else {
				root.SetLeft(Remove(root.left,key));
				return root;
			}
		}
		public Treap Kth(Treap root, int k) {
			if(root == null) return null;
			int leftSize = 0;
			if(root.left != null) leftSize = root.left.size;
			if(k <= leftSize) return Kth(root.left,k);
			if(k == leftSize+1) return root;
			else {
				return Kth(root.right,k-leftSize-1);
			}
		}	
		public int Counting_LessThan(Treap root, int key) {
			if(root == null) return 0;
			if(key <= root.key) return Counting_LessThan(root.left, key);
			else {
				int leftSize = (root.left != null) ? root.left.size : 0;
				return leftSize + 1 + Counting_LessThan(root.right, key);
			}
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