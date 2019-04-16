package com.assessment4.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.assessment4.entities.Merchandise;
import com.assessment4.repositories.MerchandiseRepository;




public class MerchandiseService {
	@Autowired
	private MerchandiseRepository merchandiseRepository;
public List<Merchandise>showAllMerchandise(){
		
		List<Merchandise> merchandise = new ArrayList<>();
		merchandiseRepository.findAll()
		.forEach(merchandise::add);//method refrence
		return merchandise;
	}
	
	public void createMerch(Merchandise merchandise) {
		
		merchandiseRepository.save(merchandise);
	}


	public List<Merchandise> findByTitle(String title) {
		// TODO Auto-generated method stub
		return merchandiseRepository.findByTitleLike("%"+title+"%");
	}


	public boolean isMerchandisePresent(String title) {
		Merchandise m=merchandiseRepository.findOne(title);
		if(m!=null)
			return true;
		
		return false;
	}
	
	public void deleteTheMerchandise(String title) {
		
		merchandiseRepository.delete(title);
	}
}
