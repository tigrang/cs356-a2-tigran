package com.tigrang.cs356.a2.delegate;

import com.tigrang.cs356.a2.model.User;
import com.tigrang.cs356.a2.mvc.R;
import com.tigrang.mvc.view.View;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenUserDialogDelegate implements ActionListener {

	private View view;

	public OpenUserDialogDelegate(View view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		TreePath path = view.findComponentByIdAs(R.id.tree, JTree.class).getSelectionPath();

		if (path == null) {
			JOptionPane.showMessageDialog(null, "Select a node first.");
			return;
		}

		DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
		if (node.isRoot() || node.getAllowsChildren()) {
			JOptionPane.showMessageDialog(null, "Select a valid user.");
			return;
		}

		User user = (User) node.getUserObject();
		JOptionPane.showMessageDialog(null, user);

//		UserDialogController controller = new UserDialogController();
//		controller.setUser(user);
//		controller.run();
	}
}
