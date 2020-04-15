import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	public static void main(String[] args) throws IOException {
		
		int[] aaa = {1,2,3,5,6};
		int count = 0;
		while(true) {
			for(int n : aaa) System.out.print(n+" ");
			System.out.println();
			count++;
			if(NextPremutation(aaa) == false) break;
		}
		System.out.println(count);
		

	}	

	//역정렬 상태가 마지막 경우로 판단하므로, 오름차 정렬 상태가 반드시 필요
	static boolean NextPremutation(int[] a) {
		int len = a.length;
		if(len < 2) return false;
		int ii, i = len-1, j = len;
		while(true) {
			// 1단계 : [i] < [ii] 를 i를 찾는다.
			ii = i;
			i--;
			if(a[i] < a[ii]) {
				//2단계 : [i] < [j]인 j를 찾는다.
				while(a[i] >= a[--j]);
				//3단계 : i,j 자리를 바꾼다.
				Swap(a,i,j);
				//4단계 : ii부터 끝까지 리버스 시킨다.
				PartialReverse(a,ii,len-1);
				//끝
				return true;
			}
			//조건에 맞는 i가 0번까지 없으면, 현재 마지막 경우의 수열(역정렬 상태)
			if(i == 0) return false;
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
