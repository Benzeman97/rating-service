package com.benz.info.controller;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.benz.info.model.Movie;

@RestController
@RequestMapping("/movieInfo")
public class MovieInfoController {

	@GetMapping("/all")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<Movie> getMovie()
	{
		return Arrays.asList(
				new Movie(101,"Game Of Throne",2011,"United Kingdom"),
				new Movie(103,"Prison Brak",2012,"USA")
				);
					
	}
	
	@GetMapping("/{movieId}")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Movie getMovie(@PathVariable("movieId")int movieId)
	{
		return new Movie(movieId,"Breaking Bad",2011,"USA");
	}
}
