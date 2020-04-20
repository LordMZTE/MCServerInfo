package de.mzte.mcserverinfo.gui;

import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame {

	public InfoTree tree;
	public HeaderRowPanel headerRowPanel;
	public MotdPanel motdPanel;
	public JScrollPane treeScroll;

	public Gui() {
		setTitle("MCServerInfo");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		setMinimumSize(new Dimension(500, 600));
		initComponents();
		setVisible(true);
	}

	private void initComponents() {

		add(headerRowPanel = new HeaderRowPanel());
		add(Box.createRigidArea(new Dimension(0, 8)));
		add(motdPanel = new MotdPanel());
		add(Box.createRigidArea(new Dimension(0, 8)));
		add(treeScroll = new JScrollPane(tree = new InfoTree()));
	}
}
