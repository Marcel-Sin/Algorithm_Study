import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE/2;
	static final int INF = Integer.MAX_VALUE/2;
	
	static final int MAX_VERTEX = 100; 
	
	static int TOTAL;
	
	
	static int[][] matrix = new int[MAX_VERTEX][MAX_VERTEX];
	static int[][] via = new int[MAX_VERTEX][MAX_VERTEX];
	static ArrayList<Integer> list = new ArrayList<Integer>();
	
	
	public static void main(String[] args) throws IOException{
		Init();
		Floyd(7);
		Path(0,5);
		for(int n : list) System.out.print((char)(n+'a') + " ");
		System.out.println("비용 : " + matrix[0][5]);
	}

	
	static void Init() throws IOException {
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				matrix[i][j] = (i == j) ? 0:INF;
				via[i][j] = -1;
			}
		}
		
		Connect(0,1,5);
		Connect(0,2,1);
		Connect(2,3,2);
		Connect(3,1,1);
		Connect(3,4,5);
		//Connect(3,5,3);
		Connect(1,6,3);
		//Connect(1,5,3);
		Connect(6,5,2);
		
		
	}
	
	static void Floyd(int size) {
		//k는 거쳐가는 정점
		for (int k = 0; k < size; k++) {
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if(matrix[i][k]+matrix[k][j] < matrix[i][j]) {
						matrix[i][j] = matrix[i][k]+matrix[k][j];
						via[i][j] = k;
					}
				}
			}
		}
	}
	
	static void Path(int start, int dest) {
		// Base Condition
		if(via[start][dest] == -1) {
			list.add(start);
			if(start != dest) list.add(dest);
			return;
		}
		
		Path(start,via[start][dest]);
		list.remove(list.size()-1);
		Path(via[start][dest],dest);
	}
	
	static void Connect(int a, int b, int w) {
		matrix[a][b] = w;
		matrix[b][a] = w;
	}
	static class Adjacent {
		public int a,b;

		public Adjacent(int a, int b) {
			super();
			this.a = a;
			this.b = b;
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