package grocceryAPI;

import java.sql.*;
import java.util.Scanner;

public class Application{
	
	public static void main(String[] args ) throws SQLException{
		double budget = 125; //$
		int days = 14;	//per shopping trip
		int people = 1;	//people in this budget
		double dailyBudget =  (budget /days)/people;	//calculated daily budget
		System.out.println(dailyBudget + ", People : " + people);	
		int calories = 2500; //kcal
		int meals = 3;		//HOW MANY MEALS PER DAY
		int snacks = 2; 	//how many snacks per day
	
		double totalMealCost = dailyBudget *.90;		//total meal budget
		double totalSnackCost = dailyBudget *.10;	//total snack budget

		double perMealCost = totalMealCost /meals;	//cost per meal serving
		double perSnackCost =  totalSnackCost / snacks;	//cost per snack serving
		
		
		System.out.println(perMealCost);	
		System.out.println("Add, Delete, Update, View... Selection: ");
		Scanner scanner = new Scanner(System.in);
		String x = scanner.nextLine();
		switch (x) {
		case "Add":
			Database.createMeal(1.10, "Chicken wings", "Just chicken wings");		// create a new meal in the database
			break;
			
		case "Delete":
			Database.deleteMeal(0);
			break;
			
		case "Update":
			Database.updateMealName(1,"Food");
			break;
			
		case "View":
			ResultSet rs= Database.getMeal(perMealCost);		//get a meal(or all the meals, overloaded function) from the database
			while(rs.next()) {	//traverse the result set
				int mealID = rs.getInt("ID");			//String element = rs.getString('identifier);
				String expense = rs.getString("Name");
				String Description = rs.getString("Description");
				Double price = rs.getDouble("Price");
				System.out.println(mealID+ " "+expense+" "+Description+" "+price);
			}
			break;
		}
		

		//Look in database for recipies that have a total cost per serving <= perMealCost and enough calories.

		
		
	}	
}
