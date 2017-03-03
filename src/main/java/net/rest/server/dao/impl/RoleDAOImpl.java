package net.rest.server.dao.impl;

import org.springframework.stereotype.Repository;

import net.rest.server.dao.RoleDAO;
import net.rest.server.domains.Role;

@Repository
public class RoleDAOImpl extends BaseDAOImpl<Role> implements RoleDAO {
	
	public RoleDAOImpl() {
		super();
		setClazz(Role.class);
	}
}
