package com.webapi.persistence.abstractions;

import com.webapi.domain.abstractions.BaseEntity;
import java.sql.SQLException;
import java.util.List;

public interface IRepository<T extends BaseEntity> {

    public boolean add(T entity) throws SQLException;

    public void delete(T entity);

    public void update(T entity);

    public List<T> getAll(Integer[] ids) throws SQLException;
}
