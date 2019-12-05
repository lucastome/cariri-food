package com.caririfood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.caririfood.domain.model.FormPayment;
import com.caririfood.domain.repository.FormPaymentRepository;

public class FormPaymentRepositoryImpl implements FormPaymentRepository{

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<FormPayment> list() {
		return this.manager.createQuery("from FormPayment", FormPayment.class).getResultList();
	}

	@Override
	public FormPayment findById(Long id) {
		return this.manager.find(FormPayment.class, id);
	}

	@Override
	public FormPayment save(FormPayment formPayment) {
		return this.manager.merge(formPayment);
	}

	@Override
	public void remove(FormPayment formPayment) {
		this.manager.remove(this.findById(formPayment.getId()));
	}

}
