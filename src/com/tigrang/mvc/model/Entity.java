package com.tigrang.mvc.model;

import java.time.Instant;
import java.util.Observable;

public abstract class Entity extends Observable implements Comparable<Entity> {

	protected Long id;

	protected long created;

	public Entity() {
		this.created = Instant.now().getEpochSecond();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
		setChanged();
		notifyObservers();
	}

	public long getCreated() {
		return created;
	}

	public void setCreated(long created) {
		this.created = created;
		setChanged();
		notifyObservers();
	}

	@Override
	public int compareTo(Entity o) {
		return Long.compare(created, o.created);
	}
}
