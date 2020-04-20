package de.mzte.mcserverinfo.gui;

import javax.swing.*;
import java.awt.*;

public class HeaderRowPanel extends JPanel {

	JLabel ipLabel;
	public JTextField ipInput;
	public JButton startButton;

	public HeaderRowPanel() {
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		initComponents();
		setVisible(true);
		setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
	}

	private void initComponents() {
		add(ipLabel = new JLabel("Enter IP:"));

		add(ipInput = new JTextField());

		add(startButton = new JButton("Start"));
		startButton.setMaximumSize(new Dimension(500, 30));
	}
}
