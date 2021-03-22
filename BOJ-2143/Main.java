import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE / 2;
	static final int INF = Integer.MAX_VALUE / 2;
	static final int MAX = 1000;
	
	static int NEED,N,M;
	static int[] A;
	static int[] B;;

	
	public static void main(String[] args) throws IOException {
		Init();
		System.out.println(Solve());
	}
	
	
	static void Init() throws IOException {
		NEED = io.inputInt();
		N = io.inputInt();
		A = new int[N];
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		for (int i = 0; i < N; i++) A[i] = nextInt(stk);
		
		M = io.inputInt();
		B = new int[M];
		stk = new StringTokenizer(io.inputStr());
		for (int i = 0; i < M; i++) B[i] = nextInt(stk);
	}
	static long Solve() throws IOException {
		long ret = 0;
		ArrayList<Integer> listA = new ArrayList<Integer>(1000001);
		ArrayList<Integer> listB = new ArrayList<Integer>(1000001);
		CreateList(A,listA);
		CreateList(B,listB);
		
		// NEED = a+b , NEED - a = b
		int num,idx = 0;
		long ca,cb;
		while(idx < listA.size()) {
			num = listA.get(idx);
			cb = lower(listB, NEED-num);
			if(cb == -1) {idx = upper(listA,num);  continue;}
			
			cb = upper(listB, NEED-num) - cb;
			ca = idx = upper(listA,num);
			ca -= lower(listA,num);
			ret += ca*cb;
		}
		return ret;
	}
	static void CreateList(int[] arr,ArrayList<Integer> list) {
		int sum;
		for(int i = 0; i < arr.length; i++) {
			sum = arr[i];
			list.add(sum);
			for(int j = i+1; j < arr.length; j++) {
				sum += arr[j];
				list.add(sum);
			}
		}
		Collections.sort(list);
	}
	
	private static int l,h,mid,value;
	static int lower(ArrayList<Integer> list, int key) {
		l = 0;
		h =list.size();
		while(l < h) {
			mid = (l+h)/2;
			value = list.get(mid);
			if(value < key) l = mid+1;
			else h = mid;
		}
		if(0 <= l && l < list.size() && list.get(l) == key) return l;
		else return -1;
	}
	static int upper(ArrayList<Integer> list, int key) {
		l = 0;
		h =list.size();
		while(l < h) {
			mid = (l+h)/2;
			value = list.get(mid);
			if(value <= key) l = mid+1;
			else h = mid;
		}
		if(0 < l && list.get(l-1) == key) return l;
		else if(l == 0) return l;
		else return -1;
	}
//	===================== ETC functions for PS =====================
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
	static double Min(double a, double b) {
		return (a > b) ? b : a;
	}
	static double Max(double a, double b) {
		return (a > b) ? a : b;
	}
	static void Display(int[] arr, int limit) {
		for (int i = 0; i < limit; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
	static void Display(int[][] arr, int limit) {
		for (int i = 0; i < limit; i++) {
			for (int j = 0; j < limit; j++) {
				System.out.printf(arr[i][j]+" ");
			}
			System.out.println();
		}
	}
}


//-------------IO_Manager--------------
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