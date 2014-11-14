package com.tigrang.cs356.a2.delegate;

import com.tigrang.cs356.a2.model.DataSource;
import com.tigrang.cs356.a2.model.User;
import com.tigrang.cs356.a2.mvc.R;
import com.tigrang.cs356.a2.view.AdminControlPanelView;
import com.tigrang.mvc.delegate.ActionDelegate;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.ActionEvent;

public class AddNewUserDelegate extends ActionDelegate {

	private AdminControlPanelView view;
	private DefaultTreeModel model;

	public AddNewUserDelegate(AdminControlPanelView view, DefaultTreeModel model) {
		this.view = view;
		this.model = model;
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

		DefaultMutableTreeNode node = DataSource.get().getActiveGroupNode();
		if (node == null) {
			return;
		}

		User user = User.newUser(username);
		user.setGroup(DataSource.get().getActiveGroup());

		DataSource.get().getUsers().put(user.getId(), user);
		model.insertNodeInto(new DefaultMutableTreeNode(user, false), node, node.getChildCount());

		textField.setText("");
	}
}
