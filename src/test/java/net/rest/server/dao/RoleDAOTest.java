package net.rest.server.dao;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import net.rest.server.base.BaseTest;
import net.rest.server.domains.Role;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RoleDAOTest extends BaseTest {
	
	private final static String ROLE_USER = "user";
	private final static String ROLE_ANONYMOUS = "anonymous";
	private final static String ROLE_GUEST = "guest";
	
	@Autowired
	private RoleDAO roleDAO;
	
	@Test
	@Sql(executionPhase=ExecutionPhase.BEFORE_TEST_METHOD, scripts="classpath:/testdata/insert.sql")
	public void test1FindAllRoles() {
		List<Role> roles = roleDAO.findAll();
		assertThat(roles, notNullValue());
		assertThat(roles.size(), equalTo(2));
	}
	
	@Test
	public void test2FindOneRole() {
		Role role = roleDAO.findOne(2L);
		assertThat(role, notNullValue());
		assertThat(role.getId(), equalTo(2L));
		assertThat(role.getName(), equalTo(ROLE_USER));
	}
	
	@Test
	public void test3InsertRole() {
		Role role = new Role();
		role.setName(ROLE_ANONYMOUS); 
		
		Role newRole = roleDAO.create(role);
		
		assertThat(roleDAO.count(), equalTo(3L));
		
		assertThat(newRole.getId(), equalTo(3L));
		assertThat(newRole.getName(), equalTo(role.getName()));
	}
	
	@Test
	public void test4updateRole() {
		Role role = roleDAO.findOne(1L);
		role.setName(ROLE_GUEST); 
		
		Role updatedRole = roleDAO.update(role);
		
		assertThat(roleDAO.count(), equalTo(3L));
		
		assertThat(updatedRole.getId(), equalTo(1L));
		assertThat(updatedRole.getName(), equalTo(role.getName()));
	}
	
	@Test
	public void test5DeleteByIdRoleTest() {
		roleDAO.deleteById(2L);
		assertThat(roleDAO.count(), equalTo(2L));	
	}
	
	@Test
	@Sql(executionPhase=ExecutionPhase.AFTER_TEST_METHOD, scripts="classpath:/testdata/drop.sql")
	public void test6DeleteRole() {
		Role role = roleDAO.findOne(3L);
		roleDAO.delete(role);
		
		assertThat(roleDAO.count(), equalTo(1L));	
		
		role = roleDAO.findOne(1L);
		roleDAO.delete(role);
		
		assertThat(roleDAO.count(), equalTo(0L));	
	}
}
