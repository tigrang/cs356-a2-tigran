package com.tigrang.cs356.a2.model.entity;

import com.tigrang.cs356.a2.model.visitor.TweetAcceptor;
import com.tigrang.cs356.a2.model.visitor.TweetVisitor;
import com.tigrang.cs356.a2.util.IndexedHashMap;
import com.tigrang.mvc.model.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Group extends Entity implements TweetAcceptor {

	public static final long ROOT_ID = 1;

	private static AtomicInteger atomicInteger = new AtomicInteger();
	private List<GroupChangeListener> groupChangeListenerList;
	private String name;

	private IndexedHashMap<Long, User> users;
	private IndexedHashMap<Long, Group> groups;
	private Group parent;

	public Group(String name) {
		super();
		this.name = name;
		this.users = new IndexedHashMap<>();
		this.groups = new IndexedHashMap<>();
		groupChangeListenerList = new ArrayList<>();
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

	public IndexedHashMap<Long, User> getUsers() {
		return users;
	}

	public IndexedHashMap<Long, Group> getGroups() {
		return groups;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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