package com.benz.api.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.benz.api.gateway.security.AuthEntryPointJwt;
import com.benz.api.gateway.security.JwtRequestFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	    @Autowired
	    private UserDetailsService userDetailsService;
	    
	    @Autowired
	    private AuthEntryPointJwt authEntryPointJwt;
	    
	    @Autowired
	    private JwtRequestFilter jwtRequestFilter;

	
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
	
	    @Bean
	   public AuthenticationProvider authenticationProvider()
	   {
	       DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
	       provider.setUserDetailsService(userDetailsService);
	       provider.setPasswordEncoder(new BCryptPasswordEncoder());
	       return provider;
	   }
	
	
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {

	        http.csrf().disable()
	                .exceptionHandling().authenticationEntryPoint(authEntryPointJwt).and()
	                .authorizeRequests()
	                .antMatchers("/api/web/auth/**").permitAll()
	                .antMatchers("/api/web/home/all").permitAll()
	                .antMatchers("/api/web/home/user").hasAnyRole("USER","ADMIN")
	                .antMatchers("/api/web/home/admin").hasRole("ADMIN")
	                .antMatchers("/api/catalog/**").hasAnyRole("USER","ADMIN")
	                .anyRequest().authenticated()
	                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	      http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	    }
	    
	    @Override
	    @Bean
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }

}
