package com.ws;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ws.dao.UserRepository;
import com.ws.model.User;
import com.ws.service.UsersService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WsProjectApplicationTests {

	
	
	
	
	 @Autowired
	 UsersService service;
	 
	 static final int Total = 20;
	 
	 @Test
		public void contextLoads() {
		}
		
	 
	 @Before
	 public void init() {
		 
	 service.deleteAll();
	 for (int i = 0; i < Total; i++) {
		 
		 User user = new User(i);
		 service.save(user);
		 }
	 }
	 
	 @Test
	 public void happyTest() {
	 List<User> list = service.findAll();
	 assertEquals(Total, list.size());
	 }
	 
	 @Test
	 public void findById() {
		 service.deleteAll();
	        User user = new User(1, "alhassane@gmail.com", "alhassane", "bah", "biography");
	        service.save(user);
	        assertNotNull(service.findOne(1));
	    }
	 
	 
	 @Test
	 public void findByFirstname() {
		 service.deleteAll();
	        User user = new User(1, "alhassane@gmail.com", "alhassane", "bah", "biography");
	        service.save(user);
	        assertNotNull(service.findByFirstName("alhassane"));
	    }
	 
	 @Test
	 public void findByLastname() {
		 service.deleteAll();
	        User user = new User(1, "alhassane@gmail.com", "alhassane", "bah", "biography");
	        service.save(user);
	        assertNotNull(service.findByLastName("bah"));
	 }
	 
	 @Test
	 public void delete() {
		 service.deleteAll();
	        User user = new User(1, "alhassane@gmail.com", "alhassane", "bah", "biography");
	        service.save(user);
	        service.delete(1);
	        assertNull(service.findByFirstName("bah"));
	    }


}
