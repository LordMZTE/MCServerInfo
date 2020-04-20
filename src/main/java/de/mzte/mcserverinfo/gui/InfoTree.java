package de.mzte.mcserverinfo.gui;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import java.util.Map;
import java.util.Set;

public class InfoTree extends JTree {
	public DefaultMutableTreeNode info = new DefaultMutableTreeNode("Info");
	public InfoTree() {
		TreeModel model = this.getModel();
		if (model instanceof DefaultTreeModel) {
			DefaultTreeModel treeModel = (DefaultTreeModel) this.getModel();
			treeModel.setRoot(info);
		}
	}
	public void setValues(JsonObject json) {
		if(json == null) {
			info.removeAllChildren();
			((DefaultTreeModel)this.getModel()).reload(info);
			return;
		}
		JsonObject players = json.getAsJsonObject("players");
		DefaultMutableTreeNode playersTree = new DefaultMutableTreeNode("Players");
		DefaultMutableTreeNode playerListTree = new DefaultMutableTreeNode("List");
		JsonObject mods = json.getAsJsonObject("mods");
		DefaultMutableTreeNode modsTree = new DefaultMutableTreeNode("Mods");
		DefaultMutableTreeNode modNameTree = new DefaultMutableTreeNode("Names");
		DefaultMutableTreeNode modRawTree = new DefaultMutableTreeNode("Raw");

		info.removeAllChildren();

		info.add(getLabeledTreeNode("IP", json.get("ip").getAsString()));
		info.add(getLabeledTreeNode("Port", json.get("port").getAsString()));
		info.add(getLabeledTreeNode("Is Online?", json.get("online").getAsString()));
		info.add(getLabeledTreeNode("Version", json.get("version").getAsString()));
		info.add(getLabeledTreeNode("Protocol", json.get("protocol").getAsString()));
		info.add(getLabeledTreeNode("Hostname", json.get("hostname").getAsString()));

		playersTree.add(getLabeledTreeNode("Online Players", players.get("online").getAsString()));
		playersTree.add(getLabeledTreeNode("Slot Count", players.get("max").getAsString()));
		if(players.getAsJsonArray("list") != null) {
			for(JsonElement player : players.getAsJsonArray("list")) {
				playerListTree.add(new DefaultMutableTreeNode(player.getAsString()));
			}
			playersTree.add(playerListTree);
		}
		info.add(playersTree);

		if(mods != null) {
			for(JsonElement mod : mods.getAsJsonArray("names")) {
				modNameTree.add(new DefaultMutableTreeNode(mod.getAsString()));
			}
			modsTree.add(modNameTree);

			Set<Map.Entry<String, JsonElement>> entrySet = mods.getAsJsonObject("raw").entrySet();
			for(Map.Entry<String, JsonElement> entry : entrySet) {
				modRawTree.add(new DefaultMutableTreeNode(entry.getValue().getAsString()));
			}
			modsTree.add(modRawTree);

			info.add(modsTree);
		}

		

		((DefaultTreeModel)this.getModel()).reload(info);
		expandAllNodes(this);
	}

	private DefaultMutableTreeNode getLabeledTreeNode(String label, String data) {
		DefaultMutableTreeNode baseNode = new DefaultMutableTreeNode(label);
		baseNode.add((new DefaultMutableTreeNode(data)));
		return baseNode;
	}


	private void expandAllNodes(JTree tree){
		int rowCount = tree.getRowCount();

		for(int i=0;i<rowCount;++i){
			tree.expandRow(i);
		}

		if(tree.getRowCount()!=rowCount){
			expandAllNodes(tree);
		}
	}
}
