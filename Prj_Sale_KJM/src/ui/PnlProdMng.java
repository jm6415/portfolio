package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.DaoCust;
import dao.DaoProd;
import vo.ProdVO;

public class PnlProdMng extends JPanel implements ActionListener {
	private JTable table;
	private JScrollPane pane;
	private DefaultTableModel model;
	private JPanel pnlMain;
	private JTextField tfName,tfPrice,tfCate;
	private JButton btnAdd, btnUpdate;
	private JLabel lb1,lb3,lb4,lb5;
	private JTextField tfId;
	public PnlProdMng() {
		setLayout(null);
		
		pnlMain = new JPanel();
		pnlMain.setBackground(SystemColor.info);
		pnlMain.setBounds(12, 10, 844, 540);
		add(pnlMain);
		pnlMain.setLayout(null);
		
		model = new DefaultTableModel(new String[] {"제품번호","제품명","카테고리","가격", }, 0);
		table = new JTable(model);
		pane = new JScrollPane(table);
		pane.setBounds(12, 55, 803, 396);
		pane.getViewport().setBackground(Color.WHITE);
		pnlMain.add(pane);
		
		lb3 = new JLabel("\uC81C\uD488\uBA85");
		lb3.setFont(new Font("휴먼엑스포", Font.PLAIN, 14));
		lb3.setBounds(12, 479, 57, 21);
		pnlMain.add(lb3);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(68, 476, 81, 27);
		pnlMain.add(tfName);
		
		lb4 = new JLabel("\uCE74\uD14C\uACE0\uB9AC");
		lb4.setFont(new Font("휴먼엑스포", Font.PLAIN, 14));
		lb4.setBounds(161, 479, 57, 21);
		pnlMain.add(lb4);
		
		tfPrice = new JTextField();
		tfPrice.setColumns(10);
		tfPrice.setBounds(361, 476, 114, 27);
		pnlMain.add(tfPrice);
		
		lb5 = new JLabel("\uAC00\uACA9");
		lb5.setFont(new Font("휴먼엑스포", Font.PLAIN, 14));
		lb5.setBounds(320, 479, 57, 21);
		pnlMain.add(lb5);
		
		tfCate = new JTextField();
		tfCate.setColumns(10);
		tfCate.setBounds(227, 476, 81, 27);
		pnlMain.add(tfCate);
		
		btnAdd = new JButton("\uCD94\uAC00");
		btnAdd.setBounds(682, 461, 114, 32);
		pnlMain.add(btnAdd);
		btnAdd.addActionListener(this);
		
		btnUpdate = new JButton("\uC218\uC815");
		btnUpdate.setBounds(682, 498, 114, 32);
		pnlMain.add(btnUpdate);
		btnUpdate.addActionListener(this);
		
		lb1 = new JLabel("\uC81C\uD488\uC815\uBCF4\uC218\uC815");
		lb1.setFont(new Font("휴먼엑스포", Font.BOLD, 18));
		lb1.setBounds(12, 10, 138, 35);
		pnlMain.add(lb1);
		
		
		table.setModel(new DaoProd().getProdAll(model));
		
		tfId = new JTextField();
		tfId.setBackground(new Color(152, 251, 152));
		tfId.setColumns(10);
		tfId.setBounds(538, 501, 93, 27);
		pnlMain.add(tfId);
		
		JLabel lb2 = new JLabel("※수정시 제품번호 기입");
		lb2.setFont(new Font("휴먼엑스포", Font.PLAIN, 15));
		lb2.setBounds(497, 470, 180, 21);
		pnlMain.add(lb2);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnAdd) {
			String pname = tfName.getText();
			String cate = tfCate.getText();
			String price = tfPrice.getText();
			if(pname.equals("")) {
				JOptionPane.showMessageDialog(btnAdd, "상품명을 입력해주세요.");
				tfName.requestFocus();
				return;
			}
			if(cate.equals("")) {
				JOptionPane.showMessageDialog(btnAdd, "카테고리를 입력해주세요.");
				tfCate.requestFocus();
				return;
			}
			if(price.equals("")) {
				JOptionPane.showMessageDialog(btnAdd, "가격을 입력해주세요.");
				tfPrice.requestFocus();
				return;
			}
			boolean insert = new DaoProd().addProd(new ProdVO(pname,cate,Integer.parseInt(price)));
			if(insert) {
				JOptionPane.showMessageDialog(btnAdd, "등록완료");
				tfName.setText("");tfCate.setText("");tfPrice.setText("");
				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				tableModel.setNumRows(0);
				table.setModel(new DaoProd().getProdAll(model));

			} else {
				JOptionPane.showMessageDialog(btnAdd, "등록실패");
			}
			
		} else if (e.getSource() == btnUpdate) {
				String pid = tfId.getText();
				String pname = tfName.getText();
				String cate = tfCate.getText();
				String price = tfPrice.getText();
				if(pname.equals("")) {
					JOptionPane.showMessageDialog(btnAdd, "상품명을 입력해주세요.");
					tfName.requestFocus();
					return;
				}
				if(cate.equals("")) {
					JOptionPane.showMessageDialog(btnAdd, "카테고리를 입력해주세요.");
					tfCate.requestFocus();
					return;
				}
				if(price.equals("")) {
					JOptionPane.showMessageDialog(btnAdd, "가격을 입력해주세요.");
					tfPrice.requestFocus();
					return;
				}
				if(price.equals("")) {
					JOptionPane.showMessageDialog(btnAdd, "제품아이디를 입력해주세요.");
					tfId.requestFocus();
					return;
				}
				boolean insert = new DaoProd().updateProd(new ProdVO(Integer.parseInt(pid),pname,cate,Integer.parseInt(price)));
				if(insert) {
					JOptionPane.showMessageDialog(btnUpdate, "수정완료");
					tfName.setText("");tfCate.setText("");tfPrice.setText("");tfId.setText("");
					DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
					tableModel.setNumRows(0);
					table.setModel(new DaoProd().getProdAll(model));

				} else {
					JOptionPane.showMessageDialog(btnAdd, "수정실패");
				}
		}
		
	}
}
