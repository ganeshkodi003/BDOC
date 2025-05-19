package com.bornfire.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
 
public class DatabaseConnector {
	
	  private static final String DB_URL = "jdbc:oracle:thin:@103.11.152.132:1521/BDOC"; // e.g., jdbc:oracle:thin:@//localhost:1521/orcl
	    private static final String USER = "BDOC";
	    private static final String PASSWORD = "bdoc";

	    public void insertData(List<String> data) {
	        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
	            String sql = "INSERT INTO att (emp_id) VALUES (?)";

	            try (PreparedStatement statement = connection.prepareStatement(sql)) {
	                for (String value : data) {
	                    System.out.println(value);
	                    statement.setString(1, value);
	                    statement.addBatch();
	                }
	                statement.executeBatch();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
}