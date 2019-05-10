package grocceryAPI;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Calendar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class Database {
	private static  final String url = "jdbc:mariadb://172.16.1.95/testing_database";	
	private static final String user = "pmauser";
	private static final String password = "password_here";
	

    Calendar calendar = Calendar.getInstance();			//initialize instance of a calendar(days, months, etc)
    java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
   
    public void database(){	}					//Blank  initialization
    
    
    
    
    
    //////////////////////////////////////////////////RESTFUL interface for meals////////////
    public static ResultSet getMeal() throws SQLException {
    	ResultSet rs = null;
    	String query = "SELECT * from meals where ID ";//SQL query template 
    	try (Connection conn = DriverManager.getConnection(url,user,password)){	//try the query
    		PreparedStatement preparedStmt = conn.prepareStatement(query);	//parse the query string template
    		rs = preparedStmt.executeQuery();				//execute the query
    		conn.close();
    	}
    	catch(Exception e){
    		System.err.println(e.getMessage());
    	}
		return rs;
    }
    //Overloaded function
    public static ResultSet getMeal(int mealID) throws SQLException {											//REST API GET
    	ResultSet rs = null;
    	String query = "SELECT * from meals where ID  = ? ";//SQL query template 
    	try (Connection conn = DriverManager.getConnection(url,user,password)){	//try the query
    		PreparedStatement preparedStmt = conn.prepareStatement(query);	//parse the query string template
    		preparedStmt.setInt(1, mealID);
    		rs = preparedStmt.executeQuery();				//execute the query
    		conn.close();
    	}
    	catch(Exception e){
    		System.err.println(e.getMessage());
    	}
		return rs;
    }
    //Overloaded function
    public static ResultSet getMeal(Double price) throws SQLException {											//REST API GET
    	ResultSet rs = null;
    	String query = "SELECT * from meals where price  <= ? ";//SQL query template 
    	try (Connection conn = DriverManager.getConnection(url,user,password)){	//try the query
    		PreparedStatement preparedStmt = conn.prepareStatement(query);	//parse the query string template
    		preparedStmt.setDouble(1, price);
    		rs = preparedStmt.executeQuery();				//execute the query
    		conn.close();
    	}
    	catch(Exception e){
    		System.err.println(e.getMessage());
    	}
		return rs;
    }
    
    public static boolean createMeal( double Expense, String Name, String Description) throws SQLException { 	//REST API CREATE
    	String query = "INSERT INTO meals (Name, Description, Price) VALUES(?,?,?) ";
    	try(Connection conn = DriverManager.getConnection(url, user, password)){
    		PreparedStatement preparedStmt = conn.prepareStatement(query);
    		preparedStmt.setDouble(3, Expense);
    		preparedStmt.setString(1,Name);
    		preparedStmt.setString(2, Description);
    		preparedStmt.execute();
    		conn.close();
    		return true;
    	}
    	catch(Exception e) {
    		System.err.println(e.getMessage());
    	}
    	return false;
    }
    			
    public static boolean deleteMeal(int mealID) throws SQLException {											//REST API DELETE 
    	String query = "DELETE from meals where ID = ?";
    	
    	try(Connection conn = DriverManager.getConnection(url,user,password)){
    		PreparedStatement preparedStmt = conn.prepareStatement(query);
    		preparedStmt.setInt(1, mealID);
    		preparedStmt.execute();
    		conn.close();
    		return true;
    	
    	}
    	catch(Exception e) {
    		System.err.println(e.getMessage());
   		
    	}
		return false;
    }
    public static boolean updateMealName(int mealID, String name ) throws SQLException {
    	String query = "UPDATE meals set Name = ? where ID = ?";
    	try(Connection conn = DriverManager.getConnection(url,user,password)){
    		PreparedStatement preparedStmt = conn.prepareStatement(query);
    		preparedStmt.setInt(2,mealID);
    		preparedStmt.setString(1, name);
    		preparedStmt.execute();
    		conn.close();
    		return true;
    		
    	}
    	catch(Exception e) {
    		System.err.println(e.getMessage());
    	}
    	return false;
    }
    public static boolean updateMealDesc(int mealID, String Desc ) throws SQLException {
    	String query = "UPDATE meals set Description = ? where ID = ?";
    	try(Connection conn = DriverManager.getConnection(url,user,password)){
    		PreparedStatement preparedStmt = conn.prepareStatement(query);
    		preparedStmt.setInt(2,mealID);
    		preparedStmt.setString(1, Desc);
    		preparedStmt.execute();
    		conn.close();
    		return true;
    		
    	}
    	catch(Exception e) {
    		System.err.println(e.getMessage());
    	}
    	return false;
    }
    public static boolean updateMealCost(int mealID, Double cost) throws SQLException {
    	String query = "UPDATE meals set Price = ? where ID = ?";
    	try(Connection conn = DriverManager.getConnection(url,user,password)){
    		PreparedStatement preparedStmt = conn.prepareStatement(query);
    		preparedStmt.setInt(2,mealID);
    		preparedStmt.setDouble(1, cost);
    		preparedStmt.execute();
    		conn.close();
    		return true;
    		
    	}
    	catch(Exception e) {
    		System.err.println(e.getMessage());
    	}
    	return false;
    }
    public static boolean updateMealCal(int mealID, Double cal) throws SQLException {
    	String query = "UPDATE meals set Calories = ? where ID = ?";
    	try(Connection conn = DriverManager.getConnection(url,user,password)){
    		PreparedStatement preparedStmt = conn.prepareStatement(query);
    		preparedStmt.setInt(2,mealID);
    		preparedStmt.setDouble(1, cal);
    		preparedStmt.execute();
    		conn.close();
    		return true;
    		
    	}
    	catch(Exception e) {
    		System.err.println(e.getMessage());
    	}
    	return false;
    }
   public static ResultSet getMeal(double budget, int  cal) {
	   String query = "Select * from meals where Calories  >= ? and Price <= ? ";
	   try(Connection conn = DriverManager.getConnection(url,user,password)){
		   PreparedStatement preparedStmt = conn.prepareStatement(query);
		   preparedStmt.setInt(1,cal);
		   preparedStmt.setDouble(2, budget);
		   ResultSet rs = preparedStmt.executeQuery();
		   conn.close();
		   return rs;
		   /*	try (Connection conn = DriverManager.getConnection(url,user,password)){	//try the query
   		PreparedStatement preparedStmt = conn.prepareStatement(query);	//parse the query string template
   		rs = preparedStmt.executeQuery();				//execute the query
   		conn.close(); */
   	}
	   catch (Exception e) {
		   System.err.println(e.getMessage());
	   }
	return null;
	   
   }
    /////////END RESTFUL INTERFACE FOR MEAL/////////////////////////////////////////////////////////////
    
}
