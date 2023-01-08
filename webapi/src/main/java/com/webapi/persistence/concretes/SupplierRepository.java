package com.webapi.persistence.concretes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.webapi.application.utils.StringUtils;
import com.webapi.domain.entities.Supplier;
import com.webapi.persistence.DatabaseConnection;
import com.webapi.persistence.abstractions.ISupplierRepository;

public class SupplierRepository implements ISupplierRepository {

	public SupplierRepository() {

	}

	@Override
	public boolean add(Supplier entity) {
		Connection con = DatabaseConnection.getConntection();
		int rowAffected = 0;
		try {
			PreparedStatement statement = con.prepareStatement("INSERT INTO SUPPLIER(name, email, phone_number, password_hash) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, entity.getName());
			statement.setString(2, entity.getEmail());
			statement.setString(3, entity.getPhoneNumber());
			statement.setString(4, entity.getPasswordHash());
			
			rowAffected = statement.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		
		return rowAffected == 1;
	}

	@Override
	public void delete(Supplier entity) {

		
	}

	@Override
	public void update(Supplier entity) {

		
	}

	@Override
	public ArrayList<Supplier> getAll() {

		return null;
	}

	@Override
	public boolean supplierExists(String email) {
		Connection con = DatabaseConnection.getConntection();
		try {
			PreparedStatement statement = con.prepareStatement("SELECT email FROM SUPPLIER WHERE email = ?");
			statement.setString(1, email);
			if(statement.execute()) {
				ResultSet result = statement.getResultSet();
				if(result.next()) {
					return StringUtils.isEqual(email, result.getString("email"));
				}
			}
		} catch (SQLException e) {
			return false;
		}
		return false;
	}

	@Override
	public Supplier getSupplier(String email) {
		Connection con = DatabaseConnection.getConntection();
		try {
			PreparedStatement statement = con.prepareStatement("SELECT * FROM SUPPLIER WHERE email = ?");
			statement.setString(1, email);
			if(statement.execute()) {
				ResultSet result = statement.getResultSet();
				if(result.next()) {
					Supplier supplier = new Supplier();
					supplier.setId(result.getInt("supplier_id"));
					supplier.setEmail(result.getString("email"));
					supplier.setName(result.getString("name"));
					supplier.setPhoneNumber(result.getString("phone_number"));
					supplier.setPasswordHash(result.getString("password_hash"));
					return supplier;
				}
			}
		} catch (SQLException e) {
			return null;
		}
		return null;
	}
}
