package com.tigrang.mvc.controller;

import com.tigrang.mvc.model.Entity;
import com.tigrang.mvc.model.Repository;
import com.tigrang.mvc.view.View;

public abstract class Controller<E extends Entity> {

	/**
	 * The main repository for this controller
	 */
	private Repository<E> repository;

	/**
	 * Current view for controller
	 */
	private View view;

	public Controller() {

	}

	public Controller(Repository<E> repository) {
		this.repository = repository;
	}

	public Repository<E> getRepository() {
		return repository;
	}

	public void setRepository(Repository<E> repository) {
		this.repository = repository;
	}

	/**
	 * Gets current view controller is acting on
	 *
	 * @return
	 */
	public View getView() {
		return view;
	}

	/**
	 * Set the view for controller to act on
	 *
	 * @param view
	 */
	public void setView(View view) {
		this.view = view;
	}


}
