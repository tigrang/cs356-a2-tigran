package com.tigrang.cs356.a2.model;

import com.tigrang.cs356.a2.model.entity.Group;
import com.tigrang.cs356.a2.model.entity.User;

import javax.swing.tree.TreePath;
import java.util.ArrayList;
import java.util.List;

public class UserTreeModel extends AbstractTreeModel<Group> implements Group.GroupChangeListener {

	public UserTreeModel(Group root) {
		super(root);
		root.addGroupChangeListener(this);
	}

	@Override
	public void setRoot(Group root) {
		if (getRoot() != null) {
			getRoot().removeGroupChangeListener(this);
		}
		super.setRoot(root);
	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		Group group = (Group) parent;
		int index = group.getGroups().indexOf(child);
		if (index == -1) {
			index = group.getUsers().indexOf(child);
			if (index != -1) {
				index += group.getGroups().size();
			}
		}
		return index;
	}

	@Override
	public Object getChild(Object parent, int index) {
		Group group = (Group) parent;
		int groupCount = group.getGroups().size();

		if (index < groupCount) {
			return group.getGroups().at(index);
		}

		return group.getUsers().at(index - groupCount);
	}

	@Override
	public int getChildCount(Object parent) {
		Group group = (Group) parent;
		return group.getUsers().size() + group.getGroups().size();
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
		List<Object> path = new ArrayList<>();
		path.add(node);

		Group group;
		if (node instanceof User) {
			group = ((User) node).getGroup();
			path.add(0, group);
		} else {
			group = (Group) node;
		}

		while (group.getParent() != null) {
			path.add(0, group.getParent());
			group = group.getParent();
		}

		return path.toArray();
	}

	@Override
	public void onGroupAdded(Group parent, Group child) {
		child.addGroupChangeListener(this);

		int[] newIndexes = new int[1];
		newIndexes[0] = getIndexOfChild(parent, child);
		nodesWereInserted(parent, newIndexes);
	}

	@Override
	public void onGroupRemoved(Group parent, Group child) {
		child.removeGroupChangeListener(this);

		int[] childIndex = new int[1];
		Object[] removedArray = new Object[1];

		childIndex[0] = getIndexOfChild(parent, child);
		removedArray[0] = child;
		nodesWereRemoved(parent, childIndex, removedArray);
	}

	@Override
	public void onUserAdded(Group group, User user) {
		int[] newIndexes = new int[1];
		newIndexes[0] = getIndexOfChild(group, user);
		nodesWereInserted(group, newIndexes);
	}

	@Override
	public void onUserRemoved(Group group, User user) {
		int[] childIndex = new int[1];
		Object[] removedArray = new Object[1];

		childIndex[0] = getIndexOfChild(group, user);
		removedArray[0] = user;
		nodesWereRemoved(group, childIndex, removedArray);
	}
}
