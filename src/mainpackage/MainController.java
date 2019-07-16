package mainpackage;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.sql.*;

public class MainController {

    @FXML
    private Button addPlayerButton;

    @FXML
    private Button viewDataButton;

    @FXML
    private TextField dataDisplayTextField;
	
	
    /////////////////////////////////////////////////////////////////////////////////////////
    //initializion block begin
    /////////////////////////////////////////////////////////////////////////////////////////
	public void initialize(){
		
		
		//Pulling in the data
		pullData();
		
	}
	/////////////////////////////////////////////////////////////////////////////////////////
	//initializion block ends
	/////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	/////////////////////////////////////////////////////////////////////////////////////////
	//Helper Methods Begin
	/////////////////////////////////////////////////////////////////////////////////////////
	public void pullData(){
		Connection c = null;
	    Statement stmt = null;
	      
	    try {
	       Class.forName("org.postgresql.Driver");
	       c = DriverManager
	          .getConnection("jdbc:postgresql://localhost:5432/nba_stats",
	          "postgres", "123");
	         
	       stmt = c.createStatement();
	       ResultSet rs = stmt.executeQuery( "SELECT * FROM person;" );
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
		
		
		this.addPlayerButton.setOnAction(event->{
			System.out.println("Button pressed");
		});
	}
	/////////////////////////////////////////////////////////////////////////////////////////
	//Helper Methods End
	/////////////////////////////////////////////////////////////////////////////////////////

	
	
}
