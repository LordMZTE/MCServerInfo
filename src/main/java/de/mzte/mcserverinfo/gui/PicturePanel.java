package de.mzte.mcserverinfo.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

public class PicturePanel extends JPanel {
	private BufferedImage image;

	public PicturePanel() {
		setBackground(new Color(0, 0, 0));
		setVisible(true);
	}
	public void setImgBase64(String str) throws IOException {
		if(str == null) {
			image = null;
			paintComponent(getGraphics());
			return;
		}
		String undecoded = str.split(",")[1];
		BufferedImage img;
		byte[] imgBytes = Base64.getMimeDecoder().decode(undecoded);
		ByteArrayInputStream bis = new ByteArrayInputStream(imgBytes);
		img = ImageIO.read(bis);
		bis.close();
		image = img;
		paintComponent(getGraphics());
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
