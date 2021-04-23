package ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.MainController;

import java.awt.SystemColor;


public class MainFrm extends BaseFrm implements ActionListener{
	private JPanel pnlNorth, pnlCenter;
	private CardLayout cLay;
	private JLabel[] lbArr = new JLabel[5];
	private String[] titleArr = {"핀메", "조회", "고객관리", "상품관리", "사용자관리"};
	private JButton btnLogout;
	private JPanel[] pnlArr = {new PnlSale(), new PnlSearch(), new PnlCustMng(),
			new PnlProdMng(), new PnlUserMng()};
	
	
	public MainFrm() {	
		setSize(900,700);
		init();
		arrange();
		inflate();	
	}

	private  void init() {		
		cLay = new CardLayout(10, 10);
		pnlCenter = new JPanel(cLay);
		pnlCenter.setBackground(SystemColor.inactiveCaption);
		
		pnlNorth = new JPanel();
		pnlNorth.setBackground(SystemColor.inactiveCaptionBorder);
		pnlNorth.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		btnLogout = new JButton("로그아웃");
		btnLogout.addActionListener(this);
		
		
		for (int i=0; i<lbArr.length;i++) {
			pnlCenter.add(titleArr[i],pnlArr[i]);
			lbArr[i] = new JLabel(titleArr[i]);
			pnlNorth.add(lbArr[i]);
			lbArr[i].setPreferredSize(new Dimension(100, 60));
			lbArr[i].setFont(new Font("휴먼아미체", Font.BOLD, 20));
			lbArr[i].setOpaque(true);
			lbArr[i].setBackground(Color.LIGHT_GRAY);
			lbArr[i].setHorizontalAlignment(JLabel.CENTER);
			final int TMP = i;
			lbArr[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					cLay.show(pnlCenter, titleArr[TMP]);
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					lbArr[TMP].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}
			});
		}		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnLogout){
			dispose();
			MainController.getControl("Login");
		}
		
	}

	private void arrange() {
		pnlNorth.add(btnLogout);
		getContentPane().add(pnlNorth,"North");getContentPane().add(pnlCenter,"Center");
	}
}

