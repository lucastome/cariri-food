package com.caririfood.domain.repository;

import java.util.List;

import com.caririfood.domain.model.State;

public interface StateRepository {

	public List<State> list();

	public State findById(Long id);

	public State save(State state);

	public void delete(Long id);

}
