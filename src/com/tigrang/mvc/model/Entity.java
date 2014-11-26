package com.tigrang.mvc.model;

import java.time.Instant;
import java.util.Observable;

public abstract class Entity extends Observable implements Comparable<Entity> {

	protected Long id;
	protected long created;
	protected long updated;

	public Entity() {
		created = updated = Instant.now().getEpochSecond();
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

	public long getUpdated() {
		return updated;
	}

	public void setUpdated(long updated) {
		this.updated = updated;
		setChanged(false);
		notifyObservers();
	}

	@Override
	public int compareTo(Entity o) {
		return Long.compare(created, o.created);
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
		updated = Instant.now().getEpochSecond();
	}
}
