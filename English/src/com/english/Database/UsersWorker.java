package com.english.Database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
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
	
	public static void AddUser
	(
		String u_name, 
		String u_surname, 
		String u_country, 
		String u_city, 
		int u_english_level, 
		String u_skype, 
		boolean u_sex, 
		String a_email, 
		String a_password
	) {
		try {
			/*  DB:
			 * 	u_name VARCHAR(30), 
				u_surname VARCHAR(30), 
				u_country VARCHAR(30),
				u_city VARCHAR(30),
				u_english_level int,
				u_skype VARCHAR(30),
				u_sex boolean,
				a_email VARCHAR(30),
				a_password VARCHAR(30)
			 */
		Connection conn = DBHandler.connect();
		CallableStatement pstat = conn.prepareCall("{ ? = call f_addUser(" + "\"" + u_name + "\", \"" + u_surname + "\", \""+ u_country + "\", \"" + u_city + "\", " + u_english_level + ", \"" + u_skype + "\", " + u_sex + ", \"" + a_email + "\", \""+ a_password + "\")}");
		pstat.registerOutParameter(1, Types.BOOLEAN);
		pstat.execute();
		DBHandler.close(conn);

		} catch (Exception ex) {
			
			Logger.getLogger(UsersWorker.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	  public static List<String> GetAllUsers() {//tested

	      List<String> list = new ArrayList<String>();

	      try {

	    	  Connection conn = DBHandler.connect();

	          Statement stmt = conn.createStatement();

	          ResultSet result = stmt.executeQuery("SELECT * FROM english.t_user");

	          while(result.next())
	          {
	             list.add(result.getString("user_id"));
	             list.add(result.getString("user_name"));
	             list.add(result.getString("user_surname"));
	             list.add(result.getString("user_country"));
	             list.add(result.getString("user_city"));
	             list.add(result.getString("user_english_level"));
	             list.add(result.getString("user_skype"));
	             list.add(result.getString("user_sex"));
	          } 

	          DBHandler.close(conn);

	      } catch (Exception ex) {
	    	  
	          Logger.getLogger(UsersWorker.class.getName()).log(Level.SEVERE, null, ex);
	      }
	      
	      return list;
	  }

	  public static List<String> GetUserAndAccount(int user_id) {//not tested

	      List<String> list = new ArrayList<String>();

	      try {
	    	  
	    	  Connection conn = DBHandler.connect();

	          Statement stmt = conn.createStatement();

	          ResultSet result = stmt.executeQuery("SELECT * FROM english.t_user JOIN english.t_account ON english.t_user.user_id=english.t_account.account_user_id and english.t_user.user_id=" + user_id);

	          result.next();//dodac ifa
	          
              list.add(result.getString("user_id"));
              list.add(result.getString("user_name"));
              list.add(result.getString("user_surname"));
              list.add(result.getString("user_country"));
              list.add(result.getString("user_city"));
              list.add(result.getString("user_english_level"));
              list.add(result.getString("user_skype"));
              list.add(result.getString("user_sex"));
	          
              list.add(result.getString("account_id"));
              list.add(result.getString("account_email"));
              list.add(result.getString("account_password"));
              list.add(result.getString("account_registration_date"));
              
	          DBHandler.close(conn);

	      } catch (Exception ex) {
	    	  
	          Logger.getLogger(UsersWorker.class.getName()).log(Level.SEVERE, null, ex);
	      }
	      
	      return list;
	  }
	  
	  public static List<String> GetUserAndAccount(String account_email) {//not tested

	      List<String> list = new ArrayList<String>();

	      try {
	    	  
	    	  Connection conn = DBHandler.connect();

	          Statement stmt = conn.createStatement();

	          ResultSet result = stmt.executeQuery("SELECT * FROM english.t_user JOIN english.t_account ON english.t_user.user_id=english.t_account.account_user_id and english.t_account.account_email='" + account_email + "';");

	          result.next();//dodac ifa
	          
              list.add(result.getString("user_id"));
              list.add(result.getString("user_name"));
              list.add(result.getString("user_surname"));
              list.add(result.getString("user_country"));
              list.add(result.getString("user_city"));
              list.add(result.getString("user_english_level"));
              list.add(result.getString("user_skype"));
              list.add(result.getString("user_sex"));
	          
              list.add(result.getString("account_id"));
              list.add(result.getString("account_email"));
              list.add(result.getString("account_password"));
              list.add(result.getString("account_registration_date"));
              
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

	          ResultSet result = stmt.executeQuery("SELECT * FROM english.t_account WHERE account_email='" + email +"';");

	          if(result.next()) {
	          
	        	  int id = Integer.parseInt(result.getString("account_user_id"));
	        	  DBHandler.close(conn);
	        	  
	              return id; 
	          }

	      } catch (Exception ex) {
	    	  
	          Logger.getLogger(UsersWorker.class.getName()).log(Level.SEVERE, null, ex);
	      }
	      
	      return -1;
	  }
	  
	  public static boolean UpdateUser(int userId, String userName, String userSurname, String userCountry, String userCity, int userEnglishLevel, String userSkype, boolean userSex, String accountEmail, String accountPassword) {//not tested

	      try {
	    	  
			Connection conn = DBHandler.connect();
			String sqlStr = "UPDATE english.t_user, english.t_account SET user_name= ?, user_surname= ?, user_country= ?, user_city= ?, user_english_level= ?, user_skype= ?, user_sex= ?, account_email= ?, account_password= ? WHERE english.t_user.user_id = english.t_account.account_id AND user_id= ?;";
			
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			
			pstmt.setString(1, userName);
			pstmt.setString(2, userSurname);
			pstmt.setString(3, userCountry);
			pstmt.setString(4, userCity);
			pstmt.setInt(5, userEnglishLevel);
			pstmt.setString(6, userSkype);
			pstmt.setBoolean(7, userSex);
			pstmt.setString(8, accountEmail);
			pstmt.setString(9, accountPassword);
			pstmt.setInt(10, userId);
			
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
	  
	  public static void DeleteUser(String user_id) {//not tested, not used
		  
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
	  
		public static void Usun
		(
			String u_name, 
			String u_surname, 
			String u_country, 
			String u_city, 
			int u_english_level, 
			String u_skype, 
			boolean u_sex, 
			String a_email, 
			String a_password
		) {
			//boolean successfulOperation = false;
			try {
				/*
				 * 	u_name VARCHAR(30), 
					u_surname VARCHAR(30), 
					u_country VARCHAR(30),
					u_city VARCHAR(30),
					u_english_level int,
					u_skype VARCHAR(30),
					u_sex boolean,
					a_email VARCHAR(30),
					a_password VARCHAR(30)
				 */
			Connection conn = DBHandler.connect();
			CallableStatement pstat = conn.prepareCall("{ ? = call f_addUser(" + "\"" + u_name + "\", \"" + u_surname + "\", \""+ u_country + "\", \"" + u_city + "\", " + u_english_level + ", \"" + u_skype + "\", " + u_sex + ", \"" + a_email + "\", \""+ a_password + "\")}");
			pstat.registerOutParameter(1, Types.BOOLEAN);
			pstat.execute();
			//successfulOperation = pstat.getBoolean(1);
			//System.out.println(successfulOperation);
			DBHandler.close(conn);

			} catch (Exception ex) {
				
				Logger.getLogger(UsersWorker.class.getName()).log(Level.SEVERE, null, ex);
				//return false;
			}
			//return successfulOperation ? true : false;	//Not possible-throw an exception
		}
}
