package com.ws.controller;

import java.security.Principal;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ws.model.User;
import com.ws.service.UsersService;

@Controller
@RequestMapping("/")
public class LoginController {
	
	
	@Autowired
	UsersService service;
	
	 @Autowired
		private ConnectionRepository connectionRepository;
		private Twitter twitter;
		
	
		@Inject
	    public LoginController(Twitter twitter, ConnectionRepository connectionRepository) {
	        this.twitter = twitter;
	        this.connectionRepository = connectionRepository;
	    }
		
		
		
		
		 @GetMapping()
		 public String home1(ModelMap map) {
		        map.addAttribute("user", new User());
		        
		        return "login";
		  }
				
		 
		 @GetMapping("/login/home")
		    public String home(ModelMap map) {
		        map.addAttribute("user", new User());
		        
		        return "login";
		  }
		 
		 

			
			
		
			@GetMapping("/home")
		    public String home2(ModelMap map) {
		        return "redirect:/login/home";
		  }
		 
		 @GetMapping("/twitter")
		 public String twitter(ModelMap model ) {
			
			 if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
		            return "redirect:/connect/twitter";
		        }

			   
			    
		        User current=service.findByEmail(twitter.userOperations().getUserProfile().getScreenName());
                
		        if(current ==null){
		        	User user=new User();
		        	user.setEmail(twitter.userOperations().getUserProfile().getScreenName());
		        	user.setFirstName(twitter.userOperations().getUserProfile().getName());
		        	user.setBiography(twitter.userOperations().getUserProfile().getDescription());
		        	service.setCurrent(user);
		        	service.save(user);
		        	
		        	
		        }
		        model.addAttribute("user",service.getCurrent());
			    return "redirect:/user/home";
		}
		 
		 

		
		 
		 @PostMapping("/login/signIn")
		 public String signIn(@ModelAttribute(value="user" )User user,Model model ) {
			    
			 
			 
			    User useSign=service.findOne(user.getEmail());
			   
			    
			    if(useSign==null){
			    	
			    				    	 
			    	return "redirect:/login/home";
			    }
			   
			    

			    service.setCurrent(useSign);
			    model.addAttribute("user",service.getCurrent());
			    return "redirect:/user/home";
		    }
	
		
		 @GetMapping("/login/signUp")
		    public String signUp(ModelMap map) {
		        map.addAttribute("user", new User());
		        
		        return "SignUp";
		  }
		
		  
		 @PostMapping("/login/signUp")
		 public String signUp(@ModelAttribute(value="user" )User user,Model model ) {
			    
			  if( service.findOne(user.getEmail())!=null){
				  return "redirect:/login/signUp";
			  }
			   User us=new User();
			   us.setEmail(user.getEmail());
			   us.setFirstName(user.getFirstName());
			   us.setLastName(user.getLastName());
			   us.setBiography(user.getBiography());
			   us.setPasswd(user.getPasswd());
			   
			    service.save(us);
			    service.setCurrent(us);
			    model.addAttribute("user",service.getCurrent());
			    return "redirect:/user/home";
		  }
		
		 
		 
		
		
		
		

}
