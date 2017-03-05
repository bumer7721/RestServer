package net.rest.server.dto;

import java.util.Objects;

import net.rest.server.domains.IdDomain;

public class IdDomainDTO {
	
	private Long id;
	
	public IdDomainDTO() {
	}

	public IdDomainDTO(IdDomain entity) {
		if (entity != null) {
			id = entity.getId();
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof IdDomainDTO)) {
			return false;
		}
		IdDomainDTO base = (IdDomainDTO) obj;
		return Objects.equals(id, base.id);
	}	
}
