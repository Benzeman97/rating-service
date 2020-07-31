package com.benz.api.gateway.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.benz.api.gateway.dao.UserDAO;
import com.benz.api.gateway.entity.User;



@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	     @Autowired
	     private UserDAO userDAO;

	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	        User user=userDAO.findByUserName(username)
	                .orElseThrow(()->new UsernameNotFoundException("username is not found with "+username));

	          return UserDetailsImpl.builder(user);
	    }
}
