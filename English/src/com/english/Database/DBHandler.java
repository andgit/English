package com.english.Database;

import java.sql.*;

public class DBHandler {

	public static Connection connect() {
		
		try {
			
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/";
			String schema = "english";
			String user = "root";
			String password = "root";
			
			Class.forName(driver).newInstance();
			return DriverManager.getConnection(url + schema, user, password);
		} catch(Exception e) {
			
			throw new Error(e);
		}
	}
	
	public static boolean close(Connection conn) {
		
		try {
			
			conn.close();
			return true;
		} catch(Exception e) {
			
			return false;
		}
	}
}
