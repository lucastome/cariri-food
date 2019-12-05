package com.caririfood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.caririfood.domain.model.City;
import com.caririfood.domain.repository.CityRepository;

public class CityRepositoryImpl implements CityRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<City> list() {
		return this.manager.createQuery("from City", City.class).getResultList();
	}

	@Override
	public City findById(Long id) {
		return this.manager.find(City.class, id);
	}

	@Override
	public City save(City city) {
		return this.manager.merge(city);
	}

	@Override
	public void remove(City city) {
		this.manager.remove(this.findById(city.getId()));
	}

}
