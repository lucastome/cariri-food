package com.caririfood.domain.repository;

import java.util.List;

import com.caririfood.domain.model.Kitchen;
import org.springframework.dao.DataIntegrityViolationException;

public interface KitchenRepository {
	
	public List<Kitchen> list();

	public Kitchen findById(Long id);

	public Kitchen save(Kitchen kitchen);

	public void delete(Long id) throws DataIntegrityViolationException;

}
