package com.benz.security.test.services;

import com.benz.security.test.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MyUserDetails implements UserDetails {

    private String userName;
    private String password;
    private boolean active;
    private Set<GrantedAuthority> authorities;

    public MyUserDetails(){}

    public MyUserDetails(String userName,String password,String active,Set<GrantedAuthority> authorities)
    {
        this.userName=userName;
        this.password=password;

        if(active.toLowerCase().equals("y"))
            this.active=true;
        else
            this.active=false;

        this.authorities=authorities;
    }

    public static MyUserDetails builder(User user)
    {
         Set<GrantedAuthority> authorities=user.getRoles().stream().map(role->new SimpleGrantedAuthority(role.getName().name()))
                 .collect(Collectors.toSet());

         return new MyUserDetails(user.getUserName(),user.getPassword(),user.getActive(),authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
