package com.tigrang.cs356.a2.controller;

import com.tigrang.cs356.a2.model.DataSource;
import com.tigrang.cs356.a2.model.User;
import com.tigrang.cs356.a2.view.UserOverviewView;
import com.tigrang.mvc.controller.Controller;

import javax.swing.*;

public class UserOverviewController extends Controller {

	private User user;

	private DefaultListModel<User> followingModel;

	public UserOverviewController(User user) {
		this.user = user;
		setupModel();
		setView(new UserOverviewView(followingModel));

		/**
		 * TODO:
		 * 	- add observer to user when a new user is followed
		 *
		 * 	user.addFollowingObserver(this);
		 * 		refreshes the model
		 */
	}

	private void setupModel() {
		followingModel = new DefaultListModel<>();

		for (int id : user.getFollowingIds()) {
			followingModel.addElement(DataSource.get().getUsers().get(id));
		}
	}

	private void addDelegates() {

	}
}
