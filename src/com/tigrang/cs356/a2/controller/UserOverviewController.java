package com.tigrang.cs356.a2.controller;

import com.tigrang.cs356.a2.controller.delegate.AddTweetDelegate;
import com.tigrang.cs356.a2.controller.delegate.FollowUserDelegate;
import com.tigrang.cs356.a2.model.User;
import com.tigrang.cs356.a2.mvc.R;
import com.tigrang.cs356.a2.view.UserOverviewView;
import com.tigrang.mvc.controller.Controller;

public class UserOverviewController extends Controller {

	private User user;

	private UserOverviewView view;

	public UserOverviewController(User user) {
		this.user = user;

		setupView();
		addDelegates();
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
