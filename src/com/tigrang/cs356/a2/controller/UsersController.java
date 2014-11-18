package com.tigrang.cs356.a2.controller;

import com.tigrang.cs356.a2.model.entity.Group;
import com.tigrang.cs356.a2.model.entity.User;
import com.tigrang.mvc.controller.Controller;
import com.tigrang.mvc.model.Repository;

public class UsersController extends Controller<User> {

	public UsersController(Repository<User> repository) {
		super(repository);
	}

	public User add(Group group, String username) throws Exception {
		if (username.isEmpty()) {
			throw new Exception("Enter a username.");
		}

		if (group == null) {
			throw new Exception("Select a group first.");
		}

		User user = new User(username);
		getRepository().add(user);
		user.setGroup(group);

		return user;
	}

	public void follow(User user, int followerId) throws Exception {
		if (user.getId() == followerId) {
			throw new Exception("Cannot follow yourself.");
		}

		if (getRepository().findById(followerId) == null) {
			throw new Exception("User does not exist.");
		}

		user.follow(followerId);
	}

	public int getTotal() {
		return getRepository().size();
	}
}
