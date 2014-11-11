package com.tigrang.cs356.a2.controller;

import com.tigrang.cs356.a2.delegate.AddNewGroupDelegate;
import com.tigrang.cs356.a2.delegate.AddNewUserDelegate;
import com.tigrang.cs356.a2.delegate.OpenUserDialogDelegate;
import com.tigrang.cs356.a2.model.DataSource;
import com.tigrang.cs356.a2.mvc.R;
import com.tigrang.cs356.a2.view.AdminControlPanelView;
import com.tigrang.mvc.controller.Controller;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public class AdminControlPanelController extends Controller {

	private DefaultTreeModel model;

	public AdminControlPanelController() {
		setView(new AdminControlPanelView());

		TreeNode rootNode = new DefaultMutableTreeNode(DataSource.get().getRoot());
		model = new DefaultTreeModel(rootNode, true);

		JTree tree = ((JTree) getView().findComponentById(R.id.tree));
		tree.setModel(model);
		tree.setSelectionPath(new TreePath(model.getPathToRoot(rootNode)));

		addEventListeners();

		getView().findComponentById(R.id.frame).setVisible(true);
	}

	private void addEventListeners() {
		// Connect add new user/group buttons
		getView().findComponentByIdAs(R.id.add_user_btn, JButton.class)
				.addActionListener(new AddNewUserDelegate((AdminControlPanelView) getView(), model));

		getView().findComponentByIdAs(R.id.add_group_btn, JButton.class)
				.addActionListener(new AddNewGroupDelegate((AdminControlPanelView) getView(), model));

		// Connect open user dialog button
		getView().findComponentByIdAs(R.id.open_user_view_btn, JButton.class)
				.addActionListener(new OpenUserDialogDelegate(getView()));

		// Connect the show total dialog buttons
		getView().findComponentByIdAs(R.id.show_user_total_btn, JButton.class)
				.addActionListener(ae -> showTotalDialog("users", DataSource.get().getUsers().size()));

		getView().findComponentByIdAs(R.id.show_group_total_btn, JButton.class)
				.addActionListener(ae -> showTotalDialog("groups", DataSource.get().getGroups().size()));

		getView().findComponentByIdAs(R.id.show_messages_total_btn, JButton.class)
				.addActionListener(ae -> showTotalDialog("messages", 0));

		getView().findComponentByIdAs(R.id.show_pos_percentage_btn, JButton.class)
				.addActionListener(ae -> showTotalDialog("positive messages", 0));
	}

	private void showTotalDialog(String type, int count) {
		JOptionPane.showMessageDialog(null, String.format("There are %d total %s.", count, type));
	}
}
