package com.caririfood.domain.repository;

import java.util.List;

import com.caririfood.domain.model.FormPayment;

public interface FormPaymentRepository {

	public List<FormPayment> list();

	public FormPayment findById(Long id);

	public FormPayment save(FormPayment formPayment);

	public void remove(FormPayment formPayment);

}
