package de.mzte.mcserverinfo;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import de.mzte.mcserverinfo.api.Api;
import de.mzte.mcserverinfo.gui.Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Main {
	public static Gui gui = new Gui();
	public static Thread updateGuiThread;
	public static void main(String[] args) {




	gui.headerRowPanel.startButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			handleStartButton();
		}
	});

	gui.headerRowPanel.stopButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			handleStopButton();
		}
	});

	gui.headerRowPanel.ipInput.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			handleStartButton();
		}
	});

	}

	private static void handleStopButton() {
		if(updateGuiThread != null) {
			updateGuiThread.interrupt();
		}
		resetGui();
	}

	private static void handleStartButton() {
		Runnable buttonThread = new Runnable() {
			@Override
			public void run() {
				gui.headerRowPanel.setActiveBackground(true);
				JsonObject returnJson = Api.getServerInfo(gui.headerRowPanel.ipInput.getText(), 5);
				if(returnJson == null) {
					resetGui();
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

					//Set Tree
					gui.tree.setValues(returnJson);

					gui.motdPanel.setTextHTML(motd);
					gui.headerRowPanel.setActiveBackground(false);
				}
			}
		};
		updateGuiThread = new Thread(buttonThread, "updateGuiThread");
		updateGuiThread.start();
	}
	private static void resetGui() {
		gui.motdPanel.setTextHTML(null);
		try {
			gui.motdPanel.picPanel.setImgBase64(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		gui.tree.setValues(null);
		gui.headerRowPanel.setActiveBackground(false);
	}


}
