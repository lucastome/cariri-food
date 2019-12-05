package com.caririfood.domain.repository;

import java.util.List;

import com.caririfood.domain.model.Permission;

public interface PermissionRepository {

	public List<Permission> list();

	public Permission findById(Long id);

	public Permission save(Permission permission);

	public void remove(Permission permission);

}
