package com.tigrang.cs356.a2.controller;

import com.tigrang.cs356.a2.controller.delegate.AddTweetDelegate;
import com.tigrang.cs356.a2.controller.delegate.FollowUserDelegate;
import com.tigrang.cs356.a2.model.DataSource;
import com.tigrang.cs356.a2.model.User;
import com.tigrang.cs356.a2.mvc.R;
import com.tigrang.cs356.a2.view.UserOverviewView;
import com.tigrang.mvc.controller.Controller;

import javax.swing.*;

public class UserOverviewController extends Controller {

	private User user;

	private UserOverviewView view;

	private DefaultListModel<User> followingModel;

	public UserOverviewController(User user) {
		this.user = user;

		setupModel();
		setupView();
		addDelegates();
	}

	private void setupModel() {
		followingModel = new DefaultListModel<>();

		for (int id : user.getFollowingIds()) {
			followingModel.addElement(DataSource.get().getUsers().get(id));
		}
	}

	private void setupView() {
		view = new UserOverviewView(user);
		setView(view);
	}

	private void addDelegates() {
		view.addDelegate(R.id.btn_follow_user, new FollowUserDelegate(view, user));
		view.addDelegate(R.id.btn_post_tweet, new AddTweetDelegate(view, user));
	}
}
