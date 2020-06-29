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
	static int INF = Integer.MAX_VALUE/2;
	static int[] PI;
	static int SIZE;
	static int[] DP = new int[10001];
	
	public static void main(String[] args) throws IOException {
		TOTAL = io.inputInt();
		for(int i = 0; i < TOTAL; i++) {
			Init();
			System.out.println(DP_Solve(SIZE-1));
		}
	} 
   //DP[i] 정의 : i지점까지의 난이도 최소 합
	static int DP_Solve(int pos) { 
		if(DP[pos] != -1) return DP[pos];
		if(pos < 5) return DP[pos] = Rank(PI,0,pos);
		int min = INF;
		for (int len = 3; len <= 5; len++) {
			int bounder = pos-len;
			if(bounder < 2) break;
			min = Min(min, DP_Solve(bounder)+Rank(PI,bounder+1,pos));
		}
		return DP[pos] = min;
	}
	
	static int Rank(int[] pi, int inStart, int inEnd) {
		if(inStart < 0) return INF;
		int len = inEnd-inStart+1;
		if(len < 3) return INF;
		
		// 모든 숫자가 같다 : 난이도 1
		boolean check = true;
		for(int i = inStart; i < inEnd; i++) if(pi[i] != pi[i+1]) {check = false; break;}	
		if(check == true) return 1;
		
		// 단조 증가 : 난이도 2
		int d = pi[inStart+1]-pi[inStart];
		if( d == 1 || d == -1) {
			check = true;
			for(int i = inStart+1; i < inEnd; i++) if( (pi[i]+d) != pi[i+1] ) {check = false; break;} 
		}
		if(check == true) return 2;
		
		// 번갈아 출현 : 난이도 4 (길이 3,4,5에 대해서만 처리)
		check = true;
		if(len == 3) {
			if(pi[inStart] != pi[inStart+2]) check = false;
		}
		else if(len == 4) {
			if(pi[inStart] != pi[inStart+2] || pi[inStart+1] != pi[inStart+3]) check = false;
		}
		else if(len == 5) {
			if(pi[inStart] != pi[inStart+2] || pi[inStart] != pi[inStart+4] || pi[inStart+1] != pi[inStart+3]) check = false;
		}
		if(check == true) return 4;
		
		
		// 등차수열을 이룸 : 난이도 5
		check = true;
		for(int i = inStart+1; i < inEnd; i++) if( (pi[i]+d) != pi[i+1] ) {check = false; break;}
		if(check == true) return 5;
		
		return 10;
	}
	static void Init() throws IOException{
		
		char[] value = io.inputStr().toCharArray();
		SIZE = value.length;
		PI = new int[SIZE];
		Arrays.fill(DP, -1);
		for(int i = 0; i < SIZE; i++) PI[i] = value[i]-'0';
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
