package com.ws.service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ws.dao.UserRepository;
import com.ws.model.Group;
import com.ws.model.User;

@Service
public class UsersService {
	
	private UserRepository userRepository;
	public static User current;
	private Group grRepository;
	
	public UsersService(UserRepository userRepository){
		this.userRepository=userRepository;
		
	}
	
	public List<User> findAll(){
		
		return userRepository.findAll();
		
	}
	
	public void save(User user){
		
		userRepository.save(user);
	}
	
	public void delete(String id){
		userRepository.delete(id);
	}
	
	
	public  User findOne(String id){
	 
	   
		
	   return userRepository.findOne(id);
	  
		
		
	         
	}
	
	
	

	
	
	
	public User findByFirstName(String firstName){
		return userRepository.findByFirstName(firstName);
		
	}
	public User findByLastName(String lastName){
		return userRepository.findByLastName(lastName);
		
	}

	public void deleteAll() {
		userRepository.deleteAll();
		
	}

	
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email);
	}
	
	public  User getCurrent() {
		return current;
	}

	public  void setCurrent(User current) {
		UsersService.current = current;
	}

	

	

	
	
	

}
