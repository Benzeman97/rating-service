package com.benz.catalog.model;


import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties("con")
@Getter
@Setter
public class DbSetting {

	private String userName;
	private String password;
	
	
}
