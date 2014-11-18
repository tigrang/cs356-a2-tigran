package com.tigrang.cs356.a2.controller;

import com.tigrang.cs356.a2.model.entity.Group;
import com.tigrang.cs356.a2.model.entity.TextTweet;
import com.tigrang.cs356.a2.model.entity.Tweet;
import com.tigrang.cs356.a2.model.entity.User;
import com.tigrang.cs356.a2.model.visitor.PositiveTweetCountVisitor;
import com.tigrang.cs356.a2.model.visitor.TweetCountVisitor;
import com.tigrang.mvc.controller.Controller;
import com.tigrang.mvc.model.Repository;

/**
 * Tweets controller
 *
 * Handles adding, viewing, and stats related to groups
 */
public class TweetsController extends Controller<Tweet> {

	public TweetsController(Repository<Tweet> repository) {
		super(repository);
	}

	/**
	 * Adds a new Tweet for the given user
	 *
	 * @param user User creating the tweet
	 * @param message Body of the tweet
	 * @return Tweet
	 * @throws Exception
	 */
	public TextTweet add(User user, String message) throws Exception {
		if (message.isEmpty()) {
			throw new Exception("Enter a message first.");
		}

		TextTweet tweet = new TextTweet(message);
		user.addTweet(tweet);
		getRepository().add(tweet);
		return tweet;
	}

	/**
	 * Returns the total number of tweets in the given group
	 *
	 * @param root The root group to begin visiting
	 * @return int
	 */
	public int getTotal(Group root) {
		TweetCountVisitor tweetCountVisitor = new TweetCountVisitor();
		root.accept(tweetCountVisitor);
		return tweetCountVisitor.getCount();
	}

	/**
	 * Return the percentage of positive messages in the given group
	 *
	 * @param root The root group to begin visiting
	 * @return double
	 */
	public double getPositivePercentage(Group root) {
		PositiveTweetCountVisitor positiveMessageVisitor = new PositiveTweetCountVisitor();
		root.accept(positiveMessageVisitor);
		return positiveMessageVisitor.getPositivePercentage();
	}
}
