import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
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
		int ans = mainObj.solve.solution("AJAAAAAJA");
		//Display(ans, ans.length);
		System.out.println(ans);
	}
	
	//import java.util.*;
	class Solution {
		int[][] DIST = new int[20][20];
		
		public int solution(String name) {
			char[] letters = name.toCharArray();
			//각 문자의 조작 횟수 총합
			int letter_Manipulation = 0;
			for (int i = 0; i < letters.length; i++) {
				if(letters[i] != 'A') letter_Manipulation+= Manipulate_Value(letters[i]);
			}
			
			//조작이 필요한 인덱스들
			ArrayList<Integer> idxs = new ArrayList<Integer>();
			idxs.add(0); // 0은 시작점이므로 반드시 필요.
			for (int i = 1; i < letters.length; i++) if(letters[i] != 'A') idxs.add(i);
			
			// 조작이 필요한 인덱스끼리 거리 계산
			Intercross_Distance(idxs,letters.length);
			
			//Next Permutation을 돌릴 배열을 형성 (시작점은 고정이므로 제거)
			int change_Distance = 0;
			idxs.remove(0);
			if(0 < idxs.size()) {
				int[] permutation_Array = new int[idxs.size()];
				for (int i = 0; i < permutation_Array.length; i++) permutation_Array[i] = idxs.get(i);
				
				change_Distance = Calc_Distance(permutation_Array);
				while(Next_Permutation(permutation_Array) == true) {
					int value = Calc_Distance(permutation_Array);
					change_Distance = (value < change_Distance) ? value:change_Distance;
				}
			}

			return letter_Manipulation + change_Distance;
		}
		void Intercross_Distance(ArrayList<Integer> v,int size) {
			int start,dest,leftDist,rightDist,dist;
			for (int i = 0; i < v.size(); i++) {
				start = v.get(i);
				for (int j = i+1; j < v.size(); j++) {
					dest = v.get(j);
					rightDist = dest-start;
					leftDist = (size-1)-dest+(start+1);
					dist = (leftDist < rightDist) ? leftDist:rightDist;
					DIST[start][dest] = dist;
					DIST[dest][start] = dist;
				}
			}
		}
		int Manipulate_Value(char c) {
			int a = 'Z'-c+1;
			int b = c-'A';
			return (a < b)? a:b;
		}
		int Calc_Distance(int[] arr) {
			int ret = 0;
			int pre = 0;
			for(int i = 0; i < arr.length; i++) {
				ret += DIST[pre][arr[i]];
				pre = arr[i];
			}
			return ret;
		}
		
		boolean Next_Permutation(int[] arr) {
			if(arr.length <= 1) return false;			
			int i = -1, ii = arr.length-1, j = arr.length-1;
			while(0 < ii && !(arr[ii-1] < arr[ii])) ii--;
			if(ii == 0) return false;
			i = ii-1;
			while(!(arr[i] < arr[j])) j--;
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			Array_Reverse(arr,ii,arr.length-1);
			return true;
		}
		void Array_Reverse(int[] arr, int inStart, int inEnd) {
			int temp = 0;
			while(inStart < inEnd) {
				temp = arr[inStart];
				arr[inStart] = arr[inEnd];
				arr[inEnd] = temp;
				inStart++;
				inEnd--;
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