package com.webapi.persistence.concretes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.webapi.application.utils.StringUtils;
import com.webapi.domain.entities.Retailer;
import com.webapi.persistence.DatabaseConnection;
import com.webapi.persistence.abstractions.IRetailerRepository;

public class RetailerRepository implements IRetailerRepository {

	public RetailerRepository() {

	}

	@Override
	public boolean add(Retailer entity) {
		Connection con = DatabaseConnection.getConntection();
		int rowAffected = 0;
		try {
			PreparedStatement statement = con.prepareStatement("INSERT INTO RETAILER(name, email, phone_number, password_hash) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			
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
	public void delete(Retailer entity) {

		
	}

	@Override
	public void update(Retailer entity) {

		
	}

	@Override
	public ArrayList<Retailer> getAll() {

		return null;
	}

	@Override
	public boolean retailerExists(String email) {
		Connection con = DatabaseConnection.getConntection();
		try {
			PreparedStatement statement = con.prepareStatement("SELECT email FROM RETAILER WHERE email = ?");
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
	public Retailer getRetailer(String email) {
		Connection con = DatabaseConnection.getConntection();
		try {
			PreparedStatement statement = con.prepareStatement("SELECT * FROM RETAILER WHERE email = ?");
			statement.setString(1, email);
			if(statement.execute()) {
				ResultSet result = statement.getResultSet();
				if(result.next()) {
					Retailer retailer = new Retailer();
					retailer.setId(result.getInt("retailer_id"));
					retailer.setEmail(result.getString("email"));
					retailer.setName(result.getString("name"));
					retailer.setPhoneNumber(result.getString("phone_number"));
					retailer.setPasswordHash(result.getString("password_hash"));
					return retailer;
				}
			}
		} catch (SQLException e) {
			return null;
		}
		return null;
	}
}
