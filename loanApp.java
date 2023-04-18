import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class loanApp {
	
	public static void main(String args[]) throws SQLException {
		
		System.out.println("********************************************");
		System.out.println("Welcome to our Loan Management Application!");
		System.out.println("********************************************");
		boolean run = true;
		while(run) {
			System.out.println("\nPlease enter a number to select an option.");
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
			case 12: exitProgram();
			break;
			}
		}
	}
	
	public static void addCustomer() throws SQLException{
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@h3oracle.ad.psu.edu:1521/orclpdb.ad.psu.edu", "jxb718", "Jakeawesome4202");
		PreparedStatement insertStmt = null;
		
		insertStmt = conn.prepareStatement("INSERT INTO Customer (ID, Name)" + "VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
		insertStmt.setInt(1, 0000);
		insertStmt.setString(2, "John Doe");
		insertStmt.executeUpdate();
	
	}
	public static void editCustomer() throws SQLException{}
	public static void removeCustomer() throws SQLException{}
	public static void searchCustomer() throws SQLException{}
	public static void browseCustomer() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@h3oracle.ad.psu.edu:1521/orclpdb.ad.psu.edu", "jxb718", "Jakeawesome4202");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT ID, name FROM Customer"); 

        while (rs.next()) {
           System.out.print("\nID: " + rs.getInt("ID"));
           System.out.print(", Name: " + rs.getString("name") + "\n");
        }
	}
	
	public static void addLoan() throws SQLException{}
	public static void editLoan() throws SQLException{}
	public static void removeLoan() throws SQLException{}
	public static void searchLoan() throws SQLException{}
	public static void browseLoan() throws SQLException{}
	
	public static void seeCustomerLoan() throws SQLException{}
	
	public static void exitProgram() {
		System.out.println("Thanks for using our Loan Management Application!");
		System.exit(0);
	}
}
