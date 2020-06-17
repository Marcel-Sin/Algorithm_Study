import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	public static void main(String[] args) throws IOException {
		int[] numberA = {6,5,4};
		int[] numberB = {3,2,1};
		ArrayList<Integer> A = new ArrayList<Integer>();
		ArrayList<Integer> B = new ArrayList<Integer>();
		for(int i = 0; i < numberA.length; i++) A.add(numberA[i]);
		for(int i = 0; i < numberB.length; i++) B.add(numberB[i]);
		
		ArrayList<Integer> result = Array_Multiply(A, B);
		for(int i = result.size()-1; i >= 0; i--) System.out.print(result.get(i)+" ");
		
		
	}
	
	
	static void Flow_Processing(ArrayList<Integer> a) {
		//마지막자리에 0을 넣어줌(밑에 자리 올림을 받기 위해)
		a.add(0);
		int len = a.size();
		for(int i = 0; i < len; i++) {
			int n = a.get(i);
			//Borrow Process
			if(n < 0) {
				int borrow = (n*(-1)+9)/ 10;
				a.set(i+1, a.get(i+1)-borrow);
				a.set(i, a.get(i)+borrow*10);
			}
			// Carry Process
			else {
				if(n < 10) continue;
				int carry = n/10;
				a.set(i+1, a.get(i+1)+carry);
				a.set(i, a.get(i)-carry*10);
			}
		}
		for(int i = len-1; i > 1; i--) {
			if(a.get(i) != 0) break;
			a.remove(i);
		}
	}
	static ArrayList<Integer> Array_Multiply(ArrayList<Integer> a,ArrayList<Integer> b) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for(int i = 0; i < a.size()+b.size(); i++) arr.add(0);
		
		for(int i = 0; i < a.size(); i++) {
			for(int j = 0; j < b.size(); j++) {
				arr.set(i+j, arr.get(i+j)+a.get(i)*b.get(j));
			}
		}
		Flow_Processing(arr);
		return arr;
	}
	
	static int Min(int a, int b) {
		return (a > b)?b:a;
	}
	static int Max(int a, int b) {
		return (a > b)?a:b;
	}
	
	static void Init() throws IOException{
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
	}}
