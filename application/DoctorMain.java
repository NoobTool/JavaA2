package application;

import main.Doctor;
import main.Manager;
import java.util.*;
import javafx.util.Pair;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.geometry.*;
import CommonSnippets.*;

public class DoctorMain {
	
	CommonOperations co = new CommonOperations();
	Manager m = new Manager("Object to return patient list");
	
	public BorderPane addPrescription(Doctor d, BorderPane bp) {
		
		// Big Wrapper
		BorderPane wrapperPane = new BorderPane();
		wrapperPane.setPadding(new Insets(20,0,0,20));
		
		// Layouts
		GridPane searchGrid = new GridPane();
		Button idButton = new Button("Search by ID");
		Button nameButton = new Button("Search by Name");
		Button submitButton = new Button("Submit");
		TextField idField = new TextField();
		TextField nameField = new TextField();
		
		//Adding nodes in gridpane
		searchGrid.add(idButton, 1, 0);
		searchGrid.add(idField, 2, 0);
		searchGrid.add(nameButton, 1, 1);
		searchGrid.add(nameField, 2, 1);
		searchGrid.add(submitButton, 2, 2);
		searchGrid.add(co.addCancelButton(bp),3,2);
		
		// GridPane formatting
		searchGrid.setHgap(20);
		searchGrid.setVgap(20);
		idField.setVisible(false);
		nameField.setVisible(false);
		searchGrid.setPadding(new Insets(20,0,0,0));
		Label errorMsg = new Label();
		
		// errorMsg editing
		errorMsg.setFont(new Font("cambria",16));
		errorMsg.setTextFill(Color.RED);
		
		// Defining actions for the buttons
		
		idButton.setOnAction(e->{
			idField.setVisible(true);
			nameField.setVisible(false);
			nameField.setText("");
		});
		
		nameButton.setOnAction(e->{
			idField.setVisible(false);
			idField.setText("");
			nameField.setVisible(true);
		});
		
		
		submitButton.setOnAction(e->{
			
			if (!idField.isVisible() && !nameField.isVisible()){
				errorMsg.setText("Please select a search option.");
			}
			
			// Searching by ID
			else if(idField.isVisible()) {
				String id = idField.getText();
				try {
					if(!co.checkBlankFields(idField.getText()))
						errorMsg.setText("Please fill the ID field.");
					else if(d.doctorSearch(m.retPatientList(),Long.parseLong(id)
							,"").retName()==null)
						errorMsg.setText("Patient not found ");
					else {
						errorMsg.setText("Patient Found");
					}
				}catch(NumberFormatException exception) {
					errorMsg.setText("The ID must be in numberic characters!");
				}
			}
		});
		
		wrapperPane.setTop(searchGrid);
		wrapperPane.setBottom(errorMsg);
		wrapperPane.setAlignment(errorMsg, Pos.CENTER);
		
		return wrapperPane;
	}
	
}
