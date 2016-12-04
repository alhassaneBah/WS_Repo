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
		
	
		
	
		 User user1=new User(1,"alhassane@gmail.com", "Bah", "alhassane", "biography");
		 User user2=new User(2,"diana@gmail.com", "ramirez", "diana", "biography");
		 User user3=new User(3,"pablo@gmail.com", "Pablo", "Escobar", "biography");

		 
		 Group group1=new Group(1,"groupe1", "description",user1);
		 Group group2=new Group(2,"groupe2", "description",user1);
		 Group group3=new Group(3,"groupe3", "description",user2);
		 Group group4=new Group(4,"groupe4", "description",user2);
		
		 
		 
		
		 //
		 
		
		 user1.addMember(group1,user3);
		 user2.addMember(group4,user3);
		 user1.joinGroup(group4);
		 user2.joinGroup(group1);
		
		
		 
		 
		 
	
		 
		 
		this.userService.save(user1);
		this.userService.save(user2);
		this.userService.save(user3);
		this.groupeService.save(group1);
		this.groupeService.save(group2);
		this.groupeService.save(group3);
		this.groupeService.save(group4);
}

}