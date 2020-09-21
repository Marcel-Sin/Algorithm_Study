import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Random;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static BST tree;
	public static void main(String[] args) throws IOException {
		tree = new BST(0);
		tree.priority = Integer.MIN_VALUE;
		
		tree.Insert(5);
		tree.Insert(3);
		tree.Insert(6);
		tree.Insert(9);
		tree.Insert(-99);
		
		
		Travel_MLR(tree.left);
		
	}
	
	static void Travel_LMR(BST root) {
		if(root == null) return;
		
		if(root.left != null) Travel_LMR(root.left);
		System.out.print(root.key+" ");
		if(root.right != null) Travel_LMR(root.right);
	}
	static void Travel_MLR(BST root) {
		if(root == null) return;
		System.out.print(root.key+"(" + root.priority + ") ");
		if(root.left != null) Travel_MLR(root.left);
		if(root.right != null) Travel_MLR(root.right);
	}
	
	
	static class BST {
		static Random rand = new Random();
		private int key,priority,size;
		private BST left,right,parent;
		public BST(int key) {
			super();
			this.key = key;
			this.priority = rand.nextInt(1000000000);
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
		
		public void Bottom_Up(BST upperNode) {
			//피벗축이 루트인 경우
			if(upperNode.parent == null) {
				this.parent = null;
				upperNode.parent = this;
			}
			//피벗이 루트가 아닌 경우,
			else {
				this.parent = upperNode.parent;
				
				//부모 쪽도 새로운 서브를 연결
				if(upperNode.parent.left == upperNode) upperNode.parent.SetLeft(this);	
				else if(upperNode.parent.right == upperNode) upperNode.parent.SetRight(this);
				
				upperNode.parent = this;
			}
			
		}

		public BST Rotate_Left() {
			if(this.right == null) return this;
			BST newRoot = this.right;
			
			if(newRoot.left != null) {
				this.SetRight(newRoot.left);
				newRoot.left.parent = this;
			}
			else this.SetRight(null);
			
			newRoot.SetLeft(this);
			
			newRoot.Bottom_Up(this);
			return newRoot;
		}
		public BST Rotate_Right() {
			if(this.left == null) return this;
			BST newRoot = this.left;
			
			if(newRoot.right != null) {
				this.SetLeft(newRoot.right);
				newRoot.right.parent = this;
			}
			else this.SetLeft(null);
			
			newRoot.SetRight(this);
			
			newRoot.Bottom_Up(this);
			
			return newRoot;
		}
	

		// 1: 이진트리의 규칙을 지킨다.
		// 2: 힙의 조건을 만족할때까지 로테이션 한다.
		public void Insert(int number) {
			BST node = new BST(number);
			BST _parent = this.left;
			
			// [예외] 루트로 처음 삽입 시,
			if(_parent == null) {
				node.parent = this;
				this.left = node;
				return;
			}
			
			
			// [제1조건] node 삽입점 찾고 삽입.
			while(true) {
				if(_parent.key < number) {
					if(_parent.right == null) {
						_parent.SetRight(node);
						node.parent = _parent;
						break;
					}
					else _parent = _parent.right;
				}
				else {
					if(_parent.left == null) {
						_parent.SetLeft(node);
						node.parent = _parent;
						break;
					}
					else _parent = _parent.left;
				}
			}
			// 삽입 종료
			
			// [제2조건] 완료까지 로테이트 반복
			while(true) {
				if(node.parent.priority < 0) break;
				if(node.priority > _parent.priority) { 	
					if(node == _parent.left) _parent = _parent.Rotate_Right();
					if(node == _parent.right) _parent = _parent.Rotate_Left();
					node = _parent;
					_parent = _parent.parent;
				}
				else break;
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