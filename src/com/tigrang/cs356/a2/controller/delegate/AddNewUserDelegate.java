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
		JTextField textField = ((JTextField) view.findComponentById(R.id.username));
		String username = textField.getText();
		if (username.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Enter a username.");
			textField.requestFocusInWindow();
			return;
		}

		Group group = DataSource.get().getActiveGroup();
		if (group == null) {
			return;
		}

		User user = User.newUser(username);
		user.setGroup(group);

		DataSource.get().getUsers().put(user.getId(), user);

		textField.setText("");
	}
}
