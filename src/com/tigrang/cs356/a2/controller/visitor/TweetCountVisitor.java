package com.tigrang.cs356.a2.controller.visitor;

import com.tigrang.cs356.a2.model.Tweet;

public class TweetCountVisitor implements TweetVisitor {

	protected int count;

	public int getCount() {
		return count;
	}

	@Override
	public void atTweet(Tweet tweet) {
		count++;
	}
}
