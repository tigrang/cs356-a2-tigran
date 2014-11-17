package com.tigrang.mvc.view;

import java.awt.*;

public abstract class View {

	/**
	 * Holds a list of component that can be looked up by id
	 */
	private Container root;

	public abstract void setupUI();

	/**
	 * @return
	 */
	public Container getRoot() {
		return root;
	}

	/**
	 * @param root
	 */
	public void setRoot(Container root) {
		this.root = root;
	}
}
