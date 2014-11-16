package com.tigrang.cs356.a2.controller.delegate;

import com.tigrang.cs356.a2.controller.UserOverviewController;
import com.tigrang.cs356.a2.model.User;
import com.tigrang.cs356.a2.mvc.R;
import com.tigrang.mvc.delegate.ActionDelegate;
import com.tigrang.mvc.view.View;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;

public class OpenUserDialogDelegate extends ActionDelegate {

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

		Object node = path.getLastPathComponent();
		if (!(node instanceof User)) {
			JOptionPane.showMessageDialog(null, "Select a valid user.");
			return;
		}

		UserOverviewController controller = new UserOverviewController((User) node);
		controller.showView(true);
	}
}
