package com.tigrang.mvc.model;

import java.time.Instant;
import java.util.Observable;

public abstract class Entity extends Observable implements Comparable<Entity> {

	protected Long id;
	protected long creationTime;
	protected long lastUpdateTime;

	public Entity() {
		creationTime = lastUpdateTime = Instant.now().getEpochSecond();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
		setChanged();
		notifyObservers();
	}

	public long getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(long creationTime) {
		this.creationTime = creationTime;
		setChanged();
		notifyObservers();
	}

	public long getLastUpdatedTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
		setChanged(false);
		notifyObservers();
	}

	@Override
	public int compareTo(Entity o) {
		return Long.compare(creationTime, o.creationTime);
	}

	/**
	 * Set changed with option of not updating last update time (manual update)
	 *
	 * @param update
	 */
	protected synchronized void setChanged(boolean update) {
		if (update) {
			setChanged();
		} else {
			super.setChanged();
		}
	}

	@Override
	protected synchronized void setChanged() {
		super.setChanged();
		lastUpdateTime = Instant.now().getEpochSecond();
	}
}
