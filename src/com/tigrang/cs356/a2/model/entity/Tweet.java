package com.tigrang.cs356.a2.model.entity;

import com.tigrang.cs356.a2.model.visitor.TweetAcceptor;
import com.tigrang.cs356.a2.model.visitor.TweetVisitor;
import com.tigrang.mvc.model.Entity;

public abstract class Tweet extends Entity implements TweetAcceptor {

	private String message;

	public Tweet(String message) {
		super();
		this.message = message;
	}

	/**
	 * Gets the message of this tweet
	 *
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	@Override
	public void accept(TweetVisitor tweetVisitor) {
		tweetVisitor.atTweet(this);
	}

	/**
	 * Sort tweets in desc order to show newer tweets on top
	 *
	 * @param o
	 * @return
	 */
	@Override
	public int compareTo(Entity o) {
		return -super.compareTo(o);
	}

	public String toString() {
		return message;
	}
}
