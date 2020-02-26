import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main {
	
	public static void main(String[] args) throws IOException{
		IO_Manager io = new IO_Manager();
		QuickSearch qs = new QuickSearch();
		Vector<Integer> vector = new Vector<Integer>();
		StringTokenizer stk = new StringTokenizer(io.InputStr());
		int count = nextTokenInt(stk);
		int k = nextTokenInt(stk);
		
		stk = new StringTokenizer(io.InputStr());
		for(int i=0; i < count; i++) vector.add(nextTokenInt(stk));
		
		int findIndex = qs.SearchPos(vector, 0, count-1, k-1);
		io.Print(vector.elementAt(findIndex) +"");
		
		for(int i=0; i < count; i++) 
		io.Close();
	}
	
	
	
	static public int nextTokenInt(StringTokenizer stk) {
		return Integer.parseInt(stk.nextToken());
	}
}


class QuickSearch {
	private int resultIndex;
	
	private <T extends Comparable<T>> int Partition(Vector<T> vector,int start, int end, int pivot) {
		T PV = vector.elementAt(pivot);
		Collections.swap(vector, pivot, end);
		int storeIndex = start;
		for (int i = start; i < end; i++) {
			if (vector.elementAt(i).compareTo(PV) <= 0) {
				Collections.swap(vector, storeIndex, i);
				storeIndex++;
			}
		}
		Collections.swap(vector, storeIndex, end);
		return storeIndex;
	}
	private int QuickPivot(int start,int end) {
		return (start+end)/2;
	}
	private <T extends Comparable<T>> void PartialSort(Vector<T> vector,int start, int end, int k) {
		int pivot = Partition(vector, start, end, QuickPivot(start,end));
		if (pivot == k) {
			resultIndex = k;
			return;
		}
		if (end-start <= 0) return;
		else if (k < pivot) PartialSort(vector, start, pivot-1, k);
		else PartialSort(vector, pivot+1, end, k);
		
	}
	public <T extends Comparable<T>> int SearchPos(Vector<T> vector,int start, int end, int k) {
		resultIndex = -1;
		PartialSort(vector, start, end, k);
		return resultIndex;
	}
}





// ************************************** //
// *-------------IO_Manager--------------* //
// ************************************** //
class IO_Manager {
	private BufferedReader br;
	private BufferedWriter bw;
	
	public IO_Manager() {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
	}
	public Integer InputInt() throws IOException {
		return Integer.parseInt(br.readLine());
	}
	public Long InputLong() throws IOException {
		return Long.parseLong(br.readLine());
	}
	public String InputStr() throws IOException {
		return br.readLine();
	}
	public void Print(String str) throws IOException {
		bw.write(str);
	}
	public void Close() throws IOException {
		br.close();
		bw.close();
	}
}
