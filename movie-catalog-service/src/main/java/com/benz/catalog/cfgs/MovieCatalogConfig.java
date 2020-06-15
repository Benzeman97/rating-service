package com.benz.catalog.cfgs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class MovieCatalogConfig {

	@Bean(name="RestTemplate")
	public RestTemplate getRestTemplate()
	{
		return new RestTemplate();
	}
	
	@Bean(name = "WebClient")
	public WebClient.Builder getWebClientBuilder()
	{
		return WebClient.builder();
	}
}
