package com.tigrang.cs356.a2.model;

import com.tigrang.cs356.a2.model.entity.Group;
import com.tigrang.cs356.a2.model.entity.User;

import javax.swing.tree.TreePath;
import java.util.ArrayDeque;
import java.util.Deque;

public class UserTreeModel extends AbstractTreeModel<Group> implements Group.GroupChangeListener {

	public UserTreeModel(Group root) {
		super(root);
	}

	@Override
	public Group setRoot(Group root) {
		Group oldRoot = super.setRoot(root);
		root.addGroupChangeListener(this);

		if (oldRoot != null) {
			oldRoot.removeGroupChangeListener(this);
		}

		return oldRoot;
	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		return ((Group) parent).getChildren().indexOf(child);
	}

	@Override
	public Object getChild(Object parent, int index) {
		return ((Group) parent).getChildren().get(index);
	}

	@Override
	public int getChildCount(Object parent) {
		return ((Group) parent).getChildren().size();
	}

	@Override
	public boolean isLeaf(Object node) {
		return node instanceof User;
	}

	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {

	}

	@Override
	public Object[] getPathToRoot(Object node) {
		Deque<Object> path = new ArrayDeque<>();
		path.add(node);

		Group group;
		if (node instanceof User) {
			group = ((User) node).getGroup();
			path.addFirst(group);
		} else {
			group = (Group) node;
		}

		while (group.getParent() != null) {
			path.addFirst(group.getParent());
			group = group.getParent();
		}

		return path.toArray();
	}

	@Override
	public void onGroupAdded(Group parent, Group child) {
		child.addGroupChangeListener(this);
		nodeInserted(parent, child);
	}

	@Override
	public void onGroupRemoved(Group parent, Group child) {
		child.removeGroupChangeListener(this);
		nodeRemoved(parent, child);
	}

	@Override
	public void onUserAdded(Group group, User user) {
		nodeInserted(group, user);
	}

	@Override
	public void onUserRemoved(Group group, User user) {
		nodeRemoved(group, user);
	}
}
