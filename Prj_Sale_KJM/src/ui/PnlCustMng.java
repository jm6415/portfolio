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
import vo.CustVO;

public class PnlCustMng extends JPanel implements ActionListener {
	private JTable table;
	private JScrollPane pane;
	private DefaultTableModel model;
	private JPanel pnlMain;
	private JTextField tfFirstName,tfAddress,tfLastname,tfCity,tfState,tfPost,tfPhone,tfLimit,tfCustid;
	private JButton btnAdd, btnUpdate;
	private JLabel lb1,lb3,lb4,lb5,lb6,lb7,lb2,lb9,lb8;
	private JLabel lblNewLabel;
	
	public PnlCustMng() {
		setLayout(null);
		
		pnlMain = new JPanel();
		pnlMain.setBackground(SystemColor.info);
		pnlMain.setBounds(12, 10, 844, 540);
		add(pnlMain);
		pnlMain.setLayout(null);
		
		model = new DefaultTableModel(new String[] {"고객번호","성","이름","주소","도시","주","우편번호","휴대폰번호","결제한도"  }, 0);
		table = new JTable(model);
		pane = new JScrollPane(table);
		pane.setBounds(12, 55, 803, 355);
		pane.getViewport().setBackground(Color.WHITE);
		pnlMain.add(pane);
		
		lb3 = new JLabel("\uC131");
		lb3.setFont(new Font("�޸տ�����", Font.PLAIN, 14));
		lb3.setBounds(22, 427, 57, 21);
		pnlMain.add(lb3);
		
		tfFirstName = new JTextField();
		tfFirstName.setColumns(10);
		tfFirstName.setBounds(44, 424, 81, 27);
		pnlMain.add(tfFirstName);
		
		lb4 = new JLabel("\uC774\uB984");
		lb4.setFont(new Font("�޸տ�����", Font.PLAIN, 14));
		lb4.setBounds(132, 427, 57, 21);
		pnlMain.add(lb4);
		
		tfAddress = new JTextField();
		tfAddress.setColumns(10);
		tfAddress.setBounds(305, 424, 223, 27);
		pnlMain.add(tfAddress);
		
		lb5 = new JLabel("\uC8FC\uC18C");
		lb5.setFont(new Font("�޸տ�����", Font.PLAIN, 14));
		lb5.setBounds(264, 427, 57, 21);
		pnlMain.add(lb5);
		
		tfLastname = new JTextField();
		tfLastname.setColumns(10);
		tfLastname.setBounds(171, 424, 81, 27);
		pnlMain.add(tfLastname);
		
		btnAdd = new JButton("\uCD94\uAC00");
		btnAdd.setBounds(718, 420, 97, 52);
		pnlMain.add(btnAdd);
		btnAdd.addActionListener(this);
		
		btnUpdate = new JButton("\uC218\uC815");
		btnUpdate.setBounds(718, 482, 97, 54);
		pnlMain.add(btnUpdate);
		btnUpdate.addActionListener(this);
		
		lb1 = new JLabel("\uACE0\uAC1D\uC815\uBCF4\uC218\uC815");
		lb1.setFont(new Font("휴먼엑스포", Font.BOLD, 18));
		lb1.setBounds(12, 10, 138, 35);
		pnlMain.add(lb1);
		
		
		tfCity = new JTextField();
		tfCity.setColumns(10);
		tfCity.setBounds(577, 424, 129, 27);
		pnlMain.add(tfCity);
		
		lb2 = new JLabel("도시");
		lb2.setFont(new Font("Dialog", Font.PLAIN, 14));
		lb2.setBounds(540, 427, 57, 21);
		pnlMain.add(lb2);
		
		tfState = new JTextField();
		tfState.setColumns(10);
		tfState.setBounds(44, 463, 81, 27);
		pnlMain.add(tfState);
		
		lb9 = new JLabel("주");
		lb9.setFont(new Font("Dialog", Font.PLAIN, 14));
		lb9.setBounds(22, 466, 57, 21);
		pnlMain.add(lb9);
		
		tfPost = new JTextField();
		tfPost.setColumns(10);
		tfPost.setBounds(190, 463, 81, 27);
		pnlMain.add(tfPost);
		
		lb6 = new JLabel("우편번호");
		lb6.setFont(new Font("Dialog", Font.PLAIN, 14));
		lb6.setBounds(132, 466, 57, 21);
		pnlMain.add(lb6);
		
		lb7 = new JLabel("폰번호");
		lb7.setFont(new Font("Dialog", Font.PLAIN, 14));
		lb7.setBounds(274, 466, 57, 21);
		pnlMain.add(lb7);
		
		tfPhone = new JTextField();
		tfPhone.setColumns(10);
		tfPhone.setBounds(325, 463, 203, 27);
		pnlMain.add(tfPhone);
		
		lb8 = new JLabel("결제한도");
		lb8.setFont(new Font("Dialog", Font.PLAIN, 14));
		lb8.setBounds(540, 466, 57, 21);
		pnlMain.add(lb8);
		
		tfLimit = new JTextField();
		tfLimit.setColumns(10);
		tfLimit.setBounds(609, 463, 97, 27);
		pnlMain.add(tfLimit);
		
		tfCustid = new JTextField();
		tfCustid.setBackground(new Color(152, 251, 152));
		tfCustid.setColumns(10);
		tfCustid.setBounds(609, 502, 97, 27);
		pnlMain.add(tfCustid);
		
		table.setModel(new DaoCust().getCustAll2(model));
		
		lblNewLabel = new JLabel("※수정시 고객번호 기입");
		lblNewLabel.setFont(new Font("휴먼엑스포", Font.BOLD, 18));
		lblNewLabel.setBounds(391, 500, 206, 30);
		pnlMain.add(lblNewLabel);
		
		table.getSelectedRow();
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnAdd) {
			String custid = tfCustid.getText();
			String firstname = tfFirstName.getText();
			String lastname = tfLastname.getText();
			String address = tfAddress.getText();
			String city = tfCity.getText();
			String state = tfState.getText();
			String post = tfPost.getText();
			String phone = tfPhone.getText();
			String limit = tfLimit.getText();
			if(firstname.equals("")) {
				JOptionPane.showMessageDialog(btnAdd, "성을 입력해주세요.");
				tfFirstName.requestFocus();
				return;
			}
			if(lastname.equals("")) {
				JOptionPane.showMessageDialog(btnAdd, "이름을 입력해주세요.");
				tfLastname.requestFocus();
				return;
			}
			if(address.equals("")) {
				JOptionPane.showMessageDialog(btnAdd, "주소를 입력해주세요.");
				tfAddress.requestFocus();
				return;
			}
			if(city.equals("")) {
				JOptionPane.showMessageDialog(btnAdd, "도시를 입력해주세요.");
				tfCity.requestFocus();
				return;
			}
			if(state.equals("")) {
				JOptionPane.showMessageDialog(btnAdd, "주를 입력해주세요.");
				tfState.requestFocus();
				return;
			}
			if(post.equals("")) {
				JOptionPane.showMessageDialog(btnAdd, "우편번호를 입력해주세요.");
				tfPost.requestFocus();
				return;
			}
			if(phone.equals("")) {
				JOptionPane.showMessageDialog(btnAdd, "휴대폰 번호를 입력해주세요.");
				tfPhone.requestFocus();
				return;
			}
			if(limit.equals("")) {
				JOptionPane.showMessageDialog(btnAdd, "결제한도를 입력해주세요.");
				tfLimit.requestFocus();
				return;
			}
			boolean insert = new DaoCust().addCust(new CustVO(custid,firstname, lastname, address, city, state, post, phone, limit));
			if(insert) {
				JOptionPane.showMessageDialog(btnAdd, "등록완료");
				tfCity.setText("");tfState.setText("");tfPost.setText("");
				tfPhone.setText("");tfLimit.setText("");			
				tfFirstName.setText("");tfLastname.setText("");tfAddress.setText("");
				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				tableModel.setNumRows(0);
				table.setModel(new DaoCust().getCustAll2(model));

			} else {
				JOptionPane.showMessageDialog(btnAdd, "등록실패");
				JOptionPane.showMessageDialog(btnAdd, "※주의: [주]를 2글자 입력해주세요. [결제한도]를 4자리이하로 지정해주세요.");
			}
			
			
		} else if (e.getSource() == btnUpdate) {
			String custid = tfCustid.getText();
			String firstname = tfFirstName.getText();
			String lastname = tfLastname.getText();
			String address = tfAddress.getText();
			String city = tfCity.getText();
			String state = tfState.getText();
			String post = tfPost.getText();
			String phone = tfPhone.getText();
			String limit = tfLimit.getText();
			if(firstname.equals("")) {
				JOptionPane.showMessageDialog(btnAdd, "성을 입력해주세요.");
				tfFirstName.requestFocus();
				return;
			}
			if(lastname.equals("")) {
				JOptionPane.showMessageDialog(btnAdd, "이름을 입력해주세요.");
				tfLastname.requestFocus();
				return;
			}
			if(address.equals("")) {
				JOptionPane.showMessageDialog(btnAdd, "주소를 입력해주세요.");
				tfAddress.requestFocus();
				return;
			}
			if(city.equals("")) {
				JOptionPane.showMessageDialog(btnAdd, "도시를 입력해주세요.");
				tfCity.requestFocus();
				return;
			}
			if(state.equals("")) {
				JOptionPane.showMessageDialog(btnAdd, "주를 입력해주세요.");
				tfState.requestFocus();
				return;
			}
			if(post.equals("")) {
				JOptionPane.showMessageDialog(btnAdd, "우편번호를 입력해주세요.");
				tfPost.requestFocus();
				return;
			}
			if(phone.equals("")) {
				JOptionPane.showMessageDialog(btnAdd, "휴대폰 번호를 입력해주세요.");
				tfPhone.requestFocus();
				return;
			}
			if(limit.equals("")) {
				JOptionPane.showMessageDialog(btnAdd, "결제한도를 입력해주세요.");
				tfLimit.requestFocus();
				return;
			}
			if(limit.equals("")) {
				JOptionPane.showMessageDialog(btnAdd, "고객번호를 입력해주세요.");
				tfCustid.requestFocus();
				return;
			}
			boolean update = new DaoCust().updateCust(new CustVO(custid,firstname, lastname, address, city, state, post, phone, limit));
			if(update) {
				JOptionPane.showMessageDialog(btnAdd, "수정완료");
				tfFirstName.setText("");tfLastname.setText("");tfAddress.setText("");
				tfCity.setText("");tfState.setText("");tfPost.setText("");
				tfPhone.setText("");tfLimit.setText("");tfCustid.setText("");
				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				tableModel.setNumRows(0);
				table.setModel(new DaoCust().getCustAll2(model));
			} else {
				JOptionPane.showMessageDialog(btnAdd, "수정실패");
				JOptionPane.showMessageDialog(btnAdd, "※주의: [주]를 2글자 입력해주세요. [결제한도]를 4자리이하로 지정해주세요.");
			}
		
		}
	}
}
	
