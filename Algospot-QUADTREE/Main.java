import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	static int TOTAL, IDX;
	static char[] TEXT;
	
	
	//DP[i][j] 정의 : i번째 위치로부터, j의 비트 0이 된 모든 곳을 탐방하고 오는 최소 비용 값
	
	public static void main(String[] args) throws IOException {
		TOTAL = io.inputInt();
		for(int i = 0; i < TOTAL; i++) {
			Init();
			System.out.println(Quad_Reverse());
		}
	}
	
	static String Quad_Reverse() {
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		StringBuilder sb3 = new StringBuilder();
		if(TEXT[IDX] == 'x') {
			sb3.append('x');
			IDX++;
			for(int i = 0; i < 4; i++) {
				if(i < 2) {
					if(TEXT[IDX] == 'x') sb1.append(Quad_Reverse());
					else sb1.append(TEXT[IDX++]);
				}
				if(2 <= i) {
					if(TEXT[IDX] == 'x') sb2.append(Quad_Reverse());
					else sb2.append(TEXT[IDX++]);
				}
			}
			sb3.append(sb2);
			sb3.append(sb1);
			return sb3.toString();
		}
		else {
			sb3.append(TEXT[IDX]);
			return sb3.toString();
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
