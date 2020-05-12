import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	public static void main(String[] args) throws IOException {
		int[] t = {9,6,7,5,2,0,1,5,3,9,7,5,6,6,6};
		QuickSearch(t,0,t.length-1,9);
		for(int n : t) System.out.print(n+" ");
		
	}

	static int BinarySearch(int[] a, int target) {
		int s = 0, e = a.length-1,mid = 0;
		while(s <= e) {
			mid = (s+e)/2;
			if(a[mid] < target) s = mid + 1;
			else if(a[mid] > target) e = mid-1;
			else break;
		}
		if(a[mid] == target) return mid;
		else return -1;
		
	}
	
	static int Lower(int[] a, int target) {
		int s = 0, e = a.length, mid = 0;
		while(s < e) {
			mid = (s+e)/2;
			if(a[mid] < target) s = mid + 1;
			else e = mid;
		}
		if(e < a.length && a[e] == target) return e;
		else return -1;
	}
	static int Upper(int[] a, int target) {
		int s = 0, e = a.length, mid = 0;
		while(s < e) {
			mid = (s+e)/2;
			if(a[mid] <= target) s = mid + 1;
			else e = mid;
		}
		if(e != 0 && a[e-1] != target) return -1;
		else if(e == 0 && a[e] != target) return -1;
		else return e;
	}
	
	
	static void Swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	static int Partition(int[] a,int s, int e,int p) {
		int P = a[p], SI = s;
		Swap(a,p,e);
		for(int i = s; i < e; i++) {
			if(a[i] < P) Swap(a,i,SI++);
		}
		Swap(a,e,SI);
		return SI;
	}
	static void QuickSearch(int[] a, int s, int e, int target) {
		if(e <= s) return;
		int part = Partition(a, s, e, (s+e)/2);
		if(a[part] == target) {
			System.out.println(part);
			return;
		}
		else if(a[part] < target) QuickSearch(a,part+1,e,target);
		else QuickSearch(a,s,part-1,target);
		
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
