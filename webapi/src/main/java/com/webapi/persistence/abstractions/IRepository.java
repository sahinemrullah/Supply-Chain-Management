package com.webapi.persistence.abstractions;

import java.util.ArrayList;

import com.webapi.domain.abstractions.BaseEntity;

public interface IRepository<T extends BaseEntity> {
	public boolean add(T entity);
	public void delete(T entity);
	public void update(T entity);
	public ArrayList<T> getAll();
}
