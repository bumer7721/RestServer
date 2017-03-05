package net.rest.server.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.rest.server.dao.UserDAO;
import net.rest.server.domains.User;
import net.rest.server.dto.UserDTO;
import net.rest.server.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;

	@Override
	@Transactional
	public UserDTO create(UserDTO dto) {
		return new UserDTO(userDAO.create(dto.convertToEntity()));
	}

	@Override
	@Transactional
	public UserDTO update(UserDTO dto) {
		return new UserDTO(userDAO.update(dto.convertToEntity()));
	}

	@Override
	@Transactional
	public void delete(UserDTO dto) {
		userDAO.delete(dto.convertToEntity());
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		userDAO.deleteById(id);
	}

	@Override
	public UserDTO findOne(Long id) {
		return new UserDTO(userDAO.findOne(id));
	}

	@Override
	public List<UserDTO> findAll() {
		List<UserDTO> res = null;
		List<User> users = userDAO.findAll();
		if (CollectionUtils.isNotEmpty(users)) {
			res = new ArrayList<>(users.size());
			for (User user : users) {
				res.add(new UserDTO(user));
			}
		}
		return res;
	}

	@Override
	public Long count() {
		return userDAO.count();
	}
	
}
