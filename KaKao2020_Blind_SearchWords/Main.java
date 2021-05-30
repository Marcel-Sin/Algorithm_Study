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
	static final int NINF = Integer.MIN_VALUE / 2;
	static final int INF = Integer.MAX_VALUE / 2;
	static final int MAX = 1000;
	public Solution solve = new Solution();

	public static void main(String[] args) throws IOException {
		Execute();
	}
	static void Execute() {
		Main mainObj = new Main();
		int[] ans = mainObj.solve.solution(new String[] { "frodo", "front", "frost", "frozen", "frame", "kakao" },
				new String[] { "fro??", "fr???", "fro???", "pro?","????o" });
		Display(ans, ans.length);
	}

	// import java.util.*;
	class Solution {
		ArrayList<String>[] list = new ArrayList[10001];
		ArrayList<String>[] rlist = new ArrayList[10001];
		boolean[] list_Sorted = new boolean[10001];
		boolean[] rlist_Sorted = new boolean[10001];
		
		int[] answer;
		
		public int[] solution(String[] words, String[] queries) {
			for(int i = 0; i < list.length; i++) {
				list[i] = new ArrayList<String>();
				rlist[i] = new ArrayList<String>();
			}
			for(int i = 0; i < words.length; i++) {
				String word = words[i];
				int len = word.length();
				list[len].add(word);
				rlist[len].add(Get_Reverse_String(word));
			}
			int[] arr = new int[queries.length];
			for (int i = 0; i < arr.length; i++) {
				arr[i] = Query(queries[i]);
			}
			return arr;
		}
		
		public int Query(String keyStr) {
			//접두 탐색(fro??)이 아니면 모두 접미 탐색(??fre)이다.
			boolean is_Prefix_Type = (keyStr.charAt(0) != '?') ? true:false;
			
			StringBuilder sb = new StringBuilder();
			int len = keyStr.length();
			if(is_Prefix_Type == false) keyStr = Get_Reverse_String(keyStr); 	
			
			for (int i = 0; i < len; i++) {
				if(keyStr.charAt(i) != '?') sb.append(keyStr.charAt(i));
				else break;
			}
			int lower = 0, upper = 0;
			if(is_Prefix_Type == false) {
				if(rlist_Sorted[len] == false) {	Collections.sort(rlist[len]);	rlist_Sorted[len] = true; }
				lower = Lower_Bound(rlist[len],sb.toString());
				if(lower == -1) return 0;
				upper = Upper_Bound(rlist[len],sb.toString());
				return upper-lower;
			}
			else {
				if(list_Sorted[len] == false) {	Collections.sort(list[len]);	list_Sorted[len] = true; }
				lower = Lower_Bound(list[len],sb.toString());
				if(lower == -1) return 0;
				upper = Upper_Bound(list[len],sb.toString());
				return upper-lower;
			}
		}
		
		public int Lower_Bound(ArrayList<String> list, String keyStr) {
			int list_Size = list.size();
			int low = 0, high = list_Size, mid, compare;
			
			while(low < high) {
				mid = (low+high)/2;
				String midStr = list.get(mid);
				compare = Compare_String(keyStr, midStr);
				if(compare <= 0) high = mid;
				else low = mid+1;
			}
			//Over Check
			if(low == list_Size) return -1;
			//Check
			String lowStr = list.get(low);
			for (int i = 0; i < keyStr.length(); i++) {
				if(keyStr.charAt(i) != lowStr.charAt(i)) return -1;
			}
			return low;
		}
		public int Upper_Bound(ArrayList<String> list, String keyStr) {
			int list_Size = list.size();
			int low = 0, high = list_Size, mid, compare;
			
			while(low < high) {
				mid = (low+high)/2;
				String midStr = list.get(mid);
				compare = Compare_String(keyStr, midStr);
				if(compare < 0) high = mid;
				else low = mid+1;
			}
			//Under Check
			if(low == 0) return -1;
			//Check
			String lowStr = list.get(low-1);
			for (int i = 0; i < keyStr.length(); i++) {
				if(keyStr.charAt(i) != lowStr.charAt(i)) return -1;
			}
			return low;
		}
		
		int Compare_String(String key, String s) {
			int len = key.length();
			for (int i = 0; i < len; i++) {
				if(key.charAt(i) == s.charAt(i)) continue;
				else {
					return (key.charAt(i) < s.charAt(i)) ? -1:1;
				}
			}
			return 0;
		}
		
		String Get_Reverse_String(String str) {
			char[] arr = str.toCharArray();
			int i = 0, j = arr.length-1;
			while(i < j) {
				char temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				i++;
				j--;
			}
			return new String(arr);
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