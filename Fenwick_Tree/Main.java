import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE / 4;
	static final int INF = Integer.MAX_VALUE / 4;
	static int TOTAL;

	public static void main(String[] args) throws IOException {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i <= 9; i++) list.add(i);
		
		Fenwick tree = new Fenwick(list);
		for (int i = 0; i < 9; i++) System.out.println(tree.Sum(i));
	}
	
	static class Fenwick {
		public int[] arraySum;
		public int size;
		
		public Fenwick(ArrayList<Integer> list) {
			size = list.size()+1;
			this.arraySum = new int[size];
			Init(list);
		}
		
		public int LSB_Zero(int value) {
			return value &= (value-1);
		}	
		public int LSB_Add(int value) {
			return value += (value & -value);
		}
		
		public void Init(ArrayList<Integer> list) {
			for (int i = 0; i < size-1; i++) {
				Add(i,list.get(i));
			}
		}
		
		public int Sum(int idx) {
			idx++;
			int ret = 0;
			while(idx > 0) {
				ret += arraySum[idx];
				idx = LSB_Zero(idx);
			}
			return ret;
		}
		public void Add(int idx, int value) {
			idx++;
			while(idx < size) {
				arraySum[idx] += value;
				idx = LSB_Add(idx);
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
		// System.out.println("요소갯수 : " + arr.length);
		for (int i = 0; i < limit; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
	static void Display(int[][] arr, int limit) {
		System.out.println("요소갯수 : " + (arr.length * arr[0].length));
		for (int i = 0; i < limit; i++) {
			System.out.print("[" + i + "] : ");
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