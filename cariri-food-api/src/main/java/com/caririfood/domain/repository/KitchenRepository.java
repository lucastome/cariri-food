package com.caririfood.domain.repository;

import java.util.List;

import com.caririfood.domain.model.Kitchen;

public interface KitchenRepository {
	
	public List<Kitchen> list();

	public Kitchen findById(Long id);

	public Kitchen save(Kitchen kitchen);

	public void remove(Kitchen kitchen);
	
}
