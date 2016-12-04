package com.ws.social.service;




import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.ws.model.User;
import com.ws.service.UsersService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;



@Component
public class SocialControllerUtil {


    private static final String USER_CONNECTION = "MY_USER_CONNECTION";
    private static final String USER_PROFILE = "MY_USER_PROFILE";

   

    @Autowired
    private UsersService usersDao;


  
    public void setModel(HttpServletRequest request, Principal currentUser, Model model) {


        String userId = currentUser == null ? null : currentUser.getName();
        HttpSession session = request.getSession();

        User connection = null;
        User profile = null;
        

        if (userId != null) {

            // Get the current UserConnection from the http session
            connection = getUserConnection(session, userId);

            // Get the current UserProfile from the http session
            profile = getUserProfile(session, userId);


            
        }

       
        
    }
    
    
    
    protected User getUserProfile(HttpSession session, String userId) {
        User profile = (User) session.getAttribute(USER_PROFILE);

        // Reload from persistence storage if not set or invalid (i.e. no valid userId)
        if (profile == null || !userId.equals(profile.getFirstName())) {
            profile = usersDao.findByFirstName(userId);
            session.setAttribute(USER_PROFILE, profile);
        }
        return profile;
    }

   
    public User getUserConnection(HttpSession session, String userId) {
        User connection;
        connection = (User) session.getAttribute(USER_CONNECTION);

        // Reload from persistence storage if not set or invalid (i.e. no valid userId)
        if (connection == null || !userId.equals(connection.getFirstName())) {
            connection = usersDao.findByFirstName(userId);
            session.setAttribute(USER_CONNECTION, connection);
        }
        return connection;
    }

   
   
}
