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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE / 2;
	static final int INF = Integer.MAX_VALUE / 2;
	static final int PROBLEM_MAX = 2001;

	static int L,C;
	static ArrayList<Character> clist = new ArrayList<Character>(); 
	static char[] mem;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		Init();
		Solve();
		System.out.print(sb.toString());
	}

	static void Init() throws IOException {
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		L = nextInt(stk); C = nextInt(stk);
		mem = new char[L];
		stk = new StringTokenizer(io.inputStr());
		for (int i = 0; i < C; i++) clist.add(stk.nextToken().charAt(0));
		Collections.sort(clist);
	}

	static void Solve() throws IOException {
		DFS(-1,0,0,0);		
	}
	
	private static char iterC;
	static void DFS(int here, int vow, int conso, int len) throws IOException {
		if(len == L) {
				if( 1 <= vow && 2 <= conso) {
					sb.append(new String(mem));
					sb.append('\n');
					return;
				}
				else return;
		}
		for (int i = here+1; i < C; i++) {
			iterC = clist.get(i);
			mem[len] = iterC;
			if(Check_Vowel(iterC) == true) DFS(i,vow+1,conso,len+1);
			else DFS(i,vow,conso+1,len+1);
		}
	}
	
	static boolean Check_Vowel(char c) {
		switch(c) {
			case 'a' : return true;
			case 'e' : return true;
			case 'i' : return true;
			case 'o' : return true;
			case 'u' : return true;
		}
		return false;
	}
//	===================== ETC functions for PS =====================
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
	static double Min(double a, double b) {
		return (a > b) ? b : a;
	}
	static double Max(double a, double b) {
		return (a > b) ? a : b;
	}
	static void Display(int[] arr, int limit) {
		for (int i = 0; i < limit; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
	static void Display(int[][] arr, int limit) {
		System.out.println("요소갯수 : " + (arr.length * arr[0].length));
		for (int i = 0; i < limit; i++) {
			for (int j = 0; j < limit; j++) {
				System.out.printf("%2d ", arr[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
}


//-------------IO_Manager--------------
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