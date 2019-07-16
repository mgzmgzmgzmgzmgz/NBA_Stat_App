package mainpackage;

import java.sql.*;

public class DBConnect {
	   public static void main(String args[]) {
		   
		      Connection c = null;
		      Statement stmt = null;
		      
		      try {
		         Class.forName("org.postgresql.Driver");
		         c = DriverManager
		            .getConnection("jdbc:postgresql://localhost:5432/nba_stats",
		            "postgres", "123");
		         
		         stmt = c.createStatement();
		         ResultSet rs = stmt.executeQuery( "SELECT * FROM name;" );
		         while ( rs.next() ) {
		            String  first_name = rs.getString("first_name");
		            String  last_name = rs.getString("last_name");
		            
		            System.out.println( "First Name = " + first_name );
		            System.out.println( "Last Name = " + last_name );
		            System.out.println();
		         }
		         rs.close();
		         stmt.close();
		         c.close();
		      } catch (Exception e) {
		         e.printStackTrace();
		         System.err.println(e.getClass().getName()+": "+e.getMessage());
		         System.exit(0);
		      }
		      System.out.println("Opened database successfully");
		   }
}
