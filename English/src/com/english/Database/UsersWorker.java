package com.english.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsersWorker {
	
	public static void Insert(String email, String password) {//tested
		
		try {

			String insert = "INSERT INTO english.user(user_email, user_password)" + "VALUES(?, ?)";

			Connection conn = DBHandler.connect();

			PreparedStatement ps = conn.prepareStatement(insert);
			ps.setString(1, email);
			ps.setString(2, password);
			ps.executeUpdate();
			
			DBHandler.close(conn);

		} catch (Exception ex) {
			
			Logger.getLogger(UsersWorker.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	  public static List<String> GetUsers() {//tested

	      List<String> list = new ArrayList<String>();

	      try {

	    	  Connection conn = DBHandler.connect();

	          Statement stmt = conn.createStatement();

	          ResultSet result = stmt.executeQuery("SELECT * FROM english.user");

	          while(result.next())
	          {
	             list.add(result.getString("user_id"));
	             list.add(result.getString("user_email"));
	             list.add(result.getString("user_password"));
	          } 

	          DBHandler.close(conn);

	      } catch (Exception ex) {
	    	  
	          Logger.getLogger(UsersWorker.class.getName()).log(Level.SEVERE, null, ex);
	      }
	      
	      return list;
	  }
	  
	  public static List<String> GetUser(int id) {//not tested

	      List<String> list = new ArrayList<String>();

	      try {
	    	  
	    	  Connection conn = DBHandler.connect();

	          Statement stmt = conn.createStatement();

	          ResultSet result = stmt.executeQuery("SELECT * FROM english.user WHERE user_id=" + id);

	          result.next();//dodac ifa
	          
              list.add(result.getString("user_id"));
              list.add(result.getString("user_email"));
              list.add(result.getString("user_password")); 

	          DBHandler.close(conn);

	      } catch (Exception ex) {
	    	  
	          Logger.getLogger(UsersWorker.class.getName()).log(Level.SEVERE, null, ex);
	      }
	      
	      return list;
	  }
	  
	  public static int GetUserByEmail(String email) {//not tested

	      try {
	    	  
	    	  Connection conn = DBHandler.connect();

	          Statement stmt = conn.createStatement();

	          ResultSet result = stmt.executeQuery("SELECT * FROM english.user WHERE user_email='" + email +"';");

	          if(result.next()) {
	          
	        	  int id = Integer.parseInt(result.getString("user_id"));
	        	  DBHandler.close(conn);
	        	  
	              return id; 
	          }

	      } catch (Exception ex) {
	    	  
	          Logger.getLogger(UsersWorker.class.getName()).log(Level.SEVERE, null, ex);
	      }
	      
	      return -1;
	  }
	  
	  public static boolean UpdateUser(int id, String email, String password) {//not tested

	      try {
	    	  
			Connection conn = DBHandler.connect();
			String sqlStr = "UPDATE english.user SET user_email= ?, user_password= ? WHERE user_id= ?;";
			
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			pstmt.setInt(3, id);
			
			pstmt.executeUpdate();
			DBHandler.close(conn);
			return true;
			
	      } catch (Exception ex) {
	    	  
			Logger.getLogger(UsersWorker.class.getName()).log(Level.SEVERE, null, ex);
			return false;
	      }
	  }
	  
	  public static ResultSet GetResultSet(String query) {//not tested

	      try {

	    	  Connection conn = DBHandler.connect();
	          Statement stmt = conn.createStatement();
	          ResultSet result = stmt.executeQuery(query);
	          //DBHandler.close(conn);
	          return result;

	      } catch (Exception ex) {
	    	  
	          Logger.getLogger(UsersWorker.class.getName()).log(Level.SEVERE, null, ex);
	      }
	      return null;
	  }
	  
	  public static void Delete(String user_id) {//not tested, not used
		  
	      try {
	          String delete = "DELETE from english.user WHERE user_id = ?";

	          Connection conn = DBHandler.connect();
	          PreparedStatement ps = conn.prepareStatement(delete);

	          ps.setString(1, user_id);
	          ps.executeUpdate();
	          
	          DBHandler.close(conn);

	      } catch (Exception ex) {
	    	  
	          Logger.getLogger(UsersWorker.class.getName()).log(Level.SEVERE, null, ex);
	      }
	  }
}
