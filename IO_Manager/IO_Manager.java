package ps_tools;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 처리 속도가 빠른 Buffered IO를 기본 System.in, System.out로
 * 바로 사용 가능하게 구성.
 * <br>
 * <br>
 * <b>[Example]</b>
 * <pre>
 * {@code
 * IO_Manager io = new IO_Manager();
 * int n = io.inputInt();
 * long ln = io.inputLong();
 * String s = io.inputStr();
 * io.write(s);
 * }
 * </pre>
 * 
 * <br>
 * @author MarcelSin
 * @version 1.0
 */
public class IO_Manager {
	private BufferedReader br;
	private BufferedWriter bw;
	
	/**
	 * System.in, System.out을 바탕으로
	 * br, bw에 각각 BufferedReader, BufferedWriter를 형성합니다.
	 */
	public IO_Manager() {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
	}
	public String InputStr() throws IOException {
		return br.readLine();
	}
	public Integer InputInt() throws IOException {
		return Integer.parseInt(br.readLine());
	}
	public Long InputLong() throws IOException {
		return Long.parseLong(br.readLine());
	}
	public void Print(String str) throws IOException {
		bw.write(str);
	}
	/**
	 * 각 버퍼 입출력을 모두 닫습니다.
	 * @throws IOException
	 * Buffered IO 관련
	 */
	public void Close() throws IOException {
		br.close();
		bw.close();
	}
}
