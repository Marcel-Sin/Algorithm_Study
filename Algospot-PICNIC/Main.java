import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	static int LOOP;
	static boolean[][] FRIEND;
	static int FRIEND_SIZE;
	static boolean[] CHECK;
	
	public static void main(String[] args) throws IOException {
		LOOP = io.inputInt();
		for(int i = 0; i < LOOP; i++) {
			Init();
			System.out.println(DFS(0,0));
		}
		
	}
	
	static int DFS(int cur,int len) {
		if(len == FRIEND_SIZE) return 1;

		
		int counter = 0;
		for(int s = cur; s < FRIEND_SIZE; s++) {
			
			if(CHECK[s] == true) continue;
			for(int i = 0; i < FRIEND_SIZE; i++) {
				if(FRIEND[s][i] == true && CHECK[i] == false) {
					CHECK[s] = CHECK[i] = true;
					counter += DFS(s,len+2);
					CHECK[s] = CHECK[i] = false;
				}
			}
		}// For END
		return counter;
		
	}
	
	
	static void Init() throws IOException{
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		FRIEND_SIZE = nextInt(stk);
		FRIEND = new boolean[FRIEND_SIZE][FRIEND_SIZE];
		CHECK = new boolean[FRIEND_SIZE];
		stk = new StringTokenizer(io.inputStr());
		int a,b;
		while(stk.hasMoreTokens()) {
			a = nextInt(stk);
			b = nextInt(stk);
			if(a > b) {int temp = a; a = b; b = temp;}
			FRIEND[a][b] = true;
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
