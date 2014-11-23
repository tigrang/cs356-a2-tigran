package com.tigrang.cs356.a2.view;

import com.tigrang.cs356.a2.controller.TweetsController;
import com.tigrang.cs356.a2.controller.UsersController;
import com.tigrang.cs356.a2.model.NewsFeedListModel;
import com.tigrang.cs356.a2.model.UserFollowingListModel;
import com.tigrang.cs356.a2.model.entity.User;
import com.tigrang.mvc.view.View;

import javax.swing.*;

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

	/**
	 * Constructor
	 *
	 * @param user The user this view is for
	 */
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

	/**
	 * Connects user UI events to controller actions
	 */
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

	/**
	 * Sets up the initial view
	 */
	public void setupUI() {
		frame = new JFrame();
		frame.setContentPane(container);
		frame.pack();
		setRoot(frame);
		setTitle();
	}

	/**
	 * Set up view models
	 */
	private void setupModel() {
		followingListModel = new UserFollowingListModel(user);
		newsFeedListModel = new NewsFeedListModel(user);

		listFollowing.setModel(followingListModel);
		listNewsFeed.setModel(newsFeedListModel);
	}

	/**
	 * Sets the title of the frame
	 */
	public void setTitle() {
		frame.setTitle("User Overview: " + user);
	}

	/**
	 * Gets the text user entered for follower id field and converts it into an int
	 *
	 * @return integer of the follower id
	 */
	public int getFollowerId() {
		return Integer.parseInt(txtUserId.getText());
	}

	/**
	 * Clears the follower id text field
	 */
	public void clearFollowerId() {
		txtUserId.setText("");
	}

	/**
	 * Gets the text the user entered in message text field
	 *
	 * @return tweet message string
	 */
	public String getTextTweetMessage() {
		return txtTweet.getText();
	}

	/**
	 * Clears the tweet text message text field
	 */
	public void clearTextTweetMessage() {
		txtTweet.setText("");
	}
}
