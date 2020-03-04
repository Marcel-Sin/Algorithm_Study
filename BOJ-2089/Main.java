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
		int RADIX = -2;
		int number = io.inputInt();
		Vector<Integer> vint = new Vector<Integer>();
		
		int value = number,rest=0;
		while(value != 0) {
			if (value < 0) {
				if(value%2 != 0)value += -2;
				rest = value % RADIX;
				value = value / RADIX;
			}
			else {
				rest = value % RADIX;
				value = value / RADIX;
			}
			rest = Math.abs(rest);
			vint.add(rest);
		}
		if(number == 0) vint.add(0);
		for(int i=vint.size()-1; i >= 0; i--) io.write(vint.elementAt(i)+"");
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
