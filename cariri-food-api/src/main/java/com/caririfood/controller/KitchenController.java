package com.caririfood.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caririfood.domain.model.Kitchen;
import com.caririfood.infrastructure.sevice.KitchenService;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {

	@Autowired
	private KitchenService kitchenService;
	
	@GetMapping
	public List<Kitchen> list() {
		return this.kitchenService.list();
	}
	
	@GetMapping(value = "/{id}")
	public Kitchen findById(@PathVariable Long id) {
		return this.kitchenService.findById(id);
	}
	
}
