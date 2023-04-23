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
			System.out.println("1:  Add a Customer");
			System.out.println("2:  Edit Customer Information");
			System.out.println("3:  Remove a Customer");
			System.out.println("4:  Search for a Customer");
			System.out.println("5:  Browse List of Customers");
			System.out.println("6:  Add a Loan");
			System.out.println("7:  Make a Loan Payment");
			System.out.println("8:  Remove a Loan");
			System.out.println("9:  Search for a Loan");
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
			case 7: makePayment();
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

	public static void removeCustomer() throws SQLException{

		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@h3oracle.ad.psu.edu:1521/orclpdb.ad.psu.edu", "jxb718", "Jakeawesome4202");
		PreparedStatement deleteStmt1 = null;
		PreparedStatement deleteStmt2 = null;

		System.out.print("Please enter the customer's ID that you wish to remove: ");
		Scanner inputId = new Scanner(System.in);
		int id = inputId.nextInt();

		deleteStmt1 = conn.prepareStatement("DELETE FROM Customer WHERE ID = ?"); 
		deleteStmt2 = conn.prepareStatement("DELETE FROM Loan WHERE customer_ID = ?"); 

		deleteStmt1.setInt(1, id); 
		deleteStmt2.setInt(1, id); 

		int res1, res2;
		res1 = deleteStmt1.executeUpdate(); 
		res2 = deleteStmt2.executeUpdate(); 

		if(res1 == 0 && res2 == 0) System.out.println("The customer ID was not found. Please try again.");
		else System.out.println("The customer with an ID of " + id + " was removed from the system.");

	}

	public static void searchCustomer() throws SQLException{

		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@h3oracle.ad.psu.edu:1521/orclpdb.ad.psu.edu", "jxb718", "Jakeawesome4202");
		Statement stmt = conn.createStatement();
		System.out.println("Input the ID of customer you want to see information of: ");
		Scanner input = new Scanner(System.in);
		int inputs = input.nextInt();
		ResultSet rs = stmt.executeQuery("SELECT ID, name FROM Customer WHERE ID =" + inputs); 

		System.out.println("               Customer Info               ");
		System.out.println("-------------------------------------------");
		while (rs.next()) System.out.print("ID: " + rs.getInt("ID") + "        Name: " + rs.getString("name") + "\n");

	}

	public static void browseCustomer() throws SQLException {

		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@h3oracle.ad.psu.edu:1521/orclpdb.ad.psu.edu", "jxb718", "Jakeawesome4202");
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT ID, name FROM Customer"); 

		System.out.println("                  Customers                ");
		System.out.println("-------------------------------------------");
		while (rs.next())  System.out.print("ID: " + rs.getInt("ID") + "        Name: " + rs.getString("name") + "\n");


	}

	public static void addLoan() throws SQLException{

		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@h3oracle.ad.psu.edu:1521/orclpdb.ad.psu.edu", "jxb718", "Jakeawesome4202");
		PreparedStatement insertStmt = null;

		Scanner input = new Scanner(System.in);

		System.out.print("Please enter the Customer ID: ");
		int customer_ID = input.nextInt();

		System.out.print("Please enter the loan ID: ");
		int loan_ID = input.nextInt();

		System.out.print("Please enter the loan amount (USD): ");
		double loan_amount = input.nextDouble();

		System.out.print("Please enter the number of payments: ");
		int num_payments = input.nextInt();

		System.out.print("Please enter the interest rate (as a decimal): ");
		double interest_rate = input.nextDouble();

		System.out.print("Please enter the amount paid (USD): ");
		double amount_paid = input.nextDouble();

		System.out.println("Please enter the start date (MM/DD/YYYY): ");
		String start_date = input.next().trim();

		System.out.println("Please enter the end date (MM/DD/YYYY): ");
		String end_date = input.next().trim();

		System.out.print("Please enter the loan type (1 for Auto, 2 for Mortgage, or 3 for Personal): ");
		int input_loan_type = input.nextInt();

		String loan_type = "";
		if (input_loan_type == 1) loan_type = "Auto";
		else if (input_loan_type == 2) loan_type = "Mortgage";
		else if (input_loan_type == 3) loan_type = "Personal";

		insertStmt = conn.prepareStatement("INSERT INTO Loan " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
		insertStmt.setInt(1, loan_ID);
		insertStmt.setInt(2, customer_ID);
		insertStmt.setDouble(3, loan_amount);
		insertStmt.setInt(4, num_payments);
		insertStmt.setDouble(5, interest_rate);
		insertStmt.setDouble(6, amount_paid);
		insertStmt.setString(7, start_date);
		insertStmt.setString(8, end_date);
		insertStmt.setString(9, loan_type);
		insertStmt.executeUpdate();

		if (input_loan_type == 1) addLoanAuto(loan_ID);
		else if (input_loan_type == 2) addLoanMortgage(loan_ID);
		else if (input_loan_type == 3) addLoanPersonal(loan_ID);

	}


	public static void makePayment() throws SQLException{

		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@h3oracle.ad.psu.edu:1521/orclpdb.ad.psu.edu", "jxb718", "Jakeawesome4202");
		Statement updateStmt = null;

		Scanner input = new Scanner(System.in);

		System.out.println("Please enter in the loan_ID: ");
		int loan_ID = input.nextInt();

		System.out.println("Please enter in the payment amount (USD): ");
		double payment_amount = input.nextDouble();

		String query1 = "UPDATE Loan SET amount_paid = amount_paid+" + payment_amount + " WHERE loan_ID =  " + loan_ID;
		String query2 = "UPDATE Loan SET num_payments = num_payments + 1 WHERE loan_ID =  " + loan_ID;

		updateStmt = conn.createStatement();
		int res1 = updateStmt.executeUpdate(query1);
		int res2 = updateStmt.executeUpdate(query2);

		if(res1 == 1 && res2 == 1) {
			System.out.println("A $" + payment_amount + " payment has been made to loan " + loan_ID + ".");
		}
		else {
			System.out.println("The loan ID was not found. Please try again.");
		}

	}

	public static void removeLoan() throws SQLException{

		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@h3oracle.ad.psu.edu:1521/orclpdb.ad.psu.edu", "jxb718", "Jakeawesome4202");
		PreparedStatement deleteStmt1 = null;

		System.out.print("Please enter the loan's ID that you wish to remove: ");
		Scanner inputId = new Scanner(System.in);
		int id = inputId.nextInt();

		deleteStmt1 = conn.prepareStatement("DELETE FROM Loan WHERE loan_ID = ?"); 

		deleteStmt1.setInt(1, id); 

		int res1 = deleteStmt1.executeUpdate(); 

		if(res1 == 0) System.out.println("The loan ID was not found. Please try again.");
		else System.out.println("The loan with an ID of " + id + " was removed from the system.");

	}

	public static void searchLoan() throws SQLException{

		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@h3oracle.ad.psu.edu:1521/orclpdb.ad.psu.edu", "jxb718", "Jakeawesome4202");
		Statement stmt = conn.createStatement();
		System.out.println("Input ID of Loan you want to see information of: ");
		Scanner input2 = new Scanner(System.in);
		int inpu = input2.nextInt();
		ResultSet rs = stmt.executeQuery("SELECT loan_ID, loan_amount, num_payments, interest_rate, amount_paid, start_date, end_date, loan_type FROM Loan WHERE loan_id =" + inpu); 
		while (rs.next()) {
			System.out.println("                  Loan " + rs.getInt("loan_ID"));
			System.out.println("-----------------------------------------------");
			System.out.print("Loan Amount: " + rs.getDouble("loan_amount") + "     ");
			System.out.print("  Number of Payment: " + rs.getInt("num_payments" )+ "     \n");
			System.out.print("Interest Rate: " + rs.getDouble("interest_rate") + "     ");
			System.out.print("   Amount Paid: " + rs.getDouble("amount_paid") + "     \n");
			System.out.print("Start Date: " + rs.getString("start_date") + "     ");
			System.out.print("End Date: " + rs.getString("end_date") + "     \n");
			System.out.print("Loan Type: " + rs.getString("loan_type") + "\n");
			System.out.println("-----------------------------------------------");
		}

	}

	public static void browseLoan() throws SQLException{
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@h3oracle.ad.psu.edu:1521/orclpdb.ad.psu.edu", "jxb718", "Jakeawesome4202");
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT loan_ID, loan_amount, num_payments, interest_rate, amount_paid, start_date, end_date, loan_type FROM Loan"); 


		while (rs.next()) {
			System.out.println("                  Loan " + rs.getInt("loan_ID"));
			System.out.println("-----------------------------------------------");
			System.out.print("Loan Amount: " + rs.getDouble("loan_amount") + "     ");
			System.out.print("  Number of Payment: " + rs.getInt("num_payments" )+ "     \n");
			System.out.print("Interest Rate: " + rs.getDouble("interest_rate") + "     ");
			System.out.print("   Amount Paid: " + rs.getDouble("amount_paid") + "     \n");
			System.out.print("Start Date: " + rs.getString("start_date") + "     ");
			System.out.print("End Date: " + rs.getString("end_date") + "     \n");
			System.out.print("Loan Type: " + rs.getString("loan_type") + "\n");
			System.out.println("-----------------------------------------------");
		}
	}

	public static void seeCustomerLoan() throws SQLException {

		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@h3oracle.ad.psu.edu:1521/orclpdb.ad.psu.edu", "jxb718", "Jakeawesome4202");
		Statement stmt = conn.createStatement();
		System.out.println("Input ID of Customer you want to see loans of: ");
		Scanner input1 = new Scanner(System.in);
		int inp = input1.nextInt();
		ResultSet rs = stmt.executeQuery("SELECT Loan.loan_ID, Loan.loan_amount, Loan.num_payments, Loan.interest_rate, Loan.amount_paid, Loan.start_date, Loan.end_date, Loan.loan_type, Customer.ID, Customer.name FROM Loan INNER JOIN Customer ON Loan.customer_id = Customer.ID WHERE ID =" + inp); 

		while (rs.next()) {
			System.out.println("                  Loan " + rs.getInt("loan_ID"));
			System.out.println("-----------------------------------------------");
			System.out.print("Customer Name: " + rs.getString("name") + "     ");
			System.out.print(" Customer ID: " + rs.getInt("ID" )+ "     \n");
			System.out.print("Loan Amount: " + rs.getDouble("loan_amount") + "     ");
			System.out.print("  Number of Payment: " + rs.getInt("num_payments" )+ "     \n");
			System.out.print("Interest Rate: " + rs.getDouble("interest_rate") + "     ");
			System.out.print("   Amount Paid: " + rs.getDouble("amount_paid") + "     \n");
			System.out.print("Start Date: " + rs.getString("start_date") + "     ");
			System.out.print("End Date: " + rs.getString("end_date") + "     \n");
			System.out.print("Loan Type: " + rs.getString("loan_type") + "\n");
			System.out.println("-----------------------------------------------");
		}

	}

	public static void addLoanAuto (int loan_ID) throws SQLException
	{
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@h3oracle.ad.psu.edu:1521/orclpdb.ad.psu.edu", "jxb718", "Jakeawesome4202");
		PreparedStatement insertStmt = null;

		Scanner input = new Scanner(System.in);



		System.out.print("Please Enter the Make of the Vehicle: ");
		String make = input.nextLine().trim();

		System.out.print("Please Enter the Model of the Vehicle: ");
		String model = input.nextLine().trim();

		System.out.print("Please enter the Manufacture Year YYYY: ");
		String year = input.nextLine().trim();

		insertStmt = conn.prepareStatement("INSERT INTO Auto_Loan " + "VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
		insertStmt.setInt(1, loan_ID);
		insertStmt.setString(2, make);
		insertStmt.setString(3, model);
		insertStmt.setString(4, year);
		insertStmt.executeUpdate();
	}

	public static void addLoanMortgage(int loan_ID) throws SQLException {

		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@h3oracle.ad.psu.edu:1521/orclpdb.ad.psu.edu", "jxb718", "Jakeawesome4202");
		PreparedStatement insertStmt = null;

		Scanner input = new Scanner(System.in);

		System.out.print("Please enter the house address: ");
		String house_address = input.nextLine().trim();

		System.out.print("Please enter the house area: ");
		int house_area = input.nextInt();

		System.out.print("Please enter the number of bedrooms: ");
		int num_bedrooms = input.nextInt();

		System.out.print("Please enter the number of bathrooms: ");
		int num_bathrooms = input.nextInt();

		System.out.print("Please enter the price of the house (USD): ");
		double house_price = input.nextDouble();

		insertStmt = conn.prepareStatement("INSERT INTO Mortgage_Loan " + "VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
		insertStmt.setInt(1, loan_ID);
		insertStmt.setString(2, house_address);
		insertStmt.setInt(3, house_area);
		insertStmt.setInt(4, num_bedrooms);
		insertStmt.setInt(5, num_bathrooms);
		insertStmt.setDouble(6, house_price);
		insertStmt.executeUpdate();

	}
	public static void addLoanPersonal(int loan_ID) throws SQLException
	{
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@h3oracle.ad.psu.edu:1521/orclpdb.ad.psu.edu", "jxb718", "Jakeawesome4202");
		PreparedStatement insertStmt = null;

		Scanner input = new Scanner(System.in);

		System.out.print("Please Enter the purpose for the Loan: ");
		String purpose = input.nextLine();

		insertStmt = conn.prepareStatement("INSERT INTO Personal_Loan " + "VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
		insertStmt.setInt(1, loan_ID);
		insertStmt.setString(2, purpose);
		insertStmt.executeUpdate();
	}

	public static void exitProgram() {

		System.out.println("Thanks for using our Loan Management Application!");
		System.exit(0);

	}

}
