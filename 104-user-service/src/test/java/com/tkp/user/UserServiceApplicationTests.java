package com.tkp.user;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.tkp.user.entity.User;
import com.tkp.user.repository.UserRepository;
import com.tkp.user.service.UserService;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private UserService service;

	@MockBean
	private UserRepository repository;

	@Test
	public void getUsersTest() {
		when(repository.findAll()).thenReturn(Stream
				.of(new User(new Long(999), "Tapan", "Parida", "tapankparida@gmail.com", new Long(10)),
						new User(new Long(999), "Tapan", "Parida", "tapankparida@gmail.com", new Long(10)))
				.collect(Collectors.toList()));
		assertEquals(2, service.getUsers().size());
	}

	@Test
	public void saveUserTest() {
		User user = new User(new Long(999), "Tapan", "Parida", "tapankparida@gmail.com", new Long(10));
		when(repository.save(user)).thenReturn(user);
		assertEquals(user, service.saveUser(user));
	}
	
	@Test
	public void testStaticMethodInJunitTest() {
		assertEquals(2, service.testStaticMethodInJunit(1));
	}
}
