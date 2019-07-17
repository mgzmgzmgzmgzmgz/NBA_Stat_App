package mainpackage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.sql.*;
import java.util.Observable;

public class MainController {

    @FXML
    private GridPane gridPane;
	
	@FXML
    private Button addPlayerButton;

    @FXML
    private Button viewDataButton;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TableView<Player> playerTableView;

    @FXML
    private TableColumn<Player, String> firstNameColumn;

    @FXML
    private TableColumn<Player, String> lastNameColumn;
    
    private ObservableList<Player> playerList = FXCollections.observableArrayList();
	
	
    /////////////////////////////////////////////////////////////////////////////////////////
    //initializion block begin
    /////////////////////////////////////////////////////////////////////////////////////////
	public void initialize(){
		
		
		//Pulling in the data
		pullData();
		System.out.println(this.playerList.size());
		this.playerTableView.setItems(playerList);
		this.firstNameColumn.setCellValueFactory(rowData -> rowData.getValue().getFirstNameProperty());
		this.lastNameColumn.setCellValueFactory(rowData -> rowData.getValue().getLastNameProperty());
		
		//Saving the data
		this.addPlayerButton.setOnAction(event ->{
			saveData();
			pullData();
		});
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
	          
	          Player p = new Player(first_name, last_name);
	          this.playerList.add(p);
	            
	            System.out.println( "First Name = " + first_name );
	            System.out.println( "Last Name = " + last_name );
	            System.out.println("Size of player array " + playerList.size());
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
	
	public void saveData(){
	      Connection c = null;
	      Statement stmt = null;
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/nba_stats",
	            "postgres", "123");
	         System.out.println("Opened database successfully");
	         stmt = c.createStatement();
	         String sql = "INSERT INTO person (first_name, last_name) " + "VALUES (\'" +  
	         this.firstNameTextField.getText().toString() +
	        		 "\', \'" + this.lastNameTextField.getText().toString() + "\');";
	        		 
	         System.out.println(sql);
	         stmt.executeUpdate(sql);
	         stmt.close();
	         c.close();
	         System.out.println("Player added successfully");
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Table created successfully");
	      
	}
	/////////////////////////////////////////////////////////////////////////////////////////
	//Helper Methods End
	/////////////////////////////////////////////////////////////////////////////////////////

	
	
}
