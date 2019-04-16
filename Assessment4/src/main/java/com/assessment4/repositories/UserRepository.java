package com.assessment4.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assessment4.entities.User;

public interface UserRepository  extends JpaRepository<User, String> {

	List<User> findByNameLike(String name); 

}
