import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	static int[] arr;
	static int bounder = -1;
	static int sum = 0;
	public static void main(String[] args) throws IOException {
		int size = io.inputInt();
		arr = new int[size];
		for(int i = 0; i < size; i++) arr[i] = io.inputInt();
		Arrays.sort(arr);
		for(int i = 0; i < size; i++) {
			if(arr[i] <= 0) continue;
			bounder = i;
			break;
		}
		Negative_Process();
		Positive_Process();
		System.out.println(sum);	
	}	


	static void Negative_Process() {
		if(bounder == 0) return;
		int negative_count = (bounder == -1)? arr.length:bounder;
		int start = 0, end = negative_count-1;
		
		for(int i = 0; i < negative_count-2; i += 2) {
			sum += arr[i]*arr[i+1];
		}
		if(negative_count%2 == 0) {
			sum += arr[end]*arr[end-1];
		}
		else if(negative_count%2 == 1) {
			sum += arr[end];
		}
	}

	static void Positive_Process() {
		if(bounder == -1) return;
		int positive_count = arr.length - bounder;
		int start = bounder, end = arr.length-1;
		int loopCount;
		int add,mul;
		if(positive_count%2 == 0) loopCount = start+2; 
		else loopCount = start+1;
		for(int i = end; i > loopCount; i-=2) {
			add = arr[i]+arr[i-1];
			mul = arr[i]*arr[i-1];
			sum += (add > mul)?add:mul;
		}
		if(positive_count%2 == 0) {
			add = arr[start]+arr[start+1];
			mul = arr[start]*arr[start+1];
			sum += (add > mul)?add:mul;
		}
		if(positive_count%2 == 1) {
			sum += arr[start];
		}
		
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
