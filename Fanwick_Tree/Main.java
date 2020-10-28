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
	static final int NINF = Integer.MIN_VALUE; 
	static final int INF = Integer.MAX_VALUE; 
	static int TOTAL;
	static Fenwick_Tree ftree;
	
	public static void main(String[] args) throws IOException {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(6);
		list.add(3);
		list.add(7);
		list.add(8);
		list.add(1);
		list.add(22);
		list.add(9);
		list.add(33);
		
		ftree = new Fenwick_Tree(list);
		System.out.println(ftree.Sum(7)-ftree.Sum(3));
	}
	
	static class Fenwick_Tree {
		int[] data;
		int size;
		
		public Fenwick_Tree(ArrayList<Integer> array) {
			size = array.size()+1;
			data = new int[size];
			for (int i = 0; i < size-1; i++) {
				Update(i,array.get(i));
			}
		}
		
		public int Sum(int k) {
			int ret = 0;
			k++;
			while(0 < k) {
				ret += data[k];
				k &= (k-1); // 111 & X = 110 
			}
			return ret;
		}
		
		public void Update(int k, int added_Value) {
			k++;
			while(k < size) {
				data[k] += added_Value;
				k += (k&-k); // 0001 0010 0100 1000
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