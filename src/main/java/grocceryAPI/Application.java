package grocceryAPI;

import java.sql.*;
import java.util.Scanner;

public class Application{
	

	
	public static void main(String[] args ) throws SQLException{
		
		
		
		System.out.println(Database.createMeal(100.10, "Potatoe sacks", "waffle jam"));// create a new meal in the database
		

		
		ResultSet rs= Database.getMeal();		//get a meal(or all the meals, overloaded function) from the database
		while(rs.next()) {	//traverse the result set
			int mealID = rs.getInt("ID");			//String element = rs.getString('identifier);
			String expense = rs.getString("Name");
			String Description = rs.getString("Description");
			Double price = rs.getDouble("Price");
			System.out.println(mealID+ " "+expense+" "+Description+" "+price);

		}
	}	
	
}
