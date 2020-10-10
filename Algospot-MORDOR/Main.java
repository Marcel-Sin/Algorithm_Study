import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE; 
	static final int INF = Integer.MAX_VALUE; 
	static MinMax_Data INF_DATA = new MinMax_Data(INF, NINF);
	static int TOTAL;
	static int[] Problem_Array = new int[100000];
	
	
	
	public static void main(String[] args) throws IOException {
		TOTAL = io.inputInt();
		for(int i = 0; i < TOTAL; i++) {
			Solve();
		}
	}
	
	
	static void Solve() throws IOException{
		int N,Q;
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		N = nextInt(stk); Q = nextInt(stk);
		
		stk = new StringTokenizer(io.inputStr());
		for(int i = 0; stk.hasMoreTokens(); i++) Problem_Array[i] = nextInt(stk);
		
		MinMax_RangedTree rtree = new MinMax_RangedTree(Problem_Array, N);
		
		int a = 0,b = 0;
		MinMax_Data ans;
		for (int i = 0; i < Q; i++) {
			stk = new StringTokenizer(io.inputStr());
			a = nextInt(stk);  b = nextInt(stk);
			ans = rtree.Query(a, b);
			System.out.println(ans.max-ans.min);
		}
		
		
	}
	
	static class MinMax_RangedTree {
		
		int size;
		MinMax_Data[] data;
		
		public MinMax_RangedTree(int[] array,int len) {
			size = len;
			data = new MinMax_Data[4*size];
			Init(array,0,0,size-1);
		}
		
		public void Init(int[] array,int node, int nodeLeft, int nodeRight) {
			if(nodeLeft == nodeRight) {
				data[node] = new MinMax_Data(array[nodeLeft], array[nodeRight]);
				return;
			}
			int mid = (nodeLeft + nodeRight)/2, left = node*2+1 , right = node*2+2;
			Init(array,left,nodeLeft,mid);
			Init(array,right,mid+1,nodeRight);
			
			data[node] = Merge(data[left],data[right]);
			return;
		}
		
		public MinMax_Data Query(int i,int j, int node, int nodeLeft, int nodeRight) {
			if( nodeRight < i || j < nodeLeft) return INF_DATA;
			if( i <= nodeLeft && nodeRight <= j) return data[node];
			
			int mid = (nodeLeft+nodeRight)/2, left = node*2+1 , right = node*2+2;
			MinMax_Data leftData = Query(i,j,left,nodeLeft,mid);
			MinMax_Data rightData = Query(i,j,right,mid+1,nodeRight);
			
			MinMax_Data ret = Merge(leftData,rightData);
			return ret;
		}
		public MinMax_Data Query(int i,int j) {
			return Query(i,j,0,0,size-1);
		}
		
		public MinMax_Data Merge(MinMax_Data a, MinMax_Data b) {
			MinMax_Data ret = new MinMax_Data(a.min, a.max);
			if(b.min < ret.min) ret.min = b.min;
			if(b.max > ret.max) ret.max = b.max;
			return ret;
		}
		
	}
	static class MinMax_Data {
		public int min,max;

		public MinMax_Data(int min, int max) {
			super();
			this.min = min;
			this.max = max;
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