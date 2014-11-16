package com.tigrang.cs356.a2.view;

import com.tigrang.cs356.a2.model.DataSource;
import com.tigrang.cs356.a2.model.UserTreeModel;
import com.tigrang.mvc.view.ViewElement;

import javax.swing.*;
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

	public AdminControlPanelView() {
		createFrame();
		parseViewElements();
		setupUI();
	}

	@Override
	public void setupUI() {
		treeUsers.setModel(new UserTreeModel(DataSource.get().getRoot()));
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

	public String getUsername() {
		return txtUsername.getText();
	}

	public void clearUsername() {
		txtUsername.setText("");
	}

	public String getGroupName() {
		return txtGroupName.getText();
	}

	public void clearGroupName() {
		txtGroupName.setText("");
	}

	public void showMessage(String message) {
		JOptionPane.showMessageDialog(getRoot(), message);
	}

	public void showError(String message, String id) {
		JOptionPane.showMessageDialog(getRoot(), message, "Error", JOptionPane.ERROR_MESSAGE);
		findComponentById(id).requestFocus();
	}
}
