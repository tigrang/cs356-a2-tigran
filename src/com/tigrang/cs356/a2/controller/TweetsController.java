package com.tigrang.cs356.a2.controller;

import com.tigrang.cs356.a2.model.entity.Group;
import com.tigrang.cs356.a2.model.entity.TextTweet;
import com.tigrang.cs356.a2.model.entity.Tweet;
import com.tigrang.cs356.a2.model.entity.User;
import com.tigrang.cs356.a2.model.visitor.PositiveTweetCountVisitor;
import com.tigrang.cs356.a2.model.visitor.TweetCountVisitor;
import com.tigrang.mvc.controller.Controller;
import com.tigrang.mvc.model.Repository;

public class TweetsController extends Controller<Tweet> {

	public TweetsController(Repository<Tweet> repository) {
		super(repository);
	}

	public TextTweet add(User user, String message) throws Exception {
		if (message.isEmpty()) {
			throw new Exception("Enter a message first.");
		}

		TextTweet tweet = new TextTweet(message);
		user.addTweet(tweet);
		getRepository().add(tweet);
		return tweet;
	}

	public int getTotal(Group root) {
		TweetCountVisitor tweetCountVisitor = new TweetCountVisitor();
		root.accept(tweetCountVisitor);
		return tweetCountVisitor.getCount();
	}

	public double getPositivePercentage(Group root) {
		PositiveTweetCountVisitor positiveMessageVisitor = new PositiveTweetCountVisitor();
		root.accept(positiveMessageVisitor);
		return positiveMessageVisitor.getPositivePercentage();
	}
}
