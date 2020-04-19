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
	static StringBuilder[] check;
	static int A,target;
	public static void main(String[] args) throws IOException {
		int total = io.inputInt();
		for(int i = 0 ; i < total; i++) {
			check = new StringBuilder[10000];
			StringTokenizer stk = new StringTokenizer(io.inputStr());
			A = nextInt(stk);
			target = nextInt(stk);
			BFS(A);
			System.out.println(check[target].toString());
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
		Queue<DSLR> queue = new LinkedList<DSLR>();
		check[start] = new StringBuilder();
		queue.add(new DSLR(start,""));
		
		while(queue.isEmpty() == false) {
			DSLR parent = queue.poll();
			if(parent.value == target) {
				check[target] = new StringBuilder(parent.command.toString());
				break;
			}
			int n1 = Double(parent.value);
			int n2 = Sub(parent.value);
			int n3 = Left(parent.value);
			int n4 = Right(parent.value);
			if(check[n1] == null) {
				DSLR temp = new DSLR(n1,parent.command.toString());
				temp.command.append('D');
				check[n1] = new StringBuilder(temp.command.toString());
				queue.add(temp);
			}
			if(check[n2] == null) {
				DSLR temp = new DSLR(n2,parent.command.toString());
				temp.command.append('S');
				check[n2] = new StringBuilder(temp.command.toString());
				queue.add(temp);
			}
			if(check[n3] == null) {
				DSLR temp = new DSLR(n3,parent.command.toString());
				temp.command.append('L');
				check[n3] = new StringBuilder(temp.command.toString());
				queue.add(temp);
			}
			if(check[n4] == null) {
				DSLR temp = new DSLR(n4,parent.command.toString());
				temp.command.append('R');
				check[n4] = new StringBuilder(temp.command.toString());
				queue.add(temp);
			}
		}
	}
	
	
	static class DSLR {
		int value;
		StringBuilder command;
		public DSLR(int value, String str) {
			super();
			this.value = value;
			this.command = new StringBuilder(str);
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
