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

import dao.DaoProd;
import dao.DaoUser;
import vo.ProdVO;
import vo.UserVO;

public class PnlUserMng extends JPanel implements ActionListener {
	private JTable table;
	private JScrollPane pane;
	private DefaultTableModel model;
	private JPanel pnlMain;
	private JTextField tfUserpass,tfUserid,tfUsername,tfid;
	private JButton btnUpdate;
	private JLabel lbtitle,lbpass,lbName,lb3,lbId;
	public PnlUserMng() {
		setLayout(null);
		
		pnlMain = new JPanel();
		pnlMain.setBackground(SystemColor.info);
		pnlMain.setBounds(12, 10, 844, 540);
		add(pnlMain);
		pnlMain.setLayout(null);
		
		model = new DefaultTableModel(new String[] {"판매자번호","이름","아이디","비밀번호" }, 0);
		table = new JTable(model);
		pane = new JScrollPane(table);
		pane.setBounds(12, 55, 803, 396);
		pane.getViewport().setBackground(Color.WHITE);
		pnlMain.add(pane);
		
		lbpass = new JLabel("비밀번호");
		lbpass.setFont(new Font("휴먼엑스포", Font.PLAIN, 14));
		lbpass.setBounds(342, 483, 57, 21);
		pnlMain.add(lbpass);
		
		tfUserpass = new JTextField();
		tfUserpass.setColumns(10);
		tfUserpass.setBounds(411, 480, 114, 27);
		pnlMain.add(tfUserpass);
		
		tfUserid = new JTextField();
		tfUserid.setColumns(10);
		tfUserid.setBounds(240, 480, 90, 27);
		pnlMain.add(tfUserid);
		
		btnUpdate = new JButton("\uC218\uC815");
		btnUpdate.setBounds(710, 475, 105, 32);
		pnlMain.add(btnUpdate);
		btnUpdate.addActionListener(this);
		
		lbtitle = new JLabel("판매자정보수정");
		lbtitle.setFont(new Font("휴먼엑스포", Font.BOLD, 18));
		lbtitle.setBounds(12, 10, 152, 35);
		pnlMain.add(lbtitle);
		
		tfUsername = new JTextField();
		tfUsername.setColumns(10);
		tfUsername.setBounds(78, 480, 90, 27);
		pnlMain.add(tfUsername);
		
		lbName = new JLabel("이름");
		lbName.setFont(new Font("휴먼엑스포", Font.PLAIN, 14));
		lbName.setBounds(32, 483, 57, 21);
		pnlMain.add(lbName);
		
		lb3 = new JLabel("※수정할 판매자번호 기입");
		lb3.setFont(new Font("휴먼엑스포", Font.PLAIN, 14));
		lb3.setBounds(537, 461, 173, 32);
		pnlMain.add(lb3);
		
		tfid = new JTextField();
		tfid.setBackground(new Color(152, 251, 152));
		tfid.setColumns(10);
		tfid.setBounds(568, 492, 105, 27);
		pnlMain.add(tfid);
		
		lbId = new JLabel("아이디");
		lbId.setFont(new Font("휴먼엑스포", Font.PLAIN, 14));
		lbId.setBounds(181, 483, 57, 21);
		pnlMain.add(lbId);
		
		table.setModel(new DaoUser().getUserAll(model));
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnUpdate) {
			String userid =tfid.getText();
			String userName = tfUsername.getText();
			String id = tfUserid.getText();
			String password = tfUserpass.getText();
			if(userid.equals("")) {
				JOptionPane.showMessageDialog(btnUpdate, "판매자번호를 입력해주세요.");
				tfid.requestFocus();
				return;
			}
			if(userName.equals("")) {
				JOptionPane.showMessageDialog(btnUpdate, "이름을 입력해주세요.");
				tfUsername.requestFocus();
				return;
			}
			if(id.equals("")) {
				JOptionPane.showMessageDialog(btnUpdate, "아이디를 입력해주세요.");
				tfUserid.requestFocus();
				return;
			}
			if(password.equals("")) {
				JOptionPane.showMessageDialog(btnUpdate, "비밀번호를 입력해주세요.");
				tfUserpass.requestFocus();
				return;
			}
			boolean insert = new DaoUser().updateUser(new UserVO(Integer.parseInt(userid),userName,id,password));
			if(insert) {
				JOptionPane.showMessageDialog(btnUpdate, "수정완료");
				tfid.setText("");tfUsername.setText("");tfUserid.setText("");tfUserpass.setText("");
				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				tableModel.setNumRows(0);
				table.setModel(new DaoUser().getUserAll(model));

			} else {
				JOptionPane.showMessageDialog(btnUpdate, "수정실패");
			}
		}
	}
}
