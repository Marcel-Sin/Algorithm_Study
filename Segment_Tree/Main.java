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
	static final int NegINF = Integer.MIN_VALUE; 
	static int TOTAL;
	static RangedTree_MaxValues rtree;
	
	public static void main(String[] args) throws IOException {
		int[] arr = {9,8,3,2,100,7,5,3,55,8,99,77,200};
		rtree = new RangedTree_MaxValues(arr, 2);
		rtree.Update(9, 900);
		Display(rtree.rangeMaxs, 32);
	}
	
	//[i,j]구간 내에서 Maximum n개를 쉽게 찾아주는 구간 트리
	
	static class RangedTree_MaxValues {
		public int[][] rangeMaxs;
		public int findCount;
		public int size;
		
		public RangedTree_MaxValues(int[] array, int n) {
			size = array.length;
			rangeMaxs = new int[4*size][n];
			for(int i = 0; i < rangeMaxs.length; i++) Arrays.fill(rangeMaxs[i], NegINF);
			findCount = n;
			Init(array,0,0,size-1);
		}
		
		public void Init(int[] array,int node,int left,int right) {
			
			if(left == right) {
				int[] temp = {array[left]};
				rangeMaxs[node] = Max_Sorting_Array(temp, null, findCount);
				return;
			}
			ArrayList<Integer> list = new ArrayList<Integer>();
			int mid = (left+right)/2, left_Child = node*2+1, right_Child = node*2+2;
			
			Init(array, left_Child, left, mid);
			Init(array, right_Child, mid+1, right);
			
			rangeMaxs[node] = Max_Sorting_Array(rangeMaxs[left_Child], rangeMaxs[right_Child], findCount);

			return;
		}

		public int[] Query(int searchLeft, int searchRight, int node, int nodeLeft, int nodeRight) {
			if(searchRight < nodeLeft || nodeRight < searchLeft) return null;
			if(searchLeft <= nodeLeft && nodeRight <= searchRight) return rangeMaxs[node];
			
			int mid = (nodeLeft+nodeRight)/2;
			int[] leftArr = Query(searchLeft,searchRight,(node*2+1),nodeLeft,mid);
			int[] rightArr = Query(searchLeft,searchRight,(node*2+2),mid+1,nodeRight);
			

			int[] ret = Max_Sorting_Array(leftArr, rightArr, findCount);
			return ret;
		}
		public void Query_Max(int i, int j) {
			int[] ret = Query(i,j,0,0,size-1);
			for(int a = 0; a < ret.length; a++) System.out.print(ret[a]+" | ");
			System.out.println();
		}
		
		
		public int[] Update(int idx, int newValue, int node, int nodeLeft, int nodeRight) {
			if(idx < nodeLeft || nodeRight < idx ) return rangeMaxs[node];
			if(nodeLeft == nodeRight) {
				int[] temp = {newValue};
				rangeMaxs[node] = Max_Sorting_Array(temp, null, findCount);
				return rangeMaxs[node];
			}
			int mid = (nodeLeft+nodeRight)/2;
			int[] leftArr = Update(idx,newValue,node*2+1,nodeLeft,mid);
			int[] rightArr = Update(idx,newValue,node*2+2,mid+1,nodeRight);
			
			rangeMaxs[node] = Max_Sorting_Array(leftArr, rightArr, findCount);
			return rangeMaxs[node];
		}
		public void Update(int idx, int newValue) {
			Update(idx,newValue,0,0,size-1);
		}
		public int[] Max_Sorting_Array(int[] arrA,int[] arrB, int n) {
			int[] ret = new int[n];
			Arrays.fill(ret, NegINF);
			ArrayList<Integer> list = new ArrayList<Integer>();
			
			if(arrA != null) {
				for(int i = 0; i < arrA.length; i++) list.add(arrA[i]);
			}
			if(arrB != null) {
				for(int i = 0; i < arrB.length; i++) list.add(arrB[i]);
			}
			Collections.sort(list,Collections.reverseOrder());
			int loopCount = Min(n,list.size());
			for(int i = 0; i < loopCount; i++) ret[i] = list.get(i);
			return ret;
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