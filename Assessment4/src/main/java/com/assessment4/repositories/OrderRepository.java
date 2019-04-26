package com.assessment4.repositories;

import java.util.List;

import javax.persistence.criteria.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assessment4.entities.UserOrder;



public interface OrderRepository  extends JpaRepository<UserOrder,Integer> {

	UserOrder findByUserEmail(String email);


}
