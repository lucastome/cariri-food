package com.caririfood.infrastructure.repository;

import com.caririfood.domain.model.City;
import com.caririfood.domain.repository.CityRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
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
