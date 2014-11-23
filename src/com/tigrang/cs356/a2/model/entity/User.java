package com.tigrang.cs356.a2.model.entity;

import com.tigrang.cs356.a2.model.visitor.TweetAcceptor;
import com.tigrang.cs356.a2.model.visitor.TweetVisitor;
import com.tigrang.mvc.model.Entity;
import com.tigrang.mvc.model.RepositoryManager;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class User extends Entity implements TweetAcceptor {

	private String username;
	private Group group;
	private List<Tweet> tweets;
	private Set<Long> following;

	public User(String username) {
		super();
		this.username = username;
		this.tweets = new ArrayList<>();
		this.following = new LinkedHashSet<>();
	}

	/**
	 * Submit a tweet by this user
	 *
	 * @param tweet
	 */
	public void addTweet(Tweet tweet) {
		tweets.add(tweet);
		setChanged();
		notifyObservers();

		for (long id : following) {
			User user = RepositoryManager.getInstance().get(User.class).findById(id);
			user.setChanged();
			user.notifyObservers();
		}
	}

	/**
	 * Gets a list of tweets made by this user
	 *
	 * @return
	 */
	public List<Tweet> getTweets() {
		return tweets;
	}

	/**
	 * Gets the group this user belongs to
	 *
	 * @return
	 */
	public Group getGroup() {
		return group;
	}

	/**
	 * Set the group this user belongs to
	 *
	 * @param group
	 */
	public void setGroup(Group group) {
		if (this.group == group) {
			return;
		}

		this.group = group;
		group.addUser(this);
		notifyObservers();
	}

	/**
	 * Get a list of follower ids
	 *
	 * @return
	 */
	public Set<Long> getFollowingIds() {
		return following;
	}

	/**
	 * Add a follower by user object
	 *
	 * @param user
	 */
	public void follow(User user) {
		follow(user.getId());
	}

	/**
	 * Add a follower by user id
	 *
	 * @param id
	 */
	public void follow(long id) {
		following.add(id);
		setChanged();
		notifyObservers();
	}

	/**
	 * Gets the username for this user
	 *
	 * @return
	 */
	public String getUsername() {
		return username;
	}

	public String toString() {
		return String.format("(%d) %s", id, username);
	}

	@Override
	public void accept(TweetVisitor visitor) {
		tweets.forEach((tweet) -> tweet.accept(visitor));
	}
}