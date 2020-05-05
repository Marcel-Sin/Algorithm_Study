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
	static int TARGET = 0,COUNT = 0;
	static boolean[] PRIME;
	static int[] PRIME_LIST = new int[283147];
	public static void main(String[] args) throws IOException {
		long start = System.currentTimeMillis();
		Initializing();
		
		int left = 0, right = 0, sum = 0, size = PRIME_LIST.length,before = 0;
		while(true) {
			if(sum >= TARGET) sum -= PRIME_LIST[left++];
			else if(right == size) break;
			else {
				before = PRIME_LIST[right++];
				sum += before;
			}
			
			if(sum == TARGET) COUNT++;
			if(before > TARGET) break;
		}
		System.out.println(COUNT);
	}
	
	static void Initializing() throws IOException{
		PRIME = new boolean[4000001];
		//Arrays.fill(PRIME,true);
		PRIME[0] = true;
		PRIME[1] = true;
		int eratos_Size = 2000;
		for(int i = 2; i <= eratos_Size; i++) {
			for(int j = i+i; j <= 4000000; j+=i) {
				PRIME[j] = true;
			}
		}
		int c = 0;
		for(int i = 2; i <= 4000000; i++) if(PRIME[i] == false) PRIME_LIST[c++] = i;
		TARGET = io.inputInt();
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
	}
}
