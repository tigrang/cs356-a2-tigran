package com.tigrang.cs356.a2.view;

import com.tigrang.cs356.a2.model.NewsFeedListModel;
import com.tigrang.cs356.a2.model.User;
import com.tigrang.cs356.a2.model.UserFollowingListModel;
import com.tigrang.mvc.view.View;
import com.tigrang.mvc.view.ViewElement;

import javax.swing.*;

public class UserOverviewView extends View {

	private JFrame frame;
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

	private User user;
	private UserFollowingListModel followingListModel;
	private NewsFeedListModel newsFeedListModel;

	public UserOverviewView(User user) {
		this.user = user;

		setupUI();
		setupModel();
		parseViewElements();
	}

	@Override
	public void setupUI() {
		frame = new JFrame();
		frame.setContentPane(container);
		frame.pack();
		setRoot(frame);
		setTitle();
	}

	private void setupModel() {
		followingListModel = new UserFollowingListModel(user);
		newsFeedListModel = new NewsFeedListModel(user);

		listFollowing.setModel(followingListModel);
		listNewsFeed.setModel(newsFeedListModel);
	}

	public void setTitle() {
		frame.setTitle("User Overview: " + user);
	}
}
