package com.tigrang.cs356.a2.model;

import com.tigrang.cs356.a2.util.IndexedHashMap;

public class DataSource {

	private static DataSource instance;

	private IndexedHashMap<Integer, User> users;

	private IndexedHashMap<Integer, Group> groups;

	private Group root;

	private Group activeGroup;

	private DataSource() {
		users = new IndexedHashMap<>();
		groups = new IndexedHashMap<>();
		activeGroup = root = Group.newGroup("Root");
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

	public IndexedHashMap<Integer, User> getUsers() {
		return users;
	}

	public IndexedHashMap<Integer, Group> getGroups() {
		return groups;
	}

	public Group getActiveGroup() {
		return activeGroup;
	}

	public void setActiveGroup(Group group) {
		this.activeGroup = group;
	}
}