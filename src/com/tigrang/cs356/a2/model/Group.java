package com.tigrang.cs356.a2.model;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Group {

	private static AtomicInteger atomicInteger = new AtomicInteger();
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
	}

	public static Group newGroup(String name) {
		return new Group(atomicInteger.incrementAndGet(), name);
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
	}

	public void removeUser(User user) {
		users.remove(user.getId());
		user.setGroup(null);
	}

	public void addGroup(Group group) {
		groups.put(group.getId(), group);
		group.setParent(this);
	}

	public void removeGroup(Group group) {
		groups.remove(group.getId());
	}

	public Group getParent() {
		return parent;
	}

	public void setParent(Group group) {
		this.parent = group;
	}

	public String toString() {
		return name;
	}
}