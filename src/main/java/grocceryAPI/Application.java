package grocceryAPI;

import java.sql.*;
import java.util.Scanner;

public class Application{
	static Scanner scanner = new Scanner(System.in);
	//Money
	static double budget = 1250; //$
	static int days = 30;	//per shopping trip
	static int people = 5;	//people in this budget
	static double dailyBudget =  (budget /days)/people;	//calculated daily budget
	//food/calories
	static int calories = 2500; //kcal
	static int meals = 3;		//HOW MANY MEALS PER DAY
	static int snacks = 2; 	//how many snacks per day

	//logic
	static double totalMealCost = dailyBudget *.90;		//total meal budget
	static double totalSnackCost = dailyBudget *.10;	//total snack budget

	static double perMealCost = totalMealCost /meals;	//cost per meal serving
	static double perSnackCost =  totalSnackCost / snacks;	//cost per snack serving
	static int ID;
	static Double price;
	
	public static void main(String[] args ) throws SQLException{
		clearScreen();
		
	//TODO : incorporate callories in to calculation and return a groccery list that is appropriate System.out.println("")

	//Main 
		System.out.println("Budget: $"+ budget + " per " +days +" days");				// month budget and days between shopping
		System.out.println("Daily budget: $"+ dailyBudget + ", People : " + people);		// Daily budget, and people budget is accounting for
		
		System.out.println("Per individual meal cost: $" +perMealCost);
		System.out.println("Per snack cost $"+ perSnackCost );

	//Menu
		while (1==1) {
			System.out.println();
			System.out.print("Add, Delete, Update, View... Selection: ");
			String x = scanner.nextLine();
			
			switch (x) {
			case "Add":
				addMeal();
				break;
				
			case "Delete":
				ID = scanner.nextInt();
				deleteMeal(ID);
				break;
				
			case "Update":
				System.out.println("ID: ");
				 ID = scanner.nextInt();
				System.out.println("price: ");
				price  = scanner.nextDouble();
				updateMealPrice(ID, price);
				break;
				
			case "View":
				System.out.println("All or budget? :");
				x = scanner.nextLine();

				switch(x) {	
				case ("All"):
					getMeals();
					break;
				case ("budget"):
					getBudgetMeals();
					break;
				}
				break;
			}
		
		}//End Menu
		
		
		//TODO:Look in database for recipies that have a total cost per serving <= perMealCost and enough calories.		
	}
	//UpdateMeal functions
	public static void updateMealName(int ID, String food) throws SQLException {
		Database.updateMealName(ID,food);
	}
	public static void updateMealDesc(int ID, String desc) throws SQLException {
		Database.updateMealDesc(ID, desc);
	}
	public static void updateMealPrice( int ID, double price ) throws SQLException {
		Database.updateMealCost(ID,price);
	}
	

	
	public static void deleteMeal(int mealID) throws SQLException {
		Database.deleteMeal(mealID);
	}

	public static void addMeal() throws SQLException {
		//get meal info and update database with it
		System.out.print("Price: ");
		Double price = scanner.nextDouble();

		scanner.nextLine();
		System.out.print("Name: ");
		String name = scanner.nextLine();
		
		System.out.print("Description: ");
		String description = scanner.nextLine();
		
		Database.createMeal(price, name, description);		// create a new meal in the database
	}
	public static void getBudgetMeals(){
		ResultSet rs;
		try {
			rs = Database.getMeal(perMealCost);
			while(rs.next()) {	//traverse the result set
				int mealID = rs.getInt("ID");			//String element = rs.getString('identifier);
				String expense = rs.getString("Name");
				String Description = rs.getString("Description");
				Double mealPrice = rs.getDouble("Price");
				System.out.println("ID: "+ mealID+ ", "+expense+", "+Description+", "+mealPrice);
			}
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
	//TODO get a meal(or all the meals, overloaded function) from the database
		
	}
	public static void getMeals(){
		ResultSet rs;
		try {
			rs = Database.getMeal();
			while(rs.next()) {	//traverse the result set
				int mealID = rs.getInt("ID");			//String element = rs.getString('identifier);
				String expense = rs.getString("Name");
				String Description = rs.getString("Description");
				Double mealPrice = rs.getDouble("Price");
				System.out.println("ID: "+ mealID+ ", "+expense+", "+Description+", "+mealPrice);
			}
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
	//TODO get a meal(or all the meals, overloaded function) from the database
		
	}
	
	
	//end anything important 
    public static void clearScreen() {	//Used to clear terminal
    System.out.print("\033[H\033[2J");
    System.out.flush();
    }
	
}
