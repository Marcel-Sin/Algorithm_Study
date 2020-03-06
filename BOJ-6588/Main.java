import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main {
	
	public static void main(String[] args) throws IOException{
		IO_Manager io = new IO_Manager();
		final int MAX = 1000000,MAX_SQRT = 1000;
		//by using 'false'array, it doesn't need Initializing.
		boolean[] notPrime = new boolean[MAX+1]; 
		
		for(int i = 2; i <= MAX_SQRT; i++) {
			for(int j = i*2; j <= MAX; j += i) notPrime[j] = true;
		}
		notPrime[1] = false; //it's not prime
		notPrime[2] = true; //it's not odd.
		
		int num,a,b;
		String str = io.inputStr();
		while(str.compareTo("0") != 0) {
			num = Integer.parseInt(str);
			for(int i=3; i < num; i++) {
				if(notPrime[i] == true) continue;
				if(notPrime[num-i] == false) {
					a = i;
					b = num-i;
					io.write(""+ num +" = "+ a +" + "+ b +"\n");
					break;
				}
			}
			str = io.inputStr();
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
