package com.tigrang.cs356.a2.model;

import com.tigrang.cs356.a2.controller.visitor.TweetAcceptor;
import com.tigrang.cs356.a2.controller.visitor.TweetVisitor;

import java.time.Instant;

public abstract class Tweet implements TweetAcceptor, Comparable<Tweet> {

	private long created;
	private String message;

	public Tweet(String message) {
		this.message = message;
		created = Instant.now().getEpochSecond();
	}

	public String getMessage() {
		return message;
	}

	public long getCreated() {
		return created;
	}

	@Override
	public int compareTo(Tweet o) {
		return Long.compare(o.created, created);
	}

	@Override
	public void accept(TweetVisitor tweetVisitor) {
		tweetVisitor.atTweet(this);
	}

	public String toString() {
		return message;
	}
}
