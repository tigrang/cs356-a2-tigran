package com.tigrang.cs356.a2.controller;

import com.tigrang.cs356.a2.model.DataSource;
import com.tigrang.cs356.a2.model.Group;

public class GroupsController {

	public Group add(String name) throws Exception {
		if (name.isEmpty()) {
			throw new Exception("Enter a group name.");
		}

		Group activeGroup = DataSource.get().getActiveGroup();
		if (activeGroup == null) {
			throw new Exception("Select a group first.");
		}

		Group group = Group.newGroup(name);
		group.setParent(activeGroup);
		DataSource.get().getGroups().put(group.getId(), group);

		return group;
	}

	public void setActive(Group group) {
		DataSource.get().setActiveGroup(group);
	}

	public int getTotal() {
		return DataSource.get().getGroups().size();
	}
}
