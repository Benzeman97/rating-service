package com.benz.catalog.cfgs;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class MovieCatalogConfig {

	
	@LoadBalanced
	@Bean(name="RestTemplate")
	public RestTemplate getRestTemplate()
	{
		return new RestTemplate();
	}
	
	@LoadBalanced
	@Bean(name = "WebClient")
	public WebClient.Builder getWebClientBuilder()
	{
		return WebClient.builder();
	}
}
