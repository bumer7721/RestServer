package net.rest.server.domains;

import java.util.List;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User extends IdDomain {

	private String userName;
	private String passWord;
	private boolean isActive;
	private List<Role> roles;
	
	@Column(name="user_name", unique=true, nullable=false, length=512)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name="password", nullable=false)
	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	@Column(name="is_active")
	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	@ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, targetEntity = Role.class)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false),
            foreignKey=@ForeignKey(ConstraintMode.CONSTRAINT),
    		inverseForeignKey=@ForeignKey(ConstraintMode.CONSTRAINT))
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
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
		if (!(obj instanceof User)) {
			return false;
		}
		User base = (User) obj;
		return Objects.equals(userName, base.userName) 
				&& Objects.equals(passWord, base.passWord) 
				&& Objects.equals(isActive, base.isActive) 
				&& super.equals(obj);
	}
}
