package com.tigrang.cs356.a2.model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NewsFeedListModel extends AbstractListModel<Tweet> {

	private User user;

	private List<Tweet> feed;

	public NewsFeedListModel(User user) {
		this.user = user;
		this.feed = new ArrayList<>();

		generateFeed();
		user.addObserver((o, a) -> generateFeed());
	}

	private void generateFeed() {
		feed.clear();
		feed = new ArrayList<>(user.getTweets());

		for (int id : user.getFollowingIds()) {
			feed.addAll(DataSource.get().getUsers().get(id).getTweets());
		}

		Collections.sort(feed);
		fireContentsChanged(feed, 0, getSize() - 1);
	}

	@Override
	public int getSize() {
		return feed.size();
	}

	@Override
	public Tweet getElementAt(int index) {
		return feed.get(index);
	}
}
