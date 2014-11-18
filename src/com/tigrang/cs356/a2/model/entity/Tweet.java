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

	public String getMessage() {
		return message;
	}

	@Override
	public void accept(TweetVisitor tweetVisitor) {
		tweetVisitor.atTweet(this);
	}

	public String toString() {
		return message;
	}

	@Override
	public int compareTo(Entity o) {
		return super.compareTo(o) * -1;
	}
}
