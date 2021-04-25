import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;



public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE / 2;
	static final int INF = Integer.MAX_VALUE / 2;
	static final int MAX = 1000;
	
	public Solution solve = new Solution();

	public static void main(String[] args) throws IOException {
		Execute();
	}
	
	
	static void Execute() {
		Main mainObj = new Main();
		String[] ans = mainObj.solve.solution(new String[][] {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}});
		//Display(ans, ans.length);
		//System.out.println(ans);
		for (int i = 0; i < ans.length; i++) {
			System.out.println(ans[i]);
		}
	}
	
	// import java.util.*;
	class Solution {
		HashMap<String, ArrayList<Ticket>> link = new HashMap<String, ArrayList<Ticket>>();
		String[] ans;
		int N;
		boolean interrupt = false;

		public String[] solution(String[][] tickets) {
			N = tickets.length;
			ans = new String[N + 1];
			// 초기화
			for (int i = 0; i < tickets.length; i++) {
				String start = tickets[i][0], dest = tickets[i][1];
				if(link.containsKey(start) == false) link.put(start, new ArrayList<Ticket>());
				if(link.containsKey(dest) == false) link.put(dest, new ArrayList<Ticket>());
			}
			// 연결 형성
			for (int i = 0; i < tickets.length; i++) {
				ArrayList<Ticket> link_list = link.get(tickets[i][0]);
				link_list.add(new Ticket(tickets[i][1],false));
				link.put(tickets[i][0], link_list);
			}
			// 알파벳 정렬
			for (String key : link.keySet()) {
				ArrayList<Ticket> link_list = link.get(key);
				Collections.sort(link_list);
			}
			DFS("ICN",0);
			return ans;
		}
		void DFS(String here, int n) {
			if (interrupt) return;
			if (N <= n) {
				ans[n] = here;
				interrupt = true;
				return;
			}
			ans[n] = here;
			ArrayList<Ticket> linkList = link.get(here);
			for (int i = 0; i < linkList.size(); i++) {
				Ticket ticket = linkList.get(i);
				if( ticket.isUsed == false ) {
					ticket.isUsed = true;
					DFS(ticket.dest,n+1);
					ticket.isUsed = false;
				}
			}
		}
		
		class Ticket implements Comparable<Ticket> {
			public String dest;
			public boolean isUsed;
			public Ticket(String dest, boolean isUsed) {
				super();
				this.dest = dest;
				this.isUsed = isUsed;
			}
			@Override
			public int compareTo(Ticket o) {
				return this.dest.compareTo(o.dest);
			}
		}
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
		for (int i = 0; i < limit; i++) {
			for (int j = 0; j < limit; j++) {
				System.out.printf(arr[i][j]+" ");
			}
			System.out.println();
		}
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