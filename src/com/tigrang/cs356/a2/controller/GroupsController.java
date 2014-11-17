package com.tigrang.cs356.a2.controller;

import com.tigrang.cs356.a2.model.entity.Group;
import com.tigrang.mvc.controller.Controller;
import com.tigrang.mvc.model.Repository;

public class GroupsController extends Controller<Group>  {

	public GroupsController(Repository<Group> repository) {
		super(repository);
	}

	public Group add(Group activeGroup, String name) throws Exception {
		if (name.isEmpty()) {
			throw new Exception("Enter a group name.");
		}

		if (activeGroup == null) {
			throw new Exception("Select a group first.");
		}

		Group group = new Group(name);
		group.setParent(activeGroup);
		getRepository().add(group);
		return group;
	}

	public Group view(long id) {
		return getRepository().findById(id);
	}

	public int getTotal() {
		return getRepository().size();
	}
}
