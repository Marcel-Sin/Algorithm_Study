import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main {

	public static void main(String[] args) throws IOException {
		IO_Manager io = new IO_Manager();
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		int K = nextInt(stk), N = nextInt(stk);
		long[] len = new long[K];
		long low,mid,high,maxLen=0;
		for(int i=0; i<K; i++) {
			len[i] = io.inputInt();
			if(len[i] > maxLen) maxLen = len[i];
		}
		
		low = 1;
		high = maxLen;
		mid = (low+high)/2;
		
		while(low <= high) {

			long lenCounter = LenCounting(len, mid);
			//System.out.println(low+"~"+high+"(mid :"+mid+") = "+lenCounter);
			if( lenCounter < N) {
				high = mid-1;
				mid = (low+high)/2;
			} 
			else if ( lenCounter >= N) {
				maxLen = mid;
				low = mid+1;
				mid = (low+high)/2;
			}
		}
		System.out.println(maxLen);

	}
	public static int nextInt(StringTokenizer stk) {
		return Integer.parseInt(stk.nextToken());
	}

	public static long LenCounting(long[] len, long cutLength) {
		long counter = 0;
		for(int i=0; i < len.length; i++) counter += len[i]/cutLength;	
		return counter;
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
