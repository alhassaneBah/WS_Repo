package com.ws.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ws.dao.GroupeRepository;
import com.ws.dao.UserRepository;
import com.ws.model.Group;
import com.ws.model.User;


@Service
public class GroupeService {
	
	private GroupeRepository groupeRepository;

	public GroupeService(GroupeRepository groupeRepository) {
		this.groupeRepository = groupeRepository;
	}
	
	
	
	public List<Group> findAll(){
		
		return groupeRepository.findAll();
		
	}
	
	public void save(Group groupe){
		groupeRepository.save(groupe);
	}
	
	public void delete(int id){
		groupeRepository.delete(id);
	}



	public Group findOne(int id) {
		// TODO Auto-generated method stub
		return groupeRepository.findOne(id);
	}



	public List<Group> findByAdmin(User admin){
		return groupeRepository.findByAdmin(admin);
	}



	
	
	 
	 
}
