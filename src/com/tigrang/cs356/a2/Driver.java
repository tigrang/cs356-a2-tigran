package com.tigrang.cs356.a2;

import com.tigrang.cs356.a2.view.AdminControlPanelView;

import javax.swing.*;

public class Driver {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			setLAF();
			AdminControlPanelView.getInstance().show(true);
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