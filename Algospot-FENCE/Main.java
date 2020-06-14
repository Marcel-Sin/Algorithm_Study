import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	static int TOTAL;
	static int[] WOOD;
	static int SIZE;
	static int ANS;
	
	public static void main(String[] args) throws IOException {
		TOTAL = io.inputInt();
		for(int i = 0; i < TOTAL; i++) {
			Init();
			System.out.println(DNC_Solve(0,SIZE-1));
		}
	}
	
	static int DNC_Solve(int left, int right) {
		
		//Base Condition
		if (left >= right) return WOOD[left];
		
		//Divide
		int mid = (left+right)/2;
		
		//conquer
		int resultA = Max(DNC_Solve(left,mid),DNC_Solve(mid+1,right));
		int resultB = Max(resultA, Middle_Max(mid,left,right));
		
		return resultB;
	}
	
	static int Middle_Max(int mid, int leftBound, int rightBound) {
		if (leftBound >= rightBound)
			return WOOD[leftBound];
		int size,max, l = mid, r = mid + 1, height;
		height = Min(WOOD[l], WOOD[r]);
		max = height * 2;
		
		while (leftBound < l && r < rightBound) {
			if (WOOD[l - 1] < WOOD[r + 1]) height = Min(height, WOOD[++r]);
			else height = Min(height, WOOD[--l]);
			
			size = height * (r - l + 1);
			max = Max(size, max);
		}
		while (r < rightBound) {
			height = Min(height, WOOD[++r]);
			size = height * (r - l + 1);
			max = Max(size, max);
		}
		while (leftBound < l) {
			height = Min(height, WOOD[--l]);
			size = height * (r - l + 1);
			max = Max(size, max);
		}
		return max;	
	}
	
	static int Min(int a, int b) {
		return (a > b)?b:a;
	}
	static int Max(int a, int b) {
		return (a > b)?a:b;
	}
	
	static void Init() throws IOException{
		SIZE = io.inputInt();
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		WOOD = new int[SIZE];
		for(int i = 0; i < SIZE; i++) WOOD[i] = nextInt(stk);
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
