package com.tigrang.mvc.model.datasource;

import com.tigrang.mvc.model.Entity;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class MemoryEngine<E extends Entity> implements StorageEngine<E> {

	private Map<Long, E> storage;

	private AtomicLong idGenerator;

	public MemoryEngine() {
		this.idGenerator = new AtomicLong();
		this.storage = new HashMap<>();
	}

	@Override
	public void create(E entity) {
		storage.put(entity.getId(), entity);
	}

	@Override
	public E read(long id) {
		return storage.get(id);
	}

	@Override
	public void update(E entity) {
		storage.put(entity.getId(), entity);
	}

	@Override
	public void delete(E entity) {
		storage.remove(entity.getId());
	}

	@Override
	public int count() {
		return storage.size();
	}

	@Override
	public long generateId() {
		return idGenerator.incrementAndGet();
	}
}
