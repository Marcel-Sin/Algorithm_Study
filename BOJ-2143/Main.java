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
	static final int INF = Integer.MAX_VALUE;
	static int NEED;
	static int[] A,B;
	static int N,M;
	static int[] v1,v2;
	static int v1_SIZE,v2_SIZE;
	
	public static void main(String[] args) throws IOException {
		
		Init();
		v1_SIZE = Case_Create(A, v1);
		v2_SIZE = Case_Create(B, v2);
		Arrays.sort(v1,0,v1_SIZE);
		Arrays.sort(v2,0,v2_SIZE);
		v1[v1_SIZE] = Integer.MAX_VALUE;
		v2[v2_SIZE] = Integer.MAX_VALUE;
		
		int rest, num = v1[1],next;
		long counter=0,c1,c2;
		while(true) {
			rest = NEED-num;
			if(Lower(v2,rest,v2_SIZE) != INF) {
				c1 = Upper(v1,num,v1_SIZE) - Lower(v1,num,v1_SIZE);
				c2 = Upper(v2,rest,v2_SIZE) - Lower(v2,rest,v2_SIZE);
				counter += c1*c2;
			}
			next = Upper(v1,num,v1_SIZE);
			if(next == INF) break;
			else num = v1[next];
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
	
	static int Case_Create(int[] a,int[] v) {
		int size = a.length,sum = 0,idx = 1;
		for(int i = 0; i < size; i++) {
			sum = 0;
			for(int j = i; j < size; j++) {
				sum += a[j];
				v[idx++] = sum;
			}
		}
		return idx;
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
		v1 = new int[2000002];
		v2 = new int[2000002];
		v1[0] = Integer.MIN_VALUE;
		v2[0] = Integer.MIN_VALUE;
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
