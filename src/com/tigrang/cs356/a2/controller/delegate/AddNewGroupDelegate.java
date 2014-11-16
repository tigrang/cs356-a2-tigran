package com.tigrang.cs356.a2.controller.delegate;

import com.tigrang.cs356.a2.model.DataSource;
import com.tigrang.cs356.a2.model.Group;
import com.tigrang.cs356.a2.mvc.R;
import com.tigrang.cs356.a2.view.AdminControlPanelView;
import com.tigrang.mvc.delegate.ActionDelegate;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AddNewGroupDelegate extends ActionDelegate {

	private AdminControlPanelView view;

	public AddNewGroupDelegate(AdminControlPanelView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String groupName = view.getGroupName();
		if (groupName.isEmpty()) {
			view.showError("Enter a group name.", R.id.group_name);
			return;
		}

		Group activeGroup = DataSource.get().getActiveGroup();
		if (activeGroup == null) {
			view.showError("Select a group first.", R.id.tree);
			return;
		}

		Group group = Group.newGroup(groupName);
		group.setParent(activeGroup);

		DataSource.get().getGroups().put(group.getId(), group);
		view.clearGroupName();
	}
}
