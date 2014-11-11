package com.tigrang.cs356.a2.view;

import com.tigrang.cs356.a2.model.Group;
import com.tigrang.mvc.view.ViewElement;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class AdminControlPanelView extends com.tigrang.mvc.view.View {

	@ViewElement(id = "frame")
	private JFrame frame;
	@ViewElement(id = "container")
	private JPanel container;
	@ViewElement(id = "username")
	private JTextField txtUsername;
	@ViewElement(id = "add_user_btn")
	private JButton btnAddUser;
	@ViewElement(id = "group_name")
	private JTextField txtGroupName;
	@ViewElement(id = "add_group_btn")
	private JButton btnAddGroup;
	@ViewElement(id = "show_user_total_btn")
	private JButton btnShowUserTotal;
	@ViewElement(id = "show_group_total_btn")
	private JButton btnShowGroupTotal;
	@ViewElement(id = "show_messages_total_btn")
	private JButton btnShowMessagesTotal;
	@ViewElement(id = "show_pos_percentage_btn")
	private JButton btnShowPositivePercentage;
	@ViewElement(id = "open_user_view_btn")
	private JButton btnOpenUserView;
	@ViewElement(id = "tree")
	private JTree treeUsers;

	public AdminControlPanelView() {
		super();

		frame = new JFrame("Mini Twitter: Admin Control Panel");
		frame.setContentPane(container);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();

		parseViewElements();
	}

	public DefaultMutableTreeNode getActiveGroupNode() {
		TreePath path = treeUsers.getSelectionPath();

		if (path == null) {
			JOptionPane.showMessageDialog(null, "Select a node first.");
			return null;
		}

		DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
		return (node.isRoot() || node.getAllowsChildren()) ? node : (DefaultMutableTreeNode) node.getParent();
	}

	public Group getActiveGroup() {
		return (Group) getActiveGroupNode().getUserObject();
	}
}
