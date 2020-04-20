package de.mzte.mcserverinfo.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.xml.bind.DatatypeConverter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class PicturePanel extends JPanel {
	BufferedImage image;

	public PicturePanel() {
		setBackground(new Color(0, 0, 0));
		setVisible(true);
	}

	public void setImgBase64(String str) throws IOException {
		String undecoded = str.split(",")[1];
		BufferedImage img;
		byte[] imgBytes;
		imgBytes = DatatypeConverter.parseBase64Binary(undecoded);
		ByteArrayInputStream bis = new ByteArrayInputStream(imgBytes);
		img = ImageIO.read(bis);
		bis.close();
		image = img;
		paintComponent(getGraphics());
	}
	public void eraseImage() {
		Graphics g = this.getGraphics();
		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}



	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		if(image != null) {
			g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
		}else {
			super.paintComponent(g);
		}
	}
}