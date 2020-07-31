package com.benz.api.gateway.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	
	 @Value("${secret.key}")
     private String secretKey;

     @Value("${token.exp.time}")
     private long expirationInMs;
	
	public String extractUserName(String token)
    {
          return extractClaim(token,Claims::getSubject);
    }

    private boolean isTokenExpired(String token)
    {
       return extractTokenExpiration(token).before(new Date());
    }
    public Date extractTokenExpiration(String token)
    {
            return extractClaim(token,Claims::getExpiration);
    }

    public <T>T extractClaim(String token, Function<Claims,T> claimsResolver)
    {
          final  Claims claims=extractAllClaims(token);
          return  claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token)
    {
         return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public String generateToken(UserDetails userDetails)
    {
        Map<String,Object> claims=new HashMap<>();
        return createToken(claims,userDetails.getUsername());
    }

    private String createToken(Map<String,Object> claims,String subject)
    {
          return Jwts.builder().setClaims(claims).setSubject(subject)
                  .setIssuedAt(new Date(System.currentTimeMillis()))
                  .setExpiration(new Date(System.currentTimeMillis()+expirationInMs))
                  .signWith(SignatureAlgorithm.HS512,secretKey).compact();
    }

    public boolean validateToken(String token,UserDetails userDetails)
    {
          final String userName=extractUserName(token);
          return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
