package ui;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.jdatepicker.JDateComponentFactory;
import org.jdatepicker.JDatePicker;

import controller.MainController;
import dao.DaoUser;
import util.MyCalendar;
import vo.UserVO;



public class JoinFrm extends BaseFrm implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnJoin) {
			String name = tfName.getText();
			String id = tfId.getText();
			String pw = String.valueOf(pfPw.getPassword());
			String rePw = String.valueOf(pfRePw.getPassword());
			String date = MyCalendar.getCal(dPic);
			
			if(name.equals("")) {
				JOptionPane.showMessageDialog(btnJoin, "이름을 확인하세요");
				tfName.requestFocus();
				return;
			}
			if(id.equals("")) {
				JOptionPane.showMessageDialog(btnJoin, "아이디를 확인하세요");
				tfId.requestFocus();
				return;
			}
			if(pw.equals("")) {
				JOptionPane.showMessageDialog(btnJoin, "비밀번호를 확인하세요");
				pfPw.requestFocus();
				return;
			}
			if(!pw.equals(rePw)) {
				JOptionPane.showMessageDialog(btnJoin, "비밀번호 재확인하세요");
				pfPw.requestFocus();
				return;
			}
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date to = new Date();
			try {
				to = fm.parse(date);
			} catch (ParseException e1) {}
			if(new DaoUser().duplicateId(id)) {
				JOptionPane.showMessageDialog(null, "아이디가 중복됩니다.");
				tfId.requestFocus();
				return;
			}
			boolean insert = new DaoUser().addUser(new UserVO(name, id, pw, to));
			if(insert) {
				JOptionPane.showMessageDialog(btnJoin, "회원가입이 성공하였습니다.");
				dispose();
				MainController.getControl("Login");
			} else {
				JOptionPane.showMessageDialog(btnJoin, "회원가입이 실패하였습니다.");
			}
			
		} else if(e.getSource() == btnCancel) {
			dispose();
			MainController.getControl("Login");
		}
	}
	private JTextField tfName;
	private JTextField tfId;
	private JPasswordField pfPw;
	private JPasswordField pfRePw;
	private JDatePicker dPic;
	private JButton btnJoin, btnCancel;
	

	public JoinFrm() {
		getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
	
		
	setSize(399,423);
	getContentPane().setLayout(null);
	
	JLabel lblNewLabel = new JLabel("\uC774\uB984");
	lblNewLabel.setFont(new Font("휴먼아미체", Font.PLAIN, 20));
	lblNewLabel.setBounds(29, 74, 60, 30);
	getContentPane().add(lblNewLabel);
	
	JLabel lblNewLabel_1 = new JLabel("\uC544\uC774\uB514");
	lblNewLabel_1.setFont(new Font("휴먼아미체", Font.PLAIN, 20));
	lblNewLabel_1.setBounds(29, 121, 60, 30);
	getContentPane().add(lblNewLabel_1);
	
	JLabel lblNewLabel_2 = new JLabel("\uBE44\uBC00\uBC88\uD638");
	lblNewLabel_2.setFont(new Font("휴먼아미체", Font.PLAIN, 20));
	lblNewLabel_2.setBounds(29, 167, 60, 30);
	getContentPane().add(lblNewLabel_2);
	
	JLabel lblNewLabel_3 = new JLabel("\uBE44\uBC00\uBC88\uD638\uD655\uC778");
	lblNewLabel_3.setFont(new Font("휴먼아미체", Font.PLAIN, 20));
	lblNewLabel_3.setBounds(23, 219, 72, 30);
	getContentPane().add(lblNewLabel_3);
	
	JLabel lblNewLabel_4 = new JLabel("\uC785\uC0AC\uC77C\uC790");
	lblNewLabel_4.setFont(new Font("휴먼아미체", Font.PLAIN, 20));
	lblNewLabel_4.setBounds(31, 268, 57, 30);
	getContentPane().add(lblNewLabel_4);
	
	btnJoin = new JButton("\uAC00\uC785");
	btnJoin.setFont(new Font("휴먼아미체", Font.PLAIN, 20));
	btnJoin.setBounds(36, 323, 123, 40);
	getContentPane().add(btnJoin);
	btnJoin.addActionListener(this);
	
	btnCancel = new JButton("\uCDE8\uC18C");
	btnCancel.setFont(new Font("휴먼아미체", Font.PLAIN, 20));
	btnCancel.setBounds(220, 323, 123, 40);
	getContentPane().add(btnCancel);
	btnCancel.addActionListener(this);
	
	tfName = new JTextField();
	tfName.setBounds(133, 77, 210, 25);
	getContentPane().add(tfName);
	tfName.setColumns(10);
	
	tfId = new JTextField();
	tfId.setColumns(10);
	tfId.setBounds(133, 124, 210, 25);
	getContentPane().add(tfId);
	
	pfPw = new JPasswordField();
	pfPw.setBounds(133, 170, 210, 25);
	getContentPane().add(pfPw);
	
	pfRePw = new JPasswordField();
	pfRePw.setBounds(133, 222, 210, 25);
	getContentPane().add(pfRePw);
	
	dPic = new JDateComponentFactory().createJDatePicker();
	dPic.setTextEditable(false);
	dPic.setShowYearButtons(true);
	JPanel pnlDpic = new JPanel();
	pnlDpic.add((JComponent)dPic);
	pnlDpic.setBounds(133, 271, 210, 25);
	getContentPane().add(pnlDpic);
	
	JLabel lblNewLabel_5 = new JLabel("\uD68C \uC6D0 \uAC00 \uC785");
	lblNewLabel_5.setFont(new Font("휴먼아미체", Font.PLAIN, 33));
	lblNewLabel_5.setBounds(130, 20, 187, 38);
	getContentPane().add(lblNewLabel_5);

	inflate();
	}

	
}	
