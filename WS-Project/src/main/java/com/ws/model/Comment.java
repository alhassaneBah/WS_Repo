package com.ws.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "comments")
public class Comment implements Serializable {
	@Id
	private Integer id;
	@DBRef(db="users")
  private User user;
  private String comment;
  private Date date;
  
  public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}

  
  
public Comment(User user, String comment) {
	super();
	this.user = user;
	this.comment = comment;
	this.date=new Date();
}
public Comment() {
	// TODO Auto-generated constructor stub
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public String getComment() {
	return comment;
}
public void setComment(String comment) {
	this.comment = comment;
}
  
}
