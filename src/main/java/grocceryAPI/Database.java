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


/*
Make a query to obtain all the records whose date on the table are greater than 
//	11/1/2010 (November onwards) showing all the fields.

Make a query to obtain all records on the table whose date is less than 
	11/1/2010 (October to back) and its amount is greater than one hundred dollars, 
	showing all fields

Perform using SQL an update of those whose date is less than 
	11/15/2010 by modifying the date to the current date. Once the update has been made, can the affected records in the previous update have the amount modified? What should we have done?

Execute using the SQL a deletion of records whose amount is greater than 
	$200.

Can two records be completely alike? How could you differentiate them?


*/
public class Database {
	private static  final String url = "jdbc:mariadb://172.16.1.95/testing_database";	
	private static final String user = "pmauser";
	private static final String password = "password_here";
	
	
    Calendar calendar = Calendar.getInstance();			//initialize instance of a calendar(days, months, etc)
    java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
   
    public void database(){	}					//Blank  initialization
    
    public static ResultSet getMeal(int mealID) throws SQLException {
    	ResultSet rs = null;
    	String query = "SELECT * from meals where ID = ?";//SQL query template 
    	try (Connection conn = DriverManager.getConnection(url,user,password)){	//try the query
    		PreparedStatement preparedStmt = conn.prepareStatement(query);	//parse the query string template
    		preparedStmt.setInt(0, mealID);
    		rs = preparedStmt.executeQuery();				//execute the query
    		conn.close();
    	}
    	catch(Exception e){
    		System.err.println(e.getMessage());
    	}
		return rs;
    }
    
    
    public void insertMeal() {}
    public void deleteMeal() {}
    public void updateMeal() {}
 
    
}
