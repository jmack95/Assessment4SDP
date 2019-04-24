package com.assessment4.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assessment4.entities.Merchandise;


public interface MerchandiseRepository  extends JpaRepository<Merchandise, Integer> {

	List<Merchandise> findByTitleLike(String title); 
	List<Merchandise> findByManufacturerLike(String manufacturer); 
	List<Merchandise> findByCategoryLike(String category);
/*	Merchandise findByMerchandiseId(int id);*/
	Merchandise findById(int id);


}
