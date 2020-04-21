package de.mzte.mcserverinfo;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import de.mzte.mcserverinfo.api.Api;
import de.mzte.mcserverinfo.gui.Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Main {
	//TODO Run Gui on seperate thread
	public static Gui gui = new Gui();
	public static void main(String[] args) {




	gui.headerRowPanel.startButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			handleButton();
		}
	});

	gui.headerRowPanel.ipInput.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			handleButton();
		}
	});

	}

	private static void handleButton() {
		JsonObject returnJson = Api.getServerInfo(gui.headerRowPanel.ipInput.getText(), 5);
		if(returnJson == null) {
			gui.motdPanel.setTextHTML("");
			gui.motdPanel.picPanel.eraseImage();
			gui.tree.setValues(null);

		}else {
			//Set Image
			try {
				gui.motdPanel.picPanel.setImgBase64(returnJson.get("icon").toString());
			} catch(IOException ex) {
				ex.printStackTrace();
			}

			//Set Motd
			StringBuilder sb = new StringBuilder();
			for(JsonElement line : returnJson.get("motd").getAsJsonObject().getAsJsonArray("html")) {
				sb.append(line.getAsString());
				sb.append("<br/>");
			}
			String motd = sb.toString().replaceAll("<br/>$", "");

			gui.motdPanel.setTextHTML(motd);
			gui.tree.setValues(returnJson);
		}
	}


}
