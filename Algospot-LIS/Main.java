import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	static int TOTAL;
	static int[] MAP = new int[501];
	static int BOUNDER;
	static int ANS;
	
	
	public static void main(String[] args) throws IOException {
		TOTAL = io.inputInt();
		for(int i = 0; i < TOTAL; i++) {
			Init();
			for(int x = 0; x < BOUNDER; x++) BruteForce_Solve(x, 0);
			System.out.println(ANS);
		}
	} 
	static void BruteForce_Solve(int pos,int counter) {
		counter++;
		if(counter > ANS) ANS = counter;
		for(int i = pos+1; i < BOUNDER; i++) {
			if(MAP[pos] < MAP[i]) {
				BruteForce_Solve(i,counter);
			}
		}
	}
	
	static void Init() throws IOException{
		int size = io.inputInt();
		BOUNDER = size;
		int i = 0;
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		while(stk.hasMoreTokens()) MAP[i++] = nextInt(stk);
		ANS = 0;
	}
	

	
	static int nextInt(StringTokenizer stk) {
		return Integer.parseInt(stk.nextToken());
	}
	static int Min(int a, int b) {
		return (a > b)?b:a;
	}
	static int Max(int a, int b) {
		return (a > b)?a:b;
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
