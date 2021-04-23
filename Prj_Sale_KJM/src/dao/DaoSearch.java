package dao;

import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

public class DaoSearch extends DaoSet {
	public DefaultTableModel getVarioustable(DefaultTableModel model, 
			String date1, String date2, String custom,
			String cate, String prod) throws SQLException {
		
		String arrTitle[] = null;
		String arrTemp[] = null;
		int cntCols = 0;

		String sql = "";
		String sqlSub = "";
		String sqlAll = "";

		sqlSub = " and order_timestamp >= TO_DATE('"+date1+" 00:00:00','YYYY-MM-DD HH24:MI:SS') ";
		sqlSub += " and order_timestamp <= TO_DATE('"+date2 +" 23:59:59','YYYY-MM-DD HH24:MI:SS') ";
		if (!custom.equals("All"))
			sqlSub += " and o.customer_id = " + Integer.parseInt(custom);

		if (cate.equals("All") && prod.equals("All")) {
			arrTitle = new String[] { "주문번호", "고객명", "주문합계액", "주문일자", "판매자" };
			cntCols = 5;
			sql = "select o.order_id, c.CUST_FIRST_NAME || ' ' || c.CUST_LAST_NAME, "
					+ "o.ORDER_TOTAL, o.ORDER_TIMESTAMP, u.USER_NAME " 
					+ "from demo_orders o, demo_customers c,demo_users u "
					+ "where o.CUSTOMER_ID=c.CUSTOMER_ID " + "and o.USER_ID= u.USER_ID ";
		} else {
			arrTitle=new String[]{"순번","고객명","주문일자","카테고리","상품명","단가","수량","판매자"};
			cntCols = 8;
			if (!cate.equals("All") && prod.equals("All")) {
				sqlSub += " and p.category='" + cate + "' ";
			} else {
				sqlSub += " and p.product_id =" + Integer.parseInt(prod);
			}
			sql = "select i.ORDER_ITEM_ID, c.CUST_FIRST_NAME || ' ' || c.CUST_LAST_NAME,"
					+ "o.ORDER_TIMESTAMP,p.CATEGORY, p.PRODUCT_NAME, " 
					+ "p.LIST_PRICE, i.QUANTITY, u.user_name "
					+ "from demo_orders o, demo_customers c,demo_users u, " 
					+ "demo_order_items i, DEMO_PRODUCT_INFO p "
					+ "where o.CUSTOMER_ID=c.CUSTOMER_ID and o.USER_ID= u.USER_ID " 
					+ "and o.ORDER_ID = i.ORDER_ID and i.PRODUCT_ID = p.PRODUCT_ID ";
		}
		sqlAll = sql + sqlSub;
		try {
			conn = connDB();
			stmt = conn.createStatement();
			model = new DefaultTableModel(arrTitle, 0);
			rs = stmt.executeQuery(sqlAll);
			if (rs != null) {
				while (rs.next()) {
					arrTemp = new String[cntCols];
					for (int i = 0; i < cntCols; i++) {
						arrTemp[i] = rs.getString(i + 1);
					}
					model.addRow(arrTemp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return model;
	}
}
