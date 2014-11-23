package com.tigrang.cs356.a2;

import com.tigrang.cs356.a2.controller.GroupsController;
import com.tigrang.cs356.a2.controller.TweetsController;
import com.tigrang.cs356.a2.controller.UsersController;
import com.tigrang.cs356.a2.model.entity.Group;
import com.tigrang.cs356.a2.model.entity.Tweet;
import com.tigrang.cs356.a2.model.entity.User;
import com.tigrang.cs356.a2.view.AdminControlPanel;
import com.tigrang.mvc.model.DefaultRepository;
import com.tigrang.mvc.model.RepositoryManager;
import com.tigrang.mvc.model.datasource.MemoryEngine;

import javax.swing.*;

public class Driver {

	public static void main(String[] args) {
		// Initialize models
		DefaultRepository<User> usersRepository = new DefaultRepository<>(new MemoryEngine<>());
		DefaultRepository<Group> groupsRepository = new DefaultRepository<>(new MemoryEngine<>());
		DefaultRepository<Tweet> tweetsRepository = new DefaultRepository<>(new MemoryEngine<>());
		groupsRepository.add(new Group("Root"));

		RepositoryManager.getInstance().register(User.class, usersRepository);
		RepositoryManager.getInstance().register(Group.class, groupsRepository);
		RepositoryManager.getInstance().register(Tweet.class, tweetsRepository);

		// Initialize controllers
		UsersController usersController = new UsersController(usersRepository);
		GroupsController groupsController = new GroupsController(groupsRepository);
		TweetsController tweetsController = new TweetsController(tweetsRepository);

		// Initialize the view
		SwingUtilities.invokeLater(() -> {
			setLAF();
			AdminControlPanel adminControlPanel = AdminControlPanel.getInstance();
			adminControlPanel.setUsersController(usersController);
			adminControlPanel.setGroupsController(groupsController);
			adminControlPanel.setTweetsController(tweetsController);

			adminControlPanel.init();
			adminControlPanel.show(true);
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