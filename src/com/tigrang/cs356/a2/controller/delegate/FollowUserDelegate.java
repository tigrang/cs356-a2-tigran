package com.tigrang.cs356.a2.controller.delegate;

import com.tigrang.cs356.a2.model.User;
import com.tigrang.cs356.a2.mvc.R;
import com.tigrang.cs356.a2.view.UserOverviewView;
import com.tigrang.mvc.delegate.ActionDelegate;

import java.awt.event.ActionEvent;

public class FollowUserDelegate extends ActionDelegate {

	private UserOverviewView view;
	private User user;

	public FollowUserDelegate(UserOverviewView view, User user) {
		this.view = view;
		this.user = user;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		try {
			int id = view.getFollowerId();
			user.follow(id);
			view.clearFollowerId();
		} catch (NumberFormatException e) {
			view.showError("Enter a valid id.", R.id.txt_user_id);
		}
	}
}
