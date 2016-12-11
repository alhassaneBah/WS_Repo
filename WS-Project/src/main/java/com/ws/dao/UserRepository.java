package com.ws.dao;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.ws.model.User;

public interface UserRepository extends MongoRepository<User,String> {
	public User findByFirstName(String firstName);
	public User findByLastName(String lastName);
	public User findByEmail(String email);
	

}
