package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	public static void main(String[] args) {
		Connection conn = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String Driver = "jdbc:oracle:thin:@localhost:1521:xe";
			String User = "db7";
			String Pass = "1234";
			conn = DriverManager.getConnection(Driver, User, Pass);
			System.out.print("Oracle ���ӿ� �����Ͽ����ϴ�!\n");
		} catch (SQLException e) {
			System.out.println("���� ���� : " + e);
		} catch (Exception ex) {
			System.out.println("���� �߻� : " + ex);
		} finally {
			if (conn != null) {
				try {
					conn.close();
					System.out.print("Oracle ���� ����!");
				} catch (SQLException e) {
					System.out.println("���� �߻� : " + e);
				}
			}
		}
	}

}
