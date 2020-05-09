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
	static HashMap<Integer,Integer> h1 = new HashMap<Integer,Integer>();
	static HashMap<Integer,Integer> h2 = new HashMap<Integer,Integer>();
	static long COUNTER,c1,c2;
	static int rest;
	static long start,end;
	public static void main(String[] args) throws IOException {
		Init();
		Array_Sum_Cases(A, B, h1);
		Array_Sum_Cases(C, D, h2);
		for(int x : h1.keySet()) {
			rest = 0-x;
			if(h2.containsKey(rest)) {
				c1 = h1.get(x);
				c2 = h2.get(rest);
				COUNTER += c1*c2;
			}
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
	}
	
	static void Array_Sum_Cases (int[] a,int[] b,HashMap<Integer,Integer> h) {
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				 SUM = a[i]+b[j];
				 h.put(SUM,h.getOrDefault(SUM, 0)+1);
			}
		}
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
