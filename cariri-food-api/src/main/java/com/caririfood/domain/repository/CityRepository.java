package com.caririfood.domain.repository;

import java.util.List;

import com.caririfood.domain.model.City;

public interface CityRepository {

	public List<City> list();

	public City findById(Long id);

	public City save(City city);

	public void remove(City city);
}
