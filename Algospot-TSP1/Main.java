import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	static int TOTAL;
	static int SIZE;
	static double[][] DIST;
	static double ANS;
	static boolean[] CHECK;
	static ArrayList<Integer> PASSED = new ArrayList<Integer>();
	
	public static void main(String[] args) throws IOException {
		
		TOTAL = io.inputInt();
		for(int i = 0; i < TOTAL; i++) {
			Init();
			ANS = Float.MAX_VALUE;
			Solve();
			System.out.printf("%.10f\n", ANS);
		}
		
	}
	
	static void Solve() {
		for(int i = 0; i < SIZE; i++) {
			PASSED.add(i);
			CHECK[i] = true;
			DFS(i,0);
			CHECK[i] = false;
			PASSED.clear();
		}
	}
	
	static void DFS(int curPos,double len) {
		if(PASSED.size() == SIZE) {
			double total_len = len; //DIST[curPos][PASSED.get(0)];
			if(total_len < ANS) ANS = total_len;
			return;
		}
						
		for (int i = 0; i < SIZE; i++) {
			if (CHECK[i] == false) {
				PASSED.add(i);
				CHECK[i] = true;
				DFS(i, len + DIST[curPos][i]);
				CHECK[i] = false;
				PASSED.remove(PASSED.size()-1);
			}
		}
		
	}
	
	static void Init() throws IOException{
		SIZE = io.inputInt();
		DIST = new double[SIZE][SIZE];
		CHECK = new boolean[SIZE];
		StringTokenizer stk = null;
		for(int a = 0; a < SIZE; a++) {
			stk = new StringTokenizer(io.inputStr());
			for(int b = 0; b < SIZE; b++) DIST[a][b] = Double.parseDouble((stk.nextToken()));	
		}
	}
	static int nextInt(StringTokenizer stk) {
		return Integer.parseInt(stk.nextToken());
	}

	static int BitSet(int value, int idx) {
		int temp = 1 << idx;
		return value|temp;
	}
	static int BitUnset(int value, int idx) {
		int temp = ~(1 << idx);
		return value&temp;
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
