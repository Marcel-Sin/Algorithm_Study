import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.Vector;


public class Main {
	static IO_Manager io = new IO_Manager();
	static int min = 9999999;
	static Vector<Integer> button= new Vector<Integer>();
	static boolean zeroPossible = true;
	static int target;
	static int current = 100;
	static int memory;
	public static void main(String[] args) throws IOException {
		for(int i = 0; i < 10; i++) button.add(i);
		target = Integer.parseInt(io.inputStr().trim());
		int breakdown = Integer.parseInt(new String(io.inputStr().trim()));
		if(breakdown > 0) {
			StringTokenizer stk = new StringTokenizer(io.inputStr());
			for (int x = 0; x < breakdown; x++) {
				int data = nextInt(stk);
				for (int i = 0; i < button.size(); i++) {
					if (button.get(i) == data) {
						button.remove(i);
						break;
					}
				}
			}
		}
		//Input end
		//Number+(+,-)   vs    only (+,-)
		int normal_count = Math.abs(target-current);
		int base = normal_count+100;
		int low = (target-base < 0)? 0:target-base;
		int high = target+base;
		if(breakdown != 10) {
			for(int i = low; i <= high; i++) CanMove(i);
		}
		System.out.println(Math.min(min, normal_count));
		
	}	


	static void CanMove(int channel) {
		int n=0, count=0,ch = channel;
		while(ch != 0) {
			count++;
			n = ch % 10;
			if(button.contains(n) == false) return;
			ch /= 10;
		}
		if(channel == 0 && button.contains(0)) count++;
		else if(channel == 0) return;
		n = count+Math.abs(target-channel);
		if(min > n) min = n;
		
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
