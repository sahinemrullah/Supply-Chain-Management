package com.webapi.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.webapi.domain.abstractions.BaseEntity;

public final class SQLUtils {
	
	public static <TEntity extends BaseEntity> List<TEntity> executeQuery(String sql, Function<ResultSet, TEntity> mapFunc) throws SQLException {
		List<TEntity> list = new ArrayList<TEntity>();
		Connection con = DatabaseConnection.getConntection();
		Statement statement = con.createStatement();
		ResultSet result = statement.executeQuery(sql);
		while(result.next()) {
			list.add(mapFunc.apply(result));
		}
		con.close();
		return list;
	}
}
