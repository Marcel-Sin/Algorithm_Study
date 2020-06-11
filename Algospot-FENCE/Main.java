import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	static int TOTAL;
	static int[] WOOD;
	static int SIZE;
	static int ANS;
	
	public static void main(String[] args) throws IOException {
		TOTAL = io.inputInt();
		for(int i = 0; i < TOTAL; i++) {
			Init();
			BruteForce_Solve();
			System.out.println(ANS);
		}
	}
	
	static void BruteForce_Solve() {
		int left, right, height,max = 0,value;
		
		for(left = 0; left < SIZE; left++) {
			height = WOOD[left];
			for(right = left; right < SIZE; right++) {
				height = Min(height,WOOD[right]);
				value = (right-left+1)*height;
				max = Max(value,max);
			}
		}
		ANS = max;
	}
	
	static int Min(int a, int b) {
		return (a > b)?b:a;
	}
	static int Max(int a, int b) {
		return (a > b)?a:b;
	}
	
	static void Init() throws IOException{
		SIZE = io.inputInt();
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		WOOD = new int[SIZE];
		for(int i = 0; i < SIZE; i++) WOOD[i] = nextInt(stk);
	}
	static int nextInt(StringTokenizer stk) {
		return Integer.parseInt(stk.nextToken());
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
