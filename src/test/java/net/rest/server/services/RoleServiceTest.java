package net.rest.server.services;

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
import net.rest.server.dto.RoleDTO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RoleServiceTest extends BaseTest {
	
	private final static String ROLE_USER = "user";
	private final static String ROLE_ANONYMOUS = "anonymous";
	private final static String ROLE_GUEST = "guest";
	
	@Autowired
	private RoleService roleService;
	
	@Test
	@Sql(executionPhase=ExecutionPhase.BEFORE_TEST_METHOD, scripts="classpath:/testdata/insert.sql")
	public void test1FindAllRoles() {
		List<RoleDTO> roles = roleService.findAll();
		assertThat(roles, notNullValue());
		assertThat(roles.size(), equalTo(2));
	}
	
	@Test
	public void test2FindOneRole() {
		RoleDTO role = roleService.findOne(2L);
		assertThat(role, notNullValue());
		assertThat(role.getId(), equalTo(2L));
		assertThat(role.getName(), equalTo(ROLE_USER));
	}
	
	@Test
	public void test3InsertRole() {
		RoleDTO role = new RoleDTO();
		role.setName(ROLE_ANONYMOUS); 
		
		RoleDTO newRole = roleService.create(role);
		
		assertThat(roleService.count(), equalTo(3L));
		
		assertThat(newRole.getId(), equalTo(3L));
		assertThat(newRole.getName(), equalTo(role.getName()));
	}
	
	@Test
	public void test4updateRole() {
		RoleDTO role = roleService.findOne(1L);
		role.setName(ROLE_GUEST); 
		
		RoleDTO updatedRole = roleService.update(role);
		
		assertThat(roleService.count(), equalTo(3L));
		
		assertThat(updatedRole.getId(), equalTo(1L));
		assertThat(updatedRole.getName(), equalTo(role.getName()));
	}
	
	@Test
	public void test5DeleteByIdRoleTest() {
		roleService.deleteById(2L);
		assertThat(roleService.count(), equalTo(2L));	
	}
	
	@Test
	@Sql(executionPhase=ExecutionPhase.AFTER_TEST_METHOD, scripts="classpath:/testdata/drop.sql")
	public void test6DeleteRole() {
		RoleDTO role = roleService.findOne(3L);
		roleService.delete(role);
		
		assertThat(roleService.count(), equalTo(1L));	
		
		role = roleService.findOne(1L);
		roleService.delete(role);
		
		assertThat(roleService.count(), equalTo(0L));	
	}
}
