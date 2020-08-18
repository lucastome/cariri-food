package com.caririfood.infrastructure.repository;

import com.caririfood.domain.model.Restaurant;
import com.caririfood.domain.repository.RestaurantRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


@Repository
public class RestaurantRepositoryImpl implements RestaurantRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Restaurant> list() {
		return this.manager.createQuery("from Restaurant", Restaurant.class).getResultList();
	}

	@Override
	public Restaurant findById(Long id) {
		return this.manager.find(Restaurant.class, id);
	}

	@Transactional
	@Override
	public Restaurant save(Restaurant restaurant) {
		return this.manager.merge(restaurant);
	}

	@Transactional
	@Override
	public void remove(Restaurant restaurant) {
		this.manager.remove(this.findById(restaurant.getId()));
	}

}
