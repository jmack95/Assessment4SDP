package com.assessment4.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assessment4.entities.Merchandise;
import com.assessment4.entities.User;

public interface MerchandiseRepository  extends JpaRepository<Merchandise, String> {

	List<Merchandise> findByTitleLike(String title); 
	List<Merchandise> findByManufacturerLike(String manufacturer); 
	List<Merchandise> findByCategoryLike(String category); 

}
