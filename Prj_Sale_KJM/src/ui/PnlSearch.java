package ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import org.jdatepicker.JDateComponentFactory;
import org.jdatepicker.JDatePicker;

import dao.DaoCust;
import dao.DaoProd;
import dao.DaoSearch;
import vo.CustVO;
import vo.ProdVO;

public class PnlSearch extends JPanel implements ActionListener{
	private JPanel pnlMain;
	private JLabel lb1,lb2,lb3,lb4,lb5,lb6,lb7;
	private JComboBox cbProd,cbCust,cbCate;
	private DefaultTableModel model;
	private JTable table;
	private JScrollPane pane;
	private JDatePicker date1,date2;
	private JButton btn;
	private ArrayList custIdSet;
	private ArrayList prodIdSet;
	private Object[] custs,prods,cates;
	private DaoProd daoProd;
	private DaoSearch daoSearch;
	private int custId;
	private DefaultTableCellRenderer dtcrProd;
	private TableColumnModel tcmProd;
	
	public PnlSearch() {
		setLayout(null);
		custs = new DaoCust().getCustAll();
		Object[] custNames = new Object[custs.length];
		daoSearch = new DaoSearch();
		
		pnlMain = new JPanel();
		pnlMain.setBackground(SystemColor.info);
		pnlMain.setBounds(12, 10, 844, 540);
		add(pnlMain);
		pnlMain.setLayout(null);
		
		lb1 = new JLabel("\uAE30\uAC04\uBCC4");
		lb1.setFont(new Font("휴먼엑스포", Font.PLAIN, 14));
		lb1.setBounds(12, 10, 57, 15);
		pnlMain.add(lb1);
		
		lb2 = new JLabel("\uC0C1\uD488\uBCC4");
		lb2.setFont(new Font("휴먼엑스포", Font.PLAIN, 15));
		lb2.setBounds(308, 76, 66, 32);
		pnlMain.add(lb2);
		
		lb3 = new JLabel("\uACE0\uAC1D\uBCC4");
		lb3.setFont(new Font("휴먼엑스포", Font.PLAIN, 15));
		lb3.setBounds(546, 43, 66, 22);
		pnlMain.add(lb3);
		
		date1 = new JDateComponentFactory().createJDatePicker();
		date1.setTextEditable(false);
		date1.setShowYearButtons(true);
		JPanel pnldate1 = new JPanel();
		pnldate1.setForeground(SystemColor.window);
		pnldate1.add((JComponent)date1);
		pnldate1.setBounds(12, 32, 212, 33);
		pnlMain.add(pnldate1);
		pnldate1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		date2 = new JDateComponentFactory().createJDatePicker();
		date2.setTextEditable(false);
		date2.setShowYearButtons(true);
		JPanel pnldate2 = new JPanel();
		pnldate2.setForeground(SystemColor.window);
		pnldate2.add((JComponent)date2);
		pnldate2.setBounds(12, 75, 212, 33);
		pnlMain.add(pnldate2);
		
		daoProd = new DaoProd();
		cates = daoProd.getCate();
		cbCate = new JComboBox();
		cbCate.setBounds(308, 43, 180, 23);
		cbCate.addItem("All");
		pnlMain.add(cbCate);
		for(int i=0;i<cates.length;i++) cbCate.addItem((String)cates[i]);
		
		
		
		cbProd = new JComboBox();
		cbProd.setBounds(308, 109, 180, 23);
		pnlMain.add(cbProd);
		cbCate.addActionListener(this);

		String cate = cbCate.getSelectedItem().toString();
		setCbProd(cate.trim());

		cbCust = new JComboBox();
		cbCust.setEditable(true);
		cbCust.setBounds(546, 76, 180, 26);
		pnlMain.add(cbCust);
		cbCust.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyReleased(KeyEvent e) {
		    	if(e.getKeyChar()>='A'&&e.getKeyChar()<='Z' || 
		    			e.getKeyChar()>='a'&&e.getKeyChar()<='z'|| e.getKeyChar()==8) {
	  	    	String search = cbCust.getEditor().getItem().toString().trim();
	  	    	
	  	    	ArrayList searchSet = new ArrayList<>(); //검색된 고객명을 comboBox에 담기위한 set
	  	    	custIdSet = new ArrayList<>(); //검색된 고객번호를 comboBox와 동일한 순서의 set
	  	    	
	  	    	searchSet.add(search);
	  	    	custIdSet.add(0);
	  	    	searchSet.add("All");
	  	    	custIdSet.add(0);
	  	    	
	  	    	for(int i=0;i<custs.length;i++) {
	  	    		CustVO vo = (CustVO) custs[i];
	  	    		if(vo.getcName().toLowerCase().contains(search.toLowerCase())) {
	  	    			searchSet.add(vo.getcName());
	  	    			custIdSet.add(vo.getCustomerId());
	  	    		}
	  	    	}
	  	    	cbCust.setModel(new DefaultComboBoxModel<>(searchSet.toArray()));
	  	    	if(cbCust.getItemCount() > 1) {
	  	    		cbCust.showPopup();
	  	    	} 
	  	    }
		    }
			});
		
		table = new JTable(model);
		pane = new JScrollPane(table);
		pane.setBounds(12, 162, 820, 357);
		pane.getViewport().setBackground(Color.WHITE);
		pnlMain.add(pane);
		
		lb4 = new JLabel("\uBD80\uD130");
		lb4.setBounds(234, 42, 42, 15);
		pnlMain.add(lb4);
		
		lb5 = new JLabel("\uAE4C\uC9C0");
		lb5.setBounds(234, 88, 42, 15);
		pnlMain.add(lb5);
		
		lb6 = new JLabel("\u203B\uB2F9\uC77C\uAC80\uC0C9\uC740 \uC804\uBD80 \uB2F9\uC77C \uAE30\uC785");
		lb6.setFont(new Font("휴먼엑스포", Font.PLAIN, 14));
		lb6.setBounds(12, 118, 212, 33);
		pnlMain.add(lb6);
		
		lb7 = new JLabel("\uCE74\uD14C\uACE0\uB9AC");
		lb7.setFont(new Font("휴먼엑스포", Font.PLAIN, 15));
		lb7.setBounds(308, 10, 66, 23);
		pnlMain.add(lb7);
		
		btn = new JButton("\uAC80\uC0C9");
		btn.setBounds(746, 61, 86, 34);
		pnlMain.add(btn);
		btn.addActionListener(this);
		
	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cbCust) {
			custId = (int) custIdSet.get(cbCust.getSelectedIndex());
		} else if (e.getSource()==cbCate) {
			String cate = cbCate.getSelectedItem().toString();
			setCbProd(cate.trim());
			
		} else if (e.getSource() == btn) {
			if(cbCust.getSelectedIndex() <=0) {
				JOptionPane.showMessageDialog(btn, "고객을선택해주세요");
				return;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal1 = (Calendar) date1.getModel().getValue();
			String date1W = sdf.format(cal1.getTime());
			Calendar cal2 = (Calendar) date2.getModel().getValue();
			String date2W = sdf.format(cal2.getTime());
			String custW = (cbCust.getSelectedIndex() <= 0 ||
					cbCust.getSelectedItem().toString().equals("All"))?
							"All":String.valueOf(custId);
			String cateW = cbCate.getSelectedItem().toString();
			String prodW = cbCate.getSelectedItem().toString();
			String prodIdx = String.valueOf(cbProd.getSelectedIndex());
			prodW = (prodW.equals("All"))?prodW:String.valueOf(prodIdx);
			
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			while (table.getRowCount() > 0) { ((DefaultTableModel) table.getModel()).removeRow(0);}
			try {
				model = daoSearch.getVarioustable(
						model,date1W,date2W,custW,cateW,prodW);
			} catch (SQLException sql) {sql.printStackTrace();}
			table.setModel(model);
			model.fireTableDataChanged();
		}
	}
	private void setCbProd(String cate) {
		prods = daoProd.getProdsByCate(cate.trim());
		cbProd.removeAllItems();
		cbProd.addItem("All");
		
		prodIdSet = new ArrayList<>();
		prodIdSet.add(0);
		
		if(prods.length>0) {
			for(int i=0;i<prods.length;i++) {
				cbProd.addItem(((ProdVO) prods[i]).getpName());
				prodIdSet.add(((ProdVO) prods[i]).getpId());
			}
		}
	}
}
