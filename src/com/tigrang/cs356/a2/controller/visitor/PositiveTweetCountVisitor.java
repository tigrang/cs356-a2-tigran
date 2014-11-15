package com.tigrang.cs356.a2.controller.visitor;

import com.tigrang.cs356.a2.model.Tweet;

public class PositiveTweetCountVisitor extends TweetCountVisitor {

	private String[] positiveWords = {
			"good",
			"great",
			"excellent",
			"awesome",
			"happy",
			"excited",
			"enthusiastic"
	};

	@Override
	public void atTweet(Tweet tweet) {
		for (String posWord : positiveWords) {
			if (tweet.getMessage().contains(posWord)) {
				count++;
				return;
			}
		}
	}
}
