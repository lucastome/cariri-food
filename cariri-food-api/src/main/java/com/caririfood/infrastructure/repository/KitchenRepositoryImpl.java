package com.caririfood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.caririfood.domain.model.Kitchen;
import com.caririfood.domain.repository.KitchenRepository;

@Component
public class KitchenRepositoryImpl implements KitchenRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Kitchen> list() {
		return this.manager.createQuery("from Kitchen", Kitchen.class).getResultList();
	}

	@Override
	public Kitchen findById(Long id) {
		Kitchen kitchen = this.manager.find(Kitchen.class, id);
		if(kitchen == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return kitchen;
	}
	
	@Transactional
	@Override
	public Kitchen save(Kitchen kitchen) {
		return this.manager.merge(kitchen);
	}

	@Transactional
	@Override
	public void delete(Long id) throws DataIntegrityViolationException {
		this.manager.remove(this.findById(id));
	}

}
