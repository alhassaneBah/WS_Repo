package com.ws.controller;






import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ws.model.Comment;
import com.ws.model.Group;
import com.ws.model.User;
import com.ws.service.GroupeService;
import com.ws.service.UsersService;



@RestController
@RequestMapping("/json")
public class JsonController {
	
	@Autowired
	UsersService userService;
	@Autowired
	GroupeService grService;
	
	
	private static String version="1.0";
	
	
	
	@GetMapping("/version")
	public  String  version()
	{
		
		
		return version;
		  
		 
		 
		
	
	}
	
    @GetMapping("/user/all")
    public ResponseEntity<String> listAllUsers() throws JsonGenerationException, JsonMappingException, IOException {
		
        List<User> users =userService.findAll();
        if(users.isEmpty()){
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }
        
        String result = new ObjectMapper().writeValueAsString(users);
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }
 
    
    
	
	
	
    
    @GetMapping("/user/profil/{email}")
    public ResponseEntity<String> userProfile (@PathVariable String email)throws JsonGenerationException, JsonMappingException, IOException {
		
        User user =userService.findOne(email);
        if(user==null){
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }
        
        String result = new ObjectMapper().writeValueAsString(user);
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    
    @GetMapping("/user/delete/{email}")
    public ResponseEntity<String> userDelete (@PathVariable String email)throws JsonGenerationException, JsonMappingException, IOException {
		
        userService.delete(email);
        List<User> users =userService.findAll();
        if(users.isEmpty()){
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }
        
        String result = new ObjectMapper().writeValueAsString(users);
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    
    
    @GetMapping("/user/ownGroup/{email}")
    public ResponseEntity<String> userOwnGroup (@PathVariable String email)throws JsonGenerationException, JsonMappingException, IOException {
		
       
        User user =userService.findOne(email);
        if(user==null){
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }
        
        List<Group> groups=user.getMyownGroup();
        if(groups.isEmpty()){
        	return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }
        String result = new ObjectMapper().writeValueAsString(groups);
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    
    
    @GetMapping("/user/joinGroup/{email}")
    public ResponseEntity<String> userJoinGroup (@PathVariable String email)throws JsonGenerationException, JsonMappingException, IOException {
		
       
        User user =userService.findOne(email);
        if(user==null){
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }
        
        List<Group> groups=user.getMyJoingroup();
        if(groups.isEmpty()){
        	return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }
        String result = new ObjectMapper().writeValueAsString(groups);
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

	
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   @GetMapping("/group/all")
	
    public ResponseEntity<String> listAllGroup() throws JsonGenerationException, JsonMappingException, IOException {
		
        List<Group> groups =grService.findAll();
        if(groups.isEmpty()){
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }
        
        String result = new ObjectMapper().writeValueAsString(groups);
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }
	
    
    
    
    
    
    
    
    @GetMapping("/group/delete/{id}")
    public ResponseEntity<String> groupDelete (@PathVariable int id)throws JsonGenerationException, JsonMappingException, IOException {
		
    	grService.delete(id);
    	List<Group> groups =grService.findAll();
        if(groups.isEmpty()){
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }
        
        String result = new ObjectMapper().writeValueAsString(groups);
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }
   
    
    @GetMapping("/group/members/{id}")
    public ResponseEntity<String> groupMembers (@PathVariable int id)throws JsonGenerationException, JsonMappingException, IOException {
		
    	
    	Group group =grService.findOne(id);
        if(group==null){
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }
        
        
        List<User> users=group.getMembers();
        if(users.isEmpty()){
        	 return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }
        String result = new ObjectMapper().writeValueAsString(users);
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }
    
    
    @GetMapping("/group/comments/{id}")
    public ResponseEntity<String> groupComments(@PathVariable int id)throws JsonGenerationException, JsonMappingException, IOException {
		
    	
    	Group group =grService.findOne(id);
        if(group==null){
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }
        
        
        List<Comment> comments=group.getComments();
        if(comments.isEmpty()){
        	 return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }
        String result = new ObjectMapper().writeValueAsString(comments);
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

	
	
	
	
	
	
	

 
	
	
	
	
	

}
