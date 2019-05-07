package grocceryAPI;

import java.sql.*;
import java.util.Scanner;

public class Application{
	

	
	public static void main(String[] args ) throws SQLException{
	ResultSet rs= 	Database.getMeal(0);
		if(rs.next()) {	//traverse the result set
			int mealID = rs.getInt("ID");			//String element = rs.getString('identifier);
			String expense = rs.getString("Name");
			String Description = rs.getString("Description");
			Double price = rs.getDouble("Price");
			
		}
		System.out.println();
	}	
}
	


