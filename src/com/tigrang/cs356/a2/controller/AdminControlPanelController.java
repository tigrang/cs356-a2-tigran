package com.tigrang.cs356.a2.controller;

import com.tigrang.cs356.a2.controller.delegate.*;
import com.tigrang.cs356.a2.controller.visitor.PositiveTweetCountVisitor;
import com.tigrang.cs356.a2.controller.visitor.TweetCountVisitor;
import com.tigrang.cs356.a2.model.DataSource;
import com.tigrang.cs356.a2.mvc.R;
import com.tigrang.cs356.a2.view.AdminControlPanelView;
import com.tigrang.mvc.controller.Controller;
import com.tigrang.mvc.delegate.ActionDelegate;

import java.awt.event.ActionEvent;

public class AdminControlPanelController extends Controller {

	private static AdminControlPanelController instance;

	private AdminControlPanelView view;

	private AdminControlPanelController() {
		setupView();
		addDelegates();
		showView(true);
	}

	public static AdminControlPanelController getInstance() {
		if (instance == null) {
			instance = new AdminControlPanelController();
		}
		return instance;
	}

	private void setupView() {
		view = new AdminControlPanelView();
		setView(view);
	}

	private void addDelegates() {
		// Connect add new user/group buttons
		view.addDelegate(R.id.add_user_btn, new AddNewUserDelegate(view));
		view.addDelegate(R.id.add_group_btn, new AddNewGroupDelegate(view));

		// Connect group select change
		view.addDelegate(R.id.tree, new UpdateActiveGroupNodeDelegate());

		// Connect open user dialog button
		view.addDelegate(R.id.open_user_view_btn, new OpenUserDialogDelegate(view));

		// Connect the show total dialog buttons
		view.addDelegate(R.id.show_user_total_btn,
				new ShowTotalDialogDelegate(view, "users", () -> DataSource.get().getUsers().size()));
		view.addDelegate(R.id.show_group_total_btn,
				new ShowTotalDialogDelegate(view, "groups", () -> DataSource.get().getGroups().size()));
		view.addDelegate(R.id.show_messages_total_btn,
				new ShowTotalDialogDelegate(view, "messages", () -> {
					TweetCountVisitor tweetCountVisitor = new TweetCountVisitor();
					DataSource.get().getRoot().accept(tweetCountVisitor);
					return tweetCountVisitor.getCount();
				}));
		view.addDelegate(R.id.show_pos_percentage_btn, new ActionDelegate() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PositiveTweetCountVisitor positiveMessageVisitor = new PositiveTweetCountVisitor();
				DataSource.get().getRoot().accept(positiveMessageVisitor);
				double percentage = positiveMessageVisitor.getPositivePercentage();
				view.showMessage(String.format("There are %.1f%% positive messages.", percentage));
			}
		});
	}
}