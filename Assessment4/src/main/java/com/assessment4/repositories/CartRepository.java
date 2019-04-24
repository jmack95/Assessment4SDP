package com.assessment4.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assessment4.entities.Cart;
import com.assessment4.entities.Merchandise;


public interface CartRepository  extends JpaRepository<Cart,Long> {

	Cart findByUserEmail(String email);


}
