package com.benz.info.controller;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.benz.info.exception.DataNotFoundException;
import com.benz.info.model.Movie;
import com.benz.info.model.MovieSummary;
import com.benz.info.services.MovieInfoService;

@RestController
@RequestMapping("/movieInfo")
public class MovieInfoController {
	
	@Autowired
	MovieInfoService movieInfo_service;

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
	public MovieSummary getMovie(@PathVariable("movieId")int movieId)
	{
		if(movieId!=0)
		return movieInfo_service.getMovieSummary(movieId);
		else
			throw new DataNotFoundException("Data Not Available");
	}
}
