import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	static int[] arr;
	static int ans = 0;
	static int caseCount = 0;
	static Queue<Integer> Q = new LinkedList<Integer>();
	public static void main(String[] args) throws IOException {
		caseCount = io.inputInt();
		arr = new int[caseCount];
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		for(int i = 0 ; i < caseCount; i++) arr[i] = nextInt(stk);
		
		Permutation P = new Permutation(0, new int[caseCount], new boolean[caseCount]);
		DFS(P);
		System.out.println(ans);
	}	

	static void Sum(Permutation p) {
		int result = 0;
		
		for(int i = 0; i < caseCount-1; i++) {
			result += Math.abs(arr[p.order[i]] - arr[p.order[i+1]]);
		}
		if(result > ans) ans = result;
		
	}
	
	static void DFS(Permutation p) {
		if(p.count == caseCount) {
			Sum(p);
			return;
		}
		boolean[] check = p.check;
		for(int i = 0; i < p.check.length; i++) {
			if(check[i] == false) {
				int[] temp1 = p.order.clone();
				temp1[p.count] = i;
				boolean[] temp2 = p.check.clone();
				temp2[i] = true;
				Permutation nextP = new Permutation(p.count+1, temp1, temp2);
				DFS(nextP);
			}
		}
	}
	
	static int nextInt(StringTokenizer stk) {
		return Integer.parseInt(stk.nextToken());
	}
	
	static class Permutation {
		int count; 
		int[] order;
		boolean[] check;
		
		public Permutation(int count, int[] order, boolean[] check) {
			super();
			this.count = count;
			this.order = order;
			this.check = check;
		}
		
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
