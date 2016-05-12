import java.sql.*;
import java.util.Scanner;

public class JDBCTest {

	public static void main(String[] args) {
		
		// create a scanner so we can read the command-line input
		Scanner scanner1 = new Scanner(System.in);//Nya grejer

		// prompt for numerical value
		System.out.print("Enter a HighRate HR: ");

		// get the int
		int theHR = scanner1.nextInt();
		int theEANID = 0;
		
		

		
		try {
			 Class.forName("com.mysql.jdbc.Driver").newInstance();
		}
		catch(java.lang.ClassNotFoundException ex) {
			System.err.println("Error: unable to load driver class!");
			return;
		}
		catch(java.lang.IllegalAccessException ex) {
				System.err.println("Error: access problem while loading!");
				return;
				
		}
		catch(java.lang.InstantiationException ex) {
				System.err.println("Error: unable to instantiate driver!");
				return;
		}
		finally
		{
			System.err.println("Error: unknown!");
			
		}
		Connection con = null;
	
		String databaseURL = "jdbc:mysql://localhost:3306/hotel"; 
		String user = "root";
		String password = "";
	
		try {
						
				
            con = DriverManager.getConnection(databaseURL, user, password);
            
		}
		catch (java.sql.SQLException sqe) {
			System.err.println("Could not connect to database.");
			return;	
		}
		finally
		{
			System.err.println("OK?");
			
		}
		
		System.out.println("From hotel database");
		try {
			String query = "SELECT EANHotelID, Name, propertycurrency " 
							+ "FROM property "
							+ "WHERE propertycurrency = 'USD'"
							+ "AND highrate > " + String.valueOf(theHR)
							+ " LIMIT 20";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			System.out.println("EANHotelID: " + 
											rs.getString("EANHotelID") +
											", Name: " +
											rs.getString("Name") +
											
										    
											".");
		}
		rs.close();
		
		//Input start
		
		// create a scanner so we can read the command-line input
		Scanner scanner = new Scanner(System.in);

		// prompt for numerical value
		System.out.print("Enter an EANHotelID from the result: ");

		// get the int
		theEANID = scanner.nextInt();

		//System.out.println(String.format("%s, your age is %d", something, theEANID));
		String query2 = "UPDATE property SET highrate = highrate + 100 "  
							
							+ "WHERE EANHotelID = " + String.valueOf(theEANID); 
		stmt.executeUpdate(query2);					
		
		stmt.close();
		
	}
	catch (java.sql.SQLException sqe){
		System.err.println("There was a JDBC problem.");
		return;
	}
	finally
		{
			System.err.println("No other Errors known!");
							
			//System.out.println(String.format("HR yo %d, and EANHotelID was %d", theHR, theEANID));

				
		}
	
		
		
	try {
		con.close();
	}
	catch (java.sql.SQLException e) {
		System.err.println("Could not close.");
		return;
	}	
	finally
	{
			System.err.println("NO Errors known!");
			
	}
  }
}	
