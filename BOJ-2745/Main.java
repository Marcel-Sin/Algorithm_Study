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
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		String numberStr = stk.nextToken();
		int radix = nextTokenInt(stk);
		int resultSum = 0;
		
		int p = numberStr.length()-1;
		for (int i=0; i < numberStr.length(); i++) {
			int n = 0;
			char c = numberStr.charAt(i);
			if ('A' <= c && c <= 'Z') {
				n = c-55;
			}
			else {
				n = c-'0';
			}
			resultSum += (n * Pow(radix,p));
			p--;
		}
		
		System.out.println(resultSum);

		
		
	}
		
	static public int nextTokenInt(StringTokenizer stk) {
		return Integer.parseInt(stk.nextToken());
	}

	static public int Pow(int x, int power) {
		
		int temp = 1;
		
		if (x == 0) return 0;
		else if (x >= 1 && power == 0) return 1;
		for (int i = 0; i < power; i++) temp *= x;
		return temp;
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
