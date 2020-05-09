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
	static final int INF = Integer.MAX_VALUE;
	static int NEED;
	static int[] A,B;
	static int N,M;
	static ArrayList<Integer> v1 = new ArrayList<Integer>();
	static ArrayList<Integer> v2 = new ArrayList<Integer>();
	
	public static void main(String[] args) throws IOException {
		
		Init();
		v1.add(Integer.MIN_VALUE);
		v2.add(Integer.MIN_VALUE);		
		Case_Create(A, v1);
		Case_Create(B, v2);
		v1.add(Integer.MAX_VALUE);
		v2.add(Integer.MAX_VALUE);
		
		Collections.sort(v1);
		Collections.sort(v2);
		
		int num = v1.get(1), rest,next;
		long c1,c2,counter=0;
		while(true) {
			rest = NEED-num;
			if(Lower(v2,rest) != INF) {
				c1 = Upper(v1,num) - Lower(v1,num);
				c2 = Upper(v2,rest) - Lower(v2,rest);
				counter += c1*c2;
			}
			next = Upper(v1,num);
			if(next == INF) break;
			else num = v1.get(next);
		}
		System.out.println(counter);

	}
	
	
	static int Lower(ArrayList<Integer> v, int target) {
		int s = 0, e = v.size()-1,m,mid;
		while(s < e) {
			m = (s+e)/2;
			mid = v.get(m);
			if(mid < target) s = m+1;
			else e = m;
		}
		if(v.get(e) != target) return INF;
		else return e;
	}
	static int Upper(ArrayList<Integer> v, int target) {
		int s = 0, e = v.size()-1,m,mid;
		while(s < e) {
			m = (s+e)/2;
			mid = v.get(m);
			if(mid <= target) s = m+1;
			else e = m;
		}
		if(v.get(e-1) != target) return INF;
		else return e;
	}
	
	static void Case_Create(int[] a,ArrayList<Integer> v) {
		int size = a.length,sum = 0;
		for(int i = 0; i < size; i++) {
			sum = 0;
			for(int j = i; j < size; j++) {
				sum += a[j];
				v.add(sum);
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
