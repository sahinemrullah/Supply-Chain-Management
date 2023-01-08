package com.webapi.persistence;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DatabaseConnection {

	public DatabaseConnection(String connectionStr) {

	}
	
	public static Connection getConntection() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/supplychainmanagement", "root", "root");
			return con;
		}
		catch(SQLException e) {
			return null;
		}
		catch(ClassNotFoundException e)
		{
			return null;
		}
	}
}
