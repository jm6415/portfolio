package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import vo.CustVO;

public class DaoCust extends DaoSet{
	
	public Object[] getCustAll() {
		ArrayList<CustVO> list = new ArrayList<>();
		try {
			conn = connDB();
			String sql = "select customer_id," 
					+"cust_first_name || ' ' || cust_last_name as cname from demo_customers";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CustVO tmp = new CustVO(rs.getInt(1), rs.getString(2));
				list.add(tmp);
			}	
		} catch (SQLException e) {e.printStackTrace();
		} finally {closeDB();}
		
		return list.toArray();
	}
	
	public CustVO[] getCustName(String name) {
		ArrayList<CustVO> list = new ArrayList<>();
		try {
			conn = connDB();
			String sql = "select customer_id, "
					+"cust_first_name || ' ' || cust_last_name as cname from demo_customers "
					+"where lower(cust_first_name || ' ' || cust_last_name) like lower ('%?%') ";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CustVO tmp = new CustVO(rs.getInt(1), rs.getString(2));
				list.add(tmp);
			}	
		} catch (SQLException e) {e.printStackTrace();
		} finally {closeDB();}
		
		return (CustVO[]) list.toArray();
	}
	public DefaultTableModel getCustAll2(DefaultTableModel model) {
		ArrayList<CustVO> list = new ArrayList<>();
		try {
			conn = connDB();
			String sql ="select customer_id,cust_first_name, cust_last_name, cust_street_address1, "
							+"cust_city,cust_state,cust_postal_code,phone_number1,credit_limit from demo_customers ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Object[] tmp = new Object[] {
						rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
						rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)};
				model.addRow(tmp);
				}	
		} catch (SQLException e) {e.printStackTrace();
		} finally {closeDB();}
		
		return model;
	}

	public boolean addCust(CustVO vo) {
		boolean result = false;
		
		try {
			conn = connDB();
			String sql = "insert into demo_customers VALUES( "
					+ "demo_users_seq.nextval,?,?,?,null,?, "
					+ "?,?,?,null,?,null) "; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getfirstname());
			pstmt.setString(2, vo.getlastname());
			pstmt.setString(3, vo.getaddress());
			pstmt.setString(4, vo.getcity());
			pstmt.setString(5, vo.getstate());
			pstmt.setString(6, vo.getpost());
			pstmt.setString(7, vo.getphone());
			pstmt.setString(8, vo.getlimit());
			
			int cnt = pstmt.executeUpdate();
	
			if(cnt > 0) {
				result = true;
			}
		} catch (SQLException e) {e.printStackTrace();} 
		finally {closeDB();}
		return result;
	}
	public boolean updateCust(CustVO vo) {
		boolean result = false;
		
		try {
			conn = connDB();
			String sql = "update demo_customers set cust_first_name=?, cust_last_name=?, cust_street_address1=?, "
						+ "cust_city=?, cust_state=?,cust_postal_code=?, phone_number1=?, credit_limit=? where customer_id=? "; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getfirstname());
			pstmt.setString(2, vo.getlastname());
			pstmt.setString(3, vo.getaddress());
			pstmt.setString(4, vo.getcity());
			pstmt.setString(5, vo.getstate());
			pstmt.setString(6, vo.getpost());
			pstmt.setString(7, vo.getphone());
			pstmt.setString(8, vo.getlimit());
			pstmt.setString(9, vo.getcustid());
			
			int cnt = pstmt.executeUpdate();
	
			if(cnt > 0) {
				result = true;
			}
		} catch (SQLException e) {e.printStackTrace();} 
		finally {closeDB();}
		return result;
	}
}
