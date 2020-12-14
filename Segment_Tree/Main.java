import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE / 4;
	static final int INF = Integer.MAX_VALUE / 4;
	static int TOTAL;

	public static void main(String[] args) throws IOException {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i <= 9; i++) list.add(i); 		
	
		
		Segment_Tree tree = new Segment_Tree(list);
		tree.Update(3, 99);
		Display(tree.arraySum, tree.size*4);
		System.out.println(tree.Query(0, 3));
	}
	
	// 부분구간합 세그먼트 트리
	static class Segment_Tree {
		public int[] arraySum;
		public int size;
		
		public Segment_Tree(ArrayList<Integer> list) {
			size = list.size();
			arraySum = new int[size*4];
			Init(list,0,size-1,0);
		}
		
		public int Init(ArrayList<Integer> list,int left,int right, int node) {
			if(left == right) return arraySum[node] = list.get(left);
			int mid = (left + right) / 2;
			int a = Init(list,left,mid,node*2+1);
			int b = Init(list,mid+1,right,node*2+2);
			return arraySum[node] = a+b;	
		}
		
		public int Query(int left,int right, int node, int nodeLeft, int nodeRight) {
			if(right < nodeLeft || nodeRight < left ) return 0;
			if(left <= nodeLeft && nodeRight <= right) return arraySum[node];
			int mid = (nodeLeft + nodeRight) / 2;
			return Query(left,right,node*2+1,nodeLeft,mid) + Query(left,right,node*2+2,mid+1,nodeRight);
		}
		
		public int Query(int left, int right) {
			return Query(left,right,0,0,size-1);
		}
		
		
		public int Update(int idx, int value, int node, int nodeLeft, int nodeRight) {
			if(idx < nodeLeft || nodeRight < idx ) return arraySum[node];
			if(nodeLeft == nodeRight) return arraySum[node] = value;
			int mid = (nodeLeft + nodeRight) / 2;
			return arraySum[node] = Update(idx,value,node*2+1,nodeLeft,mid) + Update(idx,value,node*2+2,mid+1,nodeRight);	
		}
		
		public int Update(int idx, int value) {
			return Update( idx,  value, 0,0,size-1); 
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