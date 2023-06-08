package day18.com.ict.edu;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Ex11_Map {
	// 대한민국, 캐나다, 영국, 스위스의 수도가 가가 HashMap에 저장시키고
	// 원하는 나라가 key값이 되어, 화면에서 나라를 물어보면 수도가
	// 출력되는 프로그램 작성
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		HashMap<String, String> map = new HashMap<>();
		map.put("대한민국", "서울");
		map.put("캐나다", "오타와");
		map.put("영국", "런던");
		map.put("스위스", "베른");
		
		Set<String> keys = map.keySet();
		esc:
		while (true) {
			System.out.println("나라입력 : ");
			String str = scan.next();
			if(keys.contains(str)) {
				String res = map.get(str);
				System.out.println(str + "의 수도는 " +res + "입니다.");
			}else {
				System.out.println("데이터에 없는 나라 입니다.");
			}
			while (true) {
				System.out.println("계속할까요?(y/n) >>>");
				String msg = scan.next();
				if(msg.equalsIgnoreCase("y")) {
					continue esc;
				}else if(msg.equalsIgnoreCase("n")) {
					break esc;
				}else {
					continue;
				}
			}
		}	
	}
}