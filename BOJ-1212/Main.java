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
		Vector<String> vstr = new Vector<String>();
		StringBuilder sb = new StringBuilder(io.inputStr());
		
		
		for(int i=0; i<sb.length(); i++) {
			char c = sb.charAt(i);
			if (c == '7') vstr.add("111");
			else if (c == '6') vstr.add("110");
			else if (c == '5') vstr.add("101");
			else if (c == '4') vstr.add("100");
			else if (c == '3') vstr.add("011");
			else if (c == '2') vstr.add("010");
			else if (c == '1') vstr.add("001");
			else if (c == '0') vstr.add("000");
			else System.err.println("Input Error");
		}
		//First Output Processing
		String temp = vstr.elementAt(0);
		if (temp.compareTo("111") == 0) io.write(temp);
		else if (temp.compareTo("110") == 0) io.write(temp);
		else if (temp.compareTo("101") == 0) io.write(temp);
		else if (temp.compareTo("100") == 0) io.write(temp);
		else if (temp.compareTo("011") == 0) io.write("11");
		else if (temp.compareTo("010") == 0) io.write("10");
		else if (temp.compareTo("001") == 0) io.write("1");
		else if (temp.compareTo("000") == 0) io.write("0");
		
		// ---------------------
		
		for (int i = 1; i < vstr.size(); i++) {
			io.write(vstr.elementAt(i));
		}
		
		io.close();
	}

	
	
	static public int nextTokenInt(StringTokenizer stk) {
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
