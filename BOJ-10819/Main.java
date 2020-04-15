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


public class Main {
	static IO_Manager io = new IO_Manager();
	static int[] arr;
	static int ans = 0;
	static int caseCount = 0;
	static Queue<Integer> Q = new LinkedList<Integer>();
	public static void main(String[] args) throws IOException {
		caseCount = io.inputInt();
		arr = new int[caseCount];
		int[] order = new int[caseCount];
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		for(int i = 0 ; i < caseCount; i++) {
			arr[i] = nextInt(stk);
			order[i] = i;
		}
		
		while(true) {
			Sum(order);
			if(Next_Permutation(order) == false) break;
		}
		System.out.println(ans);
	}	

	static boolean Next_Permutation(int[] a) {
		if(a.length < 2) return false;
		int len = a.length;
		int ii,i = len-1,j = len;
		
		while(true) {
			ii = i;
			i--;
			if(a[i] < a[ii]) {
				while(a[i] >= a[--j]);
				Swap(a,i,j);
				PartialReverse(a, ii, len-1);
				return true;
			}
			if(i == 0) return false;
		}
	}
	static boolean Prev_Permutation(int[] a) {
		if(a.length < 2) return false;
		int len = a.length;
		int ii,i = len-1,j = len;
		
		while(true) {
			ii = i;
			i--;
			if(a[i] >= a[ii]) {
				while(a[i] < a[--j]);
				Swap(a,i,j);
				PartialReverse(a, ii, len-1);
				return true;
			}
			if(i == 0) return false;
		}
	}
	
	static void Sum(int[] p) {
		int result = 0;
		
		for(int i = 0; i < caseCount-1; i++) {
			result += Math.abs(arr[p[i]] - arr[p[i+1]]);
		}
		if(result > ans) ans = result;
		
	}
	
	static int nextInt(StringTokenizer stk) {
		return Integer.parseInt(stk.nextToken());
	}
	
	static void Swap(int[] a, int i , int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] =temp;
	}	
	static void PartialReverse(int[] a, int start, int end) {
		int temp;
		while(end > start) {
			temp = a[start];
			a[start++] = a[end];
			a[end--] = temp;
		}
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
