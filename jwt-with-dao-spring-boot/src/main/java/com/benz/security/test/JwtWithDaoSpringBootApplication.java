package com.benz.security.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class JwtWithDaoSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtWithDaoSpringBootApplication.class, args);
    }

}
