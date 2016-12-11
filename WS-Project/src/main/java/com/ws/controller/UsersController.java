package com.ws.controller;

import java.security.Principal;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ws.model.Comment;
import com.ws.model.Group;
import com.ws.model.User;
import com.ws.service.GroupeService;
import com.ws.service.UsersService;



@Controller

@RequestMapping("/")
public class UsersController {
	
	@Autowired
	UsersService service;
	@Autowired
	GroupeService grService;
	
	    
	   
	    
	    
	   
	
	    
	    
	 
	 
	 @GetMapping("/user/home")
	  public String home(HttpServletRequest request ) {
		 if(service.getCurrent()==null){
			 return "redirect:/";
			 
		 }
		 
		 
		
      
		 User user=service.findOne(service.getCurrent().getEmail());
		 request.setAttribute("user", user);
		
	        return "userHome";
	 }
	 
	 
	 
	
	 
	 
	
	 @PostMapping("/user/save")
		public String save(@ModelAttribute User user, ModelMap map) {
		 service.save(user);
		 return "redirect:/user/home";
		}
	 
	 
	 
	 
	 
	    @GetMapping("/user/delete/{id}")
		public String delete(@PathVariable String id, ModelMap map) {
	    
	    	
	    if(service.getCurrent().getEmail().equals(id)){
	    	
	    	return "redirect:/login/home";
	    }
		 
    	
		 service.delete(id);
		 
		 return "redirect:/user/home";
		 
		
		}
	
	 
	 
	 
	 @GetMapping("/user/new")
	    public String userForm(ModelMap map) {
	        map.addAttribute("user", new User());
	        
	        return "newUser";
	    }
	 
	 
	 @PostMapping("/user/create")
	 public String userSave(@ModelAttribute(value="user" )User user ) {
		    
		    
		  
		    service.save(user);
		    return "redirect:/user/home";
	    }
	 
	 
	
	 
	 
	 
	 
	 
	 
	 @RequestMapping(value = "/user/edit/{id}", method = RequestMethod.GET)
	    public String edit(@PathVariable String id,
	                       Model model) {
	        User user = service.findOne(id);
	        
	       
	        
	       
	        
	        model.addAttribute("user", user);
	        return "editUser";
	    }
	  
	 
	
	 // @RequestMapping(value = "/user/update", method = RequestMethod.POST)
	  @PostMapping(value = "/user/update")
	    public String update(
	                               @RequestParam("email") String email,
	                               @RequestParam("firstName") String firstName,
	                               @RequestParam("lastName") String lastName,
	                               @RequestParam("biography") String biography) {
		  User user = service.findOne(email);
		  
	      user.setFirstName(firstName);
	      user.setLastName(lastName);
	      user.setBiography(biography);
	      service.save(user);
	      return "redirect:/user/home";
	    }
	 
	 
	









	   
	   
	   
	   
	   
	
	

}
