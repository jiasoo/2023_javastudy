package jdbc.com.ict.edu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Ex04_Statement_intsert {
	public static void main(String[] args) {
		// 접속과 처리를 위한 클래스 3개
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "c##jiasoo";
			String password = "1111";
			conn = DriverManager.getConnection(url, user, password);
			// insert (6번, 이강인, 대한민국 서우르 000-9000-0001)추가
			String sql = "insert into customer(custid,name,address,phone)"
					+" values(8,'이강인','대한민국 서울','010-9000-0001')";			
			stmt = conn.createStatement();
			
			result = stmt.executeUpdate(sql);
			
			if(result > 0) {
				// 성공하면 select문을 이용해서 보자
				sql = "select * from customer order by custid asc";
				//stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while(rs.next()) {
					System.out.println(rs.getString("custid")+"\n");
					System.out.println(rs.getString("name")+"\n");
					System.out.println(rs.getString("address")+"\n");
					System.out.println(rs.getString("phone")+"\t");
				}
			}else {
				System.out.println("삽입 실패1");
			}
			
		} catch (Exception e) {
			System.out.println("제대로된 데이터를 넣어주세요 : " + e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e2) {

			}
		}
	}
	
}
