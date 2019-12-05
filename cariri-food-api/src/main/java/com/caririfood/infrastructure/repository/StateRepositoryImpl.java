package com.caririfood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.caririfood.domain.model.State;
import com.caririfood.domain.repository.StateRepository;

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

	@Override
	public State save(State state) {
		return this.manager.merge(state);
	}

	@Override
	public void remove(State state) {
		this.manager.remove(this.findById(state.getId()));
	}

}
