package com.ws.controller;

import javax.inject.Inject;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/twitter2")
public class HelloTwitterController {
	
	
	

//	    private Twitter twitter;
//	    private ConnectionRepository connectionRepository;
//
//	    @Inject
//	    public HelloTwitterController(Twitter twitter, ConnectionRepository connectionRepository) {
//	        this.twitter = twitter;
//	        this.connectionRepository = connectionRepository;
//	    }
//	    
//	    
//	    @RequestMapping(method=RequestMethod.GET)
//	    public String helloTwitter(Model model, @ModelAttribute("tweet") String tweet) {
//	        if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
//	            return "redirect:/connect/twitter";
//	        }
//	            
//	        twitter.timelineOperations().updateStatus(tweet);
//	        model.addAttribute("tweets", twitter.timelineOperations().getHomeTimeline());
//	            
//	        return "/user/home";
//	    }

}
