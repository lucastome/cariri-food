package com.caririfood.infrastructure.sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caririfood.domain.model.State;
import com.caririfood.domain.repository.StateRepository;

@Service
public class StateService {

	@Autowired
	private StateRepository stateRepository;

	public List<State> list() {
		return this.stateRepository.list();
	}

}
