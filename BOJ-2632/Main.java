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
	static long c1,c2,COUNTER;
	static ArrayList<Integer> v1 = new ArrayList<Integer>();
	static ArrayList<Integer> v2 = new ArrayList<Integer>();
	public static void main(String[] args) throws IOException {
		Init();
		Case_Create(A, v1);
		Case_Create(B, v2);
		v1.add(Integer.MAX_VALUE);
		v2.add(Integer.MAX_VALUE);
		Collections.sort(v1);
		Collections.sort(v2);
		
		COUNTER = 0;
		COUNTER += CountBetween(v1, NEED);
		COUNTER += CountBetween(v2, NEED);
		
		int value = v1.get(1),rest = 0,temp;
		while(true) {
			rest = NEED-value;
			if(Lower(v2,rest) != INF) {
				c1 = CountBetween(v1, value);
				c2 = CountBetween(v2, rest);
				COUNTER += c1*c2;
			}
			temp = Upper(v1,value);
			if(temp == INF) break;
			else value = v1.get(temp);
		}
		System.out.println(COUNTER);

	}
	
	static int Lower(ArrayList<Integer> v, int target) {
		int s = 1, e = v.size()-1,m,mid;
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
		int s = 1, e = v.size()-1,m,mid;
		while(s < e) {
			m = (s+e)/2;
			mid = v.get(m);
			if(mid <= target) s = m+1;
			else e = m;
		}
		if(v.get(e-1) != target) return INF;
		else return e;
	}
	static int CountBetween(ArrayList<Integer> v, int target) {
		return Upper(v,target) - Lower(v,target);
	}
	
	
	
	static void Case_Create(int[] a,ArrayList<Integer> v) {
		int size = a.length,sum;
		for(int s = 0; s < size; s++) {
			sum = 0;
			for(int i = 0; i < size-1; i++) {
				sum += a[(s+i)%size];
				v.add(sum);
			}
		}
		sum = 0;
		for(int i = 0; i < size; i++) sum += a[i];
		v.add(sum);
	}
	static void Init() throws IOException{
		NEED = io.inputInt();
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		N = nextInt(stk);
		M = nextInt(stk);
		A = new int[N];
		for(int i = 0; i < N; i++) A[i] = io.inputInt();
		B = new int[M];
		for(int i = 0; i < M; i++) B[i] = io.inputInt();
		v1.add(Integer.MIN_VALUE);
		v2.add(Integer.MIN_VALUE);
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
