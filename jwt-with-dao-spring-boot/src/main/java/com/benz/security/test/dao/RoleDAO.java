package com.benz.security.test.dao;

import com.benz.security.test.entity.Role;
import com.benz.security.test.util.ERole;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDAO extends JpaRepository<Role,Integer>{

	 Optional<Role> findByName(ERole name);
}
