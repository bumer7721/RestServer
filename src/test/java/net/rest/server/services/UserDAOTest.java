package net.rest.server.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import net.rest.server.base.BaseTest;
import net.rest.server.dto.RoleDTO;
import net.rest.server.dto.UserDTO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDAOTest extends BaseTest {
	
	private final static String USER_NAME_1 = "Pupkin";
	private final static String USER_PASSWORD_1 = "0000";
	
	private final static String USER_NAME_2 = "zuzukin";
	private final static String USER_PASSWORD_2 = "123456";
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Test
	@Sql(executionPhase=ExecutionPhase.BEFORE_TEST_METHOD, scripts={"classpath:/testdata/role_data/insert.sql", "classpath:/testdata/user_data/insert.sql"})
	public void test1FindAllUsers() {
		List<UserDTO> users = userService.findAll();
		assertThat(users, notNullValue());
		assertThat(users.size(), equalTo(2));
	}
	
	@Test
	public void test2FindOneUser() {
		UserDTO user = userService.findOne(1L);
		
		assertThat(user, notNullValue());		
		assertThat(user.getId(), equalTo(1L));		
		assertThat(user.getUserName(), equalTo(USER_NAME_2));		
		assertThat(user.getPassWord(), equalTo(USER_PASSWORD_2));		
		assertThat(user.getIsActive(), equalTo(true));	
	}
	
	@Test
	public void test3InsertUser() {
		RoleDTO role = roleService.findOne(2L);

		UserDTO user = new UserDTO();
		user.setUserName(USER_NAME_1); 
		user.setPassWord(USER_PASSWORD_1); 
		user.setIsActive(true);
		user.setRoles(Arrays.asList(role));
		
		UserDTO newUser = userService.create(user);
		assertThat(newUser.getId(), equalTo(3L));
		assertThat(newUser.getUserName(), equalTo(user.getUserName()));
		assertThat(newUser.getPassWord(), equalTo(user.getPassWord()));
		assertThat(newUser.getIsActive(), equalTo(user.getIsActive()));
		assertThat(newUser.getRoles(), notNullValue());
		assertThat(newUser.getRoles().size(), equalTo(1));
		assertThat(newUser.getRoles().get(0).getId(), equalTo(role.getId()));
		
		assertThat(userService.count(), equalTo(3L));
	}
	
	@Test
	public void test4DeleteUserById() {
		userService.deleteById(3L);
		assertThat(userService.count(), equalTo(2L));
		
		assertThat(roleService.count(), equalTo(2L));
	}
	
	@Test
	public void test5UpdateUser() {
		RoleDTO role = roleService.findOne(1L);
		UserDTO user = userService.findOne(2L);
		user.setUserName(USER_NAME_1);
		user.setRoles(Arrays.asList(role));
		
		UserDTO updatedUser = userService.update(user);
		assertThat(updatedUser.getId(), equalTo(2L));
		assertThat(updatedUser.getUserName(), equalTo(user.getUserName()));
		assertThat(updatedUser.getPassWord(), equalTo(user.getPassWord()));
		assertThat(updatedUser.getIsActive(), equalTo(user.getIsActive()));
		assertThat(updatedUser.getRoles(), notNullValue());
		assertThat(updatedUser.getRoles().size(), equalTo(1));
		assertThat(updatedUser.getRoles().get(0).getId(), equalTo(role.getId()));
		
		assertThat(userService.count(), equalTo(2L));
	}
	
	@Test
	public void test6DeleteUser(){
		UserDTO user = userService.findOne(1L);
		userService.delete(user);
		assertThat(userService.count(), equalTo(1L));
		
		user = userService.findOne(2L);
		userService.delete(user);
		assertThat(userService.count(), equalTo(0L));
	}
}
