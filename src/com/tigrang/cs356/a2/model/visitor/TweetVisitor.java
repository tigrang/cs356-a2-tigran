package com.tigrang.cs356.a2.model.visitor;

import com.tigrang.cs356.a2.model.Tweet;

public interface TweetVisitor {

	public void atTweet(Tweet tweet);
}
