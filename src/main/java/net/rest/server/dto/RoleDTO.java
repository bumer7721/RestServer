package net.rest.server.dto;

import java.util.Objects;

import net.rest.server.domains.Role;

public class RoleDTO extends IdDomainDTO {

	private String name;
	
	public RoleDTO() {
	}
	
	public RoleDTO(Role entity) {
		super(entity);
		if (entity != null) {
			name = entity.getName();
		}
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), name);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof RoleDTO)) {
			return false;
		}
		RoleDTO base = (RoleDTO) obj;
		return Objects.equals(name, base.name) 
				&& super.equals(obj);
	}
	
	public Role convertToEntity() {
		Role role = new Role();
		role.setId(getId());
		role.setName(name);
		return role;
	}
}
