package de.mzte.mcserverinfo.gui;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.CompactXmlSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.XmlSerializer;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;

public class MotdPanel extends JPanel {
	public JEditorPane motdPane;
	public PicturePanel picPanel;

	HTMLEditorKit kit = new HTMLEditorKit();
	javax.swing.text.Document doc = kit.createDefaultDocument();


	public MotdPanel() {
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		initComponents();
		setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
		setVisible(true);
	}

	private void initComponents() {
		add(picPanel = new PicturePanel());
		picPanel.setPreferredSize(new Dimension(80, 80));

		add(motdPane = new JEditorPane());
		motdPane.setEditable(false);
		motdPane.setEditorKit(kit);
		motdPane.setDocument(doc);
	}

	public void setTextHTML(String txt) {
		if(txt == null) {
			motdPane.setText("");
			return;
		}
		CleanerProperties props = new CleanerProperties();
		props.setOmitXmlDeclaration(true);
		props.setTranslateSpecialEntities(true);
		props.setTransResCharsToNCR(true);
		props.setOmitComments(true);
		props.setNamespacesAware(true);
		props.setOmitCdataOutsideScriptAndStyle(true);

		XmlSerializer xml = new CompactXmlSerializer(props);
		motdPane.setText(xml.getAsString(new HtmlCleaner(props).clean(txt)));
	}
}
