package mainpackage;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Player {
	
	public Player(String firstName, String lastName){
		firstNameProperty = new SimpleStringProperty();
		lastNameProperty = new SimpleStringProperty();
		
		this.firstNameProperty.set(firstName);
		this.lastNameProperty.set(lastName);
	}

	
	private StringProperty firstNameProperty;
	private StringProperty lastNameProperty;
	
	public void setFirstNameProperty(String s){
		firstNameProperty.set(s);
	}
	public StringProperty getFirstNameProperty(){
		return this.firstNameProperty;
	}
	public void setLastNameProperty(String s){
		lastNameProperty.set(s);
	}
	public StringProperty getLastNameProperty(){
		return this.lastNameProperty;
	}
	
	
	
}
