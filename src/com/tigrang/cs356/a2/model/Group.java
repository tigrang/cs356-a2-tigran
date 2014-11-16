package com.tigrang.cs356.a2.model;

import com.tigrang.cs356.a2.model.visitor.TweetAcceptor;
import com.tigrang.cs356.a2.model.visitor.TweetVisitor;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Group extends Observable implements TweetAcceptor {

	private static AtomicInteger atomicInteger = new AtomicInteger();
	private List<GroupChangeListener> groupChangeListenerList;
	private int id;
	private String name;
	private Map<Integer, User> users;
	private Map<Integer, Group> groups;
	private Group parent;

	protected Group(int id, String name) {
		this.id = id;
		this.name = name;
		this.users = new HashMap<>();
		this.groups = new HashMap<>();
		groupChangeListenerList = new ArrayList<>();
	}

	public static Group newGroup(String name) {
		return new Group(atomicInteger.incrementAndGet(), name);
	}

	public void addGroupChangeListener(GroupChangeListener listener) {
		groupChangeListenerList.add(listener);
	}

	public void removeGroupChangeListener(GroupChangeListener listener) {
		groupChangeListenerList.remove(listener);
	}

	protected void notifyGroupAdded(Group group) {
		for (GroupChangeListener listener : groupChangeListenerList) {
			listener.onGroupAdded(this, group);
		}
	}

	protected void notifyGroupRemoved(Group group) {
		for (GroupChangeListener listener : groupChangeListenerList) {
			listener.onGroupRemoved(this, group);
		}
	}

	protected void notifyUserAdded(User user) {
		for (GroupChangeListener listener : groupChangeListenerList) {
			listener.onUserAdded(this, user);
		}
	}

	protected void notifyUserRemoved(User user) {
		for (GroupChangeListener listener : groupChangeListenerList) {
			listener.onUserRemoved(this, user);
		}
	}

	public Map<Integer, User> getUsers() {
		return users;
	}

	public Map<Integer, Group> getGroups() {
		return groups;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void addUser(User user) {
		if (users.containsKey(user.getId())) {
			return;
		}
		users.put(user.getId(), user);
		user.setGroup(this);
		setChanged();
		notifyObservers();
		notifyUserAdded(user);
	}

	public void removeUser(User user) {
		users.remove(user.getId());
		user.setGroup(null);
		setChanged();
		notifyObservers();
		notifyUserRemoved(user);
	}

	public void addGroup(Group group) {
		groups.put(group.getId(), group);
		group.setParent(this);
		setChanged();
		notifyObservers();
		notifyGroupAdded(group);
	}

	public void removeGroup(Group group) {
		groups.remove(group.getId());
		setChanged();
		notifyObservers();
		notifyGroupRemoved(group);
	}

	public Group getParent() {
		return parent;
	}

	public void setParent(Group group) {
		this.parent = group;
		if (!group.getGroups().containsKey(getId())) {
			group.addGroup(this);
		}
		setChanged();
		notifyObservers();
	}

	public String toString() {
		return name;
	}

	@Override
	public void accept(TweetVisitor visitor) {
		groups.forEach((id, group) -> group.accept(visitor));
		users.forEach((id, user) -> user.accept(visitor));
	}

	public interface GroupChangeListener {

		public void onGroupAdded(Group parent, Group child);

		public void onGroupRemoved(Group parent, Group child);

		public void onUserAdded(Group group, User user);

		public void onUserRemoved(Group group, User user);
	}
}