package com.assessment4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assessment4.entities.Role;

public interface RoleRepository  extends JpaRepository<Role, String>{
 
}
