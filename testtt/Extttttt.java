package testtt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Extttttt {
	public static void main(String[] args) {
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		
		InputStreamReader isr = null;
		BufferedReader br = null;
		
		try {
			//System.out.println("원하는 문자 :")
			//모니터에 정보를 내보내자
			
			osw = new OutputStreamWriter(System.out);
			bw = new BufferedWriter(osw);
			bw.write("원하는 문자 : ");
			bw.flush();
			
			// 키보드를 통해서 정보를 입력 받기
			isr = new InputStreamReader(System.in);
			br = new BufferedReader(isr);
			String msg = br.readLine();
			
			// 반은 정보 출력
			bw.write("받은 문자 : ");
			bw.flush();
			
		} catch (Exception e) {
		}finally {
			try {
				br.close();
				isr.close();
				bw.close();
				osw.close();				
			} catch (Exception e2) {
	
			}
		}
		
	}
	
}
