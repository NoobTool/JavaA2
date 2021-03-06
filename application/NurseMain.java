package application;
import main.Manager;
import main.Nurse;
import main.Patient;
import main.Login;
import prescription.MedicineBlock;
import prescription.MedicineDose;
import javafx.stage.*;
import javafx.scene.*;
import CustomExceptions.InputValidation;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import javafx.util.Pair;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
	Scene scene=null;
	Patient p;
	InputValidation iv = new InputValidation();
	RetrieveValues r = new RetrieveValues();
	
	public int bedNum;
	public int wardNum;
	public int roomNum;
	
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
		Button mapButton = co.retMapButton();
		
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
						String idError = iv.validateId(Long.parseLong(id),"Patient");
						if(idError.length()==0) {
							p =  n.nurseSearch(m.retPatientList(),Long.parseLong(id),"");
							Login login = new Login();
							if(p.retName()!=null) {
								if(login.checkCompliance(n)) {
									callFeatures(n,p,purpose,bp);
								}else
									errorMsg.setText("Not rostered for this shift.");
							}
							else
								errorMsg.setText("Patient does not exist.");
						}
						else
							errorMsg.setText("Wrong ID");
					}
				}catch(NumberFormatException exception) {
					errorMsg.setText("The ID must be in numberic characters!");
				}
			}
			
			// Searching by name
			else {
				if(!co.checkBlankFields(nameField.getText()))
					errorMsg.setText("Please fill the name field.");
				else {
					Pair<Boolean,String> namePair = iv.validateName(nameField.getText(),true);
					if(namePair.getKey()) {
						p = n.nurseSearch(m.retPatientList(),-1,namePair.getValue());
						if(p.retName()!=null)
							callFeatures(n,p,purpose,bp);
						else
							errorMsg.setText("Patient does not exist.");
					}
						
					else
						errorMsg.setText("Wrong Name");
				}
			}
		});	
		
		
		mapButton.setOnAction(e->{
			Stage primaryStage = new Stage();
			HBox map = new HBox();
			Manager m = new Manager("To return patients list");
			WardMap wm = new WardMap(m.retPatientList());
			map = wm.retMap();
			if(map.getScene()!=null)
				scene = map.getScene();
			else
				scene = new Scene(map,1000,1000);
			
			scene.setOnMouseClicked(e2->{
				if(wm.retWardNumber()!=0) {
					bedNum = wm.retBedNumber();
					roomNum = wm.retRoomNumber();
					wardNum = wm.retWardNumber();
					Pair<String,Patient> patientPair = n.patientInBed(bedNum, roomNum, wardNum);
					primaryStage.close();
					scene=null;
					if(patientPair.getKey()=="") {
						callFeatures(n,patientPair.getValue(),purpose,bp);
					}
				}
					
			});		
		
			primaryStage.setScene(scene);
			primaryStage.show();
		
		});
		bp.setRight(mapButton);
		BorderPane.setMargin(mapButton, new Insets(40,30,10,10));
		bp.setCenter(wrapperPane);
		bp.setBottom(errorMsg);
	}
	
	
	private void callFeatures(Nurse n,Patient p, String purpose, BorderPane bp) {
		
		if(purpose=="Administer")
			administerMedicine(n,p,bp);
		else if(purpose=="ChangeBed")
			changeBed(n,p,bp);
		else if(purpose=="ChangeBedAuto")
			changeBedAuto(n,p,bp);
		else if(purpose=="isolate")
			provideIsolation(n, p, bp);
		else if(purpose=="discharge")
			dischargePatient(n,p);
		else
			displayPatientDetails(p,bp);
		bp.setRight(null);
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
				if(medicineIndex!=-1) {
					String returnValue = n.administerMedicine(p, medicines.get(medicineIndex));
					
					if(returnValue=="") {
						errorMsg.setTextFill(Color.GREEN);
						errorMsg.setText(" Successfully administered "+
								medicines.get(medicineIndex).retName());
						try {
							ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("administers"));
							output.writeObject(n.retAdministerMedicines());
						}catch(Exception exception){
							errorMsg.setTextFill(Color.RED);
							errorMsg.setText(exception.toString());
						}
					}
						
					else
						errorMsg.setText(returnValue);
				}
				
				else 
					errorMsg.setText("Please make a selection first.");				
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
	
	// Changing bed manually
	public void changeBed(Nurse n, Patient p, BorderPane bp) {
		
		InputValidation iv = new InputValidation();
		
		// Layout Elements
		GridPane wrapperPane = new GridPane();
		Label errorMsg = co.retErrorLabel();
		TextField wardField = new TextField();
		TextField roomField = new TextField();
		TextField bedField = new TextField();
		HBox buttonHolder = co.addButtonHolder(bp);
		
		// Adding Elements in wrapperPane
		wrapperPane.add(new Label("Enter new ward number"), 0, 0);
		wrapperPane.add(wardField, 1, 0);
		wrapperPane.add(new Label("Enter new room number"), 0, 1);
		wrapperPane.add(roomField, 1, 1);
		wrapperPane.add(new Label("Enter new bed number"), 0, 2);
		wrapperPane.add(bedField, 1, 2);
		wrapperPane.add(buttonHolder, 1, 3);
		
		((Button)buttonHolder.getChildren().get(0)).setOnAction(e->{
			errorMsg.setTextFill(Color.RED);
			
			if(co.checkBlankFields(wardField,roomField,bedField)) {
				
				try {
					
					int wardNumber = Integer.parseInt(wardField.getText());
					int roomNumber = Integer.parseInt(roomField.getText());
					int bedNumber = Integer.parseInt(bedField.getText());
					
					String wardMsg = iv.validateWardNumber(wardNumber);
					String roomMsg = iv.validateRoomNumber(roomNumber);
					String bedMsg = iv.validateBedNumber(bedNumber,roomNumber);
					 
					if(wardMsg.length()>0)
						errorMsg.setText(wardMsg);
					
					else if(roomMsg.length()>0)
						errorMsg.setText(roomMsg);
				
					else if(bedMsg.length()>0)
						errorMsg.setText(bedMsg);
						
					else{
						Pair<Boolean,String> returnValue = n.changeBed(p, wardNumber, roomNumber
								, bedNumber);
						if(returnValue.getKey()==false)
							errorMsg.setText(returnValue.getValue());
						else {
							errorMsg.setTextFill(Color.GREEN);
							errorMsg.setText(returnValue.getValue());
							try {
								co.writePatients(m.retPatientList());
							}catch(Exception exception) {
								errorMsg.setTextFill(Color.RED);
								errorMsg.setText(exception.toString());
							}
						}
					}
					
				}catch(NumberFormatException exception) {
					errorMsg.setText("Only numeric Characters are expected in all fields. ");
				}
			}
			else 
				errorMsg.setText("All fields are mandatory");
		});
		
		
		// Formatting wrapperPane
		wrapperPane.setHgap(30);
		wrapperPane.setVgap(20);
		wrapperPane.setPadding(new Insets(30,0,0,40));
		
		bp.setCenter(wrapperPane);
		bp.setBottom(errorMsg);
				
	}
	
	// Changing bed automatically
	public void changeBedAuto(Nurse n, Patient p, BorderPane bp) {
		
		// Label Elements
		HBox wrapperBox = new HBox(40);
		HBox buttonHolder = co.addButtonHolder(bp);
		((Button)buttonHolder.getChildren().get(0)).setText("Change Ward Automatically");
		Label errorMsg = co.retErrorLabel();
				
		((Button)buttonHolder.getChildren().get(0)).setOnAction(e->{
			errorMsg.setTextFill(Color.RED);
			Pair<Boolean,String> returnValue = n.changeWardAutomatically(p);
			if(returnValue.getKey()) {
				errorMsg.setTextFill(Color.GREEN);
				errorMsg.setText("Changed Successfully");
				try {
					co.writePatients(m.retPatientList());
				}catch(Exception exception) {
					errorMsg.setTextFill(Color.RED);
					errorMsg.setText(exception.toString());
				}
			}
			else
				errorMsg.setText(returnValue.getValue());
			
		});
		
		// Adding and formatting elements in wrapperBox
		wrapperBox.getChildren().addAll(new Label("Press this button to change ward magically!"),
				buttonHolder);
		wrapperBox.setPadding(new Insets(30,0,0,40));
		
		bp.setCenter(wrapperBox);
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
		scene.setOnMouseClicked(e->{
			if(wm.retWardNumber()!=0)
				bedNum = wm.retWardNumber();
				roomNum = wm.retRoomNumber();
				bedNum = wm.retWardNumber();
			primaryStage.close();
		});
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	// Provide isolation
	public void provideIsolation(Nurse n, Patient p, BorderPane bp) {
		bp.setCenter(null);
		
		// Label Elements
		GridPane wrapperBox = new GridPane();
		HBox buttonHolder = co.addButtonHolder(bp);
		
		
		buttonHolder.setAlignment(Pos.CENTER);
		buttonHolder.getChildren().set(0, new Button("Provide Isolation"));
		Label errorMsg = co.retErrorLabel();
		buttonHolder.setMaxWidth(500);
		buttonHolder.setMinWidth(300);
		
		((Button)buttonHolder.getChildren().get(0)).setOnAction(e->{
			errorMsg.setTextFill(Color.RED);
			Pair<Boolean,String> returnValue = n.provideIsolation(p);
			if(returnValue.getKey()) {
				errorMsg.setTextFill(Color.GREEN);
				errorMsg.setText("Provided Successfully");
				try {
					co.writePatients(m.retPatientList());
				}catch(Exception exception) {
					errorMsg.setTextFill(Color.RED);
					errorMsg.setText(exception.toString());
				}
			}
			else
				errorMsg.setText(returnValue.getValue());
			
		});
		
		// Adding and formatting elements in wrapperBox
//		wrapperBox.getChildren().addAll(new Label("Press this button \nto change ward magically!"),
//				buttonHolder);
		wrapperBox.setHgap(10);
		wrapperBox.add(new Label("Press this button \nto change ward magically!"),0,0);
		wrapperBox.add(buttonHolder, 1, 0);
		wrapperBox.setPadding(new Insets(40,0,0,30));
		
		buttonHolder.setPadding(new Insets(0,0,0,
				40));
		
		bp.setCenter(wrapperBox);
		bp.setBottom(errorMsg);
		BorderPane.setAlignment(errorMsg, Pos.CENTER);
		
	} 
	
	public void dischargePatient(Nurse n, Patient p) {
		m.retOldPatientList().add(p);
		n.removePatient(p.retWardNumber(), p.retRoomNumber(), p.retBedNumber());
		for(Patient pat: m.retPatientList())
			System.out.println(p.retName());
		try {
			co.writePatients(m.retPatientList());
			ObjectOutputStream output = new ObjectOutputStream
					( new FileOutputStream("oldPatients"));
			output.writeObject(m.retOldPatientList());
			output.close();
		}catch(Exception e ) {
			System.out.println("error");
		}
	}

	
	// Function to display patients
	public void displayPatientDetails(Patient p,BorderPane bp) {
		// Big Wrapper
		BorderPane wrapperPane = new BorderPane();
		
		// Layout Elements
		ScrollPane sp = new ScrollPane();
		
		
		String returnValue = "Name: "+p.retName()+"\nAge: "+p.retAge()
				+"\nGender: "+p.retGender()+"\nWard Number: "+p.retWardNumber()
				+"\nRoom Number: "+p.retRoomNumber()+"\nBed Number: "+p.retBedNumber()+"\n\n";
		returnValue+=p.printPrescription();
		
		sp.setContent(new Label(returnValue));
		
		// Assigning elements to corresponding elements
		wrapperPane.setTop(null);
		wrapperPane.setCenter(sp);
		
		bp.setCenter(wrapperPane);
		bp.setBottom(null);
		bp.setRight(null);
	}
	
}
