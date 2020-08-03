package com.benz.redis.controller;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.benz.redis.model.User;
import com.benz.redis.service.UserDetailsService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserDetailsService userDetailsService;

	@PostMapping("/save")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void saveUser(@RequestBody User user) throws Exception {
		userDetailsService.save(user);

	}

	
	  @DeleteMapping("/remove")
	  @Consumes({MediaType.APPLICATION_JSON})
	  public void deleteUser(@RequestBody User user) throws Exception 
	  {  userDetailsService.delete(user.getUserId()); }
	  
	  @PutMapping("/update")
	  @Consumes({MediaType.APPLICATION_JSON}) public void updateUser(@RequestBody
	  User user) {  userDetailsService.update(user); }
	  
	  @GetMapping("/all")
	  @Produces({MediaType.APPLICATION_JSON}) public Map<Integer,User> getUsers()
	  throws Exception {  return userDetailsService.findAll(); }
	  
	  @GetMapping("/id")
	  @Consumes({MediaType.APPLICATION_JSON})
	  @Produces({MediaType.APPLICATION_JSON}) public User getUser(@RequestBody User user) 
	  { return userDetailsService.findByUserId(user.getUserId()); }
	 
}
