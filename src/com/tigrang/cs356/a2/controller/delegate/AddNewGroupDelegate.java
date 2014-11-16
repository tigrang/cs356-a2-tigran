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
		JTextField textField = (JTextField) view.findComponentById(R.id.group_name);
		String groupName = textField.getText();
		if (groupName.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Enter a group name.");
			textField.requestFocusInWindow();
			return;
		}

		Group activeGroup = DataSource.get().getActiveGroup();
		if (activeGroup == null) {
			return;
		}

		Group group = Group.newGroup(groupName);
		group.setParent(activeGroup);

		DataSource.get().getGroups().put(group.getId(), group);

		textField.setText("");
	}
}
