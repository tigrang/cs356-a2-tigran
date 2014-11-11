package com.tigrang.cs356.a2.delegate;

import com.tigrang.cs356.a2.model.DataSource;
import com.tigrang.cs356.a2.model.Group;
import com.tigrang.cs356.a2.mvc.R;
import com.tigrang.cs356.a2.view.AdminControlPanelView;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddNewGroupDelegate implements ActionListener {

	private AdminControlPanelView view;
	private DefaultTreeModel model;

	public AddNewGroupDelegate(AdminControlPanelView view, DefaultTreeModel model) {
		this.view = view;
		this.model = model;
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

		DefaultMutableTreeNode node = view.getActiveGroupNode();
		if (node == null) {
			return;
		}

		Group group = Group.newGroup(groupName);
		group.setParent(view.getActiveGroup());

		DataSource.get().getGroups().put(group.getId(), group);
		model.insertNodeInto(new DefaultMutableTreeNode(group), node, node.getChildCount());

		textField.setText("");
	}
}
