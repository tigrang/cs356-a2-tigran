package com.tigrang.cs356.a2.model;

import javax.swing.event.EventListenerList;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 * Modified version of DefaultTreeModel. Made Abstract and Generic.
 */
public abstract class AbstractTreeModel<T> implements TreeModel {

	protected EventListenerList listenerList = new EventListenerList();

	protected T root;

	public AbstractTreeModel(T root) {
		setRoot(root);
	}

	public T getRoot() {
		return root;
	}

	public void setRoot(T root) {
		Object oldRoot = this.root;
		this.root = root;
		if (root == null && oldRoot != null) {
			fireTreeStructureChanged(this, null);
		} else {
			nodeStructureChanged(root);
		}
	}

	public void reload() {
		reload(root);
	}

	public void reload(T node) {
		if (node != null) {
			fireTreeStructureChanged(this, getPathToRoot(node), null, null);
		}
	}

	protected void nodesWereInserted(T node, int[] childIndices) {
		if (listenerList != null && node != null && childIndices != null
				&& childIndices.length > 0) {
			int cCount = childIndices.length;
			Object[] newChildren = new Object[cCount];

			for (int counter = 0; counter < cCount; counter++)
				newChildren[counter] = getChild(node, childIndices[counter]);
			fireTreeNodesInserted(this, getPathToRoot(node), childIndices,
					newChildren);
		}
	}

	protected void nodesWereRemoved(T node, int[] childIndices,
									Object[] removedChildren) {
		if (node != null && childIndices != null) {
			fireTreeNodesRemoved(this, getPathToRoot(node), childIndices,
					removedChildren);
		}
	}

	public void nodeStructureChanged(T node) {
		if (node != null) {
			fireTreeStructureChanged(this, getPathToRoot(node), null, null);
		}
	}

	public abstract Object[] getPathToRoot(Object node);

	public void addTreeModelListener(TreeModelListener l) {
		listenerList.add(TreeModelListener.class, l);
	}

	public void removeTreeModelListener(TreeModelListener l) {
		listenerList.remove(TreeModelListener.class, l);
	}

	public TreeModelListener[] getTreeModelListeners() {
		return listenerList.getListeners(TreeModelListener.class);
	}

	protected void fireTreeNodesInserted(Object source, Object[] path,
										 int[] childIndices,
										 Object[] children) {
		// Guaranteed to return a non-null array
		Object[] listeners = listenerList.getListenerList();
		TreeModelEvent e = null;
		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == TreeModelListener.class) {
				// Lazily create the event:
				if (e == null)
					e = new TreeModelEvent(source, path,
							childIndices, children);
				((TreeModelListener) listeners[i + 1]).treeNodesInserted(e);
			}
		}
	}

	protected void fireTreeNodesRemoved(Object source, Object[] path,
										int[] childIndices,
										Object[] children) {
		// Guaranteed to return a non-null array
		Object[] listeners = listenerList.getListenerList();
		TreeModelEvent e = null;
		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == TreeModelListener.class) {
				// Lazily create the event:
				if (e == null)
					e = new TreeModelEvent(source, path,
							childIndices, children);
				((TreeModelListener) listeners[i + 1]).treeNodesRemoved(e);
			}
		}
	}

	protected void fireTreeStructureChanged(Object source, Object[] path,
											int[] childIndices,
											Object[] children) {
		// Guaranteed to return a non-null array
		Object[] listeners = listenerList.getListenerList();
		TreeModelEvent e = null;
		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == TreeModelListener.class) {
				// Lazily create the event:
				if (e == null)
					e = new TreeModelEvent(source, path,
							childIndices, children);
				((TreeModelListener) listeners[i + 1]).treeStructureChanged(e);
			}
		}
	}

	protected void fireTreeStructureChanged(Object source, TreePath path) {
		// Guaranteed to return a non-null array
		Object[] listeners = listenerList.getListenerList();
		TreeModelEvent e = null;
		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == TreeModelListener.class) {
				// Lazily create the event:
				if (e == null)
					e = new TreeModelEvent(source, path);
				((TreeModelListener) listeners[i + 1]).treeStructureChanged(e);
			}
		}
	}
}
