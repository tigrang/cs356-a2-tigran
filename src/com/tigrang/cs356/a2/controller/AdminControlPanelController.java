package com.tigrang.cs356.a2.controller;

import com.tigrang.cs356.a2.controller.delegate.*;
import com.tigrang.cs356.a2.model.DataSource;
import com.tigrang.cs356.a2.model.visitor.TweetCountVisitor;
import com.tigrang.cs356.a2.mvc.R;
import com.tigrang.cs356.a2.view.AdminControlPanelView;
import com.tigrang.mvc.controller.Controller;

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
		view.addDelegate(R.id.add_user, new AddNewUserDelegate(view));
		view.addDelegate(R.id.add_group, new AddNewGroupDelegate(view));

		// Connect group select change
		view.addDelegate(R.id.tree, new UpdateActiveGroupNodeDelegate());

		// Connect open user dialog button
		view.addDelegate(R.id.open_user_view, new OpenUserDialogDelegate(view));

		// Connect the show total dialog buttons
		view.addDelegate(R.id.show_user_total,
				new ShowTotalDialogDelegate(view, "users", () -> DataSource.get().getUsers().size()));
		view.addDelegate(R.id.show_group_total,
				new ShowTotalDialogDelegate(view, "groups", () -> DataSource.get().getGroups().size()));
		view.addDelegate(R.id.show_messages_total,
				new ShowTotalDialogDelegate(view, "messages", () -> {
					TweetCountVisitor tweetCountVisitor = new TweetCountVisitor();
					DataSource.get().getRoot().accept(tweetCountVisitor);
					return tweetCountVisitor.getCount();
				}));
		view.addDelegate(R.id.show_pos_percentage, new ShowPositivePercentageDialogDelegate(view));
	}
}