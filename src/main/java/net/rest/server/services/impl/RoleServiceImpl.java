package net.rest.server.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.rest.server.dao.RoleDAO;
import net.rest.server.domains.Role;
import net.rest.server.dto.RoleDTO;
import net.rest.server.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDAO roleDAO;

	@Override
	@Transactional
	public RoleDTO create(RoleDTO dto) {
		return new RoleDTO(roleDAO.create(dto.convertToEntity()));
	}

	@Override
	@Transactional
	public RoleDTO update(RoleDTO dto) {
		return new RoleDTO(roleDAO.update(dto.convertToEntity()));
	}

	@Override
	@Transactional
	public void delete(RoleDTO dto) {
		roleDAO.delete(dto.convertToEntity());
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		roleDAO.deleteById(id);
	}

	@Override
	public RoleDTO findOne(Long id) {
		return new RoleDTO(roleDAO.findOne(id));
	}

	@Override
	public List<RoleDTO> findAll() {
		List<RoleDTO> res = null;
		List<Role> roles = roleDAO.findAll();
		if (CollectionUtils.isNotEmpty(roles)) {
			res = new ArrayList<>(roles.size());
			for (Role role : roles) {
				res.add(new RoleDTO(role));
			}
		}
		return res;
	}

	@Override
	public Long count() {
		return roleDAO.count();
	}
	
}
