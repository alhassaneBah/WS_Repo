package com.ws.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ws.model.Group;
import com.ws.model.User;

public interface GroupeRepository extends MongoRepository<Group, Integer> {
	
	public List<Group> findByAdmin(User admin);

}

