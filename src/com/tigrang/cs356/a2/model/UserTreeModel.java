package com.tigrang.cs356.a2.model;

import javax.swing.tree.TreePath;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		Group group = (Group) parent;
		int index = -1;

		if (group.getGroups().containsValue(child)) {
			for (Map.Entry<Integer, Group> entry : group.getGroups().entrySet()) {
				index++;
				if (entry.getValue().equals(child)) {
					return index;
				}
			}
		}

		if (group.getUsers().containsValue(child)) {
			for (Map.Entry<Integer, User> entry : group.getUsers().entrySet()) {
				index++;
				if (entry.getValue().equals(child)) {
					return index;
				}
			}
		}

		return -1;
	}

	@Override
	public Object getChild(Object parent, int index) {
		Group group = (Group) parent;
		int groupCount = group.getGroups().size();

		if (index < groupCount) {
			return new ArrayList<>(group.getGroups().values()).get(index);
		}

		return new ArrayList<>(group.getUsers().values()).get(index - groupCount);
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
	public Object[] getPathToRoot(Group group) {
		List<Object> path = new ArrayList<>();
		path.add(group);
		while (group.getParent() != null) {
			path.add(0, group.getParent());
			group = group.getParent();
		}

		return path.toArray();
	}
}
