package com.tigrang.cs356.a2.view;

import com.tigrang.cs356.a2.model.User;
import com.tigrang.mvc.view.View;
import com.tigrang.mvc.view.ViewElement;

import javax.swing.*;

public class UserOverviewView extends View {

	@ViewElement(id = "user_overview_container")
	private JPanel container;
	@ViewElement(id = "txt_user_id")
	private JTextField txtUserId;
	@ViewElement(id = "btn_follow_user")
	private JButton followUserButton;
	@ViewElement(id = "list_following")
	private JList listFollowing;
	@ViewElement(id = "txt_tweet")
	private JTextField txtTweet;
	@ViewElement(id = "btn_post_tweet")
	private JButton postTweetButton;
	@ViewElement(id = "list_news_feed")
	private JList listNewsFeed;

	public UserOverviewView(DefaultListModel<User> followingModel) {
		JFrame frame = new JFrame("User Overview");
		frame.setContentPane(container);
		frame.pack();

		setRoot(frame);
		listFollowing.setModel(followingModel);
	}

	@Override
	public void setupUI() {

	}
}
