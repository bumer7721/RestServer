package net.rest.server.domains;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="roles")
public class Role extends IdDomain {

	private String name;
	
	@Column(name="name", unique=true, nullable=false, length=128)
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
		if (!(obj instanceof Role)) {
			return false;
		}
		Role base = (Role) obj;
		return Objects.equals(name, base.name) 
				&& super.equals(obj);
	}
}
