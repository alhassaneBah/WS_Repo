package com.ws.social.service;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;

import com.ws.service.UsersService;

import java.util.UUID;

public class AccountConnectionSignUpService implements ConnectionSignUp {


    private final UsersService usersDao;

    public AccountConnectionSignUpService(UsersService usersDao) {
        this.usersDao = usersDao;
    }

    public String execute(Connection<?> connection) {
        UserProfile profile = connection.fetchUserProfile();
        String userId = UUID.randomUUID().toString();
      //  usersDao.save(user);
        return userId;
    }
}