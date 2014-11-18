package com.tigrang.cs356.a2.controller;

import com.tigrang.cs356.a2.model.entity.Group;
import com.tigrang.mvc.controller.Controller;
import com.tigrang.mvc.model.Repository;

/**
 * Groups controller
 *
 * Handles adding, viewing, and stats related to groups
 */
public class GroupsController extends Controller<Group> {

	public GroupsController(Repository<Group> repository) {
		super(repository);
	}

	/**
	 * Add a new sub-group to the parent group
	 *
	 * @param parent Parent group for the sub-group to be added to
	 * @param name Name of the new sub-group
	 * @return Newly created Group
	 * @throws Exception
	 */
	public Group add(Group parent, String name) throws Exception {
		if (name.isEmpty()) {
			throw new Exception("Enter a group name.");
		}

		if (parent == null) {
			throw new Exception("Select a group first.");
		}

		Group group = new Group(name);
		group.setParent(parent);
		getRepository().add(group);
		return group;
	}

	/**
	 * Find the Group by id
	 *
	 * @param id Id of the group to view
	 * @return Group
	 */
	public Group view(long id) {
		return getRepository().findById(id);
	}

	/**
	 * Returns the total number of groups
	 *
	 * @return int
	 */
	public int getTotal() {
		return getRepository().size();
	}
}
