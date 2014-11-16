package com.tigrang.cs356.a2.controller.delegate;

import com.tigrang.cs356.a2.model.DataSource;
import com.tigrang.cs356.a2.model.Group;
import com.tigrang.cs356.a2.model.User;
import com.tigrang.cs356.a2.mvc.R;
import com.tigrang.cs356.a2.view.AdminControlPanelView;
import com.tigrang.mvc.delegate.ActionDelegate;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AddNewUserDelegate extends ActionDelegate {

	private AdminControlPanelView view;

	public AddNewUserDelegate(AdminControlPanelView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String username = view.getUsername();
		if (username.isEmpty()) {
			view.showError("Enter a username.", R.id.username);
			return;
		}

		Group group = DataSource.get().getActiveGroup();
		if (group == null) {
			view.showError("Select a group first.", R.id.tree);
			return;
		}

		User user = User.newUser(username);
		user.setGroup(group);

		DataSource.get().getUsers().put(user.getId(), user);
		view.clearUsername();
	}
}
