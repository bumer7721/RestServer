package net.rest.server.dao;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import net.rest.server.domains.Role;


public class RoleDAOTest extends BaseTest {
	
	private final static String ROLE_USER = "user";
	private final static String ROLE_ANONYMOUS = "anonymous";
	
	@Autowired
	private RoleDAO roleDAO;
	
	@Test
	@Sql(executionPhase=ExecutionPhase.BEFORE_TEST_METHOD, scripts="classpath:/testdata/role_data/insert.sql")
	@Sql(executionPhase=ExecutionPhase.AFTER_TEST_METHOD, scripts="classpath:/testdata/role_data/drop.sql")
	public void findAllRolesTest() {
		List<Role> roles = roleDAO.findAll();
		assertThat(roles, notNullValue());
		assertThat(roles.size(), equalTo(3));
	}
	
	@Test
	@Sql(executionPhase=ExecutionPhase.BEFORE_TEST_METHOD, scripts="classpath:/testdata/role_data/insert.sql")
	@Sql(executionPhase=ExecutionPhase.AFTER_TEST_METHOD, scripts="classpath:/testdata/role_data/drop.sql")
	public void findOneRoleTest() {
		Role role = roleDAO.findOne(2L);
		assertThat(role, notNullValue());
		assertThat(role.getId(), equalTo(2L));
		assertThat(role.getName(), equalTo(ROLE_USER));
	}
	
	@Test
	public void insertRoleTest() {
		Role role = new Role();
		role.setName(ROLE_USER); 
		
		Role newRole = roleDAO.create(role);
		
		List<Role> users = roleDAO.findAll();
		assertThat(users, notNullValue());
		assertThat(users.size(), equalTo(1));
		
		assertThat(newRole.getId(), equalTo(1L));
		assertThat(newRole.getName(), equalTo(role.getName()));
	}
	
	@Test
	@Sql(executionPhase=ExecutionPhase.BEFORE_TEST_METHOD, scripts="classpath:/testdata/role_data/insert.sql")
	@Sql(executionPhase=ExecutionPhase.AFTER_TEST_METHOD, scripts="classpath:/testdata/role_data/drop.sql")
	public void updateRoleTest() {
		Role role = roleDAO.findOne(1L);
		role.setName(ROLE_ANONYMOUS); 
		
		Role updatedRole = roleDAO.update(role);
		
		List<Role> users = roleDAO.findAll();
		assertThat(users, notNullValue());
		assertThat(users.size(), equalTo(3));
		
		assertThat(updatedRole.getId(), equalTo(1L));
		assertThat(updatedRole.getName(), equalTo(role.getName()));
	}
	
	@Test
	@Sql(executionPhase=ExecutionPhase.BEFORE_TEST_METHOD, scripts="classpath:/testdata/role_data/insert.sql")
	@Sql(executionPhase=ExecutionPhase.AFTER_TEST_METHOD, scripts="classpath:/testdata/role_data/drop.sql")
	public void deleteRoleTest() {
		Role role = roleDAO.findOne(1L);
		roleDAO.delete(role);
		
		List<Role> users = roleDAO.findAll();
		assertThat(users, notNullValue());
		assertThat(users.size(), equalTo(2));	
	}
	
	@Test
	@Sql(executionPhase=ExecutionPhase.BEFORE_TEST_METHOD, scripts="classpath:/testdata/role_data/insert.sql")
	@Sql(executionPhase=ExecutionPhase.AFTER_TEST_METHOD, scripts="classpath:/testdata/role_data/drop.sql")
	public void deleteByIdRoleTest() {
		roleDAO.deleteById(2L);
		
		List<Role> users = roleDAO.findAll();
		assertThat(users, notNullValue());
		assertThat(users.size(), equalTo(2));	
	}
}
