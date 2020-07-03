package com.benz.catalog.cfgs;


import java.net.URI;
import java.util.function.Function;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.http.client.reactive.ClientHttpResponse;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.benz.catalog.model.DbSetting;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Configuration
public class MovieCatalogConfig {

	
	@LoadBalanced
	@Bean(name="RestTemplate")
	public RestTemplate getRestTemplate()
	{
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory=
				new HttpComponentsClientHttpRequestFactory();
		
		clientHttpRequestFactory.setConnectTimeout(3000);
		
		return new RestTemplate(clientHttpRequestFactory);
	}
	
	@LoadBalanced
	@Bean(name = "WebClient")
	public WebClient.Builder getWebClientBuilder()
	{
		
		/*
		 * HttpClient httpClient=HttpClient.create() .tcpConfiguration(client->
		 * client.option(ChannelOption.CONNECT_TIMEOUT_MILLIS,3000)
		 * .doOnConnected(conn-> conn.addHandlerLast(new ReadTimeoutHandler(10))
		 * .addHandlerLast(new ReadTimeoutHandler(10))));
		 * 
		 * ClientHttpConnector connector=new
		 * ReactorClientHttpConnector(httpClient.wiretap(true));
		 */
			
		
		return WebClient.builder();
		
//				.clientConnector(connector)
//				.defaultHeader(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE)
//				.build();
	}
	
	@Bean
	public DbSetting getDbSetting()
	{
		return new DbSetting();
	}
}
