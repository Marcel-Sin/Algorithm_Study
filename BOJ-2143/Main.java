import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	static final int INF = Integer.MAX_VALUE;
	static int NEED;
	static int[] A,B;
	static int N,M;
	static HashMap<Integer,Integer> h1 = new HashMap<Integer,Integer>();
	static HashMap<Integer,Integer> h2 = new HashMap<Integer,Integer>();
	public static void main(String[] args) throws IOException {
		
		Init();
		Case_Create(A, h1);
		Case_Create(B, h2);
		int rest;
		long c1,c2,counter=0;
		for(int x : h1.keySet()) {
			rest = NEED-x;
			if(h2.containsKey(rest)) {
				c1 = h1.get(x);
				c2 = h2.get(rest);
				counter += c1*c2;
			}
		}
		System.out.println(counter);
	}
	
	
	static int Lower(int[] a, int target, int size) {
		int s = 1, e = size-1,m;
		while(s < e) {
			m = (s+e)/2;
			if(a[m] < target) s = m+1;
			else e = m;
		}
		if(a[e] != target) return INF;
		else return e;
	}
	static int Upper(int[] a, int target, int size) {
		int s = 1, e = size,m;
		while(s < e) {
			m = (s+e)/2;
			if(a[m] <= target) s = m+1;
			else e = m;
		}
		if(a[e-1] != target) return INF;
		else return e;
	}
	
	static void Case_Create(int[] a,HashMap<Integer,Integer> h) {
		int size = a.length,sum = 0;
		for(int i = 0; i < size; i++) {
			sum = 0;
			for(int j = i; j < size; j++) {
				sum += a[j];
				h.put(sum,h.getOrDefault(sum, 0)+1);
			}
		}
	}
	static void Init() throws IOException{
		NEED = io.inputInt();
		N = io.inputInt();
		A = new int[N];
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		for(int i = 0; i < N; i++) A[i] = nextInt(stk); 
		
		M = io.inputInt();
		B = new int[M];
		stk = new StringTokenizer(io.inputStr());
		for(int i = 0; i < M; i++) B[i] = nextInt(stk);
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
