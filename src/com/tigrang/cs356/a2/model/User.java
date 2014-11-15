package com.tigrang.cs356.a2.model;

import com.tigrang.cs356.a2.controller.visitor.TweetAcceptor;
import com.tigrang.cs356.a2.controller.visitor.TweetVisitor;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class User extends Observable implements TweetAcceptor {

	private static AtomicInteger atomicInteger = new AtomicInteger();
	private int id;
	private String username;
	private Group group;
	private List<Tweet> tweets;
	private Set<Integer> following;

	protected User(int id, String username) {
		this.id = id;
		this.username = username;
		this.tweets = new ArrayList<>();
		this.following = new LinkedHashSet<>();
	}

	public static User newUser(String username) {
		return new User(atomicInteger.incrementAndGet(), username);
	}

	public void addTweet(Tweet tweet) {
		tweets.add(tweet);
		setChanged();
		notifyObservers();

		for (int id : following) {
			User user = DataSource.get().getUsers().get(id);
			user.setChanged();
			user.notifyObservers();
		}
	}

	public List<Tweet> getTweets() {
		return tweets;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		if (this.group == group) {
			return;
		}

		this.group = group;
		group.addUser(this);
		notifyObservers();
	}

	public Set<Integer> getFollowingIds() {
		return following;
	}

	public void follow(User user) {
		follow(user.getId());
	}

	public void follow(int id) {
		following.add(id);
		setChanged();
		notifyObservers();
	}

	public String getUsername() {
		return username;
	}

	public int getId() {
		return id;
	}

	public String toString() {
		return String.format("(%d) %s", id, username);
	}

	@Override
	public void accept(TweetVisitor visitor) {
		tweets.forEach((tweet) -> tweet.accept(visitor));
	}
}