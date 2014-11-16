package com.tigrang.cs356.a2.controller.visitor;

import com.tigrang.cs356.a2.model.Tweet;

public class PositiveTweetCountVisitor extends TweetCountVisitor {

	private int positiveCount;
	private String[] positiveWords = {
			"good",
			"great",
			"excellent",
			"awesome",
			"happy",
			"excited",
			"enthusiastic"
	};

	public int getPositiveCount() {
		return positiveCount;
	}

	public double getPositivePercentage() {
		if (getCount() == 0) {
			return 0;
		}
		return positiveCount * 100.0f / getCount();
	}

	@Override
	public void atTweet(Tweet tweet) {
		super.atTweet(tweet);
		for (String posWord : positiveWords) {
			if (tweet.getMessage().toLowerCase().contains(posWord)) {
				positiveCount++;
				return;
			}
		}
	}
}
