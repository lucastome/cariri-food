package com.caririfood.domain.repository;

import java.util.List;

import com.caririfood.domain.model.Restaurant;

public interface RestaurantRepository {

	public List<Restaurant> list();

	public Restaurant findById(Long id);

	public Restaurant save(Restaurant restaurant);

	public void remove(Restaurant restaurant);
	
}
