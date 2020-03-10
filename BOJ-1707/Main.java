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
		HashMap<Integer, HashSet<Integer>> graph = new HashMap<Integer, HashSet<Integer>>();
		boolean[] check = new boolean[20001];
		char[] temper = new char[20001];
		ClearTemper(temper);
		ClearCheck(check);
		
		int caseCount = io.inputInt();
		for (int k = 0; k < caseCount; k++) {
			StringTokenizer stk = new StringTokenizer(io.inputStr());
			int N = Integer.parseInt(stk.nextToken());
			int M = Integer.parseInt(stk.nextToken());
			
			for (int i = 1; i <= N; i++) graph.put(i, new HashSet<Integer>());
			for (int i = 0; i < M; i++) {
				stk = new StringTokenizer(io.inputStr());
				Join_Eachother(graph, Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()));
			}
			
			
			boolean isOkay = true;
			int nextVisit = 1;
			while (nextVisit != -1) {
				if(BFS(graph,check,temper,nextVisit) == false) {
					isOkay = false;
					break;
				}
				ClearTemper(temper);
				nextVisit = NextUnvisit(check,N);
			}
			if(isOkay) System.out.println("YES");
			else if(!isOkay) System.out.println("NO");
			ClearTemper(temper);
			ClearCheck(check);
			graph.clear();
			
		}
		
	}
	
	
	
	
	static public void Join_Eachother(HashMap<Integer, HashSet<Integer>> graph, int a, int b) {
		graph.get(a).add(b);
		graph.get(b).add(a);
	}
	static public void ClearTemper(char[] carr) {
		for (int i = 1; i < 20001; i++) carr[i] = 'X';
	}
	static public void ClearCheck(boolean[] barr) {
		for (int i = 1; i < 20001; i++) barr[i] = false;
	}
	
	static public int NextUnvisit(boolean[] check,int max) {
		for(int i = 1; i <= max; i++) {
			if(check[i] == false) return i;
		}
		return -1;
	}
	
	static public boolean BFS(HashMap<Integer, HashSet<Integer>> graph, boolean[] check,char[] temper, int start) {
		Queue<Integer> nodeQueue = new LinkedList<Integer>();
		check[start] = true;
		temper[start] = 'R';
		nodeQueue.add(start);
		while(nodeQueue.isEmpty() == false) {
			int parent = nodeQueue.poll();
			char nextTemper = InverseTemper(temper[parent]);
			for(int next : graph.get(parent)) {
				if(temper[next] == 'X') temper[next] = nextTemper;
			}
			
			for(int next : graph.get(parent)) {
				if(temper[next] != nextTemper) return false;
				if(check[next] == false && temper[next] == nextTemper) {
					check[next] = true;
					nodeQueue.add(next);
				}
			}
		}
		
		return true;
	}
	
	static public char InverseTemper(char c) {
		return (c == 'R') ? 'B': 'R';
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
