package com.tigrang.cs356.a2.controller.delegate;

import com.tigrang.cs356.a2.model.DataSource;
import com.tigrang.cs356.a2.model.Group;
import com.tigrang.cs356.a2.model.User;
import com.tigrang.mvc.delegate.TreeSelectionDelegate;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class UpdateActiveGroupNodeDelegate extends TreeSelectionDelegate {

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		TreePath path = e.getNewLeadSelectionPath();
		if (path == null) {
			DataSource.get().setActiveGroup(null);
			return;
		}

		Object node = path.getLastPathComponent();
		if (node instanceof User) {
			node = ((User) node).getGroup();
		}
		DataSource.get().setActiveGroup((Group) node);
	}
}
