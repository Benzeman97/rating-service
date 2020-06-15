package com.benz.catalog.controller;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.benz.catalog.model.MovieCatalog;

@RestController
@RequestMapping("/movieCatalog")
public class MovieCatalogController {

	@GetMapping("/{userId}")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<MovieCatalog> getMovieCatalog(@PathVariable("userId") int userId)
	{
		return Arrays.asList(
				new MovieCatalog("Avengers","average",5.5),
				new MovieCatalog("Harry Potter","Good",7.3),
				new MovieCatalog("Braking Bad","Excellent",9.4)
				);
	}
}
