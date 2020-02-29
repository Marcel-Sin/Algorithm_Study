import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main {
	
	public static void main(String[] args) throws IOException{
		IO_Manager io = new IO_Manager();
		Vector<Long> results = new Vector<Long>();
		int count = io.inputInt();
		int[][] arr = new int[count][];
		StringTokenizer stk;
		
		for (int i=0; i < count; i++) {
			stk = new StringTokenizer(io.inputStr());
			int loopCount = nextTokenInt(stk);
			arr[i] = new int[loopCount];
			for (int j = 0; j < loopCount; j++) arr[i][j] = nextTokenInt(stk);
		}
		// ---  input End. ---
		
		for(int i = 0; i < count; i++) {
			long temp = 0;
			for(int a = 0; a < arr[i].length-1; a++) {
				for(int b = a+1; b < arr[i].length; b++) {
					temp += GCD(arr[i][a],arr[i][b]);
				}
			}
			results.add(temp);
		}
		
		for(long n : results) io.write(n+"\n");
		io.close();
		

		
		
	}
		
	static public int nextTokenInt(StringTokenizer stk) {
		return Integer.parseInt(stk.nextToken());
	}

	static public int GCD(int a, int b) {
		int rest, temp1=a, temp2=b;
		while(true) {
			rest = temp1 % temp2;
			if (rest == 0) break;
			temp1 = temp2;
			temp2 = rest;
		}
		return temp2;
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
