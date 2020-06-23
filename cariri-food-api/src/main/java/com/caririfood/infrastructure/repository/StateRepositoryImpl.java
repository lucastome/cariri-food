package com.caririfood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.caririfood.domain.model.State;
import com.caririfood.domain.repository.StateRepository;
import org.springframework.transaction.annotation.Transactional;

@Component
public class StateRepositoryImpl implements StateRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<State> list() {
		return this.manager.createQuery("from State", State.class).getResultList();
	}

	@Override
	public State findById(Long id) {
		return this.manager.find(State.class, id);
	}

	@Transactional
	@Override
	public State save(State state) {
		return this.manager.merge(state);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		this.manager.remove(this.findById(id));
	}

}
