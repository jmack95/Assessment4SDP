package com.assessment4.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment4.entities.Merchandise;
import com.assessment4.repositories.MerchandiseRepository;

@Service
public class MerchandiseService {

	@Autowired
	private MerchandiseRepository merchandiseRepository;
	
public List<Merchandise>showAllMerchandise(){
		
		List<Merchandise> merchandise = new ArrayList<>();
		merchandiseRepository.findAll()
		.forEach(merchandise::add);//method refrence
		return merchandise;
	}

	public void createMerchandise(Merchandise merchandise) {
		merchandiseRepository.save(merchandise);
	}

	public boolean isMerchandisePresent(Integer id) {
		Merchandise m=merchandiseRepository.findOne(id);
		if(m!=null)
			return true;
		
		return false;
	}
public void deleteTheMerchandise(int id) {
		
		merchandiseRepository.delete(id);
	}
	
	public List<Merchandise> findByTitleLike(String title) {
		return merchandiseRepository.findByTitleLike("%" + title + "%");
	}

	public Merchandise findById(int id) {
		return merchandiseRepository.findById(id);
	}

	

}