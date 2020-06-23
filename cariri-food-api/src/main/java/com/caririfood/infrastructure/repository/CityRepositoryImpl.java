package com.caririfood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.caririfood.domain.model.City;
import com.caririfood.domain.repository.CityRepository;
import org.springframework.transaction.annotation.Transactional;

@Component
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

	@Transactional
	@Override
	public City save(City city) {
		return this.manager.merge(city);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		this.manager.remove(this.findById(id));
	}

}
