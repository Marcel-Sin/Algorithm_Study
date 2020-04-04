import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;


public class Main {
	static long swapCounter = 0;
	static int[] sorted = new int[500000];
	public static void main(String[] args) throws IOException {
		IO_Manager io = new IO_Manager();
		int size = io.inputInt();
		int[] arr = new int[size];
		
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		for (int i = 0; i < arr.length; i++) {
			arr[i] = nextInt(stk);
		}
		
		MergeSort(arr,0,arr.length-1);
		System.out.println(swapCounter);
		
		
	}
	
	static void MergeSort(int[] A,int low, int high) {
		//Base Condition
		if( low >= high ) return;
		
		//Divide
		int mid = (low + high)/2;
		
		
		//Conquer
		MergeSort(A,low,mid);
		MergeSort(A,mid+1,high);
		
		
		// Combine
		int i = low, j = mid+1, k = low;
		for(;k<=high; k++) {
			if(mid < i) sorted[k] = A[j++];
			else if(high < j) sorted[k] = A[i++];
			else if(A[i] <= A[j]) sorted[k] = A[i++];
			else {
				swapCounter += mid-i+1;
				sorted[k] = A[j++];
			}
		}
		
		
		//Copy
		for(i = low; i <= high; i++) A[i] = sorted[i];
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