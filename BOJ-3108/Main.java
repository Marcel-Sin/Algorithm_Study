import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	static int[][] MAP = new int[2001][2001];
	static int ANS = 0;
	public static void main(String[] args) throws IOException {
		Initializing();
		if(MAP[1000][1000] == 1) {
			DFS(1000,1000);
		}
		for(int i = 0; i <= 2000; i++) {
			for(int j = 0; j <= 2000; j++) {
				if(MAP[i][j] == 1) {
					DFS(i,j);
					ANS++;
				}
			}
		}
		System.out.println(ANS);
	}	
	
	static void DFS(int i, int j) {
		MAP[i][j] = 2;
		if(i-1 >= 0 && MAP[i-1][j] == 1) DFS(i-1,j);
		if(i+1 <= 2000 && MAP[i+1][j] == 1) DFS(i+1,j);
		if(j-1 >= 0 && MAP[i][j-1] == 1) DFS(i,j-1);
		if(j+1 <= 2000 && MAP[i][j+1] == 1) DFS(i,j+1);
	}


	static void RectCreate(int...args) {
		for(int i = 0 ; i < args.length; i++) {
			if(args[i] < 0) args[i] = args[i]*2+1000;
			else args[i] = args[i]*2+1000;
			
		}
		
		for(int y = args[1]; y <= args[3]; y++) {
			MAP[y][args[0]] = 1;
			MAP[y][args[2]] = 1;
		}
		for(int x = args[0]; x <= args[2]; x++) {
			MAP[args[1]][x] = 1;
			MAP[args[3]][x] = 1;
		}
		
		
	}

	static void Initializing() throws IOException{
		int caseCount = io.inputInt();
		for(int i = 0 ; i < caseCount; i++) {
			StringTokenizer stk = new StringTokenizer(io.inputStr());
			RectCreate(nextInt(stk),nextInt(stk),nextInt(stk),nextInt(stk));
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
