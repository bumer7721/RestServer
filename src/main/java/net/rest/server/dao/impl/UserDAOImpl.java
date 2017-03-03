package net.rest.server.dao.impl;

import org.springframework.stereotype.Repository;

import net.rest.server.dao.UserDAO;
import net.rest.server.domains.User;

@Repository
public class UserDAOImpl extends BaseDAOImpl<User> implements UserDAO {
	
	public UserDAOImpl() {
		super();
		setClazz(User.class);
	}
	
}
