package com.tigrang.cs356.a2.model;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class User {

	private static AtomicInteger atomicInteger = new AtomicInteger();
	private int id;
	private String username;
	private Group group;
	private Set<Integer> following;

	protected User(int id, String username) {
		this.id = id;
		this.username = username;
		this.following = new LinkedHashSet<>();
	}

	public static User newUser(String username) {
		return new User(atomicInteger.incrementAndGet(), username);
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		if (this.group == group) {
			return;
		}

		this.group = group;
		group.addUser(this);
	}

	public Set<Integer> getFollowingIds() {
		return following;
	}

	public void follow(User user) {
		follow(user.getId());
	}

	public void follow(int id) {
		following.add(id);
	}

	public String getUsername() {
		return username;
	}

	public int getId() {
		return id;
	}

	public String toString() {
		return username;
	}
}