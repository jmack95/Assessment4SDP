package com.assessment4.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assessment4.entities.CartMerchandise;

public interface CartMerchandiseRepository extends JpaRepository<CartMerchandise,Integer> {

	CartMerchandise findByMerchandiseId(int id);

	List<CartMerchandise> findByCartId(int cartId);

	

}
