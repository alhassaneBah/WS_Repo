package com.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

import com.ws.model.Group;
import com.ws.model.User;
import com.ws.service.GroupeService;
import com.ws.service.UsersService;

@SpringBootApplication
@EnableOAuth2Sso
public class WsProjectApplication implements CommandLineRunner {
	
	@Autowired
    private UsersService userService;
	@Autowired
	private GroupeService groupeService;
    
	 public static String current;

	public static void main(String[] args) {
		SpringApplication.run(WsProjectApplication.class, args);
	}
	
	
	@Override
	 public void run(String... strings) throws Exception {
		
	
		
	   
		
		
		 User user1=new User("alhassaneba88@gmail", "Bah", "alhassane", "Etudiant lyon1");
		 User user2=new User("diana@gmail", "ramirez", "diana", "Etudiant saint etienne");
		 User user3=new User("pablo@gmail", "Pablo", "Escobar", "biography");
		 User user4=new User("john@gmail", "John", "Gotti", "Etudiant paris");

		 
		 Group group1=new Group(1,"groupe1", "description",user1);
		 Group group2=new Group(2,"groupe2", "description",user1);
		 Group group3=new Group(3,"groupe3", "description",user2);
		 Group group4=new Group(4,"groupe4", "description",user2);
		
		 
		 
		
		 //
		 
		
		 user1.addMember(group1,user3);
		 user1.addMember(group2,user4);
		 
		 user2.addMember(group3,user3);
		 user2.addMember(group4,user4);
		 user1.joinGroup(group4);
		 user2.joinGroup(group1);
		
		
		 
		 
		 
	
		 this.userService.deleteAll();
		 this.groupeService.deleteAll();
		 
		this.userService.save(user1);
		this.userService.save(user2);
		this.userService.save(user3);
		this.userService.save(user4);
		this.groupeService.save(group1);
		this.groupeService.save(group2);
		this.groupeService.save(group3);
		this.groupeService.save(group4);
}

}