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
	static int N,S;
	static int[] A;
	static HashMap<Integer, Integer> h1 = new HashMap<Integer,Integer>();
	static HashMap<Integer, Integer> h2 = new HashMap<Integer,Integer>();
	public static void main(String[] args) throws IOException {
		Init();
		if(N <= 20) {
			DFS(-1,0,N,h1);
			if(h1.containsKey(S)) System.out.println(h1.get(S));
			else System.out.println(0);
		}
		else {
			int mid = N/2;
			long counter = 0;
			DFS(-1,0,mid,h1);
			DFS(mid-1,0,N,h2);
			if(h1.containsKey(S)) counter += h1.get(S);
			if(h2.containsKey(S)) counter += h2.get(S);
			
			for(int x : h1.keySet()) {
				int rest = S-x;
				if(h2.containsKey(rest)) {
					int c1 = h1.get(x);
					int c2 = h2.get(rest);
					counter += (long)c1 * (long)c2;
				}
			}
			System.out.println(counter);
		}
	}

	static void Init() throws IOException{
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		N = nextInt(stk);
		S = nextInt(stk);
		A = new int[N];
		stk = new StringTokenizer(io.inputStr());
		for(int i = 0; i < N; i++) A[i] = nextInt(stk);
	}
	
	static void DFS(int start,int sum, int end, HashMap<Integer, Integer> h) {
		if(start == end) return;
		
		for(int i = start+1; i < end; i++) {
			int n = sum+A[i];
			DFS(i,n,end,h);
			if(h.containsKey(n) == false) h.put(n, 1);
			else h.put(n, h.get(n)+1);
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
