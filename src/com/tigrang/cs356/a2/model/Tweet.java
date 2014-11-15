package com.tigrang.cs356.a2.model;

import java.time.Instant;

public class Tweet implements Comparable<Tweet> {

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

	public String toString() {
		return message;
	}
}
