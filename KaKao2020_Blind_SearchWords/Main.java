import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
		int[] ans = mainObj.solve.solution(new String[] {"frodo", "front", "frost", "frozen", "frame", "kakao"}, new String[] {"fro??", "????o", "fr???", "fro???", "pro?"});
		// System.out.println(ans);
		Display(ans, ans.length);
	}

	// import java.util.*;
	class Solution {
		Node[] root = new Node[10001];
		Node[] rev_root = new Node[10001];
		public int[] solution(String[] words, String[] queries) {
			for (int i = 0; i < rev_root.length; i++) {
				root[i] = new Node();
				rev_root[i] = new Node();
			}
			
			for (int i = 0; i < words.length; i++) {
				int len = words[i].length();
				Insert(0, words[i].toCharArray(), root[len]);
				Insert(0, Reverse(words[i].toCharArray()), rev_root[len]);
			}
			int[] ans = new int[queries.length];
			for (int i = 0; i < queries.length; i++) {
				ans[i] = Query(queries[i]);
			}
			return ans;
		}
		public int Query(String str) {
			char[] query = str.toCharArray();
			if(query[0] == '?') return Find_Count(0,Reverse(query),rev_root[query.length]);
			else return Find_Count(0,query,root[query.length]);
		}
		
		public void Insert(int pos, char[] word, Node parent) {
			if(pos == word.length) return;
			Node node = parent.link.getOrDefault(word[pos], null);
			parent.child_Count++;
			if(node == null) {
				node = new Node();
				parent.link.put(word[pos], node);
			}
			Insert(pos+1,word,node);
			return;
		}
		public int Find_Count(int pos, char[] word, Node parent) {
			int ret = 0;
			if(word[pos] == '?') return parent.child_Count;
			else {
				Node node = parent.link.getOrDefault(word[pos], null);
				if(node != null) return Find_Count(pos+1, word, node);
				else return 0;
			}
		}
		public char[] Reverse(char[] arr) {
			int i = 0, j = arr.length-1;
			while(i < j) {
				char temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				i++; j--;
			}
			return arr;
		}
		class Node {
			public int child_Count = 0;
			public HashMap<Character, Node> link = new HashMap<Character, Node>();
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
				System.out.printf(arr[i][j] + " ");
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