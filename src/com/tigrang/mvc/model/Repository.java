package com.tigrang.mvc.model;

import java.util.List;

public interface Repository<E extends Entity> {

	public void add(E entity);

	public void remove(E entity);

	public E findById(long id);

	public List<E> findAll();

	public int size();
}
