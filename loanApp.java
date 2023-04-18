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
	// Jacob
	public static void addCustomer() throws SQLException{
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@h3oracle.ad.psu.edu:1521/orclpdb.ad.psu.edu", "jxb718", "Jakeawesome4202");
		PreparedStatement insertStmt = null;
		
		System.out.print("Please enter the customer's ID: ");
		Scanner inputId = new Scanner(System.in);
		int id = inputId.nextInt();
		
		System.out.print("Please enter the customer's name: ");
		Scanner inputName = new Scanner(System.in);
		String name = inputName.nextLine().trim();
		
		insertStmt = conn.prepareStatement("INSERT INTO Customer (ID, Name)" + "VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
		insertStmt.setInt(1, id);
		insertStmt.setString(2, name);
		insertStmt.executeUpdate();
	
	}
	// Jacob
	public static void editCustomer() throws SQLException{
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@h3oracle.ad.psu.edu:1521/orclpdb.ad.psu.edu", "jxb718", "Jakeawesome4202");
		Statement updateStmt = null;
		
		System.out.print("Please enter the customer's ID that you wish to edit: ");
		Scanner inputId = new Scanner(System.in);
		int id = inputId.nextInt();
		
		System.out.print("Please enter the customer's new name: ");
		Scanner inputName = new Scanner(System.in);
		String name = inputName.nextLine().trim();
		
		String query = "UPDATE Customer SET name ='" + name + "'WHERE ID =  '" + id + "'";
		
		updateStmt = conn.createStatement();
		updateStmt.executeUpdate(query);
		
	}
	// Jacob
	public static void removeCustomer() throws SQLException{
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@h3oracle.ad.psu.edu:1521/orclpdb.ad.psu.edu", "jxb718", "Jakeawesome4202");
		PreparedStatement deleteStmt1 = null;
		PreparedStatement deleteStmt2 = null;
		PreparedStatement deleteStmt3 = null;
		PreparedStatement deleteStmt4 = null;
		PreparedStatement deleteStmt5 = null;
		
		System.out.print("Please enter the customer's ID that you wish to remove: ");
		Scanner inputId = new Scanner(System.in);
		int id = inputId.nextInt();
		
		deleteStmt1 = conn.prepareStatement("DELETE FROM Customer WHERE ID = ?"); 
		deleteStmt2 = conn.prepareStatement("DELETE FROM Loan WHERE loan_ID = ?"); 
		deleteStmt3 = conn.prepareStatement("DELETE FROM Auto_Loan WHERE loan_ID = ?"); 
		deleteStmt4 = conn.prepareStatement("DELETE FROM Personal_Loan WHERE loan_ID = ?"); 
		deleteStmt5 = conn.prepareStatement("DELETE FROM Mortgage_Loan WHERE loan_ID = ?"); 
		
		deleteStmt1.setInt(1, id); 
		deleteStmt2.setInt(1, id); 
		deleteStmt3.setInt(1, id); 
		deleteStmt4.setInt(1, id); 
		deleteStmt5.setInt(1, id); 
		
		int res1, res2, res3, res4, res5;
		res1 = deleteStmt1.executeUpdate(); 
		res2 = deleteStmt2.executeUpdate(); 
		res3 = deleteStmt3.executeUpdate(); 
		res4 = deleteStmt4.executeUpdate();  
		res5 = deleteStmt5.executeUpdate(); 
		
		if(res1 == 0 && res2 == 0 && res3 == 0 && res4 == 0 && res5 == 0) System.out.println("The customer ID was not found. Please try again.");
		else System.out.println("The customer with an ID of " + id + " was removed from the system.");
		
	}
	//Jacob
	public static void searchCustomer() throws SQLException
	{
	Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@h3oracle.ad.psu.edu:1521/orclpdb.ad.psu.edu", "jxb718", "Jakeawesome4202");
        Statement stmt = conn.createStatement();
        System.out.println("Input ID of Customer you want to see information of: ");
        Scanner input3 = new Scanner(System.in);
		int inputs = input3.nextInt();
        ResultSet rs = stmt.executeQuery("SELECT ID, name FROM Customer WHERE ID =" + inputs); 

        while (rs.next()) {
           System.out.print("\nID: " + rs.getInt("ID"));
           System.out.print(", Name: " + rs.getString("name") + "\n");
        }
	}
	// Jacob
	public static void browseCustomer() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@h3oracle.ad.psu.edu:1521/orclpdb.ad.psu.edu", "jxb718", "Jakeawesome4202");
        	Statement stmt = conn.createStatement();
        	ResultSet rs = stmt.executeQuery("SELECT ID, name FROM Customer"); 

        	while (rs.next()) {
           		System.out.print("\nID: " + rs.getInt("ID"));
           		System.out.print(", Name: " + rs.getString("name") + "\n");
        	}
	}
	// Peter
	public static void addLoan() throws SQLException{}
	// Peter
	public static void editLoan() throws SQLException{}
	// Peter
	public static void removeLoan() throws SQLException{}
	// Peter 
	public static void searchLoan() throws SQLException
	{
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@h3oracle.ad.psu.edu:1521/orclpdb.ad.psu.edu", "jxb718", "Jakeawesome4202");
        Statement stmt = conn.createStatement();
        System.out.println("Input ID of Loan you want to see information of: ");
        Scanner input2 = new Scanner(System.in);
		int inpu = input2.nextInt();
		ResultSet rs = stmt.executeQuery("SELECT loan_ID, loan_amount, num_payments, interest_rate, amount_paid, start_date, end_date, loan_type FROM Loan WHERE loan_id =" + inpu); 
        while (rs.next()) {
           System.out.print("\nID: " + rs.getInt("ID"));
           System.out.print(", Name: " + rs.getString("name") + "\n");
           System.out.print("\nLoan ID: " + rs.getInt("loan_ID"));
           System.out.print("Loan Amount: " + rs.getDouble("loan_amount"));
           System.out.print("Number of Payment: " + rs.getInt("num_payments"));
           System.out.print("Interest Rate: " + rs.getDouble("interest_rate"));
           System.out.print("Amount Paid: " + rs.getDouble("amount_paid"));
           System.out.print("Start Date: " + rs.getString("start_date"));
           System.out.print("End Date: " + rs.getString("end_date"));
           System.out.print("Type of Loan: " + rs.getString("loan_type"));
           System.out.print("Loan Type: " + rs.getString("loan_type") + "\n");
           System.out.print("--------------------");
        }
	}
	// Tom
	public static void browseLoan() throws SQLException{
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@h3oracle.ad.psu.edu:1521/orclpdb.ad.psu.edu", "jxb718", "Jakeawesome4202");
        	Statement stmt = conn.createStatement();
        	ResultSet rs = stmt.executeQuery("SELECT loan_ID, loan_amount, num_payments, interest_rate, amount_paid, start_date, end_date, loan_type FROM Loan"); 

        	while (rs.next()) {
           		System.out.print("\nLoan ID: " + rs.getInt("loan_ID"));
           		System.out.print("Loan Amount: " + rs.getDouble("loan_amount"));
           		System.out.print("Number of Payment: " + rs.getInt("num_payments"));
           		System.out.print("Interest Rate: " + rs.getDouble("interest_rate"));
           		System.out.print("Amount Paid: " + rs.getDouble("amount_paid"));
           		System.out.print("Start Date: " + rs.getString("start_date"));
           		System.out.print("End Date: " + rs.getString("end_date"));
           		System.out.print("Type of Loan: " + rs.getString("loan_type"));
           		System.out.print("Loan Type: " + rs.getString("loan_type") + "\n");
           		System.out.print("--------------------");
        	}
	}
	
	public static void seeCustomerLoan() throws SQLException
	{
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@h3oracle.ad.psu.edu:1521/orclpdb.ad.psu.edu", "jxb718", "Jakeawesome4202");
        Statement stmt = conn.createStatement();
        System.out.println("Input ID of Customer you want to see loans of: ");
        Scanner input1 = new Scanner(System.in);
		int inp = input1.nextInt();
        ResultSet rs = stmt.executeQuery("SELECT Loan.loan_ID, Loan.loan_amount, Loan.num_payments, Loan.interest_rate, Loan.amount_paid, Loan.start_date, Loan.end_date, Loan.loan_type, Customer.ID, Customer.name FROM Loan INNER JOIN Customer ON Loan.loan_ID = Customer.ID WHERE ID = " + inp); 

        while (rs.next()) {
           System.out.print("\nID: " + rs.getInt("ID"));
           System.out.print(", Name: " + rs.getString("name") + "\n");
           System.out.print("\nLoan ID: " + rs.getInt("loan_ID"));
           System.out.print("Loan Amount: " + rs.getDouble("loan_amount"));
           System.out.print("Number of Payment: " + rs.getInt("num_payments"));
           System.out.print("Interest Rate: " + rs.getDouble("interest_rate"));
           System.out.print("Amount Paid: " + rs.getDouble("amount_paid"));
           System.out.print("Start Date: " + rs.getString("start_date"));
           System.out.print("End Date: " + rs.getString("end_date"));
           System.out.print("Type of Loan: " + rs.getString("loan_type"));
           System.out.print("Loan Type: " + rs.getString("loan_type") + "\n");
           System.out.print("--------------------");
        }
	}
	
	public static void exitProgram() {
		System.out.println("Thanks for using our Loan Management Application!");
		System.exit(0);
	}
}
