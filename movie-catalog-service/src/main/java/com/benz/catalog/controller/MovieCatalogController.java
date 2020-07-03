package com.benz.catalog.controller;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.benz.catalog.model.DbSetting;
import com.benz.catalog.model.MovieCatalog;
import com.benz.catalog.services.MovieCatalogService;

@RestController
@RefreshScope
@RequestMapping("/movieCatalog")
public class MovieCatalogController {
	
	@Autowired
	private MovieCatalogService movie_catalog_service;;

	@GetMapping("/{userId}")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<MovieCatalog> getMovieCatalog(@PathVariable("userId") int userId) throws Exception
	{
		return movie_catalog_service.getMovieCatalog(userId);
	}
	
	/*
	 * @GetMapping("/discoveryClient") public void discoveryClient() {
	 * movie_catalog_service.discoveryClient(); }
	 */

	@Value("${app.greeting}")
	private String greeting;
	
	@Value("${app.msg}")
	private String msg;
	
	@Value("${app.list.values}")
	private List<String> getList;
	
	@Value("#{${app.dbValues}}")
	private Map<String,String> dbValues;
	
	@Autowired
	DbSetting dbSetting;
	

	@GetMapping("/app/{value}")
	public String getString(@PathVariable("value") String value)
	{
		
		
		 switch (value) {
			 case "greeting":
				 return greeting;
			 case "msg":
			 	return msg;
			 case "username":
				 return dbSetting.getUserName();
			 case "password":
				 return dbSetting.getPassword();		 
			 default:
			 	return null;
		 }
	}

	@GetMapping("/list/Values")
	public List<String> getList()
	{
		return getList;
	}

	@GetMapping("/key/Values")
	public Map<String,String> getKeyValues()
	{
		return dbValues;
	}
	
	

}
