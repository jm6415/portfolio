package dao;

import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import java.sql.Date;

import vo.OrderVO;

public class DaoOrder extends DaoSet {
	public boolean orderPay(OrderVO vo) {
		boolean result = false; 
		int orderId = 0;
		try {
			conn = connDB();
			conn.setAutoCommit(false); //transaction¶§¹®¿¡ false
			String sql = "insert into demo_orders values(demo_ord_seq.nextval,?,?, "
						+ "sysdate,?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getCustomerId());
			pstmt.setInt(2, vo.getTotal());
			pstmt.setInt(3, vo.getUserId());
			int ordersCnt = pstmt.executeUpdate();
			
			sql ="select demo_ord_seq.currval from dual ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) orderId= rs.getInt(1);
			
			int orderItemsCnt = 0;
			DefaultTableModel model = vo.getModel();
			sql = "insert into demo_order_items values(demo_order_items_seq.nextval, ?,?,?,?) ";
			for(int i = 0;i<model.getRowCount();i++) {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, orderId);
				pstmt.setInt(2, Integer.parseInt(model.getValueAt(i, 0).toString()));
				pstmt.setInt(3, Integer.parseInt(model.getValueAt(i, 3).toString()));
				pstmt.setInt(4, Integer.parseInt(model.getValueAt(i, 4).toString()));
				if(pstmt.executeUpdate()<1) return false;
				orderItemsCnt++;
			}
			if(ordersCnt>0 && orderId>0 && orderItemsCnt>0) {
				conn.commit();
				result = true;
		}else {conn.rollback();
		}
		conn.setAutoCommit(true);	
		} catch (SQLException e) {e.printStackTrace();
		} finally {	closeDB();}
		return result;
	}
}
