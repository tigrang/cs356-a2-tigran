package com.tigrang.cs356.a2.model.entity;

import com.tigrang.cs356.a2.model.visitor.TweetAcceptor;
import com.tigrang.cs356.a2.model.visitor.TweetVisitor;
import com.tigrang.mvc.model.Entity;

import java.util.ArrayList;
import java.util.List;

public class Group extends Entity implements TweetAcceptor {

	public static final long ROOT_ID = 1;

	private List<GroupChangeListener> groupChangeListenerList;
	private String name;
	private List<User> users;
	private List<Group> groups;
	private List<Entity> children;
	private Group parent;

	public Group(String name) {
		super();
		this.name = name;
		this.users = new ArrayList<>();
		this.groups = new ArrayList<>();
		this.children = new ArrayList<>();
		this.groupChangeListenerList = new ArrayList<>();
	}

	/**
	 * Gets a list of children in this group
	 *
	 * @return
	 */
	public List<Entity> getChildren() {
		return children;
	}

	/**
	 * Attach an event listener for changes in this group
	 *
	 * @param listener
	 */
	public void addGroupChangeListener(GroupChangeListener listener) {
		groupChangeListenerList.add(listener);
	}

	/**
	 * Removes an event listener
	 *
	 * @param listener
	 */
	public void removeGroupChangeListener(GroupChangeListener listener) {
		groupChangeListenerList.remove(listener);
	}

	/**
	 * Gets a list of all users in this group
	 *
	 * @return
	 */
	public List<User> getUsers() {
		return users;
	}

	/**
	 * Returns a list of all sub-groups in this group
	 *
	 * @return
	 */
	public List<Group> getGroups() {
		return groups;
	}

	/**
	 * Gets the name of this group
	 *
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of this group
	 *
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Adds a user to this group
	 *
	 * @param user
	 */
	public void addUser(User user) {
		if (users.contains(user)) {
			return;
		}
		children.add(user);
		users.add(user);
		user.setGroup(this);
		setChanged();
		notifyObservers();
		notifyUserAdded(user);
	}

	/**
	 * Removes a user from this group
	 *
	 * @param user
	 */
	public void removeUser(User user) {
		children.remove(user);
		users.remove(user.getId());
		user.setGroup(null);
		setChanged();
		notifyObservers();
		notifyUserRemoved(user);
	}

	/**
	 * Adds a sub-group to this group
	 *
	 * @param group
	 */
	public void addGroup(Group group) {
		if (groups.contains(group)) {
			return;
		}

		children.add(group);
		groups.add(group);
		group.setParent(this);
		setChanged();
		notifyObservers();
		notifyGroupAdded(group);
	}

	/**
	 * Removes a sub-group from this group
	 *
	 * @param group
	 */
	public void removeGroup(Group group) {
		children.remove(group);
		groups.remove(group);
		setChanged();
		notifyObservers();
		notifyGroupRemoved(group);
	}

	/**
	 * Gets the parent of this group
	 *
	 * @return
	 */
	public Group getParent() {
		return parent;
	}

	/**
	 * Sets the parent for this group
	 *
	 * @param group
	 */
	public void setParent(Group group) {
		this.parent = group;
		if (!group.getGroups().contains(group)) {
			group.addGroup(this);
		}
		setChanged();
		notifyObservers();
	}

	/**
	 * toString()
	 *
	 * @return
	 */
	public String toString() {
		return name;
	}

	@Override
	public void accept(TweetVisitor visitor) {
		groups.forEach((group) -> group.accept(visitor));
		users.forEach((user) -> user.accept(visitor));
	}

	/**
	 * Notify all listeners that a group was added to this group
	 *
	 * @param group
	 */
	protected void notifyGroupAdded(Group group) {
		for (GroupChangeListener listener : groupChangeListenerList) {
			listener.onGroupAdded(this, group);
		}
	}

	/**
	 * Notify all listeners that a group was removed to this group
	 *
	 * @param group
	 */
	protected void notifyGroupRemoved(Group group) {
		for (GroupChangeListener listener : groupChangeListenerList) {
			listener.onGroupRemoved(this, group);
		}
	}

	/**
	 * Notify all listeners that a user was added to this group
	 *
	 * @param user
	 */
	protected void notifyUserAdded(User user) {
		for (GroupChangeListener listener : groupChangeListenerList) {
			listener.onUserAdded(this, user);
		}
	}

	/**
	 * Notify all listeners that a user was removed to this group
	 *
	 * @param user
	 */
	protected void notifyUserRemoved(User user) {
		for (GroupChangeListener listener : groupChangeListenerList) {
			listener.onUserRemoved(this, user);
		}
	}

	public interface GroupChangeListener {

		public void onGroupAdded(Group parent, Group child);

		public void onGroupRemoved(Group parent, Group child);

		public void onUserAdded(Group group, User user);

		public void onUserRemoved(Group group, User user);
	}
}