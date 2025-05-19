package com.bornfire.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.boot.jdbc.DataSourceBuilder;

public class ConnectionManager {
	Connection conn;
	public Connection getConnection() {
		try {
			//	Class.forName("oracle.jdbc.driver.OracleDriver");			
				//DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());				 
			//	System.out.println("1--- INSIDE CONNECTION MANAGER");

			DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
			dataSourceBuilder.driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
		        conn = DriverManager.getConnection("jdbc:oracle:thin:@103.11.152.132:1521/BDOC","BDOC","bdoc");
				
				
			//	System.out.println("INSIDE CONNECTION MANAGER");			
		}  catch (SQLException sqlexcp) {
			sqlexcp.printStackTrace();		
		} catch(Exception e){
			e.printStackTrace();
		}
		
		
		return conn;
	}
}
