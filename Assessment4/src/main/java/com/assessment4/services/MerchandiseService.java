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

	public List<Merchandise> showAllMerchandise() {

		List<Merchandise> merchandise = new ArrayList<>();
		merchandiseRepository.findAll().forEach(merchandise::add);// method refrence
		return merchandise;
	}

	public void createMerch(Merchandise merchandise) {

		merchandiseRepository.save(merchandise);
	}

	public List<Merchandise> findByTitle(String title) {
		// TODO Auto-generated method stub
		return merchandiseRepository.findByTitleLike("%" + title + "%");
	}

	public List<Merchandise> findByManufacturer(String manufacturer) {
		// TODO Auto-generated method stub
		return merchandiseRepository.findByManufacturerLike("%" + manufacturer + "%");
	}

	public List<Merchandise> findByCategory(String category) {
		// TODO Auto-generated method stub
		return merchandiseRepository.findByCategoryLike("%" + category + "%");
	}

	public boolean isMerchandisePresent(String title) {
		Merchandise m = merchandiseRepository.findOne(title);
		if (m != null)
			return true;

		return false;
	}

	public void deleteTheMerchandise(String title) {

		merchandiseRepository.delete(title);
	}
}
