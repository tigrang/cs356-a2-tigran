package com.tigrang.cs356.a2.controller.delegate;

import com.tigrang.mvc.delegate.ActionDelegate;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ShowTotalDialogDelegate extends ActionDelegate {

	private String type;
	private QueryTotal queryTotal;

	public ShowTotalDialogDelegate(String type, QueryTotal queryTotal) {
		this.type = type;
		this.queryTotal = queryTotal;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, String.format("There are %d total %s.", queryTotal.getTotal(), type));
	}

	public interface QueryTotal {
		public int getTotal();
	}
}
