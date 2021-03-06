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
	static int TOTAL;
	static int MAX_TOWN, DAY, START;
	static double[][] MAP = new double[50][50];
	
	static ArrayList<Integer> path = new ArrayList<Integer>(); 
	static ArrayList<Integer> ANS_LIST = new ArrayList<Integer>(); 
	
	
	public static void main(String[] args) throws IOException {
		TOTAL = io.inputInt();
		for(int i = 0; i < TOTAL; i++) {
			Init();
			System.out.println();
			path.add(START);
			for(int x = 0; x < ANS_LIST.size(); x++) System.out.printf("%.8f ",Brute_Solve(ANS_LIST.get(x)));
			path.clear();
		}
		
	}

	static double Brute_Solve(int target) {
		
		int cur = path.get(path.size()-1);
		if(path.size() == DAY+1) {
			if(cur != target) return 0.0;
			double prob = 1.0d;
			for(int i = 0; i < path.size()-1; i++) prob *= MAP[path.get(i)][path.get(i+1)];
			return prob;
		}
		
		double ret = 0.0;
		for(int next = 0; next < MAX_TOWN; next++) {
			if(MAP[cur][next] > 0.0) {
				path.add(next);
				ret += Brute_Solve(target);
				path.remove(path.size()-1);
			}
		}
		return ret;
	}
	
	
	
	static void Init() throws IOException {
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		MAX_TOWN = nextInt(stk);
		DAY = nextInt(stk);
		START = nextInt(stk);
		int num,path;
		for(int i = 0; i < MAX_TOWN; i++) {
			path = 0;
			stk = new StringTokenizer(io.inputStr());
			for(int x = 0; x < MAX_TOWN; x++) {
				num = nextInt(stk);
				if(num == 1) path++;
				MAP[i][x] = (double)num;
			}
			for(int x = 0; x < MAX_TOWN; x++) MAP[i][x] /= path; 
		}
		ANS_LIST.clear();
		io.inputInt();
		stk = new StringTokenizer(io.inputStr());
		while(stk.hasMoreTokens()) ANS_LIST.add(nextInt(stk));
	}
	
	static int nextInt(StringTokenizer stk) {
		return Integer.parseInt(stk.nextToken());
	}
	static long Min(long a, long b) {
		return (a > b)?b:a;
	}
	static long Max(long a, long b) {
		return (a > b)?a:b;
	}
	static int Min(int a, int b) {
		return (a > b)?b:a;
	}
	static int Max(int a, int b) {
		return (a > b)?a:b;
	}
	static void Display(int[] arr,int limit) {
		System.out.println("요소갯수 : " + arr.length);
		for(int i = 0; i < limit; i++) System.out.print(arr[i]+" ");
		System.out.println();
	}
	static void Display(int[][] arr,int limit) {
		System.out.println("요소갯수 : " + (arr.length*arr[0].length));
		for(int i = 0; i < limit; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j]+" ");
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
	}}
