package com.tigrang.mvc.model.datasource;

import com.tigrang.mvc.model.Entity;

import java.util.List;

public interface StorageEngine<E extends Entity> {

	public void create(E entity);

	public E read(long id);

	public List<E> readAll();

	public void update(E entity);

	public void delete(E entity);

	public int count();

	public long generateId();
}
