package de.mzte.mcserverinfo.gui;

import javax.swing.*;
import java.awt.*;

public class HeaderRowPanel extends JPanel {
	private JLabel ipLabel;
	public JTextField ipInput;
	public JButton startButton;
	public JButton stopButton;

	public HeaderRowPanel() {
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		initComponents();
		setVisible(true);
		setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
	}

	private void initComponents() {
		add(ipLabel = new JLabel("Enter IP:"));
		add(Box.createRigidArea(new Dimension(8, 0)));
		add(ipInput = new JTextField());
		add(Box.createRigidArea(new Dimension(8, 0)));
		add(startButton = new JButton("Start"));
		startButton.setMaximumSize(new Dimension(500, 30));
		add(Box.createRigidArea(new Dimension(8, 0)));
		add(stopButton = new JButton("Stop"));
		stopButton.setMaximumSize(new Dimension(500, 30));
	}

	public void setActiveBackground(boolean b) {
		if(b) {
			this.setBackground(new Color(0, 255, 0));
		}else {
			this.setBackground(new Color(255, 255, 255));
		}
	}
}
