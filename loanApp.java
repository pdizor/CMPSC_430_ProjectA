package sql430;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.*;
public class loanApp {
	static final String QUERY1 = "SELECT ID, name FROM Customer";

	public static void main(String args[]) {
		connectToDB();
		System.out.println("********************************************");
		System.out.println("Welcome to our Loan Management Application!");
		System.out.println("********************************************\n");
		boolean run = true;
		while(run) {
			System.out.println("Please enter a number to select an option.");
			System.out.println("-------------------------------------------");
			System.out.println("1: Add a Customer");
			System.out.println("2: Edit Customer Information");
			System.out.println("3: Remove a Customer");
			System.out.println("4: Search for a Customer");
			System.out.println("5: Browse List of Customers");
			System.out.println("6: Add a Loan");
			System.out.println("7: Edit Loan Information");
			System.out.println("8: Remove a Loan");
			System.out.println("9: Search for a Loan");
			System.out.println("10: Browse for a Loan");
			System.out.println("11: See a Customer Loan");
			System.out.println("12: Exit Program\n");
			System.out.println("Input:");
			Scanner input = new Scanner(System.in);
			int choice = input.nextInt();
			
			
			
			
			switch (choice) {
			case 1: addCustomer();
			break;
			case 2: editCustomer();
			break;
			case 3: removeCustomer();
			break;
			case 4: searchCustomer();
			break;
			case 5: browseCustomer();
			break;
			case 6: addLoan();
			break;
			case 7: editLoan();
			break;
			case 8: removeLoan();
			break;
			case 9: searchLoan();
			break;
			case 10: browseLoan();
			break;
			case 11: seeCustomerLoan();
			break;
			case 12: System.exit(0);
			break;
			}
		}
	}
	
	public static void connectToDB() {
		String dbUrl = "jdbc:oracle:thin:@h3oracle.ad.psu.edu:1521/orclpdb.ad.psu.edu"; 
		String username = "jxb718";
		String password = "Jakeawesome4202";
		
		
		try {
			Connection conn = DriverManager.getConnection(dbUrl, username, password);
			if(conn != null) System.out.println("You are connected!");
		}
		catch (SQLException error) {
			error.printStackTrace();
		}
	}

	
	public static void addCustomer() {}
	public static void editCustomer() {}
	public static void removeCustomer() {}
	public static void searchCustomer() {}
	public static void browseCustomer() 
	{
		String dbUrl = "jdbc:oracle:thin:@h3oracle.ad.psu.edu:1521/orclpdb.ad.psu.edu"; 
		String username = "jxb718";
		String password = "Jakeawesome4202";
	
		try(Connection conn = DriverManager.getConnection(dbUrl, username, password);
		         Statement stmt = conn.createStatement();
		         ResultSet rs = stmt.executeQuery(QUERY1);) {
		         // Extract data from result set
		         while (rs.next()) {
		            // Retrieve by column name
		            System.out.print("ID: " + rs.getInt("ID"));
		            System.out.print(", Name: " + rs.getInt("name"));
		            
		         }
		      } catch (SQLException e) {
		         e.printStackTrace();
		      } 
	
	}
	
	public static void addLoan() {}
	public static void editLoan() {}
	public static void removeLoan() {}
	public static void searchLoan() {}
	public static void browseLoan() {}
	
	public static void seeCustomerLoan() {}
	
}
