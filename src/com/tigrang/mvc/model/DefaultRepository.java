package com.tigrang.mvc.model;

import com.tigrang.mvc.model.datasource.StorageEngine;

import java.util.List;

public class DefaultRepository<E extends Entity> implements Repository<E> {

	private StorageEngine<E> storageEngine;

	public DefaultRepository(StorageEngine<E> storageEngine) {
		this.storageEngine = storageEngine;
	}

	public StorageEngine<E> getStorageEngine() {
		return storageEngine;
	}

	@Override
	public void add(E entity) {
		if (entity.getId() == null) {
			entity.setId(storageEngine.generateId());
		}
		storageEngine.create(entity);
	}

	@Override
	public void remove(E entity) {
		storageEngine.delete(entity);
	}

	@Override
	public int size() {
		return storageEngine.count();
	}

	@Override
	public E findById(long id) {
		return storageEngine.read(id);
	}

	@Override
	public List<E> findAll() {
		return storageEngine.readAll();
	}
}
