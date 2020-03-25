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
import java.util.Vector;

public class Main {

	public static void main(String[] args) throws IOException {
		IO_Manager io = new IO_Manager();
		
		// ---------------input---------------------
		StringTokenizer strtok = new StringTokenizer(io.inputStr());
		int home_Count = nextInt(strtok);
		int needed_Hub = nextInt(strtok);
		int[] homes = new int[home_Count];
		for (int i = 0; i < homes.length; i++) homes[i] = io.inputInt();
		Arrays.sort(homes);
		int theFarthestHome = homes[homes.length-1];
		// ----------------------------------------
		
		int min,max,hubGap,passedValue;
		min = 1;
		max = (theFarthestHome/needed_Hub)+1;
		hubGap = 0;
		passedValue = 0;
		
		while(min <= max) {
			hubGap = (min+max)/2;
			int installed_Hub = HUB_Install(homes, hubGap);
			if(installed_Hub < needed_Hub) {
				max = hubGap-1;
			}
			else if(installed_Hub >= needed_Hub) {
				passedValue = hubGap;
				min = hubGap+1;
			}
		}
		
		System.out.println(passedValue);
	}

	public static int HUB_Install(int[] homes, int gap) {
		int counter = 1;
		int installed = 0;
		for(int i=1; i < homes.length; i++) {
			if(homes[i]-homes[installed] < gap) continue;
			installed = i;
			counter++;
		}
		return counter;
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
