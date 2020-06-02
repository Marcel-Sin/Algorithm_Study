import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	static int[] CLOCK = new int[16];
	static int[][] SWITCH = {
			{0,1,2},
			{3,7,9,11},
			{4,10,14,15},
			{0,4,5,6,7},
			{6,7,8,10,12},
			{0,2,14,15},
			{3,14,15},
			{4,5,7,14,15},
			{1,2,3,4,5},
			{3,4,5,9,13}
	};
	static int ANS= Integer.MAX_VALUE,TOTAL;
			
	
	public static void main(String[] args) throws IOException {
		TOTAL = io.inputInt();
		for(int i = 0; i < TOTAL; i++) {
			Init();
			DFS(0,0);
			if(ANS != Integer.MAX_VALUE) System.out.println(ANS);
			else System.out.println(-1);
			ANS = Integer.MAX_VALUE;
		}
		
	}
	
	
	static void DFS(int curSwitch,int pressCount) {
		if(ClockCheck() == true) {
			if(pressCount < ANS) ANS = pressCount;
			return;
		}
		if(curSwitch >= 10) return;
		
		for(int i = 1; i < 4; i++) {
			PressSwitch(curSwitch);
			DFS(curSwitch+1,pressCount+i);
		}
		PressSwitch(curSwitch);
		DFS(curSwitch+1,pressCount);
		
	}
	
	static boolean ClockCheck() {
		for(int i = 0; i < 16; i++) {
			if(CLOCK[i] != 12) return false;
		}
		return true;
	}
	static void PressSwitch(int num) {
		int c = 0;
		for(int i = 0; i < SWITCH[num].length; i++) {
			c = SWITCH[num][i]; 
			CLOCK[c] += 3;
			if(CLOCK[c] == 15) CLOCK[c] = 3;
		}
	}
	
	static void Init() throws IOException{
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		for(int i = 0; stk.hasMoreTokens(); i++) CLOCK[i] = nextInt(stk); 
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
