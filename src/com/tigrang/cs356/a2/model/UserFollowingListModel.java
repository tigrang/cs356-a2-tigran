package com.tigrang.cs356.a2.model;

import javax.swing.*;
import java.util.ArrayList;

public class UserFollowingListModel extends AbstractListModel<User> {

	private User user;

	public UserFollowingListModel(User user) {
		this.user = user;
		user.addObserver((o, a) -> fireContentsChanged(o, 0, getSize() - 1));
	}

	@Override
	public int getSize() {
		return user.getFollowingIds().size();
	}

	@Override
	public User getElementAt(int index) {
		int id = new ArrayList<>(user.getFollowingIds()).get(index);
		return DataSource.get().getUsers().get(id);
	}
}
