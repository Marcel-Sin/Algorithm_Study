import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	static final int INF = Integer.MAX_VALUE;
	static int N;
	static int SUM;
	static int[] A,B,C,D;
	static int[] A1;
	static int[] A2;
	static int A1_IDX,A2_IDX;
	static long c1,c2,COUNTER;
	static int rest;
	public static void main(String[] args) throws IOException {
		Init();
		A1_IDX = Array_Sum_Cases(A, B, A1);
		A2_IDX = Array_Sum_Cases(C, D, A2);
		Arrays.sort(A1,0,A1_IDX);
		Arrays.sort(A2,0,A2_IDX);
		A1[A1_IDX] = Integer.MAX_VALUE;
		A2[A2_IDX] = Integer.MAX_VALUE;
		
		int num = A1[1];
		int temp;
		while(true) {
			rest = 0-num;
			if(Lower_Bound(A2,A2_IDX,rest) != INF) {
				c1 = Upper_Bound(A1,A1_IDX,num) - Lower_Bound(A1,A1_IDX,num);
				c2 = Upper_Bound(A2,A2_IDX,rest) - Lower_Bound(A2,A2_IDX,rest);
				COUNTER += c1*c2;
			}
			temp = Upper_Bound(A1,A1_IDX,num);
			if(temp == INF) break;
			else num = A1[temp];
		}
		System.out.println(COUNTER);
		
	}

	static void Init() throws IOException{
		N = io.inputInt();
		A = new int[N];
		B = new int[N];
		C = new int[N];
		D = new int[N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer stk = new StringTokenizer(io.inputStr());
			A[i] = nextInt(stk);
			B[i] = nextInt(stk);
			C[i] = nextInt(stk);
			D[i] = nextInt(stk);
		}
		
		int size = N*N+2;
		A1 = new int[size];
		A2 = new int[size];
		A1[0] = Integer.MIN_VALUE;
		A2[0] = Integer.MIN_VALUE;
	}
	
	static int Array_Sum_Cases (int[] a,int[] b,int[] c) {
		int idx = 1;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				 SUM = a[i]+b[j];
				 c[idx++] = SUM; 
			}
		}
		return idx;
	}
	
	static int Lower_Bound(int[] a, int size,int target) {
		int s = 1, e = a.length-1, mid;
		while(s < e) {
			mid = (s+e)/2;
			if(a[mid] < target) s = mid+1;
			else e = mid;
		}
		if(a[e] == target) return e;
		else return INF;
	}
	static int Upper_Bound(int[] a, int size,int target) {
		int s = 1, e = a.length-1, mid;
		while(s < e) {
			mid = (s+e)/2;
			if(a[mid] <= target) s = mid+1;
			else e = mid;
		}
		if(a[e-1] == target) return e;
		else return INF;
	}
	static int nextInt(StringTokenizer stk) {
		return Integer.parseInt(stk.nextToken());
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
