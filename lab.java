package sql;

import java.sql.*;

public class lab {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/test";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "password";

	public static void main(String[] args) {
		
		
		Connection connection = null;
		Statement statement = null;
		
		
		try {
			//STEP 2: Register JDBC driver 
			Class.forName(JDBC_DRIVER);
			
			//STEP 3: Open a connection
			System.out.println("Connecting to database...");
			connection = DriverManager.getConnection(DB_URL,USER,PASS);
			
			//STEP 4: Execute a query 
			System.out.println("\n\nCreating statement...\n\n");
			statement = connection.createStatement();
			String sql;
			sql = "SELECT * FROM books";
			ResultSet rs = statement.executeQuery(sql);
			
			//STEP 5: Extract data from result set 
			while(rs.next()) {
				//Retrieve column by name
				int isbn = rs.getInt("isbn");
				String author = rs.getString("author");
				String title = rs.getString("title");
				
				//Display values 
				System.out.print("ISBN: " + isbn);
				System.out.print("\t\tAuthor: " + author);
				System.out.println("\t\tTitle: " + title);
			}
			
			//STEP 6: Clean-up environment
			rs.close();
			statement.close();
			connection.close();
			
			
		} catch (SQLException sqle) {
			// TODO: handle exception
			sqle.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			//close resources
			try {
				if(statement != null) connection.close();
			} catch (SQLException se) {
				// TODO: handle exception
				se.printStackTrace();
			} // end finally
		} // end try
		 System.out.println("\n\nGoodbye!");

	}// end main
}
