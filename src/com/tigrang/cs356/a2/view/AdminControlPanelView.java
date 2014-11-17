package com.tigrang.cs356.a2.view;

import com.tigrang.cs356.a2.controller.GroupsController;
import com.tigrang.cs356.a2.controller.TweetsController;
import com.tigrang.cs356.a2.controller.UsersController;
import com.tigrang.cs356.a2.model.DataSource;
import com.tigrang.cs356.a2.model.Group;
import com.tigrang.cs356.a2.model.User;
import com.tigrang.cs356.a2.model.UserTreeModel;

import javax.swing.*;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;

public class AdminControlPanelView extends com.tigrang.mvc.view.View {

	private static final AdminControlPanelView INSTANCE = new AdminControlPanelView();

	private UserTreeModel model;

	private UsersController usersController;

	private GroupsController groupsController;

	private TweetsController tweetsController;

	private JFrame frame;

	private JPanel container;

	private JTextField txtUsername;

	private JButton btnAddUser;

	private JTextField txtGroupName;

	private JButton btnAddGroup;

	private JButton btnShowUserTotal;

	private JButton btnShowGroupTotal;

	private JButton btnShowMessagesTotal;

	private JButton btnShowPositivePercentage;

	private JButton btnOpenUserView;

	private JTree treeUsers;

	private AdminControlPanelView() {
		usersController = new UsersController();
		groupsController = new GroupsController();
		tweetsController = new TweetsController();

		createFrame();
		setupUI();
		setupActions();
	}

	public static AdminControlPanelView getInstance() {
		return INSTANCE;
	}

	public void show(boolean visible) {
		getRoot().setVisible(visible);
	}

	@Override
	public void setupUI() {
		model = new UserTreeModel(DataSource.get().getRoot());
		treeUsers.setModel(model);
		treeUsers.setSelectionRow(0);
		treeUsers.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
	}

	private void createFrame() {
		frame = new JFrame("Mini Twitter: Admin Control Panel");
		frame.setContentPane(container);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		setRoot(frame);
	}

	private void setupActions() {
		btnAddUser.addActionListener((ae) -> {
			try {
				User user = usersController.add(getUsername());
				selectAndScrollTo(user);
				clearUsername();
			} catch (Exception e) {
				showError(e.getMessage(), txtUsername);
			}
		});

		btnAddGroup.addActionListener((ae) -> {
			try {
				Group group = groupsController.add(getGroupName());
				selectAndScrollTo(group);
				clearGroupName();
			} catch (Exception e) {
				showError(e.getMessage(), txtUsername);
			}
		});

		treeUsers.addTreeSelectionListener((e) -> {
			TreePath path = e.getNewLeadSelectionPath();
			if (path == null) {
				groupsController.setActive(null);
				return;
			}

			Object node = path.getLastPathComponent();
			if (node instanceof User) {
				node = ((User) node).getGroup();
			}
			groupsController.setActive((Group) node);
		});

		btnOpenUserView.addActionListener((e) -> {
			TreePath path = treeUsers.getSelectionPath();
			if (path == null) {
				showError("Select a valid user", treeUsers);
				return;
			}

			Object node = path.getLastPathComponent();
			if (!(node instanceof User)) {
				showError("Select a valid user", treeUsers);
				return;
			}

			UserOverviewView overviewView = new UserOverviewView(((User) node));
			overviewView.show(true);
		});

		btnShowUserTotal.addActionListener((ae) -> showMessage(String.format("There are %d users.",
				usersController.getTotal())));

		btnShowGroupTotal.addActionListener((ae) -> showMessage(String.format("There are %d groups.",
				groupsController.getTotal())));

		btnShowMessagesTotal.addActionListener((ae) -> showMessage(String.format("There are %d messages.",
				tweetsController.getTotal())));

		btnShowPositivePercentage.addActionListener((ae) -> showMessage(
				String.format("There are %.1f%% positive messages.", tweetsController.getPositivePercentage())));
	}

	public String getUsername() {
		return txtUsername.getText();
	}

	public void clearUsername() {
		txtUsername.setText("");
	}

	public String getGroupName() {
		return txtGroupName.getText();
	}

	public void clearGroupName() {
		txtGroupName.setText("");
	}

	public void selectAndScrollTo(Object object) {
		TreePath path = new TreePath(model.getPathToRoot(object));
		treeUsers.setSelectionPath(path);
		treeUsers.scrollPathToVisible(path);
	}

	public void showMessage(String message) {
		JOptionPane.showMessageDialog(getRoot(), message);
	}

	public void showError(String message, Component component) {
		JOptionPane.showMessageDialog(getRoot(), message, "Error", JOptionPane.ERROR_MESSAGE);
		component.requestFocus();
	}
}
