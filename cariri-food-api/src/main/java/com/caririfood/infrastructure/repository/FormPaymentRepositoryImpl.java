package com.caririfood.infrastructure.repository;

import com.caririfood.domain.model.FormPayment;
import com.caririfood.domain.repository.FormPaymentRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
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

	@Transactional
	@Override
	public FormPayment save(FormPayment formPayment) {
		return this.manager.merge(formPayment);
	}

	@Transactional
	@Override
	public void remove(FormPayment formPayment) {
		this.manager.remove(this.findById(formPayment.getId()));
	}

}
