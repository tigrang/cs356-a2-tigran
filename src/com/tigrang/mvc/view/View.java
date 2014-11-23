package com.tigrang.mvc.view;

import javax.swing.*;
import java.awt.*;

public abstract class View {

	/**
	 * Holds the root container for this view
	 */
	private Container root;

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

	/**
	 * Toggles visibility of the main container for this view
	 *
	 * @param visible
	 */
	public void show(boolean visible) {
		getRoot().setVisible(visible);
	}

	/**
	 * Shows the given message in a dialog
	 *
	 * @param message
	 */
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(getRoot(), message);
	}

	/**
	 * Shows the given error message and focuses the component it is meant for
	 *
	 * @param message
	 * @param component
	 */
	public void showError(String message, Component component) {
		JOptionPane.showMessageDialog(getRoot(), message, "Error", JOptionPane.ERROR_MESSAGE);
		component.requestFocus();
	}
}
