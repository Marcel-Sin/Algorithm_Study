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
	static char[] MEMBER;
	static char[] FAN;
	static int MEMBER_SIZE;
	static int FAN_SIZE;
	
	
	
	public static void main(String[] args) throws IOException {
		TOTAL = io.inputInt();
		for(int i = 0; i < TOTAL; i++) {
			Init();
			System.out.println(BruteForce_Solve());
		}
	}
	
	static int BruteForce_Solve() {
		int counter = 0, loop = FAN_SIZE-MEMBER_SIZE;
		for(int i = 0; i <= loop; i++) {
			if(Check(i) == true) counter++;
		}
		return counter;
	}
	
	static boolean Check(int left) {
		for(int i = 0; i < MEMBER_SIZE; i++) {
			if(FAN[left++] == 'M' && MEMBER[i] == 'M') {
				return false;
			}
		}
		return true;
	}
	static int Min(int a, int b) {
		return (a > b)?b:a;
	}
	static int Max(int a, int b) {
		return (a > b)?a:b;
	}
	
	static void Init() throws IOException{
		MEMBER = io.inputStr().toCharArray();
		FAN = io.inputStr().toCharArray();
		MEMBER_SIZE = MEMBER.length;
		FAN_SIZE = FAN.length;
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
