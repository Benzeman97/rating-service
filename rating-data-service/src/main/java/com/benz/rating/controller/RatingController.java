package com.benz.rating.controller;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.benz.rating.model.MovieRating;
import com.benz.rating.model.UserRating;

@RestController
@RequestMapping("/rating")
public class RatingController {

	
	@GetMapping("/{movieId}")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public MovieRating getMovieRating(@PathVariable("movieId") int movieId)
	{
		return new MovieRating(movieId,5.5);
	}
	
	@GetMapping("/user/{userId}")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public UserRating getUserRating(@PathVariable("userId") int userId)
	{
		UserRating rating =new UserRating();
		rating.initData(userId);
		return rating;
	}
}
