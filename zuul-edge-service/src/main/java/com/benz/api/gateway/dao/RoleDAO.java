package com.benz.api.gateway.dao;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.benz.api.gateway.entity.Role;
import com.benz.api.gateway.util.ERole;

@Repository
public interface RoleDAO extends JpaRepository<Role,Integer>{

	 Optional<Role> findByName(ERole name);
}
