package net.rest.server.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;

import net.rest.server.domains.Role;
import net.rest.server.domains.User;

public class UserDTO extends IdDomainDTO {

	private String userName;
	private String passWord;
	private boolean isActive;
	private List<RoleDTO> roles;
	
	public UserDTO() {
	}	

	public UserDTO(User entity) {
		super(entity);
		if (entity!= null) {
			userName = entity.getUserName();
			passWord = entity.getPassWord();
			isActive = entity.getIsActive();
			
			List<Role> entityRoles = entity.getRoles();
			if (CollectionUtils.isNotEmpty(entityRoles)) {
				roles = new ArrayList<>(entityRoles.size());
				for (Role role : entityRoles) {
					roles.add(new RoleDTO(role));
				}
			}
		}
	}	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public List<RoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDTO> roles) {
		this.roles = roles;
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), userName, passWord, isActive, roles);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof UserDTO)) {
			return false;
		}
		UserDTO base = (UserDTO) obj;
		return Objects.equals(userName, base.userName) 
				&& Objects.equals(passWord, base.passWord) 
				&& Objects.equals(isActive, base.isActive) 
				&& super.equals(obj);
	}
	
	public User convertToEntity() {
		User user = new User();
		user.setId(getId());
		user.setUserName(userName);
		user.setPassWord(passWord);
		user.setIsActive(isActive);
		
		if (CollectionUtils.isNotEmpty(roles)) {
			List<Role> entityRoles = new ArrayList<>(roles.size());
			for (RoleDTO roleDTO : roles) {
				entityRoles.add(roleDTO.convertToEntity());
			}
			user.setRoles(entityRoles);
		}
		return user;
	}
}
