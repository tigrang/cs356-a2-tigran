package com.tigrang.cs356.a2.controller;

import com.tigrang.cs356.a2.model.DataSource;
import com.tigrang.cs356.a2.model.TextTweet;
import com.tigrang.cs356.a2.model.User;
import com.tigrang.cs356.a2.model.visitor.PositiveTweetCountVisitor;
import com.tigrang.cs356.a2.model.visitor.TweetCountVisitor;

public class TweetsController {

	public TextTweet add(User user, String message) throws Exception {
		if (message.isEmpty()) {
			throw new Exception("Enter a message first.");
		}

		TextTweet tweet = new TextTweet(message);
		user.addTweet(tweet);
		return tweet;
	}

	public int getTotal() {
		TweetCountVisitor tweetCountVisitor = new TweetCountVisitor();
		DataSource.get().getRoot().accept(tweetCountVisitor);
		return tweetCountVisitor.getCount();
	}

	public double getPositivePercentage() {
		PositiveTweetCountVisitor positiveMessageVisitor = new PositiveTweetCountVisitor();
		DataSource.get().getRoot().accept(positiveMessageVisitor);
		return positiveMessageVisitor.getPositivePercentage();
	}
}
