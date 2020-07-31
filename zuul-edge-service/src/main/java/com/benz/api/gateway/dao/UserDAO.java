package com.benz.api.gateway.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.benz.api.gateway.entity.User;

import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<User,Integer> {

     Optional<User> findByUserName(String userName);

     boolean existsByUserName(String userName);
}
