package com.tigrang.mvc.model;

public interface Repository<E extends Entity> {

	public void add(E entity);

	public void remove(E entity);

	public E findById(long id);

	public int size();
}
