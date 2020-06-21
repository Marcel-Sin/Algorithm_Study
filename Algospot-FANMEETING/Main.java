import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	static ArrayList<Integer> A = new ArrayList<Integer>();
	static ArrayList<Integer> B = new ArrayList<Integer>();
	static ArrayList<Integer> ANS = new ArrayList<Integer>();
	
	static int TOTAL;
	public static void main(String[] args) throws IOException {
		TOTAL = io.inputInt();
		for(int test = 0; test < TOTAL; test++) {
			Init();
			int fan = B.size(), member = A.size(),count = 0;
			ANS = Karatsuba_Multiply(A,B);
			for(int i = member-1; i < fan; i++) if(ANS.get(i) == 0) count++;
			System.out.println(count);
		}
	}   
	
	static ArrayList<Integer> Karatsuba_Multiply(ArrayList<Integer> a, ArrayList<Integer> b) {
		int sizeA = a.size(),sizeB = b.size();
		if(sizeA < sizeB) return Karatsuba_Multiply(b, a);
		
		//Base Condition
		if(sizeA == 0 || sizeB == 0) return new ArrayList<Integer>(); // ret = 0
		if(sizeA+sizeB <= 50) return Array_Multiply(a, b);
		
		int mid = sizeA/2;
		ArrayList<Integer> a0 = new ArrayList<Integer>(a.subList(0, mid));
		ArrayList<Integer> a1 = new ArrayList<Integer>(a.subList(mid, sizeA));
		ArrayList<Integer> b0 = new ArrayList<Integer>(b.subList(0, Min(mid,sizeB)));
		ArrayList<Integer> b1 = new ArrayList<Integer>(b.subList(Min(mid,sizeB), sizeB));
		
		ArrayList<Integer> a0a1 = Array_Add(a0, a1, 0);
		ArrayList<Integer> b0b1 = Array_Add(b0, b1, 0);
		
		ArrayList<Integer> z0 = Karatsuba_Multiply(a0, b0);
		
		ArrayList<Integer> z2 = Karatsuba_Multiply(a1, b1);
		
		ArrayList<Integer> z1 = Karatsuba_Multiply(a0a1, b0b1);
		z1 = Array_Sub(z1, z0);
		z1 = Array_Sub(z1, z2);
		
		ArrayList<Integer> result = new ArrayList<Integer>();
		result = Array_Add(result, z2, mid*2);
		result = Array_Add(result, z0, 0);
		result = Array_Add(result, z1, mid);
		return result;
	}
	static ArrayList<Integer> Array_Add(ArrayList<Integer> a, ArrayList<Integer> b, int pow) {
		ArrayList<Integer> arr = new ArrayList<Integer>(a.subList(0, a.size()));
		int size = b.size()+pow;
		if(arr.size() < size) {
			for(int i = arr.size(); i < size; i++) arr.add(0);
		}
		for(int i = 0; i < b.size(); i++) arr.set(i+pow, arr.get(i+pow)+b.get(i));
		return arr;
	}
	static ArrayList<Integer> Array_Sub(ArrayList<Integer> a, ArrayList<Integer> b) {
		if(a.size() < b.size()) { return Array_Sub(b,a); }
		ArrayList<Integer> arr = new ArrayList<Integer>(a.subList(0, a.size()));
		for(int i = 0; i < b.size(); i++) arr.set(i, arr.get(i)-b.get(i));	
		return arr;
	}
	static void Array_Display(ArrayList<Integer> a) {
		for(int i = a.size()-1; i >= 0; i--) {
			System.out.print(a.get(i));
			if(i % 50 == 0 ) System.out.println();
		}
		System.out.println();
	}
	static ArrayList<Integer> Array_Multiply(ArrayList<Integer> a,ArrayList<Integer> b) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for(int i = 0; i < a.size()+b.size(); i++) arr.add(0);
		for(int i = 0; i < a.size(); i++) {
			for(int j = 0; j < b.size(); j++) {
				arr.set(i+j, arr.get(i+j)+a.get(i)*b.get(j));
			}
		}
		return arr;
	}
	
	static int Min(int a, int b) {
		return (a > b)?b:a;
	}
	static int Max(int a, int b) {
		return (a > b)?a:b;
	}
	
	static void Init() throws IOException{
		char[] valueA = io.inputStr().toCharArray();
		char[] valueB = io.inputStr().toCharArray();
		A.clear();
		B.clear();
		ANS.clear();
		for(int i = 0; i < valueA.length; i++) A.add((valueA[i] == 'F')?0:1);
		for(int i = valueB.length-1; i >= 0; i--) B.add((valueB[i] == 'F')?0:1);
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
