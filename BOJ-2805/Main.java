import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main {

	public static void main(String[] args) throws IOException {
		IO_Manager io = new IO_Manager();
		
		StringTokenizer strtok = new StringTokenizer(io.inputStr());
		
		// ---------------input---------------------
		int wood_Count = nextInt(strtok);
		int needed_Length = nextInt(strtok);
		int[] woods = new int[wood_Count];
		strtok = new StringTokenizer(io.inputStr());
		int theLongestWood = 0;
		for (int i = 0; i < woods.length; i++) {
			woods[i] = nextInt(strtok);
			if(woods[i] > theLongestWood) theLongestWood = woods[i]; 
		}
		// ----------------------------------------
		
		
		int min, max, cutter, passedCutLength;
		min = 1;					// 절단기 최소 높이
		max = theLongestWood; // 절단기 최대 높이
		passedCutLength = 0;
		
		while(min <= max) {
			cutter = (min+max)/2;
			long totalWoodLength = Saw(woods,cutter);
			if(totalWoodLength < needed_Length) { // "길이가 모자르므로 더 낮춰서 잘라!"
				max = cutter-1;
			}
			else if(totalWoodLength >= needed_Length) { // "필요한량은 맞는데, 좀 더 나무를 남겨보자."
				passedCutLength = cutter;
				min = cutter+1;
			}
		}
		System.out.println(passedCutLength);
		
	}
	
	
	public static long Saw(int[] woods, int cutLength) {
		long meter=0;
		for (int i = 0; i < woods.length; i++) if(woods[i] > cutLength)meter += (woods[i]-cutLength);
		return meter;
	}
	
	public static int nextInt(StringTokenizer stk) {
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
