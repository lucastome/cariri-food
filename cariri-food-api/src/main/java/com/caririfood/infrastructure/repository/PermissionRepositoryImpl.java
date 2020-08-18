package com.caririfood.infrastructure.repository;

import com.caririfood.domain.model.Permission;
import com.caririfood.domain.repository.PermissionRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class PermissionRepositoryImpl implements PermissionRepository{

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Permission> list() {
		return this.manager.createQuery("from Permission", Permission.class).getResultList();
	}

	@Override
	public Permission findById(Long id) {
		return this.manager.find(Permission.class, id);
	}

	@Transactional
	@Override
	public Permission save(Permission state) {
		return this.manager.merge(state);
	}

	@Transactional
	@Override
	public void remove(Permission state) {
		this.manager.remove(this.findById(state.getId()));
	}


}
