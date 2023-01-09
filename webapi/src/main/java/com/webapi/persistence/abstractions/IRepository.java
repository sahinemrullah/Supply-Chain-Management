package com.webapi.persistence.abstractions;

import java.util.ArrayList;

import com.webapi.domain.abstractions.BaseEntity;
import java.sql.SQLException;

public interface IRepository<T extends BaseEntity> {
	public boolean add(T entity) throws SQLException ;
	public void delete(T entity);
	public void update(T entity);
	public ArrayList<T> getAll();
}
