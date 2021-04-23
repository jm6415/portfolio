package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DaoSet {
	Connection conn; //��Ʈ��ũ ���� DB ���� ��ü
	PreparedStatement pstmt; // SQL�� ����� �� �ְ� ���ִ� ��ü
	Statement stmt;
	ResultSet rs; // ������ ������� �� ������� ������� ��ü

	public Connection connDB() throws SQLException{
		String driver = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "db7", pass = "1234";
		conn = DriverManager.getConnection(driver,user,pass);
		return conn;
	}
	
	public void closeDB() {
		try {
			if(conn != null)conn.close();if(stmt != null) stmt.close();
			if(pstmt != null)pstmt.close();if(rs != null) rs.close();
		} catch (SQLException e)  {e.printStackTrace();}
	}
}
