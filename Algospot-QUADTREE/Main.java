import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	static int TOTAL,IDX;
	static char[][] MAP = new char[32][32];
	static char[] TEXT;
	
	
	//DP[i][j] 정의 : i번째 위치로부터, j의 비트 0이 된 모든 곳을 탐방하고 오는 최소 비용 값
	
	public static void main(String[] args) throws IOException {
		TOTAL = io.inputInt();
		for(int i = 0; i < TOTAL; i++) {
			Init();
			Quad_Decompress(0, 0, 32);
			for(int x = 0; x < 32; x++) {
				System.out.println(new String(MAP[x]));
			}
		}
	}
	
	static void Quad_Decompress(int row, int col, int size) {
		if(TEXT[IDX] == 'b') {
			for(int i = 0; i < size; i++) {
				for(int j = 0; j < size; j++) {
					MAP[row+i][col+j] = '■';
				}
			}
			IDX++;
		}
		else if(TEXT[IDX] == 'w') {
			for(int i = 0; i < size; i++) {
				for(int j = 0; j < size; j++) {
					MAP[row+i][col+j] = '□';
				}
			}
			IDX++;
		}
		else {
			int half = size/2;
			IDX++;
			Quad_Decompress(row, col, half);
			Quad_Decompress(row, col+half, half);
			Quad_Decompress(row+half, col, half);
			Quad_Decompress(row+half, col+half, half);
		}
	}

	
	static void Init() throws IOException{
		TEXT = io.inputStr().toCharArray();
		IDX = 0;
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
