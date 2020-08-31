import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

import javax.swing.event.ListSelectionEvent;

public class Main {
	static IO_Manager io = new IO_Manager();
	static int TOTAL;
	static ArrayList<Integer> PRE = new ArrayList<Integer>(); 
	static ArrayList<Integer> IN = new ArrayList<Integer>(); 
	public static void main(String[] args) throws IOException {
		TOTAL = io.inputInt();
		for (int i = 0; i < TOTAL; i++) {
			Init();
			Solve(PRE,IN);
			System.out.println();
		}
	}

	static void Solve(ArrayList<Integer> pre,ArrayList<Integer> in) {
		
		if(pre.size() == 0) return;
		int root_value = pre.get(0);
		int mid = in.indexOf(root_value);
		
		
		ArrayList<Integer> pre1 = new ArrayList<Integer>(pre.subList(1, mid+1));
		ArrayList<Integer> pre2 = new ArrayList<Integer>(pre.subList(mid+1, pre.size()));
		
		ArrayList<Integer> in1 = new ArrayList<Integer>(in.subList(0,mid));
		ArrayList<Integer> in2 = new ArrayList<Integer>(in.subList(mid+1,in.size()));
		
		Solve(pre1,in1);
		Solve(pre2,in2);
		System.out.print(root_value + " ");
	}
	

	static void Init() throws IOException {
		PRE.clear();
		IN.clear();
		int size = io.inputInt();
		StringTokenizer stk;
		stk = new StringTokenizer(io.inputStr());
		while(stk.hasMoreTokens()) PRE.add(nextInt(stk));
		stk = new StringTokenizer(io.inputStr());
		while(stk.hasMoreTokens()) IN.add(nextInt(stk));
	}
	
	// ===================== functions for PS =====================
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
	static void Display(int[] arr, int limit) {
		System.out.println("요소갯수 : " + arr.length);
		for (int i = 0; i < limit; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
	static void Display(int[][] arr, int limit) {
		System.out.println("요소갯수 : " + (arr.length * arr[0].length));
		for (int i = 0; i < limit; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
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