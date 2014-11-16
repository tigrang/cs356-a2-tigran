package com.tigrang.cs356.a2.model.visitor;

public interface TweetAcceptor {

	public void accept(TweetVisitor visitor);
}
