package net.rest.server.dao;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import net.rest.server.dao.impl.UserDAOImpl;
import net.rest.server.domains.User;


public class UserDAOTest extends BaseTest {
	
	private final static String USER_NAME = "Pupkin";
	private final static String USER_PASSWORD = "0000";
	
	@Autowired
	private UserDAOImpl userDAO;
	
	@Test
	@Sql(executionPhase=ExecutionPhase.BEFORE_TEST_METHOD, scripts="classpath:/testdata/user_data/insert.sql")
	@Sql(executionPhase=ExecutionPhase.AFTER_TEST_METHOD, scripts="classpath:/testdata/user_data/drop.sql")
	public void findAllUsersTest() {
		List<User> users = userDAO.findAll();
		assertThat(users, notNullValue());
		assertThat(users.size(), equalTo(2));
	}
	
	@Test
	public void insertUserTest() {
		User user = new User();
		user.setUserName(USER_NAME); 
		user.setPassWord(USER_PASSWORD); 
		user.setIsActive(true);
		
		userDAO.create(user);
		
		List<User> users = userDAO.findAll();
		assertThat(users, notNullValue());
		assertThat(users.size(), equalTo(1));
		
		User newUser = users.get(0);
		assertThat(newUser.getId(), equalTo(1L));
		assertThat(newUser.getUserName(), equalTo(user.getUserName()));
		assertThat(newUser.getPassWord(), equalTo(user.getPassWord()));
		assertThat(newUser.getIsActive(), equalTo(user.getIsActive()));
	}

}
