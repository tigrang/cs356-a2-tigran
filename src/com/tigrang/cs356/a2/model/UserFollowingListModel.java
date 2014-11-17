package com.tigrang.cs356.a2.model;

import com.tigrang.cs356.a2.model.entity.User;
import com.tigrang.mvc.model.RepositoryManager;

import javax.swing.*;
import java.util.ArrayList;

public class UserFollowingListModel extends AbstractListModel<User> {

	private User user;

	public UserFollowingListModel(User user) {
		this.user = user;
		user.addObserver((obj, arg) -> fireContentsChanged(obj, 0, getSize() - 1));
	}

	@Override
	public int getSize() {
		return user.getFollowingIds().size();
	}

	@Override
	public User getElementAt(int index) {
		long id = new ArrayList<>(user.getFollowingIds()).get(index);
		return RepositoryManager.getInstance().get(User.class).findById(id);
	}
}
