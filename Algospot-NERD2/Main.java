import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.event.ListSelectionEvent;

public class Main {
	static IO_Manager io = new IO_Manager();
	static int TOTAL;
	static TreeMap<Integer,Integer> tree = new TreeMap<Integer, Integer>();
	
	public static void main(String[] args) throws IOException {
		TOTAL = io.inputInt();
		for (int i = 0; i < TOTAL; i++) {
			System.out.println(Init_And_Solve());
			
		}
	}
	
	static int Solve(int x, int y) {
		int upperX, upperY, lowerX, lowerY;
		upperX = 0;
		upperY = 0;
		if (tree.higherKey(x) != null) {
			upperX = tree.higherKey(x);
			upperY = tree.get(upperX);
		}
		// 현재 신청자는 지배되서 참가불가 (너드 아님)
		if (y < upperY) return tree.size();
		
		// 조건 통과하면 삽입 처리전에 lower 조건을 모두 제거.
		while (tree.lowerKey(x) != null) {
			lowerX = tree.lowerKey(x);
			lowerY = tree.get(lowerX);
			if (lowerY < y) {
				tree.remove(lowerX);
			}
			else break;
		}
		tree.put(x,y);
			
		return tree.size();
}

	static int Init_And_Solve() throws IOException {
		tree.clear();
		int count = io.inputInt();;
		int sum = 0;
		
		for(int i = 0; i < count; i++) {
			StringTokenizer stk = new StringTokenizer(io.inputStr());
			int registerCount = Solve(nextInt(stk),nextInt(stk));
			sum += registerCount;
		}
		return sum;
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
		System.out.println("요소갯수 : " + arr.length);
		for (int i = 0; i < limit; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
	static void Display(int[][] arr, int limit) {
		System.out.println("요소갯수 : " + (arr.length * arr[0].length));
		for (int i = 0; i < limit; i++) {
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