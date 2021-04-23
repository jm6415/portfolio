package ui;

import javax.swing.JFrame;

public abstract class BaseFrm extends JFrame {
	
	public void inflate() {
		setTitle(getClass().getName().toString().split("\\.")[1]);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(this);
		setVisible(true);
	}
	
}
