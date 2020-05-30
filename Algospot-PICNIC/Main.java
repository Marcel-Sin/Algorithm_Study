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
	static int FRIEND_SIZE;
	static ArrayList<Integer>[] FRIEND;   
	static boolean[] CHECK;
	static int COUNTER;
	
	public static void main(String[] args) throws IOException {
		LOOP = io.inputInt();
		for(int i = 0; i < LOOP; i++) {
			Init();
			DFS(0,0);
			System.out.println(COUNTER);
		}

	}
	
	static void DFS(int cur,int len) {
		if(len == FRIEND_SIZE) {
			COUNTER++;
			return;
		}
		//2명씩 짝지어주고 DFS로 넘기기.
		for(int s = cur; s < FRIEND_SIZE; s++) {
			if(CHECK[s] == false) {
				for(int i = 0; i < FRIEND[s].size(); i++) {
					if( CHECK[FRIEND[s].get(i)] == false ) {
						CHECK[FRIEND[s].get(i)] = CHECK[s] = true;
						DFS(s,len+2);
						CHECK[FRIEND[s].get(i)] = CHECK[s] = false;
					}
				}
			}
		}
		
	}
	
	static void Init() throws IOException{
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		FRIEND_SIZE = nextInt(stk);
		int relation = nextInt(stk);
		COUNTER = 0;
		CHECK = new boolean[FRIEND_SIZE];
		FRIEND = new ArrayList[FRIEND_SIZE];
		for (int i = 0; i < FRIEND_SIZE; i++) FRIEND[i] = new ArrayList<Integer>();
		
		stk = new StringTokenizer(io.inputStr());
		int a,b;
		for(int i = 0; i < relation; i++) {
			a = nextInt(stk);
			b = nextInt(stk);
			if(a > b) {int temp = a; a=b; b=temp;}
			FRIEND[a].add(b);
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
