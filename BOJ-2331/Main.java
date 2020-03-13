import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) throws IOException {
		IO_Manager io = new IO_Manager();
		HashMap<Integer, Integer> graph = new HashMap<Integer, Integer>();
		int[][] powArr = new int[10][];
		// TargetN : 0~9,  P : 1~5.
		for(int i=0; i <= 9; i++) {
			powArr[i] = new int[6];
			for(int j=1; j <= 5; j++) {
				powArr[i][j] = Pow(i,j);
			}
		}
		
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		int A = Integer.parseInt(stk.nextToken());
		int P = Integer.parseInt(stk.nextToken());
		graph.put(A,null);
		int counter = 0;
		int dupliStarting = Travel(graph,powArr,P,A);
		int nextVisit = A;
		while(nextVisit != dupliStarting) {
			counter++;
			nextVisit = graph.get(nextVisit);
		}
		System.out.println(counter);
	}
	
	
	
	static public void ClearCheck(boolean[] barr) {
		for (int i = 1; i < barr.length; i++) barr[i] = false;
	}
	static public int Travel(HashMap<Integer, Integer> graph,int[][] parr, int p, int pos) {
		int resultNext = 0;
		while(true) { 
			resultNext = 0;
			StringBuilder sb = new StringBuilder(pos+"");
			for(int i=0; i<sb.length(); i++) { 
				int x = sb.charAt(i)-0x30;
				resultNext += parr[x][p];
			}
			if (graph.containsKey(resultNext)) break;
			graph.put(pos,resultNext);
			pos = resultNext;
		}
		return resultNext;
	}
	
	static public int Pow(int x, int n) {
		if(x == 0) return 0;
		int result = 1;
		for(int i = 0; i < n; i++) result *= x;
		return result;
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
