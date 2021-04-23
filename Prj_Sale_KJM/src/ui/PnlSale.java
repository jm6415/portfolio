package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.jdatepicker.JDateComponentFactory;
import org.jdatepicker.JDatePicker;

import controller.MainController;
import dao.DaoCust;
import dao.DaoOrder;
import dao.DaoProd;
import util.MyCalendar;
import vo.CustVO;
import vo.OrderVO;
import java.awt.Component;
import javax.swing.Box;




public class PnlSale extends JPanel implements ActionListener {
	private JPanel pnlNorth,pnlWest,pnlEast;
	private JButton btnUpdate,btnAdd,btnDel,btnPay,btnReset;
	private DefaultTableModel model, mdProd;
	private JTable table,tblProd;
	private JScrollPane paneProd,pane;
	private JLabel lblNewLabel,lblNewLabel_1,lbUser,lbTotal,logo1,logo2,logo3,lbcustname;
	private javax.swing.Timer timer; 
	private JSpinner spQt;
	private DaoProd daoprod;
	private JComboBox cbCust;
	private Object[] custs,prods;
	private JDatePicker dPic;
	private ArrayList searchIdSet = new ArrayList();
	private int custId;
	
	
	public PnlSale() {
		setLayout(null);
		setOpaque(true);
		setBackground(SystemColor.activeCaption);
		custs = new DaoCust().getCustAll();
		Object[] custNames = new Object[custs.length];
	
//---------------------------North------------------------------------		
	
		pnlNorth = new JPanel();
		pnlNorth.setBounds(12, 10, 840, 66);
		pnlNorth.setLayout(null);
		pnlNorth.setBackground(SystemColor.inactiveCaptionBorder);
		add(pnlNorth);
		
		lbUser = new JLabel("어서오세요. ["+MainController.getInstance().getSession().getUserName()+"] 님 환영합니다!");
		lbUser.setFont(new Font("휴먼엑스포", Font.PLAIN, 18));
		lbUser.setBounds(475, 10, 353, 46);
		pnlNorth.add(lbUser);
		
		logo1 = new JLabel("GS");
		logo1.setForeground(Color.BLUE);
		logo1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		logo1.setBounds(12, 10, 141, 37);
		pnlNorth.add(logo1);
		
		logo2 = new JLabel("2");
		logo2.setForeground(new Color(255, 102, 0));
		logo2.setFont(new Font("휴먼엑스포", Font.BOLD | Font.ITALIC, 20));
		logo2.setBounds(41, 10, 57, 40);
		pnlNorth.add(logo2);
		
		logo3 = new JLabel("4");
		logo3.setForeground(new Color(255, 140, 0));
		logo3.setFont(new Font("휴먼엑스포", Font.BOLD | Font.ITALIC, 21));
		logo3.setBounds(59, 10, 57, 40);
		pnlNorth.add(logo3);
		
		dPic = new JDateComponentFactory().createJDatePicker();
		dPic.setTextEditable(false);
		dPic.setShowYearButtons(true);
		pnlNorth.add((JComponent)dPic);
		
	
		
		model = new DefaultTableModel(new String[] { "상품번호","구분", "상품명", "단가", "수량", "소계" }, 0);
		mdProd = new DefaultTableModel(new String[] { "상품번호", "상품명", "카테고리", "단가", }, 0);
		
		
		
//---------------------------East------------------------------------
		
		pnlEast = new JPanel();
		pnlEast.setBounds(404, 86, 448, 470);
		pnlEast.setLayout(null);
		pnlEast.setBackground(SystemColor.inactiveCaptionBorder);
		add(pnlEast);
		
		lblNewLabel = new JLabel("\uCD1D \uD569\uACC4");
		lblNewLabel.setFont(new Font("휴먼아미체", Font.BOLD, 20));
		lblNewLabel.setBounds(88, 355, 81, 23);
		pnlEast.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("\uC6D0");
		lblNewLabel_1.setFont(new Font("휴먼아미체", Font.BOLD, 20));
		lblNewLabel_1.setBounds(327, 351, 57, 31);
		pnlEast.add(lblNewLabel_1);
		
		btnPay = new JButton("\uACB0\uC81C");
		btnPay.setBounds(88, 422, 97, 23);
		btnPay.addActionListener(this);
		pnlEast.add(btnPay);
		
		btnReset = new JButton("\uCDE8\uC18C");
		btnReset.setBounds(259, 422, 97, 23);
		btnReset.addActionListener(this);
		pnlEast.add(btnReset);
		
		table = new JTable(model);
		pane = new JScrollPane(table);
		pane.setBounds(12, 23, 424, 303);
		pane.getViewport().setBackground(Color.WHITE);
		pnlEast.add(pane);
		
		lbTotal = new JLabel("0");
		lbTotal.setFont(new Font("휴먼모음T", Font.PLAIN, 20));
		lbTotal.setBounds(219, 356, 57, 21);
		pnlEast.add(lbTotal);
		
		//table 컬럼크기 조절
		int[] colWidth = { 80,120, 170, 100, 50, 110 };
		TableColumn column = null;
		for (int i = 0; i < table.getColumnCount(); i++) {
			column = table.getColumnModel().getColumn(i);
			column.setPreferredWidth(colWidth[i]);
		} 
		
//---------------------------West------------------------------------
		
		pnlWest = new JPanel();
		pnlWest.setBounds(12, 86, 380, 470);
		pnlWest.setLayout(null);
		pnlWest.setBackground(SystemColor.inactiveCaptionBorder);
		add(pnlWest);
		
		btnAdd = new JButton("\uB2F4\uAE30");
		btnAdd.setFont(new Font("휴먼모음T", Font.PLAIN, 14));
		btnAdd.setBounds(12, 372, 97, 40);
		btnAdd.addActionListener(this);
		pnlWest.add(btnAdd);
		
		btnDel = new JButton("\uBE7C\uAE30");
		btnDel.setFont(new Font("휴먼모음T", Font.PLAIN, 14));
		btnDel.setBounds(130, 372, 97, 40);
		btnDel.addActionListener(this);
		pnlWest.add(btnDel);
		
		cbCust = new JComboBox();
		cbCust.setBounds(42, 29, 185, 34);
		pnlWest.add(cbCust);
		cbCust.setEditable(true);
		
		cbCust.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyReleased(KeyEvent e) {
		    	if(e.getKeyChar()>='A'&&e.getKeyChar()<='Z' || 
		    			e.getKeyChar()>='a'&&e.getKeyChar()<='z'|| e.getKeyChar()==8) {
	  	    	String search = cbCust.getEditor().getItem().toString().trim();
	  	    	ArrayList searchSet = new ArrayList<>();
	  	    	searchIdSet = new ArrayList<>();
	  	    	searchSet.add(search);
	  	    	searchIdSet.add(0);
	  	    	for(int i=0;i<custs.length;i++) {
	  	    		CustVO vo = (CustVO) custs[i];
	  	    		if(vo.getcName().toLowerCase().contains(search.toLowerCase())) {
	  	    			searchSet.add(vo.getcName());
	  	    			searchIdSet.add(vo.getCustomerId());
	  	    		}
	  	    	}
	  	    	cbCust.setModel(new DefaultComboBoxModel<>(searchSet.toArray()));
	  	    	if(cbCust.getItemCount() > 1) {
	  	    		cbCust.showPopup();
	  	    	} 
	  	    }
		    }
			});
		
		lbcustname = new JLabel("\uB2D8 \uC785\uB2C8\uB2E4.");
		lbcustname.setFont(new Font("휴먼엑스포", Font.PLAIN, 20));
		lbcustname.setBounds(239, 28, 97, 37);
		pnlWest.add(lbcustname);
		
		tblProd = new JTable(model);
		paneProd = new JScrollPane(tblProd);
		paneProd.setBounds(12, 84, 354, 262);
		paneProd.getViewport().setBackground(Color.white);
		pnlWest.add(paneProd);
		tblProd.setModel(new DaoProd().getProdAll(mdProd));
		
		spQt = new JSpinner();
		spQt.setModel(new SpinnerNumberModel(1, 1, 100, 1));
		spQt.setBounds(252, 373, 84, 40);
		pnlWest.add(spQt);
		
		btnUpdate = new JButton("상품 갱신");
		btnUpdate.setBounds(12, 422, 215, 35);
		pnlWest.add(btnUpdate);
		btnUpdate.setFont(new Font("휴먼모음T", Font.PLAIN, 14));
		btnUpdate.addActionListener(this);
		cbCust.addActionListener(this);
	
		
	}
	

//-----------------------------------------------------------------
	
	@Override
	public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnAdd) {
        	int row = tblProd.getSelectedRow();
        	if(row == -1) {
        		JOptionPane.showMessageDialog(btnAdd, "제품목록에서 제품을 먼저 선택하세요.");
        		return;
        	}
        	String pId=tblProd.getValueAt(row, 0).toString();
        	String pName=tblProd.getValueAt(row, 1).toString();
        	String cate=tblProd.getValueAt(row, 2).toString();
        	String price=tblProd.getValueAt(row, 3).toString();
        	String quantity=spQt.getValue().toString();
        	String subTotal=String.valueOf(Integer.parseInt(quantity)*Integer.parseInt(price));
        	model.addRow(new Object[] {pId,cate,pName,price,quantity,subTotal});
        	
        	lbTotal.setText(String.valueOf(Integer.parseInt(lbTotal.getText())+
        			Integer.parseInt(subTotal)));
         
        	
        }	else if (e.getSource()==btnDel) {
        	int row = table.getSelectedRow();
        	if (row== -1) {
			JOptionPane.showMessageDialog(btnAdd, "제품목록에서 제품을 먼저 선택하세요.");
			return;
        	}
        	int cha = Integer.parseInt(table.getValueAt(row, 5).toString());
        	model.removeRow(row);
        	lbTotal.setText(String.valueOf(Integer.parseInt(lbTotal.getText())-cha));
       
        
        }	else if (e.getSource() == btnPay) {
        	//유효성검사
        	if(model.getRowCount() == 0) {
        		JOptionPane.showMessageDialog(btnPay,"결제할 건이 없습니다.");
        		return;
        	}	
        	boolean result = new DaoOrder().orderPay(new OrderVO(
        			custId, Integer.parseInt(lbTotal.getText()),
        			MyCalendar.getCal(dPic),
        			MainController.getInstance().getSession().getUserId(),model
        			));
        	JOptionPane.showMessageDialog(btnPay, ((result)? "등록" : "실패")+"입니다.");
        	model.setNumRows(0);lbTotal.setText("0");
        } else if (e.getSource() == btnUpdate) {
        	DefaultTableModel tableModel = (DefaultTableModel) tblProd.getModel();
			tableModel.setNumRows(0);
			tblProd.setModel(new DaoProd().getProdAll(mdProd));
        }
        
        if(e.getSource()==cbCust) {
        	custId = (int)searchIdSet.get(cbCust.getSelectedIndex());
        }
	}
}
