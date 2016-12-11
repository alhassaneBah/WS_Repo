package com.ws.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.BasicDBObject;


@Document(collection = "groups")
public class Group implements Serializable  {
	
	@Id
	private int id;
	private String name;
	private String description;
	@JsonIgnore
	@DBRef(db="users" , lazy=true)
	private User admin;
	
	@JsonIgnore
	@DBRef(db="users" , lazy=true)
	private List<User> members=new ArrayList<>();
	@JsonIgnore
	private List<Comment> comments=new ArrayList<>();
	
	
	
	
	public Group(int id,String name,String description,User admin) {
		super();
		this.id=id;
		this.name = name;
		this.description=description;
		this.admin=admin;
		this.admin.addGroup(this);
		
	}
	
	
	
	
	public Group() {
		// TODO Auto-generated constructor stub
	}

	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getAdmin() {
		return admin;
	}
	public void setAdmin(User admin) {
		this.admin = admin;
	}

	
	
	
	public void addMembers(User user){
		
		
		members.add(user);
	}
	
	public void removeMembers(User users){
		members.remove(users);
		
	}
	
	public void removeMembers(int index){
		members.remove(index);
	}
	

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}




	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}
   
	
	
	
	public List<Comment> getComments() {
		return comments;
	}

	
	


	public String getEmailAdmin(){
		return admin.getEmail();
	}
	
	public int getSize(){
		return members.size();
	}

    public int getNbComment(){
    	return comments.size();
    }


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Group other = (Group) obj;
		if (id != other.id)
			return false;
		return true;
	}




	public void removeMembers(Group gr) {
		members.remove(gr);
		
	}




	public void addComment(Comment com) {
		
		comments.add(com);
		
		
	}
	

	
	

}
