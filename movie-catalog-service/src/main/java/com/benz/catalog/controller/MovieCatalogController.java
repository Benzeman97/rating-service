package com.benz.catalog.controller;

import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.benz.catalog.model.MovieCatalog;
import com.benz.catalog.services.MovieCatalogService;

@RestController
@RequestMapping("/movieCatalog")
public class MovieCatalogController {
	
	@Autowired
	private MovieCatalogService movie_catalog_service;;

	@GetMapping("/{userId}")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<MovieCatalog> getMovieCatalog(@PathVariable("userId") int userId)
	{
		return movie_catalog_service.getMovieCatalog(userId);
	}
}
