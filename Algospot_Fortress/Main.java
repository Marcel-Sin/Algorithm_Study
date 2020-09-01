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

import javax.swing.event.ListSelectionEvent;

public class Main {
	static IO_Manager io = new IO_Manager();
	static int TOTAL;
	static Circle_Node ROOT;
	static ArrayList<Circle_Node> CIRCLES = new ArrayList<Circle_Node>();
	
	public static void main(String[] args) throws IOException {
		TOTAL = io.inputInt();
		for (int i = 0; i < TOTAL; i++) {
			Init();
			System.out.println(Solve());
		}
	}

	
	static void Create_Tree() {
		Circle_Node A,B;
		for(int a = CIRCLES.size()-1; a > 0; a--) {
			for(int b = a-1; b >= 0; b--) {
				A = CIRCLES.get(a);
				B = CIRCLES.get(b);
				if(Check_Including(A, B) == true) {
					A.links.add(B);
					B.links.add(A);
					break;
				}
			}
		}
	}
	static int Solve() {
		Create_Tree();
		for(int i = 0; i < CIRCLES.size(); i++) CIRCLES.get(i).dist = -1;
		CIRCLES.get(0).dist = 0;
		DFS(CIRCLES.get(0));
		
		int maxDist=-1,maxIdx=0;
		for(int i = 0; i < CIRCLES.size(); i++) {
			if(maxDist < CIRCLES.get(i).dist) {
				maxDist = CIRCLES.get(i).dist;
				maxIdx = i;
			}
		}
		
		
		for(int i = 0; i < CIRCLES.size(); i++) CIRCLES.get(i).dist = -1;
		CIRCLES.get(maxIdx).dist = 0;
		DFS(CIRCLES.get(maxIdx));
		
		int maxDist2 = -1;
		for(int i = 0; i < CIRCLES.size(); i++) {
			maxDist2 = Max(maxDist2,CIRCLES.get(i).dist);
		}
		return Max(maxDist,maxDist2);
	}
	
	static void DFS(Circle_Node root) {
		
		int size = root.links.size();
		for(int i = 0; i < size; i++) {
			Circle_Node next = root.links.get(i);
			if(next.dist == -1) {
				next.dist = root.dist + 1;
				DFS(next);
			}
		}
	}
	
	// 원A가 원B 안에 들어가는가?
	static boolean Check_Including(Circle_Node a, Circle_Node b) {
      double dist = Math.sqrt((a.x - b.x) * (a.x - b.x)
                            + (a.y - b.y) * (a.y - b.y));
      return dist < b.r;
    }


	static void Init() throws IOException {
		StringTokenizer temp = new StringTokenizer(io.inputStr());
		int loop = nextInt(temp);
		CIRCLES.clear();
		for(int i = 0; i < loop; i++) {
			StringTokenizer stk = new StringTokenizer(io.inputStr());
			CIRCLES.add( new Circle_Node(nextInt(stk),nextInt(stk),nextInt(stk)));			
		}
		ROOT = CIRCLES.get(0);
		Collections.sort(CIRCLES);
	}
	
	static class Circle_Node implements Comparable<Circle_Node>{
		ArrayList<Circle_Node> links;
		int x,y,r,dist;
		
		public Circle_Node(int x,int y, int r) {
			this.links = new ArrayList<Circle_Node>();
			this.x = x;
			this.y = y;
			this.r = r;
			dist = 0;
		}
		@Override
		public int compareTo(Circle_Node o) {
			if(this.r == o.r) return 0;
			else if(this.r > o.r) return -1;
			else return 1;
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