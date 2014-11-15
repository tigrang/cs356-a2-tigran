package com.tigrang.cs356.a2.controller.delegate;

import com.tigrang.cs356.a2.model.User;
import com.tigrang.cs356.a2.mvc.R;
import com.tigrang.cs356.a2.view.UserOverviewView;
import com.tigrang.mvc.delegate.ActionDelegate;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class FollowUserDelegate extends ActionDelegate {

	private UserOverviewView view;
	private User user;

	public FollowUserDelegate(UserOverviewView view, User user) {
		this.view = view;
		this.user = user;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JTextField textField = view.findComponentByIdAs(R.id.txt_user_id, JTextField.class);

		if (textField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(view.getRoot(), "Enter an id first.");
		}
		int id = Integer.parseInt(textField.getText());
		user.follow(id);
		textField.setText("");
	}
}
