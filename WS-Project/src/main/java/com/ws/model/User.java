package com.ws.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;


@Document(collection = "users")
public class User implements Serializable {
	
	
	@Id
	private String email;
	@JsonIgnore
	private String passwd;
	private String firstName;
	private String lastName;
	private String biography;
	
	
	@JsonIgnore
	@DBRef(db="groups",lazy=true)
	private List<Group> myownGroup=new ArrayList<>();
	@JsonIgnore
	@DBRef(db="groups",lazy=true)
	private List<Group> myJoingroup=new ArrayList<>();
	
	
	

  //  private BasicDBList gr=new BasicDBList();
    
	
	
	public User() {
		super();
		
	}
	
	
	public User(String email, String firstName, String lastName, String biography) {
		super();
		
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.biography = biography;
		
	}
	
	
	
	
	
	

	public User(String email) {
		this.email=email;
	}


	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getBiography() {
		return biography;
	}
	public void setBiography(String biography) {
		this.biography = biography;
	}

	public Boolean deleteAccount(){
		return true;
	}
	
	
	
	public boolean ChangeDescription(Group group, String description){
		if(group.getAdmin().getEmail()==this.getEmail()){
		  group.setDescription(description);
		  return true;
		}
		return false;
	}
	

	
	public void addMember(Group group, User user) {
		
		if(group.getAdmin()==this){
			group.addMembers(user);
			
		}
		
	}
	
	
	public void addComment(Group group, Comment comment){
		
		group.getComments().add(comment);
		
	}
	
	public boolean deleteComment(Group group, Comment comment){
		if(comment.getUser()==this){
		return group.getComments().remove(comment);
		}
		
		return false;
		 
		
	}
	
    public void addGroup(Group groupe){
		
    	myownGroup.add(groupe);
		
	}
    public Boolean  joinGroup(Group group) {
		if(!myownGroup.contains(group) && !myJoingroup.contains(group)){
			
			
			myJoingroup.add(group);
			return true;
			
		
		}
		return false;
    	
	}
	
	
	public void leaveGroup(int id){
		
		
	   Group gr=getMyJoinGroup(id);
	   if(gr !=null){
		  
		   myJoingroup.remove(gr);
		   gr.removeMembers(gr);
	   }
	}
	
	
	public void delete(int id){
		
		
		   Group gr=getMyOwnGroup(id);
		   if(gr !=null){
			  
			   myownGroup.remove(gr);
			   
		   }
		}
	
	
	public Group getMyOwnGroup(int id){
		for(Group gr : myownGroup ){
			
			
			if(gr.getId()==id){
				return gr;
			}
		}
		 return null;
	}
	
	
	public Group getMyJoinGroup(int id){
		for(Group gr : myJoingroup ){
			if(gr.getId()==id){
				return gr;
			}
		}
		 return null;
	}
	
	
	
	
	
	public int  getMyownGroupSize() {
		return myownGroup.size();
	}

	public void setMyownGroup(List<Group> myownGroup) {
		this.myownGroup = myownGroup;
	}

	@Override
	public String toString() {
		return String.format("Users[id=%s, firstName='%s', lastName='%s']", email,
				firstName, lastName,biography);
	}
	
	
	public List<Group> getMyJoingroup() {
		return myJoingroup;
	}

	public void setMyJoingroup(List<Group> myJoingroup) {
		this.myJoingroup = myJoingroup;
	}

	public List<Group> getMyownGroup() {
		return myownGroup;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	
	

	
	
	
	

}
