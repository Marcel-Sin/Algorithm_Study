import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Random;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE / 4;
	static final int INF = Integer.MAX_VALUE / 4;
	static int TOTAL;
	static Random rand = new Random();

	public static void main(String[] args) throws IOException {
		Node tree = new Node(11);
		tree = tree.Insert(tree, new Node(9));
		tree = tree.Insert(tree, new Node(3));
		tree = tree.Insert(tree, new Node(10));
		tree = tree.Insert(tree, new Node(5));
		tree = tree.Insert(tree, new Node(15));
		tree = tree.Insert(tree, new Node(13));
		tree = tree.Insert(tree, new Node(14));
		tree = tree.Insert(tree, new Node(19));
		
		System.out.println(tree.CountlessThan(tree, 20));
	}
	static void LMR(Node root) {
		if (root == null)
			return;
		LMR(root.left);
		System.out.println(root.key);
		LMR(root.right);
	}

	static class Node {
		public int key, size, priority;
		public Node left, right;

		public Node(int key) {
			super();
			this.key = key;
			this.size = 1;
			this.priority = rand.nextInt();
		}
		public int Calc() {
			size = 1;
			if (left != null)
				size += left.Calc();
			if (right != null)
				size += right.Calc();
			return size;
		}
		public void SetLeft(Node node) {
			left = node;
			Calc();
		}
		public void SetRight(Node node) {
			right = node;
			Calc();
		}
		// root를 key 기준으로 작은 쪽, 큰 쪽으로 쪼갠다.
		public Pair Split(Node root, int key) {
			// 재귀 종료조건 : 쪼개지는 공간 발견 시
			if (root == null)
				return new Pair(null, null);
			// 키값이 더 큰 경우, 오른쪽을 쪼개고 결과 반환.
			if (root.key < key) {
				Pair rs = Split(root.right, key);
				// 쪼개진 오른쪽을 올바르게 다시 만드는 작업
				root.SetRight(rs.a);
				return new Pair(root, rs.b);
			}
			// 키값이 더 작은 경우, 왼쪽을 쪼개고 결과 반환
			else {
				Pair ls = Split(root.left, key);
				// 쪼개진 왼쪽을 올바르게 다시 만드는 작업
				root.SetLeft(ls.b);
				return new Pair(ls.a, root);
			}
		}
		public Node Insert(Node root, Node node) {
			if (root == null)
				return node;
			if (root.priority < node.priority) {
				// 노드가 루트가 되어야 한다.
				Pair pair = Split(root, node.key);
				node.SetLeft(pair.a);
				node.SetRight(pair.b);
				return node;
			}
			if (root.key < node.key)
				root.SetRight(Insert(root.right, node));
			else
				root.SetLeft(Insert(root.left, node));
			return root;
		}
		// max(a) < min(b) 이므로 left, right로 결합이 대소 비교로 이어짐.
		public Node Merge(Node a, Node b) {
			if (a == null)
				return b;
			else if (b == null)
				return a;
			// a쪽이 b의 왼쪽으로 들어가야함
			if (a.priority < b.priority) {
				b.SetLeft(Merge(a, b.left));
				return b;
			}
			// b쪽이 a의 오른쪽으로 들어가야함
			a.SetRight(Merge(a.right, b));
			return a;
		}
		public Node Erase(Node root, int key) {
			if (root == null)
				return root;
			if (root.key == key) {
				root = Merge(root.left, root.right);
				return root;
			}
			if (root.key < key)
				root.SetRight(Erase(root.right, key));
			else
				root.SetLeft(Erase(root.left, key));
			return root;
		}
		public Node Kth(Node root, int k) {

			
			int leftSize = 1;
			if (root.left != null)
				leftSize += root.left.size;
			if (leftSize == k)
				return root;
			else if (leftSize < k)
				return Kth(root.right, k - leftSize);
			else
				return Kth(root.left, k);
		}
		public int CountlessThan(Node root, int key) {
			if(root == null) return 0;
			if(key <= root.key) return CountlessThan(root.left,key);
			int counter = (root.left == null) ? 1 : root.left.size+1;
			counter += CountlessThan(root.right,key);
			return counter;
		}
	}

	static class Pair {
		public Node a, b;

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