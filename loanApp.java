import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class loanApp {
	
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
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(dbUrl, username, password);
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
	public static void browseCustomer() {}
	
	public static void addLoan() {}
	public static void editLoan() {}
	public static void removeLoan() {}
	public static void searchLoan() {}
	public static void browseLoan() {}
	
	public static void seeCustomerLoan() {}
	
}
