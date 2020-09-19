import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Random;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();

	public static void main(String[] args) throws IOException {
		
	}
	
	static class BST {
		static Random rand = new Random();
		private int key,priority,size;
		private BST left,right,parent;
		public BST(int key) {
			super();
			this.key = key;
			this.priority = rand.nextInt();
			this.size = 1;
			this.left = null;
			this.right = null;
			this.parent = null;
		}
		
		public void CalcSize() {
			size = 1;
			if(left != null) size += left.size;
			if(right != null) size += right.size;
		}
		public void SetLeft(BST node) {
			left = node;
			CalcSize();
		}
		public void SetRight(BST node) {
			right = node;
			CalcSize();
		}
		
		public void Rotate_Left() {
			if(this.right == null) return;
			BST newRoot = this.left;
			
			this.SetLeft(newRoot.right);
			newRoot.right.parent = this;
			
			newRoot.SetRight(this);
			
			//계승
			if(this.parent.right == this) this.parent.right = newRoot;
			else this.parent.left = newRoot;
			
			newRoot.parent = this.parent;
			this.parent = newRoot;
		}
		public void Rotate_Right() {

			if(this.left == null) return;
			BST newRoot = this.right;
			
			this.SetRight(newRoot.left);
			newRoot.left.parent = this;
			
			newRoot.SetLeft(this);
			
			//계승
			if(this.parent.right == this) this.parent.right = newRoot;
			else this.parent.left = newRoot;
			
			newRoot.parent = this.parent;
			this.parent = newRoot;
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