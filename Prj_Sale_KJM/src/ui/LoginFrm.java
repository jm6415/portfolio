package ui;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.MainController;
import dao.DaoUser;
import vo.UserVO;


public class LoginFrm extends BaseFrm implements ActionListener{
	private JTextField tfid;
	private JPasswordField pfpw;
	private JButton btnLogin;
	private JButton btnJoin;
	private JButton btnClose;
	
	public LoginFrm() {
		getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		getContentPane().setForeground(Color.BLACK);
		setSize(440,156);
		getContentPane().setLayout(null);
		
		JLabel lbID = new JLabel("ID");
		lbID.setFont(new Font("휴먼아미체", Font.PLAIN, 24));
		lbID.setBounds(23, 22, 14, 28);
		getContentPane().add(lbID);
		
		JLabel lbPass = new JLabel("Pass");
		lbPass.setFont(new Font("휴먼아미체", Font.PLAIN, 24));
		lbPass.setBounds(12, 69, 36, 28);
		getContentPane().add(lbPass);
		
		tfid = new JTextField();
		tfid.setColumns(10);
		tfid.setBounds(61, 24, 131, 25);
		getContentPane().add(tfid);
		
		btnLogin = new JButton("\uB85C\uADF8\uC778");
		btnLogin.setFont(new Font("휴먼아미체", Font.PLAIN, 20));
		btnLogin.setBounds(216, 25, 97, 73);
		getContentPane().add(btnLogin);
		
		btnJoin = new JButton("\uD68C\uC6D0\uAC00\uC785");
		btnJoin.setFont(new Font("휴먼아미체", Font.PLAIN, 20));
		btnJoin.setBounds(325, 25, 87, 35);
		getContentPane().add(btnJoin);
		
		btnClose = new JButton("\uCDE8\uC18C");
		btnClose.setFont(new Font("휴먼아미체", Font.PLAIN, 20));
		btnClose.setBounds(325, 70, 87, 28);
		getContentPane().add(btnClose);
		
		pfpw = new JPasswordField();
		pfpw.setBounds(60, 71, 133, 25);
		getContentPane().add(pfpw);
		
		setTitle("");
		inflate();
		
		btnLogin.addActionListener(this);
		btnJoin.addActionListener(this);
		btnClose.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnLogin){
			UserVO vo = 
					new DaoUser().loginCheck(tfid.getText(), String.valueOf(pfpw.getPassword()));
			if(vo==null) {
				JOptionPane.showMessageDialog(btnLogin, "없는 계정입니다.");
				tfid.setText("");pfpw.setText("");tfid.requestFocus();
				return;
			}
			MainController.getInstance().setSession(vo);
			MainController.getControl("Main");
			dispose();
		}else if(e.getSource() == btnClose) {
			System.exit(-1);
		}else if(e.getSource() == btnJoin) {
			dispose();
			MainController.getControl("Join");
		}
		
	}
	
	
	
}
