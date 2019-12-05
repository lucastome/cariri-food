package com.caririfood.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caririfood.domain.model.State;
import com.caririfood.infrastructure.sevice.StateService;

@RestController
@RequestMapping("/states")
public class StateController {

	@Autowired
	private StateService stateService;
	
	@GetMapping
	public List<State> list() {
		return this.stateService.list();
	}
}
