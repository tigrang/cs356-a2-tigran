package com.tigrang.cs356.a2.controller;

import com.tigrang.cs356.a2.controller.delegate.*;
import com.tigrang.cs356.a2.controller.visitor.PositiveTweetCountVisitor;
import com.tigrang.cs356.a2.controller.visitor.TweetCountVisitor;
import com.tigrang.cs356.a2.model.DataSource;
import com.tigrang.cs356.a2.mvc.R;
import com.tigrang.cs356.a2.view.AdminControlPanelView;
import com.tigrang.mvc.controller.Controller;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class AdminControlPanelController extends Controller {

	private static AdminControlPanelController instance;

	private DefaultTreeModel model;

	private AdminControlPanelController() {
		setupModel();
		setView(new AdminControlPanelView(model));
		addDelegates();
		showView(true);
	}

	public static AdminControlPanelController getInstance() {
		if (instance == null) {
			instance = new AdminControlPanelController();
		}
		return instance;
	}

	private void setupModel() {
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(DataSource.get().getRoot());
		model = new DefaultTreeModel(rootNode, true);
		DataSource.get().setActiveGroupNode(rootNode);
	}

	private void addDelegates() {
		AdminControlPanelView view = (AdminControlPanelView) getView();

		// Connect add new user/group buttons
		view.addDelegate(R.id.add_user_btn, new AddNewUserDelegate(view, model));
		view.addDelegate(R.id.add_group_btn, new AddNewGroupDelegate(view, model));

		// Connect group select change
		view.addDelegate(R.id.tree, new UpdateActiveGroupNodeDelegate());

		// Connect open user dialog button
		view.addDelegate(R.id.open_user_view_btn, new OpenUserDialogDelegate(view));

		// Connect the show total dialog buttons
		view.addDelegate(R.id.show_user_total_btn,
				new ShowTotalDialogDelegate("users", () -> DataSource.get().getUsers().size()));
		view.addDelegate(R.id.show_group_total_btn,
				new ShowTotalDialogDelegate("groups", () -> DataSource.get().getGroups().size()));
		view.addDelegate(R.id.show_messages_total_btn,
				new ShowTotalDialogDelegate("messages", () -> {
					TweetCountVisitor tweetCountVisitor = new TweetCountVisitor();
					DataSource.get().getRoot().accept(tweetCountVisitor);
					return tweetCountVisitor.getCount();
				}));
		view.addDelegate(R.id.show_pos_percentage_btn,
				new ShowTotalDialogDelegate("positive messages", () -> {
					PositiveTweetCountVisitor positiveMessageVisitor = new PositiveTweetCountVisitor();
					DataSource.get().getRoot().accept(positiveMessageVisitor);
					return positiveMessageVisitor.getCount();
				}));
	}
}