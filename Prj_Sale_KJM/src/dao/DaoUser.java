package dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import vo.ProdVO;
import vo.UserVO;

public class DaoUser extends DaoSet{
	public boolean duplicateId(String id) {
		boolean result = false;
		try {
			conn = connDB();
			String sql = "select * from demo_users where id = Upper(?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {closeDB();}
		return result;
	}
	public UserVO loginCheck(String id, String pass) {
		UserVO vo = null;
		try {
			conn = connDB();
			String sql = "select * from demo_users where id=Upper(?) and password =? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new UserVO(rs.getInt(1),rs.getString(2),rs.getString(3),
				rs.getDate(4),rs.getInt(5),rs.getString(6),rs.getDate(7),rs.getString(8),rs.getString(9));
			}
		
		} catch (SQLException e) {e.printStackTrace();} 
		finally {closeDB();}
		return vo;
	}
	public boolean addUser(UserVO vo) {
		boolean result = false;
		
		try {
			conn = connDB();
			String sql = "insert into demo_users VALUES( "
					+ "demo_users_seq.nextval,?,?,to_date(?, 'YYYY-MM-DD HH24:MI:SS'), "
					+ "null,'Y',null,'N',upper(?)) "; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getUserName());
			pstmt.setString(2, vo.getPassword());
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			pstmt.setString(3, fm.format(vo.getCreatedOn()));
			pstmt.setString(4, vo.getId());
			int cnt = pstmt.executeUpdate();
	
			if(cnt > 0) {
				result = true;
			}
		} catch (SQLException e) {e.printStackTrace();} 
		finally {closeDB();}
		return result;
	}
	public DefaultTableModel getUserAll(DefaultTableModel model) {
		ArrayList<UserVO> list = new ArrayList<>();
		try {
			conn = connDB();
			String sql = "select user_id,user_name,id,password from demo_users ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Object[] tmp = new Object[] {
						rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4) };
				model.addRow(tmp);
				}	
		} catch (SQLException e) {e.printStackTrace();
		} finally {closeDB();}
		
		return model;
	}
	public boolean updateUser(UserVO vo) {
		boolean result = false;
		
		try {
			conn = connDB();
			String sql = "update demo_users set user_name=?, "
					+ "id=upper(?),password=? where user_id=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getUserName());
			pstmt.setString(2, vo.getId());
			pstmt.setString(3, vo.getPassword());
			pstmt.setInt(4, vo.getUserId());
			
			int cnt = pstmt.executeUpdate();
	
			if(cnt > 0) {
				result = true;
			}
		} catch (SQLException e) {e.printStackTrace();} 
		finally {closeDB();}
		return result;
	}
}