import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Random;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static int TOTAL;
	static Random RANDOM = new Random();
	static B_Tree TREE;
	public static void main(String[] args) throws IOException {
		TREE = new B_Tree(10);
		TREE = TREE.Insert(TREE, new B_Tree(66));
		TREE = TREE.Insert(TREE, new B_Tree(61));
		TREE = TREE.Insert(TREE, new B_Tree(99));
		TREE = TREE.Insert(TREE, new B_Tree(3));
		//TREE = TREE.Remove(TREE, 10);
		Travel(TREE);
		System.out.println(TREE.kth(TREE,3).key);
		System.out.println(TREE.CountLessThan(TREE, 66));
	}
	
	static void Travel(B_Tree root) {
		if(root == null) return;
		
		Travel(root.left);
		System.out.print(root.key+" ");
		Travel(root.right);
		
	}
	
	static class Pair<T> {
		public T first,second;

		public Pair(T first, T second) {
			super();
			this.first = first;
			this.second = second;
		}
		
	}
	
	static class B_Tree {
		int key;
		int priority;
		int size;
		B_Tree left,right;
		
		
		public B_Tree(int key) {
			super();
			this.key = key;
			this.priority = RANDOM.nextInt();
			this.left = null;
			this.right = null;
			size = 1;
		}
		
		public void CalcSize() {
			size = 1;
			if(left != null) size += left.size;
			if(right != null) size += right.size;
		}
		
		public void SetRight(B_Tree node) {
			right = node;
			CalcSize();
		}
		public void SetLeft(B_Tree node) {
			left = node;
			CalcSize();
		}
		
		
		public Pair<B_Tree> Split(B_Tree root, int key) {
			if(root == null) return new Pair<B_Tree>(null,null);
			
			if(root.key < key) {
				Pair<B_Tree> rs = Split(root.right,key);
				root.SetRight(rs.first);
				return new Pair<B_Tree>(root,rs.second);
			}
			else {
				Pair<B_Tree> ls = Split(root.left,key);
				root.SetLeft(ls.second);
				return new Pair<B_Tree>(ls.first,root);
			}
			
		}
		
		public B_Tree Insert(B_Tree root, B_Tree node) {
			if(root == null) return node;
			if(root.priority < node.priority) {
				Pair<B_Tree> sub = Split(root,node.key);
				node.SetLeft(sub.first);
				node.SetRight(sub.second);
				return node;
			}
			else if(root.key < node.key) {
				root.SetRight(Insert(root.right,node));
				return root;
			}
			else {
				root.SetLeft(Insert(root.left,node));
				return root;
			}
		}
		
		// a의 최대(이진검색트리 좌측) < b의 최소(우측)
		public B_Tree Merge(B_Tree a, B_Tree b) {
			
			if(a == null) return b;
			if(b == null) return a;
		
			if(a.priority < b.priority) {
				// b가 루트 되어야 함.
				B_Tree ret = Merge(a,b.left);
				b.SetLeft(ret);
				return b;
			}
			else {
				// a가 루트 되어야 함.
				B_Tree ret = Merge(a.right,b);
				a.SetRight(ret);
				return a;
			}
		}
		
		public B_Tree Remove(B_Tree root,int key) {
			if(root == null) return root;
			if(root.key == key) {
				B_Tree ret = Merge(root.left,root.right);
				return ret;
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
		
		public B_Tree kth(B_Tree root,int k) {
			int leftSize = 0;
			if(root.left != null) leftSize = root.left.size;
			if(k <= leftSize) return kth(root.left, k);
			if(leftSize+1 == k) return root;
			return kth(root.right,k-leftSize-1);
		}
		
		public int CountLessThan(B_Tree root, int num) {
			if(root == null) return 0;
			if(root.key >= num) return CountLessThan(root.left, num);
			else {
				int counter = 0;
				if(root.left != null) counter = root.left.size;
				return counter+1+CountLessThan(root.right, num);
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
		System.out.println("요소갯수 : " + arr.length);
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