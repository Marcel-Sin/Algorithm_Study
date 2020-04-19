import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	static char[] check = new char[10000];
	static int[] trace = new int[10000];
	static int A,target;
	public static void main(String[] args) throws IOException {
		int total = io.inputInt();
		
		for(int i = 0 ; i < total; i++) {
			Arrays.fill(check, (char)0);
			Arrays.fill(trace, -1);
			StringTokenizer stk = new StringTokenizer(io.inputStr());
			A = nextInt(stk);
			target = nextInt(stk);
			BFS(A);
			Print(target);
			io.bw.flush();
			System.out.println();
		}
		
		
	
	}	
	
	static int Double(int number) {
		return (number*2)%10000;
	}	
	static int Sub(int number) {
		return (number-1 < 0)? 9999:number-1;
	}
	static int Left(int number) {
		int MLN = number/1000;
		number *= 10;
		number %= 10000;
		number += MLN;
		return number;
	}
	static int Right(int number) {
		int MRN = (number%10)*1000;
		number /= 10;
		number += MRN;
		return number;
	}
	

	
	static void BFS(int start) {
		Queue<Integer> queue = new LinkedList<Integer>();
		check[start] = 0;
		trace[start] = start;
		queue.add(start);
		
		while(queue.isEmpty() == false) {
			int parent = queue.poll();
			if(parent == target) {
				break;
			}
			int n1 = Double(parent);
			int n2 = Sub(parent);
			int n3 = Left(parent);
			int n4 = Right(parent);
			if(trace[n1] == -1) {
				check[n1] = 'D';
				trace[n1] = parent;
				queue.add(n1);
			}
			if(trace[n2] == -1) {
				check[n2] = 'S';
				trace[n2] = parent;
				queue.add(n2);
			}
			if(trace[n3] == -1) {
				check[n3] = 'L';
				trace[n3] = parent;
				queue.add(n3);
			}
			if(trace[n4] == -1) {
				check[n4] = 'R';
				trace[n4] = parent;
				queue.add(n4);
			}
			
			
		}
	}
	
	
	static void Print(int number) throws IOException{
		if(number != trace[number]) {
			Print(trace[number]);
			io.bw.write(check[number]);
		}
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
