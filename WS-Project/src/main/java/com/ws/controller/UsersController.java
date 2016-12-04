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
	
    @Autowired
	private ConnectionRepository connectionRepository;
	private Twitter twitter;
	
	    
	    
	    
	    @Inject
	    public UsersController(Twitter twitter, ConnectionRepository connectionRepository) {
	        this.twitter = twitter;
	        this.connectionRepository = connectionRepository;
	    }

	   
	    
	   
	    
	    
	    
	   
	    @GetMapping
	    public  String helloTwitter(Model model, Principal currentUser) {
	        if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
	            return "login";
	            
	            
	        }
	 
	        
	        model.addAttribute("ID" , twitter.userOperations().getUserProfile());
	        model.addAttribute("profil" , twitter.userOperations().getUserProfile());
	        CursoredList<TwitterProfile> friends = twitter.friendOperations().getFriends();
	        model.addAttribute("friends", friends);
	        User current=service.findByFirstName(twitter.userOperations().getUserProfile().getName());
	        service.setCurrent(current);
	        
	        return "redirect:/user/home";
	    }
	    
	    
	
	
	 @GetMapping("/user/home")
	  public String home(HttpServletRequest request ) {
		 if(service.getCurrent()==null){
			 return "redirect:/";
			 
		 }
		 
		 
		 

		 request.setAttribute("current", service.getCurrent());
		 
		 request.setAttribute("users", service.findAll());
		 
	        return "userList";
	 }
	 
	 
	 
	
	 
	 
	
	 @PostMapping("/user/save")
		public String save(@ModelAttribute User user, ModelMap map) {
		 service.save(user);
		 return "redirect:/user/home";
		}
	 
	 
	 
	 
	 
	    @GetMapping("/user/delete/{id}")
		public String delete(@PathVariable int id, ModelMap map) {
		 
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
	    public String edit(@PathVariable int id,
	                       Model model) {
	        User user = service.findOne(id);
	        model.addAttribute("user", user);
	        return "editUser";
	    }
	  
	 
	
	  @RequestMapping(value = "/user/update", method = RequestMethod.POST)
	    public String update(@RequestParam("id") int id,
	                               @RequestParam("email") String email,
	                               @RequestParam("firstName") String firstName,
	                               @RequestParam("lastName") String lastName,
	                               @RequestParam("biography") String biography) {
		  User user = service.findOne(id);
		  user.setEmail(email);
	      user.setFirstName(firstName);
	      user.setLastName(lastName);
	      user.setBiography(biography);
	      service.save(user);
	      return "redirect:/user/home";
	    }
	 
	 
	  /*
	   * Gestion group
	   *  
	   */

		 @GetMapping("/group/home")
		 public String  home(ModelMap model){
			 
			 if(service.getCurrent()==null){
				 return "redirect:/";
				 
			 }
			 
			 
	        
			model.addAttribute("groups" , grService.findAll());
			//grService.findByAdmin(service.getCurrent())
			
			model.addAttribute("myOwnGroup" ,service.getCurrent().getMyownGroup());
			
			model.addAttribute("myJoinGroup" ,service.getCurrent().getMyJoingroup());
			 
			 return "groupeList";
		 }
		 
	 
	  @GetMapping("/group/new")
	    public String groupForm(ModelMap map) {
		  
		  if(service.getCurrent() == null){
				 return "redirect:/";
			 }
			 
			 
	        map.addAttribute("group", new Group());
	        
	        return "newGroup";
	    }
	 
	 
	 @PostMapping("/group/create")
	 public String CreateGroup(@ModelAttribute(value="group" )Group group ) {
		    
		  
		 
		 
		 if(service.getCurrent() == null){
			 return "redirect:/";
		 }
		 
		 
		 group.setId(grService.findAll().size()+1);
		 group.setAdmin(service.getCurrent());
		 grService.save(group);
	     return "redirect:/group/home";
	    }
	 
	 
	 
	 
	 
	 @RequestMapping(value = "/group/edit/{id}", method = RequestMethod.GET)
	    public String editGroup(@PathVariable int id,
	                       Model model) {
		 
		 

		 if(service.getCurrent() == null){
			 return "redirect:/";
		 }
		 
		 Group group = grService.findOne(id);
	      model.addAttribute("group", group);
	      return "editGroup";
		 
		 
	
	    }
	 
	
	  
	 
	 @RequestMapping(value = "/group/update", method = RequestMethod.POST)
	    public String update(@RequestParam("id") int id,
	                               @RequestParam("name") String name,
	                               @RequestParam("description") String description
	                               ) {
		 

		 if(service.getCurrent() == null){
			 return "redirect:/";
		 }
		 
		 Group group = grService.findOne(id);
		  group.setId(id);
		  group.setName(name);
		  group.setDescription(description);
		  grService.save(group);
	      return "redirect:/group/home";
	    }
	 
	 
	   
	   @GetMapping("/group/delete/{id}")
		public String deleteGroup(@PathVariable int id, ModelMap model) {
		 
		 

		 if(service.getCurrent() == null){
			 return "redirect:/";
		 }
		
	        grService.delete(id);
	        
	        service.getCurrent().delete(id);
			return "redirect:/group/home";
			
		}
	   
	   
	   @GetMapping("/group/leave/{id}")
		public String leave(@PathVariable int id, ModelMap model) {
		 
		 

		 if(service.getCurrent() == null){
			 return "redirect:/";
		 }
		
		   service.getCurrent().leaveGroup(id);
			return "redirect:/group/home";
			
		}
	   
	   
	   
	   
	   @GetMapping("/group/join/{id}")
		public String Join(@PathVariable int id, ModelMap model) {
		 
		 

		 if(service.getCurrent() == null){
			 return "redirect:/";
		 }
		
		 Group group = grService.findOne(id);
		   service.getCurrent().joinGroup(group);
			return "redirect:/group/home";
			
		}
	   
	   
	 
      
	   
	   // addcomment

	 
	   
	   @RequestMapping(value = "/group/addComment/{id}", method = RequestMethod.GET)
	    public String addMember(@PathVariable int id,
	                       Model model) {
		 
		 

		 if(service.getCurrent() == null){
			 return "redirect:/";
		 }
		 
		
		 
		 Group group = grService.findOne(id);
		 System.out.println(group.getId());
		 model.addAttribute("group", group);
		 model.addAttribute("user", service.getCurrent());
		 model.addAttribute("commentaire", new Comment());
	      
	      
	      return "addComment";
		 
		 
	
	    }
	   
	   @RequestMapping(value = "/group/updateComment", method = RequestMethod.POST)
	    public String updateComment(@RequestParam("group") int idgroup,
	    		                   @RequestParam("userId") int userId,
	                               @RequestParam("comment") String comment) {
		   
		   
		   
		 
	      
	      if(service.getCurrent() == null){
				 return "redirect:/";
			 }
	      
	       User user=service.findOne(userId);
		   Comment com=new Comment(user, comment); 
		  
		   Group group = grService.findOne(idgroup);
		   com.setId(group.getComments().size()+1);
		   group.addComment(com);
		   grService.save(group);
		   
		      return "redirect:/group/home";
	    }

		 
	     

		
	   
	      
	   
	   
	  
	   
	   @RequestMapping(value = "/group/updateMember", method = RequestMethod.POST)
	    public String updateMember(@RequestParam("group") int idgroup,
	    		
	    		                  
	                               @RequestParam("email") String email,
	                               @RequestParam("firstName") String firstName,
	                               @RequestParam("lastName") String lastName,
	                               @RequestParam("biography") String biography) {
		   
		  User user = new User(service.findAll().size()+1, email, firstName, lastName, biography);
		  
		 // user.setId(service.findAll().size()+1);
	      service.save(user);
	      if(service.getCurrent() == null){
				 return "redirect:/";
			 }
			 
			 Group group = grService.findOne(idgroup);
			  
			  group.addMembers(user);
			  grService.save(group);
		      return "redirect:/group/home";
	    }




















	   
	   
	   
	   
	   
	
	

}
