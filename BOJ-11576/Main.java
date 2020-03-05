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
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		int fromRADIX = nextTokenInt(stk);
		int toRADIX = nextTokenInt(stk);
		int count = io.inputInt();
		stk = new StringTokenizer(io.inputStr());
		int decSum=0;
		
		for (int i = count-1; i >= 0; i--) decSum += nextTokenInt(stk) * pow(fromRADIX,i);
		
		int value = decSum, rest = 0;
		while(value != 0) {
			rest = value % toRADIX;
			value = value / toRADIX;
			vint.add(rest);
		}
		for (int i = vint.size()-1; i >= 0; i--) io.write(vint.elementAt(i)+" ");
		io.close();
	}

	

	static public int pow(int n, int x) {
		int temp = 1;
		if (n == 1) return 1;
		else if (n == 0) return 0;
		else if (x == 0 && n > 0) return 1;
		for(int i = 0; i < x; i++) temp *= n;
		return temp;
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
