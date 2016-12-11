package com.ws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ws.model.Comment;
import com.ws.model.Group;
import com.ws.model.User;
import com.ws.service.GroupeService;
import com.ws.service.UsersService;

@Controller
public class GroupeController {
	
	@Autowired
	UsersService service;
	@Autowired
	GroupeService grService;
	
	
	 @GetMapping("/group/home")
	 public String  home(ModelMap model){
		 
		 if(service.getCurrent()==null){
			 return "redirect:/";
			 
		 }
	
		model.addAttribute("groups" , grService.findAll());
		
		model.addAttribute("myOwnGroup" ,service.getCurrent().getMyownGroup());
		
		model.addAttribute("myJoinGroup" ,service.getCurrent().getMyJoingroup());
		 
		 return "groupeHome";
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
	 group.getAdmin().addGroup(group);
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
	  if( service.getCurrent().joinGroup(group)==true){
		  
		  
		  service.save(service.getCurrent());
		  group.addMembers(service.getCurrent());
		  grService.save(group);
	  };
	   
	   
	   
	   
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
	
	 model.addAttribute("group", group);
	 model.addAttribute("user", service.getCurrent());
	 model.addAttribute("commentaire", new Comment());
      
      
      return "addComment";
	 
	 

    }
   
   @RequestMapping(value = "/group/updateComment", method = RequestMethod.POST)
    public String updateComment(@RequestParam("group") int idgroup,
    		                   @RequestParam("userId") String userId,
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
	   
	  User user = new User( email, firstName, lastName, biography);
	  
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
