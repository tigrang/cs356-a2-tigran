package com.tigrang.cs356.a2.controller.delegate;

import com.tigrang.cs356.a2.model.TextTweet;
import com.tigrang.cs356.a2.model.User;
import com.tigrang.cs356.a2.mvc.R;
import com.tigrang.cs356.a2.view.UserOverviewView;
import com.tigrang.mvc.delegate.ActionDelegate;

import java.awt.event.ActionEvent;

public class AddTextTweetDelegate extends ActionDelegate {

	private UserOverviewView view;
	private User user;

	public AddTextTweetDelegate(UserOverviewView view, User user) {
		this.view = view;
		this.user = user;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String message = view.getTextTweetMessage();

		if (message.isEmpty()) {
			view.showError("Enter a message first.", R.id.txt_tweet);
			return;
		}

		user.addTweet(new TextTweet(message));
		view.clearTextTweetMessage();
	}
}
