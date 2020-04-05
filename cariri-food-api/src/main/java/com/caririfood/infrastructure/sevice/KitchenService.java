package com.caririfood.infrastructure.sevice;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
	
	public Kitchen findById(Long id) {
		return this.kitchenRepository.findById(id);
	}

	public Kitchen save(Kitchen kitchen) {
		return this.kitchenRepository.save(kitchen);
	}

	public Kitchen update(Long id, Kitchen newKitchen, Kitchen oldKitchen) {
		BeanUtils.copyProperties(newKitchen, oldKitchen, "id");
		return this.kitchenRepository.save(oldKitchen);
	}

	public void delete(Long id) throws DataIntegrityViolationException{
		this.kitchenRepository.delete(id);
	}
	
}
