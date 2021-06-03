package application;
import main.Manager;
import main.Nurse;
import main.Patient;
import prescription.MedicineBlock;
import prescription.MedicineDose;
import javafx.stage.*;
import javafx.scene.*;

import java.util.ArrayList;
import java.util.Objects;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class NurseMain {
	
	CommonOperations co = new CommonOperations();
	Manager m = new Manager("Object to return patientlist");
	static Scene scene=null;
	Patient p;
	
	public void patientSearch(Nurse n, BorderPane bp, String purpose) {
		// Big Wrapper
		BorderPane wrapperPane = new BorderPane();
		wrapperPane.setPadding(new Insets(20,0,0,20));
		
		// Layouts
		GridPane searchGrid = new GridPane();
		Button idButton = new Button("Search by ID");
		Button nameButton = new Button("Search by Name");
		TextField idField = new TextField();
		TextField nameField = new TextField();
		HBox buttonHolder = co.addButtonHolder(bp);
		
		//Adding nodes in grid pane
		searchGrid.add(idButton, 1, 0);
		searchGrid.add(idField, 2, 0);
		searchGrid.add(nameButton, 1, 1);
		searchGrid.add(nameField, 2, 1);
		searchGrid.add(buttonHolder, 2, 2);
		
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
		
		wrapperPane.setTop(searchGrid);
		
		((Button)buttonHolder.getChildren().get(0)).setOnAction(e->{
			
			if (!idField.isVisible() && !nameField.isVisible())
				errorMsg.setText("Please select a search option.");
			
			// Searching by ID
			else if(idField.isVisible()) {
				String id = idField.getText();
				try {
					if(!co.checkBlankFields(idField.getText()))
						errorMsg.setText("Please fill the ID field.");
					else {
						p =  n.nurseSearch(m.retPatientList(),Long.parseLong(id)
								,"");
						if(purpose=="Administer")
							administerMedicine(n,p,bp);
						else
							displayPatientDetails(p,bp);
						
					}
				}catch(NumberFormatException exception) {
					errorMsg.setText("The ID must be in numberic characters!");
				}
			}
		});	
		
		bp.setCenter(wrapperPane);
		bp.setBottom(errorMsg);
	}
	
	
	public void administerMedicine(Nurse n, Patient p, BorderPane bp) {
		
		Label errorMsg = co.retErrorLabel();
		
		if(!Objects.isNull(p.retPrescription())) {
			MedicineBlock medicineBlock = p.retPrescription().retMedicineBlock();
			ArrayList<MedicineDose> medicines = medicineBlock.retMedicines();
			
			// Layout Elements
			VBox wrapperBox = new VBox(30);
			HBox inputBox = new HBox(80);
			Label administerMsg = new Label("Choose the medicine to be administered");
			
			ComboBox<String> medicineBox = new ComboBox<String>();
			HBox buttonHolder = co.addButtonHolder(bp);
			
			// Adding strings in comboBox
			for(MedicineDose md: medicines)
				medicineBox.getItems().add(md.retName());
			
			((Button)buttonHolder.getChildren().get(0)).setOnAction(e->{
				int medicineIndex = medicineBox.getSelectionModel().getSelectedIndex();
				String returnValue = n.administerMedicine(p, medicines.get(medicineIndex));
				
				if(returnValue=="")
					errorMsg.setText(" Successfully administered "+
							medicines.get(medicineIndex).retName());
				else
					errorMsg.setText(returnValue);
				
			});
			
			
			// Adding elements in inputBox
			inputBox.getChildren().addAll(administerMsg,medicineBox);
			
			// Adding and formatting elements in wrapperBox
			wrapperBox.getChildren().addAll(inputBox,buttonHolder);
			wrapperBox.setPadding(new Insets(30,0,0,80));
			
			bp.setCenter(wrapperBox);
		}
		
		
		bp.setBottom(errorMsg);
		
	}
	
	public void displayMap() {
		Stage primaryStage = new Stage();
		HBox map = new HBox();
		Manager m = new Manager("To return patients list");
		WardMap wm = new WardMap(m.retPatientList());
		map = wm.retMap();
		for(Patient p:m.retPatientList())
			System.out.println("In nurse patient name "+p.retName()+p.retRoomNumber()+p.retBedNumber());
		if(scene==null)
			scene = new Scene(map,1000,1000);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
	// Function to display patients
	public void displayPatientDetails(Patient p,BorderPane bp) {
		// Big Wrapper
		BorderPane wrapperPane = new BorderPane();
		wrapperPane.setPadding(new Insets(20,0,0,20));
		
		
		// Layout Elements
		ScrollPane sp = new ScrollPane();
		
		String returnValue = p.printPrescription();
		
		sp.setContent(new Label(returnValue));
		
		// Assigning elements to corresponding elements
		wrapperPane.setTop(null);
		wrapperPane.setCenter(sp);
		
		bp.setCenter(wrapperPane);
		bp.setBottom(null);
		bp.setRight(null);
	}
	
}
