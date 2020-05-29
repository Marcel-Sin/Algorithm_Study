import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	static char[][] MAP = new char[5][];
	static char[] WORD;
	static int WORD_LEN;
	static int MAX_INDEX = 4;
	static boolean resultCheck;
	static int[][] DIR = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
	
	public static void main(String[] args) throws IOException {
		Init();
		int loop = io.inputInt();
		System.out.println();
		for(int i = 0; i < loop; i++) {
			WORD = io.inputStr().toCharArray();
			WORD_LEN = WORD.length;
			resultCheck = false;
			
			for(int y = 0; y < 5; y++) {
				for(int x = 0; x < 5; x++) {
					if(MAP[y][x] == WORD[0]) DFS(y,x,1);
					if(resultCheck == true) { y = 6; break;}
				}
				
			}
			
			if(resultCheck == true) System.out.println(new String(WORD)+" Yes");
			else System.out.println(new String(WORD)+" No");
		} // for END
	}
	
	static void DFS(int r,int c,int len) {
		if(len == WORD_LEN) {
			resultCheck = true;
			return;
		}
		int nextR,nextC;
		for(int i = 0; i < 8; i++) {
			nextR = r-DIR[i][0];
			nextC = c-DIR[i][1];
			if(nextR < 0 || nextC < 0 || nextR > MAX_INDEX || nextC > MAX_INDEX || MAP[nextR][nextC] != WORD[len]) continue;
			DFS(nextR,nextC,len+1);
		}
	}
	
	static void Init() throws IOException{
		io.inputInt();
		for(int i = 0; i < 5; i++) MAP[i] = io.inputStr().toCharArray();
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
