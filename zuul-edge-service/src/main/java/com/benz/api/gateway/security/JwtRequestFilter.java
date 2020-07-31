package com.benz.api.gateway.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{

	@Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authorizationHeader= request.getHeader("Authorization");
        
        System.out.println("Hello");
        System.out.println(authorizationHeader);

        String userName=null;
        String token=null;
        
        if(authorizationHeader!=null && authorizationHeader.startsWith("Benz "))
        {
            token=authorizationHeader.substring(5);
            
            userName=jwtUtil.extractUserName(token);

            if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null)
            {
                UserDetails userDetails=userDetailsService.loadUserByUsername(userName);

                  if(jwtUtil.validateToken(token,userDetails))
                  {
                      UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                              =new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

                      usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                      SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                  }
            }
        }
         filterChain.doFilter(request,response);
    }
}
