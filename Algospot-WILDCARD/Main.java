import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	static int TOTAL;
	static char[] WILD;
	static char[][] DB;
	static int[][] DP;
	static ArrayList<String> ANS = new ArrayList<String>();
	
	
	public static void main(String[] args) throws IOException {
		TOTAL = io.inputInt();
		for(int i = 0; i < TOTAL; i++) {
			Init();
			Wild_Search();
		}
	} 
		
	static void Wild_Search() {
		ANS.clear();
		for(int i = 0; i < DB.length; i++) {
			DP_Clear();
			if(Compare_Wild(WILD,0,DB[i],0) == 1) ANS.add(new String(DB[i]));
		}
		Collections.sort(ANS);
		for(int i = 0; i < ANS.size(); i++) System.out.println(ANS.get(i));
	}
	
	static void DP_Clear() {
		for(int i=0;i<DP.length; i++) Arrays.fill(DP[i], -1);
	}
	
	static int Compare_Wild(char[] origin,int originStart, char[] data, int dataStart) {
		int originPos = originStart, dataPos = dataStart;
		int originEnd = origin.length-1, dataEnd = data.length-1;
		if(DP[originStart][dataStart] != -1) return DP[originStart][dataStart]; 
		while(originPos <= originEnd && dataPos <= dataEnd) {
			if(origin[originPos] != '?' && origin[originPos] != data[dataPos]) break;
			originPos++;
			dataPos++;
		}
		// 1. 패턴 문자가 끝에 도달했다. (true 경우가 존재)
		// 2. 비교 문자가 끝에 도달했다. (true 경우가 존재)
		// 3. origin에서 '*'를 만났다. (true 경우가 존재)
		
		// 1. 패턴 문자 마지막까지 통과했을 시,
		if(originPos == originEnd+1) {;
			int originLength = originEnd - originStart;
			int dataLength = dataEnd - dataStart;
			if (dataLength == originLength) DP[originStart][dataStart] = 1;
			else DP[originStart][dataStart] = 0;
			return DP[originStart][dataStart];
		}
		// 2. 비교 문자가 끝에 도달했다.
		if(dataPos == dataEnd+1) {
			for(int i = originPos; i <= originEnd; i++) if(origin[i] != '*') { 
				DP[originStart][dataStart] = 0;
				return DP[originStart][dataStart];
			}
			DP[originStart][dataStart] = 1;
			return DP[originStart][dataStart];
		}
		if(origin[originPos] == '*') {
			//마지막 번째자리가 *이였을 경우, 비교문자 마지막이 무엇이든 트루
			if(originPos == originEnd) {
				DP[originStart][dataStart] = 1;
				return DP[originStart][dataStart];
			}
			for(int skip = 0; dataPos+skip <= dataEnd; skip++) {
				if(Compare_Wild(origin,originPos+1,data,dataPos+skip) == 1) 
					{
						DP[originStart][dataStart] = 1;
						return DP[originStart][dataStart];
					}
			}
		}
		DP[originStart][dataStart] = 0;
		return DP[originStart][dataStart];
	}
	
	static void Init() throws IOException{
		WILD = io.inputStr().toCharArray();
		int db_size = io.inputInt();
		DB = new char[db_size][];
		DP = new int[101][101];
		for(int i = 0; i < db_size; i++) DB[i] = io.inputStr().toCharArray();
		for(int i = 0; i < 101; i++) Arrays.fill(DP[i], -1);
	}
	static int nextInt(StringTokenizer stk) {
		return Integer.parseInt(stk.nextToken());
	}
	static int Min(int a, int b) {
		return (a > b)?b:a;
	}
	static int Max(int a, int b) {
		return (a > b)?a:b;
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
