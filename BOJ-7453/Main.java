import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE / 2;
	static final int INF = Integer.MAX_VALUE / 2;

	
	static int N;
	static int[][] array = new int[4][4000];
	static int[] r1;
	static int[] r2;

	public static void main(String[] args) throws IOException {
		Init();
		System.out.println(Solve());
	}
	
	
	static void Init() throws IOException {
		N = io.inputInt();
		for (int i = 0; i < N; i++) {
			StringTokenizer stk = new StringTokenizer(io.inputStr());
			for (int j = 0; j < 4; j++) array[j][i] = nextInt(stk);	
		}

	}
	static long Solve() throws IOException {
		long ret = 0;
		r1 = new int[N*N+2];
		r2 = new int[N*N+2];
		int sum = 0,idxR1=0,idxR2=0;
		
		r1[idxR1++] = NINF; r2[idxR2++] = NINF;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sum = array[0][i]+array[1][j];
				r1[idxR1++] = sum;
				sum = array[2][i]+array[3][j];
				r2[idxR2++] = sum;
			}
		}
		r1[idxR1++] = INF; r2[idxR2++] = INF;
		
		Arrays.sort(r1,0,idxR1);
		Arrays.sort(r2,0,idxR2);
		int idx = 1;
		long a,b,c,d;
		boolean error = false;
		while(idx < idxR1) {
			// r1에 대한 카운팅
			a = LowerBound(r1,idxR1,r1[idx]);
			if(a == -1) break;
			b = UpperBound(r1,idxR1,r1[idx]);
			// r2에 대한 카운팅
			c = LowerBound(r2,idxR2,-r1[idx]);
			if(c == -1) {idx = (int)b; continue;}
			d = UpperBound(r2,idxR2,-r1[idx]);
			ret += (b-a)*(d-c);
			
			idx = (int)b;
		}
		return ret;
	}
	
	
	
	// 첫번째 시작지점 반환
	private static int low,mid,high,value;
	static int LowerBound(int[] arr,int size, int key) {
		low = 0;
		high = size;
		while(low < high) {
			mid = (low+high)/2;
			value = arr[mid];
			if(value < key) low = mid+1;
			else high = mid;
		}
		if(arr[low] == key) return low;
		else return -1;
	}
	// 키보다 큰 시작지점 low 반환
	static int UpperBound(int[] arr,int size, int key) {
		low = 0;
		high = size;
		while(low < high) {
			mid = (low+high)/2;
			value = arr[mid];
			if(value <= key) low = mid+1;
			else high = mid;
		}
		if (arr[low-1] == key) return low;
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