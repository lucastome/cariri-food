package com.caririfood.infrastructure.sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caririfood.domain.model.Kitchen;
import com.caririfood.domain.repository.KitchenRepository;

@Service
public class KitchenService {

	@Autowired
	private KitchenRepository kitchenRepository;
	
	public List<Kitchen> list() {
		return this.kitchenRepository.list();
	}
	
}
