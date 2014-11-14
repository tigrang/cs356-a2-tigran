package com.tigrang.cs356.a2.view;

import com.tigrang.mvc.view.ViewElement;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeSelectionModel;

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

	private TreeModel model;

	public AdminControlPanelView(DefaultTreeModel model) {
		this.model = model;

		createFrame();
		parseViewElements();
		setupUI();
	}

	@Override
	public void setupUI() {
		treeUsers.setModel(model);
		treeUsers.setSelectionRow(0);
		treeUsers.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
	}

	private void createFrame() {
		frame = new JFrame("Mini Twitter: Admin Control Panel");
		frame.setContentPane(container);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		setRoot(frame);
	}
}
