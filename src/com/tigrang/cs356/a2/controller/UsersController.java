package com.tigrang.cs356.a2.controller;

import com.tigrang.cs356.a2.model.DataSource;
import com.tigrang.cs356.a2.model.Group;
import com.tigrang.cs356.a2.model.User;

public class UsersController {

	public User add(String username) throws Exception {
		if (username.isEmpty()) {
			throw new Exception("Enter a username.");
		}

		Group group = DataSource.get().getActiveGroup();
		if (group == null) {
			throw new Exception("Select a group first.");
		}

		User user = User.newUser(username);
		user.setGroup(group);
		DataSource.get().getUsers().put(user.getId(), user);
		return user;
	}

	public void follow(User user, int followerId) {
		user.follow(followerId);
	}

	public int getTotal() {
		return DataSource.get().getUsers().size();
	}
}
