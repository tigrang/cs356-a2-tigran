package com.tigrang.cs356.a2.view;

import com.tigrang.cs356.a2.controller.TweetsController;
import com.tigrang.cs356.a2.controller.UsersController;
import com.tigrang.cs356.a2.model.NewsFeedListModel;
import com.tigrang.cs356.a2.model.UserFollowingListModel;
import com.tigrang.cs356.a2.model.entity.User;
import com.tigrang.mvc.view.View;

import javax.swing.*;
import java.awt.*;

public class UserOverviewDialog extends View {

	private TweetsController tweetsController;

	private UsersController usersController;

	private User user;

	private UserFollowingListModel followingListModel;

	private NewsFeedListModel newsFeedListModel;

	private JFrame frame;

	private JPanel container;

	private JTextField txtUserId;

	private JButton followUserButton;

	private JList listFollowing;

	private JTextField txtTweet;

	private JButton postTweetButton;

	private JList listNewsFeed;

	public UserOverviewDialog(User user) {
		this.user = user;
	}

	public void init(UsersController usersController, TweetsController tweetsController) {
		this.usersController = usersController;
		this.tweetsController = tweetsController;

		setupUI();
		setupModel();
		setupActions();
	}

	public void show(boolean visible) {
		getRoot().setVisible(visible);
	}

	private void setupActions() {
		followUserButton.addActionListener((ae) -> {
			try {
				usersController.follow(user, getFollowerId());
				clearFollowerId();
			} catch (NumberFormatException e) {
				showError("Enter a valid user id", txtUserId);
			} catch (Exception e) {
				showError(e.getMessage(), txtUserId);
			}
		});

		postTweetButton.addActionListener((ae) -> {
			try {
				tweetsController.add(user, getTextTweetMessage());
				clearTextTweetMessage();
			} catch (Exception e) {
				showError(e.getMessage(), txtTweet);
			}
		});
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

	public int getFollowerId() {
		return Integer.parseInt(txtUserId.getText());
	}

	public void clearFollowerId() {
		txtUserId.setText("");
	}

	public String getTextTweetMessage() {
		return txtTweet.getText();
	}

	public void clearTextTweetMessage() {
		txtTweet.setText("");
	}

	public void showError(String message, Component component) {
		JOptionPane.showMessageDialog(getRoot(), message, "Error", JOptionPane.ERROR_MESSAGE);
		component.requestFocus();
	}
}
