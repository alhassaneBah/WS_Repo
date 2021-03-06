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
import com.ws.model.Comment;
import com.ws.model.Group;
import com.ws.model.User;
import com.ws.service.GroupeService;
import com.ws.service.UsersService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WsProjectApplicationTests {

	
	
	
	
	 @Autowired
	 UsersService service;
	 

	 @Autowired
	 GroupeService grservice;
	 
	 static final int Total = 20;
	 
	 @Test
		public void contextLoads() {
		}
		
	 
	 @Before
	 public void init() {
		 
	 service.deleteAll();
	 for (int i = 0; i < Total; i++) {
		 
		 User user = new User("email"+ i);
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
	        User user = new User("alhassane@gmail.com", "alhassane", "bah", "biography");
	        service.save(user);
	        assertNotNull(service.findOne("alhassane@gmail.com"));
	    }
	 
	 
	 @Test
	 public void findByFirstname() {
		 service.deleteAll();
	        User user = new User("alhassane@gmail.com", "alhassane", "bah", "biography");
	        service.save(user);
	        assertNotNull(service.findByFirstName("alhassane"));
	    }
	 
	 @Test
	 public void findByLastname() {
		 service.deleteAll();
	        User user = new User("alhassane@gmail.com", "alhassane", "bah", "biography");
	        service.save(user);
	        assertNotNull(service.findByLastName("bah"));
	 }
	 
	 @Test
	 public void delete() {
		 service.deleteAll();
	        User user = new User("alhassane@gmail.com", "alhassane", "bah", "biography");
	        service.save(user);
	        service.delete("alhassane@gmail.com");
	        assertNull(service.findOne("alhassane@gmail.com"));
	    }
   
	 
	 @Test
	 public void createGroup() {
		 grservice.deleteAll();
		    
	        User user = new User("alhassane@gmail.com", "alhassane", "bah", "biography");
	        Group group=new Group(1, "group1", "description", user);
	        grservice.save(group);
	       
	        assertNotNull(grservice.findOne(1));
	    }

       
	   
	
	 
	   
	 @Test
	 public void addMember() {
		 
	       Group group= grservice.findOne(1);
	         group.addMembers(new User("george@gmail", "george", "goerge", "biography"));
	         
	         grservice.save(group);
	         assertEquals(1, grservice.findOne(1).getMembers().size());
	    }
	 
	 
	 
	 @Test
	 public void addComment() {
		 
	       Group group= grservice.findOne(1);
	         group.addComment(new Comment(service.findOne("george@gmail"), "this is a comment junit test"));
	         
	         grservice.save(group);
	         assertEquals(1, grservice.findOne(1).getComments().size());
	    }
	 
	 
	 @Test
	 public void deleteGroup() {
	        grservice.delete(1);
	        assertNull(grservice.findOne(1));
	    }

}
