import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class Main {
	static long swapCounter = 0;
	static int[] sorted = new int[500000];
	public static void main(String[] args) throws IOException {
		IO_Manager io = new IO_Manager();
		String str = io.inputStr();
		char[] chars = str.toCharArray();
		int sum = 0;
		boolean zeroCheck = false;
		for(int i = 0; i < chars.length; i++) {
			int c = chars[i]-48;
			if(c == 0) zeroCheck = true;
			sum += c;
		}
		if (sum % 3 == 0 && zeroCheck == true) {
			Arrays.sort(chars);
			StringBuilder sb = new StringBuilder(new String(chars));
			io.write(sb.reverse().toString());
		}
		else System.out.println(-1);
		io.close();
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
