package com.benz.redis.controller;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.benz.redis.model.PrincipalUser;
import com.benz.redis.service.RequestService;

@RestController
@RequestMapping("/req")
public class PrincipalController {
	
	@Autowired
	private RequestService requestService;
	
	@PostMapping("/save")
	@Consumes({MediaType.APPLICATION_JSON})
	public ResponseEntity<?> save(@RequestBody PrincipalUser req)
	{
		requestService.save(req);
		return new ResponseEntity<PrincipalUser>(req,HttpStatus.OK);
		 
	}
	
	@DeleteMapping("/remove")
	@Consumes({MediaType.APPLICATION_JSON})
	public void delete(@RequestBody PrincipalUser req)
	{
		requestService.delete(req.getUserName());
	}
	
	@PutMapping("/update")
	@Consumes({MediaType.APPLICATION_JSON})
	public void update(@RequestBody PrincipalUser req)
	{
		requestService.update(req);
	}
	
	
	@GetMapping("/all")
	@Produces({MediaType.APPLICATION_JSON})
	public ResponseEntity<?> getRequests()
	{
		Map<String,PrincipalUser> users= requestService.gerRequests();
		
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("/user")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public PrincipalUser getReq(@RequestBody PrincipalUser req)
	{
		return requestService.getRequest(req.getUserName());
	}

}
