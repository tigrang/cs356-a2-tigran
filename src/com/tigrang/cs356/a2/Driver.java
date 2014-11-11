package com.tigrang.cs356.a2;

import com.tigrang.cs356.a2.controller.AdminControlPanelController;

import javax.swing.*;

public class Driver {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			setLAF();
			new AdminControlPanelController();
		});
	}

	private static void setLAF() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Could not set LAF to system default.");
		}
	}
}