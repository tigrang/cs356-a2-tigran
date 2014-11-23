package com.tigrang.cs356.a2.view;

import com.tigrang.cs356.a2.controller.GroupsController;
import com.tigrang.cs356.a2.controller.TweetsController;
import com.tigrang.cs356.a2.controller.UsersController;
import com.tigrang.cs356.a2.model.UserTreeModel;
import com.tigrang.cs356.a2.model.entity.Group;
import com.tigrang.cs356.a2.model.entity.User;

import javax.swing.*;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

public class AdminControlPanel extends com.tigrang.mvc.view.View {

	private static final AdminControlPanel INSTANCE = new AdminControlPanel();

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

	private AdminControlPanel() {

	}

	/**
	 * Singleton
	 *
	 * @return AdminControlPanel instance
	 */
	public static AdminControlPanel getInstance() {
		return INSTANCE;
	}

	public void init() {
		createFrame();
		setupUI();
		setupActions();
	}

	/**
	 * Sets up UI models and initial view settings
	 */
	public void setupUI() {
		model = new UserTreeModel(groupsController.view(Group.ROOT_ID));
		treeUsers.setModel(model);
		treeUsers.setSelectionRow(0);
		treeUsers.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
	}

	/**
	 * Create the main frame
	 */
	private void createFrame() {
		frame = new JFrame("Mini Twitter: Admin Control Panel");
		frame.setContentPane(container);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setSize(600, 500);
		setRoot(frame);
	}

	/**
	 * Connects user UI events to controller actions
	 */
	private void setupActions() {
		btnAddUser.addActionListener((ae) -> {
			try {
				User user = usersController.add(getSelectedGroup(), getUsername());
				selectAndScrollTo(user);
				clearUsername();
			} catch (Exception e) {
				showError(e.getMessage(), txtUsername);
			}
		});

		btnAddGroup.addActionListener((ae) -> {
			try {
				Group group = groupsController.add(model.getRoot(), getGroupName());
				selectAndScrollTo(group);
				clearGroupName();
			} catch (Exception e) {
				showError(e.getMessage(), txtUsername);
			}
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

			UserOverviewDialog userOverviewDialog = new UserOverviewDialog((User) node);
			userOverviewDialog.setUsersController(usersController);
			userOverviewDialog.setTweetsController(tweetsController);
			userOverviewDialog.init();
			userOverviewDialog.show(true);
		});

		btnShowUserTotal.addActionListener((ae) -> showMessage(String.format("There are %d users.",
				usersController.getTotal())));

		btnShowGroupTotal.addActionListener((ae) -> showMessage(String.format("There are %d groups.",
				groupsController.getTotal())));

		btnShowMessagesTotal.addActionListener((ae) -> showMessage(String.format("There are %d messages.",
				tweetsController.getTotal(model.getRoot()))));

		btnShowPositivePercentage.addActionListener((ae) -> showMessage(
				String.format("There are %.1f%% positive messages.",
						tweetsController.getPositivePercentage(model.getRoot()))));
	}

	public UsersController getUsersController() {
		return usersController;
	}

	public void setUsersController(UsersController usersController) {
		this.usersController = usersController;
	}

	public GroupsController getGroupsController() {
		return groupsController;
	}

	public void setGroupsController(GroupsController groupsController) {
		this.groupsController = groupsController;
	}

	public TweetsController getTweetsController() {
		return tweetsController;
	}

	public void setTweetsController(TweetsController tweetsController) {
		this.tweetsController = tweetsController;
	}

	/**
	 * Gets the text the user entered in username field
	 *
	 * @return text of username field
	 */
	public String getUsername() {
		return txtUsername.getText();
	}

	/**
	 * Clears the text inside of username text field
	 */
	public void clearUsername() {
		txtUsername.setText("");
	}

	/**
	 * Gets the text the user entered in group field
	 *
	 * @return text of the group field
	 */
	public String getGroupName() {
		return txtGroupName.getText();
	}

	/**
	 * Clears the text inside of group text field
	 */
	public void clearGroupName() {
		txtGroupName.setText("");
	}

	/**
	 * Returns the selected group in the tree
	 *
	 * @return selected Group
	 */
	public Group getSelectedGroup() {
		TreePath path = treeUsers.getSelectionPath();
		if (path == null) {
			return null;
		}

		Object node = path.getLastPathComponent();
		if (node instanceof User) {
			return ((User) node).getGroup();
		}
		return (Group) node;
	}

	/**
	 * Selects the given object in the tree and scrolls to it to make it visible
	 * in the viewport
	 *
	 * @param object
	 */
	public void selectAndScrollTo(Object object) {
		TreePath path = new TreePath(model.getPathToRoot(object));
		treeUsers.setSelectionPath(path);
		treeUsers.scrollPathToVisible(path);
	}
}
