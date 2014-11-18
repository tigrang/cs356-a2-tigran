package com.tigrang.cs356.a2.controller;

import com.tigrang.cs356.a2.model.entity.Group;
import com.tigrang.cs356.a2.model.entity.User;
import com.tigrang.mvc.controller.Controller;
import com.tigrang.mvc.model.Repository;

/**
 * Users controller
 *
 * Handles adding, following, and stats related to users
 */
public class UsersController extends Controller<User> {

	public UsersController(Repository<User> repository) {
		super(repository);
	}

	/**
	 * Adds a new user to the given group
	 *
	 * @param group Group the user belongs to
	 * @param username Name of the user
	 * @return User
	 * @throws Exception
	 */
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

	/**
	 * Adds a follower by id for the given user
	 *
	 * @param user
	 * @param followerId
	 * @throws Exception
	 */
	public void follow(User user, int followerId) throws Exception {
		if (user.getId() == followerId) {
			throw new Exception("Cannot follow yourself.");
		}

		if (getRepository().findById(followerId) == null) {
			throw new Exception("User does not exist.");
		}

		user.follow(followerId);
	}

	/**
	 * Returns the total number of users
	 *
	 * @return int
	 */
	public int getTotal() {
		return getRepository().size();
	}
}
