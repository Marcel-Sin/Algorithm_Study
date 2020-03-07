import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException{
		IO_Manager io = new IO_Manager();
		int n,r,c,resultA,resultB;
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		n = nextTokenInt(stk);
		r = nextTokenInt(stk);
		
		resultA = FactorCounter(n,5) - FactorCounter(r,5) - FactorCounter(n-r,5);
		resultB = FactorCounter(n,2) - FactorCounter(r,2) - FactorCounter(n-r,2);
		System.out.println((resultA < resultB)? resultA : resultB);
		io.close();
	}

	

	static public int nextTokenInt(StringTokenizer stk) {
		return Integer.parseInt(stk.nextToken());
	}
	static public int	FactorCounter(long x, long f) {
		long count = 0;
		long factor = f;
		while(x/factor != 0) {
			count += x/factor;
			factor *= f;
		}
		return (int)count;
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
