import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main {

	public static void main(String[] args) throws IOException {
		IO_Manager io = new IO_Manager();
		StringBuilder sb = new StringBuilder();
		int haveCount = io.inputInt();
		int[] have = new int[haveCount];
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		for(int i = 0; i < haveCount; i++) have[i] = nextInt(stk);
		
		int findCount = io.inputInt();
		int[] find = new int[findCount];
		stk = new StringTokenizer(io.inputStr());
		for(int i = 0; i < findCount; i++) find[i] = nextInt(stk);
		Arrays.sort(have);
		
		int lower,upper;
		for (int i = 0; i < findCount; i++) {
			upper = Upper_Bound(have, find[i]);
			if(upper == -20000000) {
				sb.append('0');
				sb.append(' ');
				continue;
			}
			lower = Lower_Bound(have, find[i]);
			upper = upper - lower +1;
			sb.append(upper+" ");
		}
		io.write(sb.toString());
		io.close();
	
	}


	public static int Lower_Bound(int[] arr, int target) {
		int low,high,mid,result;
		low = 0;
		high = arr.length-1;
		result = -20000000;
		while(low <= high) {
			mid = (low+high)/2;
			if(target <= arr[mid]) {
				if(target == arr[mid]) result = mid;
				high = mid - 1;
			}
			else low = mid + 1;
		}
		return result;
	}
	public static int Upper_Bound(int[] arr, int target) {
		int low,high,mid,result;
		low = 0;
		high = arr.length-1;
		result = -20000000;
		while(low <= high) {
			mid = (low+high)/2;
			if(target >= arr[mid]) {
				if(target == arr[mid]) result = mid;
				low = mid + 1;
			}
			else high = mid - 1;
		}
		return result;
	}
	
	
	public static int nextInt(StringTokenizer stk) {
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
