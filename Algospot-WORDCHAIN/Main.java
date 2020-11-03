import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE; 
	static final int INF = Integer.MAX_VALUE; 
	static final int MAX_LETTER = 26; 
	
	static int TOTAL,N;
	static ArrayList<char[]> WORD = new ArrayList<char[]>();
	static int[][] MATRIX = new int[MAX_LETTER][MAX_LETTER];
	static int[] IN = new int[MAX_LETTER];
	static int[] OUT = new int[MAX_LETTER];
	static Stack<Integer> STACK = new Stack<Integer>();
	static boolean[] PRINTED = new boolean[100];
	
	
	public static void main(String[] args) throws IOException {	
		TOTAL = io.inputInt();
		for (int i = 0; i < TOTAL; i++) {
			Init();
			Solve();
		}
	}
	
	static void Init() throws IOException {
		
		// [Clear]
		STACK.clear();
		WORD.clear();
		for (int i = 0; i < MAX_LETTER; i++) Arrays.fill(MATRIX[i], 0);
		Arrays.fill(IN,0);
		Arrays.fill(OUT,0);
		Arrays.fill(PRINTED,false);
		
		
		// [Input]
		N = io.inputInt();
		for (int i = 0; i < N; i++) WORD.add(io.inputStr().toCharArray());
		
		/*
		// [정렬] : 앞 알파벳 우선적, 끝 알파벳 
		Collections.sort(WORD, (a, b) -> {
			char lastA = a[a.length-1], lastB = b[b.length-1];
			if(a[0] < b[0]) return -1;
			else if(a[0] > b[0]) return 1;
			else {
				if(lastA < lastB) return -1;
				else if(lastA < lastB) return 1;
				else return 0;
			}
		});
		*/
		
		// [단계1] : 인접행렬 생성
		for(int w = 0; w < N; w++) {
			//sc = 첫자리 알파벳
			//ec = 끝자리 알파벳
			int s = WORD.get(w)[0] - 'a';
			int e = WORD.get(w)[WORD.get(w).length-1] - 'a';		
			
			Connect(s,e);
			IN[e]++;
			OUT[s]++;
		}
		
	}
	
	static void Solve() {
		int sp = Check_Euler();
		if (sp == -1) {
			System.out.println("IMPOSSIBLE");
			return;
		}
		else DFS(sp);
		
		if(STACK.size() != N+1) {
			System.out.println("IMPOSSIBLE");
			return;
		}

		int c1 = STACK.pop(), c2 = STACK.pop();
		Printing(c1,c2);
		while(STACK.isEmpty() == false) {
			c1 = c2;
			c2 = STACK.pop();
			Printing(c1,c2);
		}
		System.out.println();
	}
	
	static void DFS(int here) {
		for (int i = 0; i < MAX_LETTER; i++) {
			if(MATRIX[here][i] > 0) {
				MATRIX[here][i] -= 1;
				DFS(i);
			}
		}
		STACK.add(here);
	}
	
	// 오일러 서킷은 0 리턴, 오일러 트레일은 시작지점 리턴, 그외 -1 (오일러 불가)
	static int Check_Euler() {
		int oddIn = 0, oddOut = 0;
		for(int i = 0; i < MAX_LETTER; i++) {
			int dif = OUT[i]-IN[i];
			if(dif == 0) continue;
			else if(dif == -1) oddIn++;
			else if(dif == 1) oddOut++;
			else return -1;
		}
		// "오일러 서킷인가?"
		if(oddIn == 0 && oddOut == 0) {
			for(int i = 0; i < MAX_LETTER; i++) if(OUT[i] > 0) return i; 
		}
		// "오일러 트레일인가?"
		else if (oddIn == 1 && oddOut == 1) {
			for(int i = 0; i < MAX_LETTER; i++) if(IN[i] < OUT[i]) return i; 
		}
		
		// 리턴되지 못했으면 오일러 조건 부합 안됨.
		return -1;
	}
	
	static void Connect(int a, int b) {
		MATRIX[a][b] += 1;
	}

	static void Printing(int s, int e) {
		s = s+'a';
		e = e+'a';
		for(int i = 0; i < N; i++) {
			if(!PRINTED[i]) {
				char ws = WORD.get(i)[0], we = WORD.get(i)[WORD.get(i).length-1];
				if(s == ws && e == we) {
					PRINTED[i] = true;
					System.out.print(new String(WORD.get(i)) +" ");
					return;
				}
			}
		}
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
		//System.out.println("요소갯수 : " + arr.length);
		for (int i = 0; i < limit; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
	static void Display(int[][] arr, int limit) {
		System.out.println("요소갯수 : " + (arr.length * arr[0].length));
		for (int i = 0; i < limit; i++) {
			System.out.print("["+i+"] : ");
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