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
		
		long counter = 0;
		int L = 1,R = v2.size()-2,value,sum;
		int c1,c2;
		int sizeA = v1.size()-1;
		while(L < sizeA && R >= 1) {
			sum = v1.get(L) + v2.get(R);
			if(sum < NEED) {
				value = v1.get(L);
				while(L < sizeA && v1.get(L) == value) L++;
			}
			else if(sum > NEED) {
				value = v2.get(R);
				while(R >= 1 && v2.get(R) == value) R--;
			}
			else {
				c1 = 0;
				c2 = 0;
				value = v1.get(L);
				while(L < sizeA && v1.get(L) == value) { L++; c1++; }
				value = v2.get(R);
				while(R >= 1 && v2.get(R) == value) { R--; c2++; }
				counter += (long)c1*(long)c2;
			}
		}
		System.out.println(counter);
		

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
