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
		Vector<Integer> vint = new Vector<Integer>();
		StringBuilder sb = new StringBuilder();
		String str = io.inputStr();
		
		// for fitting 3%str.len == 0, pads it '0'
		int pad = 3 - str.length() % 3;
		for (int i=0; i < pad; i++) sb.append('0');
		sb.append(str);
		
		// 3%n times Loop, such as sub(0,3), sub(3,6) ... 
		int count = 0;
		for (int i=0; i <sb.length()/3; i++) {
			vint.add(binaryToOctal(sb.substring(count, count+3)));
			count += 3;
		}
		
		// start printing when it meets the first '1'.
		int startPrintingIndex = -1;
		for(int i=0; i < vint.size(); i++) {
			if(vint.elementAt(i) > 0) { startPrintingIndex = i;
			break;
			}
		}
		if (startPrintingIndex != -1) {
			for(int i=startPrintingIndex; i < vint.size(); i++) io.write(vint.elementAt(i)+"");
		}
		else io.write(0+"");
		io.close();
	}
		
	static public int nextTokenInt(StringTokenizer stk) {
		return Integer.parseInt(stk.nextToken());
	}

	static public int binaryToOctal(String bstr) {
		int temp = 0;
		if(bstr.charAt(0) == '1') temp += 4;
		if(bstr.charAt(1) == '1') temp += 2;
		if(bstr.charAt(2) == '1') temp += 1;
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
