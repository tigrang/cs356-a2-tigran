package com.tigrang.cs356.a2.controller.delegate;

import com.tigrang.cs356.a2.model.DataSource;
import com.tigrang.cs356.a2.model.visitor.PositiveTweetCountVisitor;
import com.tigrang.cs356.a2.view.AdminControlPanelView;
import com.tigrang.mvc.delegate.ActionDelegate;

import java.awt.event.ActionEvent;

public class ShowPositivePercentageDialogDelegate extends ActionDelegate {

	AdminControlPanelView view;

	public ShowPositivePercentageDialogDelegate(AdminControlPanelView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		PositiveTweetCountVisitor positiveMessageVisitor = new PositiveTweetCountVisitor();
		DataSource.get().getRoot().accept(positiveMessageVisitor);
		double percentage = positiveMessageVisitor.getPositivePercentage();
		view.showMessage(String.format("There are %.1f%% positive messages.", percentage));
	}
}
