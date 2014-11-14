package com.tigrang.cs356.a2.model;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.HashMap;
import java.util.Map;

public class DataSource {

	private static DataSource instance;

	private Map<Integer, User> users;

	private Map<Integer, Group> groups;

	private Group root;

	private DefaultMutableTreeNode activeGroupNode;

	private DataSource() {
		users = new HashMap<>();
		groups = new HashMap<>();
		root = Group.newGroup("Root");
		groups.put(root.getId(), root);
	}

	public static DataSource get() {
		if (instance == null) {
			instance = new DataSource();
		}
		return instance;
	}

	public Group getRoot() {
		return root;
	}

	public Map<Integer, User> getUsers() {
		return users;
	}

	public Map<Integer, Group> getGroups() {
		return groups;
	}

	public DefaultMutableTreeNode getActiveGroupNode() {
		return activeGroupNode;
	}

	public void setActiveGroupNode(DefaultMutableTreeNode node) {
		this.activeGroupNode = node;
	}

	public Group getActiveGroup() {
		return (activeGroupNode == null) ? null : (Group) activeGroupNode.getUserObject();
	}
}