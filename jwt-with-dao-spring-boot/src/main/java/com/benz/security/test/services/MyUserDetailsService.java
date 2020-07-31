package com.benz.security.test.services;

import com.benz.security.test.dao.UserDAO;
import com.benz.security.test.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

     @Autowired
     private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user=userDAO.findByUserName(username)
                .orElseThrow(()->new UsernameNotFoundException("username is not found with "+username));

          return MyUserDetails.builder(user);
    }
}
