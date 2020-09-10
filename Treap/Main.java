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
	static BBST TREE;
	public static void main(String[] args) throws IOException {
		TOTAL = io.inputInt();
		/*
		for (int i = 0; i < TOTAL; i++) {
		}
		*/
		TREE = new BBST(10);
		TREE = TREE.Insert(TREE, new BBST(5));
		TREE = TREE.Insert(TREE, new BBST(25));
		TREE = TREE.Insert(TREE, new BBST(18));
		TREE = TREE.Insert(TREE, new BBST(12));
		System.out.println(TREE.Count_LessThan(TREE, 0)+"개");
		Travel(TREE);
		System.out.println(TREE.size);
	}
	
	static void Travel(BBST tree) {
		if(tree == null) return;
		
		Travel(tree.left);
		System.out.println(tree.contents);
		Travel(tree.right);
	}
	
	static class BBST_Pair {
		BBST first;
		BBST second;
		
		public BBST_Pair(BBST f,BBST s) {
			first = f;
			second = s;
		}
		
	}
	
	static class BBST {
		public int contents;
		public int priority;
		public int size = 1;
		public BBST left = null;
		public BBST right = null;
		
		public BBST(int key) {
			contents = key;
			priority = RANDOM.nextInt();
		}
		
		public void SetRight(BBST tree) {
			right = tree;
			Calc_Size();
		}
		public void SetLeft(BBST tree) {
			left = tree;
			Calc_Size();
		}
		
		public int Calc_Size() {
			size = 1;
			if(left != null) size += left.Calc_Size();
			if(right != null) size += right.Calc_Size();
			return size;
		}
		
		
		public BBST_Pair Split(BBST root, int key) {
			if(root == null) return new BBST_Pair(null,null);
			
			if(root.contents < key) {
				BBST_Pair sub = Split(root.right,key);
				root.SetRight(sub.first);
				return new BBST_Pair(root,sub.second);
			}
			BBST_Pair sub = Split(root.left,key);
			root.SetLeft(sub.second);
			return new BBST_Pair(sub.first,root);
		}
		
		public BBST Insert(BBST root,BBST node) {
			if(root == null) return node;
			
			if(root.priority < node.priority) {
				BBST_Pair sub = Split(root, node.contents);
				node.SetLeft(sub.first);
				node.SetRight(sub.second);
				return node;
			}
			else if (node.contents < root.contents) {
				root.SetLeft(Insert(root.left,node));
				return root;
			}
			else {
				root.SetRight(Insert(root.right,node));
				return root;
			}
			
		}
		
		// 조건 : a트리가 key 왼쪽 트리, b가 key 오른쪽 만족해야 함.
		public BBST Merge(BBST a, BBST b) {
			if(a == null) return b;
			if(b == null) return a;
			
			if(a.priority < b.priority) {
				b.SetLeft(Merge(a,b.left));
				return b;
			}
			else {
				a.SetRight(Merge(a.right,b));
				return a;
			}
			
		}
		
		
		public BBST Erase(BBST root, int key) {
			if(root == null) return root;
			
			if(root.contents == key) {
				BBST ret = Merge(root.left,root.right);
				return ret;
			}
			else if(key < root.contents) {
				root.SetLeft(Erase(root.left,key));
				return root;
			}
			else root.SetRight(Erase(root.right,key));
			return root;
		}
		
		public BBST k_th(BBST root, int k) {
			int leftSize = 0;
			if(root.left != null) leftSize = root.left.size;
			
			if(k <= leftSize ) return k_th(root.left,k);
			else if(k == leftSize+1) return root; 
			else return k_th(root.right,k-leftSize-1);
		}
		
		public int Count_LessThan(BBST root, int key) {
			if(root == null) return 0;
			int counter = 0;
			if(key <= root.contents) return Count_LessThan(root.left, key);
			else {
				counter += (root.left != null)? root.left.size:0;
				counter += 1;
				return counter + Count_LessThan(root.right, key);
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