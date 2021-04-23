package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import vo.ProdVO;
public class DaoProd extends DaoSet{
	public DefaultTableModel getProdAll(DefaultTableModel model) {
		ArrayList<ProdVO> list = new ArrayList<>();
		try {
			conn = connDB();
			String sql = "select product_id, product_name, category, list_price " 
					+"from demo_product_info ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Object[] tmp = new Object[] {
						rs.getInt(1), rs.getString(2),rs.getString(3),rs.getInt(4) };
				model.addRow(tmp);
				}	
		} catch (SQLException e) {e.printStackTrace();
		} finally {closeDB();}
		
		return model;
	}
	
	public Object[] getCate(){
			ArrayList list = new ArrayList();
			String sql = "select distinct category from demo_product_info "
							+ "order by category ";
			try {
				conn = connDB();
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();	
				while(rs.next()) {
					list.add(rs.getString(1));
					}	
			} catch (SQLException e) {e.printStackTrace();
			} finally {closeDB();}
			
			return list.toArray();
		}
	public Object[] getProdsByCate(String cate){
		ArrayList list = new ArrayList();
		String sql = "select product_id, product_name, category, list_price, " 
				+"product_image from demo_product_info ";
		sql += (cate.equals("All")) ? " " : "where category= ? ";
		sql += "order by product_name ";
		try {
			conn = connDB();
			pstmt = conn.prepareStatement(sql);
			if(!cate.equals("All")) pstmt.setString(1, cate);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProdVO prod = new ProdVO(rs.getInt(1), rs.getString(2),
						rs.getString(3),rs.getInt(4));
				list.add(prod);
				}	
		} catch (SQLException e) {e.printStackTrace();
		} finally {closeDB();}
		
		return list.toArray();
	}
	public boolean addProd(ProdVO vo) {
		boolean result = false;
		
		try {
			conn = connDB();
			String sql = "insert into demo_product_info VALUES( "
					+ "demo_prod_seq.nextval,?,null,?,'Y',?, "
					+ "UTL_RAW.CAST_TO_RAW('TEST'),'image/jpeg','XXX.jpg',sysdate) "; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getpName());
			pstmt.setString(2, vo.getCate());
			pstmt.setInt(3, vo.getPrice());
			
			int cnt = pstmt.executeUpdate();
	
			if(cnt > 0) {
				result = true;
			}
		} catch (SQLException e) {e.printStackTrace();} 
		finally {closeDB();}
		return result;
	}
	public boolean updateProd(ProdVO vo) {
		boolean result = false;
		
		try {
			conn = connDB();
			String sql = "update demo_product_info set product_name=?, "
					+ "category=?,list_price=? where product_id=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getpName());
			pstmt.setString(2, vo.getCate());
			pstmt.setInt(3, vo.getPrice());
			pstmt.setInt(4, vo.getpId());
			
			int cnt = pstmt.executeUpdate();
	
			if(cnt > 0) {
				result = true;
			}
		} catch (SQLException e) {e.printStackTrace();} 
		finally {closeDB();}
		return result;
	}
}
