package application;
import main.*;
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
import CustomExceptions.*;
import Actions.*;
import java.io.*;
import database.*;

public class Main extends Application implements Serializable{
		
	Manager m = new Manager();
	DisplayMenu dm = new DisplayMenu();
	CommonOperations co = new CommonOperations();
	InputValidation i = new InputValidation();
	DBClass db = new DBClass();
	
	@Override
	public void start(Stage primaryStage) {
		
		FlowPane flowPane = new FlowPane(Orientation.VERTICAL,5,5);
		
		// Labels
		Label errorMsg = new Label("\n\n\n");
		errorMsg.setTextFill(Color.RED);
		Label username = new Label("Username:");
		Label password = new Label("Password:");
		
		// Text fields
		TextField usernameField = new TextField();
		PasswordField passwordField = new PasswordField();
		
		// Buttons
		Button submit = new Button("Submit");
		
		flowPane.getChildren().addAll(errorMsg,username, usernameField, password,
				passwordField, new Label("\n\n"), submit);
		
		flowPane.setPadding(new Insets(10,0,0,50));
		
		submit.setOnAction(e->{
			try {
				long userId= Long.parseLong(usernameField.getText().strip());
				String userText = Long.toString(userId);
				String passText = passwordField.getText(); 
				if(passText.isBlank())
					errorMsg.setText("*Password cannot be empty!\n\n\n");
				 
				// Verifying details for login
				else {
					Login login = new Login();
					
					// Manager's Login
					if(userText.substring(0,2).equals("77")) {
						try {
							Manager m = login.managerLogin(userId, passText);
							if(m.retName()!=null) {
								errorMsg.setText("Hi, "+m.retName()+"\n\n\n");
								primaryStage.close();
								managerStart(m);
							}
							else {
								errorMsg.setText("Id or password is wrong!"+"\n\n\n");
								co.clearAllFields(usernameField, passwordField);
							}
						}catch(RestrictedTimingException exception) {
							errorMsg.setText("Not Rostered for this shift!"+"\n\n\n");
							co.clearAllFields(usernameField, passwordField);
						}catch(InvalidCredentialsException exception) {
							errorMsg.setText("Id or password is wrong!"+"\n\n\n");
							co.clearAllFields(usernameField, passwordField);
						}
					}
					
					// Doctor's Login
					else if(userText.substring(0,2).equals("68")) {
						try {
							Doctor d = login.doctorLogin(userId, passText);
							if(d.retName()!=null) {
								errorMsg.setText("Hi, "+d.retName()+"\n\n\n");
								primaryStage.close();
								doctorStart(d);
							} 
							else {
								errorMsg.setText("Id or password is wrong!"+"\n\n\n");
								co.clearAllFields(usernameField, passwordField);
							}
						}catch(RestrictedTimingException exception) {
							errorMsg.setText("Not Rostered for this shift!"+"\n\n\n");
							co.clearAllFields(usernameField, passwordField);
						}catch(InvalidCredentialsException exception) {
							errorMsg.setText("Id or password is wrong!"+"\n\n\n");
							co.clearAllFields(usernameField, passwordField);
						}
					}	
					 
					// Nurse's Login
					else if(userText.substring(0,2).equals("78")) {
						try {
							Nurse n = login.nurseLogin(userId, passText);
							if(n.retName()!=null) {
								errorMsg.setText("Hi, "+n.retName()+"\n\n\n");
								primaryStage.close();
								nurseStart(n);
							}
							else {
								errorMsg.setText("Id or password is wrong!"+"\n\n\n");
								co.clearAllFields(usernameField, passwordField);
							}
						}catch(RestrictedTimingException exception) {
							errorMsg.setText("Not Rostered for this shift!"+"\n\n\n");
							co.clearAllFields(usernameField, passwordField);
						}catch(InvalidCredentialsException exception) {
							errorMsg.setText("Id or password is wrong!"+"\n\n\n");
							co.clearAllFields(usernameField, passwordField);
						}
						
					}
						
					// Wrong ID
					else
						errorMsg.setText("No employee by this ID.");
					
				}
			}catch(NumberFormatException n) {
				errorMsg.setText("*Invalid ID\n\n\n");
			}
			
				
			  
			//primaryStage.close();
		});
		
		primaryStage.setScene(new Scene(flowPane,300,400));
		primaryStage.show();
		
	}
	
	private Button addExitButton(Stage thisStage) {
		Button exit = new Button("Exit");
	    exit.setPrefSize(100, 20);
	    
	    exit.setOnAction(e->{
			
	    	start(new Stage());
			thisStage.close();
		});
	    
		return exit;
	}
	
	private GridPane displayUsingGridPane(Employee e) {
		int rowIndex=1,columnIndex=1;
		
		GridPane displayCell = new GridPane();
		displayCell.add(new Label("Name:"),1,rowIndex);
		displayCell.add(new Label(e.retName().strip()),2,rowIndex);
		
		displayCell.add(new Label("Age:"),1,++rowIndex);
		displayCell.add(new Label(""+e.retAge()),2,rowIndex);
		
		displayCell.add(new Label("Gender:"),1,++rowIndex);
		displayCell.add(new Label(""+e.retGender()),2,rowIndex);
		
		displayCell.add(new Label("Shifts:"),1,++rowIndex);
		for(String s: e.retShifts()) {
			displayCell.add(new Label(s),++columnIndex, rowIndex);
		}
		
		displayCell.add(new Label("----------------"),1,++rowIndex);
		displayCell.add(new Label(""),1,++rowIndex);
		
		columnIndex=2;
		rowIndex=1;
		displayCell.setHgap(10);
		
		return displayCell;
	}
	
	// Graphical User Interface for different roles
	
	//Manager
	private void managerStart(Manager m) {
		
		// Layout Elements
		Stage managerStage = new Stage();
		BorderPane bp = new BorderPane();
		VBox vbox = new VBox(15);
		BorderPane topBar = new BorderPane();
		
		// VBox and HBox formatting
		topBar.setPadding(new Insets(15, 12, 15, 12));
	    topBar.setStyle("-fx-background-color: #4477aa;");
	    vbox.setPadding(new Insets(50,50,50,50));
	    
	    // Hi message
	    Label currentUser = new Label("Hi, "+m.retName());
	    currentUser.setFont(new Font("cambria",16));
	    currentUser.setStyle("-fx-font-weight: bold");
	    
	    //Buttons
	    Button admitButton = new Button(dm.managerMenu().get(0));
	    Button hireButton = new Button(dm.managerMenu().get(1));
	    Button modifyButton = new Button(dm.managerMenu().get(2));
	    Button displayButton = new Button(dm.managerMenu().get(3));
	    Button displayActions = new Button(dm.managerMenu().get(4));
	    
	    // Top Bar hbox formatting
	    topBar.setLeft(currentUser);
	    topBar.setRight(addExitButton(managerStage));
		
	    //Setting elements in Vbox
		vbox.setPadding(new Insets(10,0,0,50));
		vbox.getChildren().addAll(admitButton,hireButton,modifyButton,displayButton,displayActions);
		
		// Adding elements to the main border pane
		bp.setLeft(vbox);
		bp.setTop(topBar);
			
		admitButton.setOnAction(e->{
			Button submitButton = new Button("Submit");
			Label errorMsg = new Label();
			HBox errorBox = new HBox();
			errorMsg.setTextFill(Color.RED);
			errorMsg.setFont(new Font("Cambria",16));
			errorBox.setPadding(new Insets(0,0,20,0));
			
			GridPane gp = new GridPane();			
			gp.setPadding(new Insets(15,30,0,50));
			gp.setHgap(10);
	        gp.setVgap(10);
			TextField name = new TextField();
			TextField age = new TextField();
			TextField gender = new TextField();
			
			gp.add(new Label("Name:"), 1, 0);
			gp.add(new Label("Age:"), 1, 1);
			gp.add(new Label("Gender:"), 1, 2);
			gp.add(submitButton, 3, 4);
			gp.add(name,3,0);
			gp.add(age,3,1);
			gp.add(gender,3,2);
			errorBox.getChildren().add(errorMsg);
			errorBox.setAlignment(Pos.CENTER);
			
			bp.setCenter(gp);
			bp.setBottom(errorBox);
			bp.setRight(null);
			
			submitButton.setOnAction(e2->{
				if(co.checkBlankFields(name.getText(),age.getText(),gender.getText())) {
					Pair<Boolean,String> namePair= i.validateName(name.getText(),false);
					Pair<Double,String> agePair = i.validateAge(Double.parseDouble(age.getText()));
					String genderString = i.validateGender(gender.getText().toUpperCase().charAt(0));
					
					if(!namePair.getKey()) 
						errorMsg.setText(namePair.getValue());
						
					else if(agePair.getValue().length()>0)
						errorMsg.setText(agePair.getValue());
					
					else if(genderString.length()>0)
						errorMsg.setText(genderString);
					
					else {
						Pair<Boolean,String> returnValue = m.admitPatient(namePair.getValue(), 
								agePair.getKey(),gender.getText().toUpperCase().charAt(0), "patient");
						if(returnValue.getKey()==true) {
							errorMsg.setTextFill(Color.GREEN);
							errorMsg.setText(returnValue.getValue());
							try {
								ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("objects"));
								output.writeObject(m.retActionList());
							}catch(Exception exception) {
								System.out.println("Error in file handling!");
							}	
						}
						
						else {
							errorMsg.setTextFill(Color.RED);
							errorMsg.setText(returnValue.getValue());
						}
					}		
				}
				
				else {
					errorMsg.setTextFill(Color.RED);
					errorMsg.setText("All fields are mandatory");
				}
						
			});
		});
		
		
		hireButton.setOnAction(e->{
			
			// Parent elements
			GridPane gp = new GridPane();	
			Label errorMsg = new Label();
			HBox errorBox = new HBox();
			ComboBox<String> cb = new ComboBox<String>();
			HBox comboHBox = new HBox();
			
			
			// Grid pane formatting
			gp.setPadding(new Insets(15,30,0,50));
			gp.setHgap(10);
	        gp.setVgap(10);
			
	        // ComboBox Items
			cb.getItems().addAll("Manager","Doctor","Nurse");
		
			// TextFields
			TextField name = new TextField();
			TextField age = new TextField();
			TextField gender = new TextField();
			TextField shifts = new TextField();
			PasswordField password = new PasswordField();
			
			// Buttons
			Button submitButton = new Button("Submit");
			
			// Adding elements at grid pane
			gp.add(new Label("Name:"), 1, 1);
			gp.add(new Label("Age:"), 1, 2);
			gp.add(new Label("Gender:"), 1, 3);
			gp.add(new Label("Shifts:"), 1, 4);
			gp.add(new Label("Password:"), 1, 5);
			gp.add(name,2,1);
			gp.add(age,2,2);
			gp.add(gender,2,3);
			gp.add(shifts,2,4);
			gp.add(password,2,5);
			gp.add(submitButton, 2, 6);
			gp.add(co.addCancelButton(bp), 3, 6);
			
			// Adding elements to errorBox and formatting
			errorMsg.setTextFill(Color.RED);
			errorMsg.setFont(new Font("Cambria",16));
			errorBox.getChildren().add(errorMsg);
			errorBox.setAlignment(Pos.CENTER);
			
			// Adding elements to comboHBox and formatting
			comboHBox.getChildren().add(cb);
			comboHBox.setPadding(new Insets(25,0,0,0));
			
			// Adding elements at border pane
			bp.setCenter(gp);			
			bp.setBottom(errorBox);
			bp.setRight(comboHBox);
			
			// Assigning actions to buttons
			submitButton.setOnAction(e2->{
				String selectedItem = cb.getSelectionModel().getSelectedItem();
				errorMsg.setTextFill(Color.RED);
				
				if((co.checkBlankFields(name.getText(),age.getText(),
						gender.getText(),shifts.getText(),password.getText()) || 
						
						(co.checkBlankFields(name.getText(),age.getText(),
						gender.getText(),password.getText()) && selectedItem.equals("Nurse")))
						&& cb.getSelectionModel().isEmpty()==false) {
					
					Pair<Boolean,String> namePair = i.validateName(name.getText(),false);
					Pair<Double,String> agePair = i.validateAge(Double.parseDouble(age.getText()));
					String genderString = i.validateGender(gender.getText().toUpperCase().charAt(0));
					Pair<Boolean,String> shiftText = i.validateShifts(shifts.getText(), selectedItem);
					
					if(!namePair.getKey()) 
						errorMsg.setText(namePair.getValue());
						
					else if(agePair.getValue().length()>0)
						errorMsg.setText(agePair.getValue());
					
					else if(genderString.length()>0)
						errorMsg.setText(genderString);
						
					else if(!shiftText.getKey()) {
						errorMsg.setText(shiftText.getValue());
					}
						
					else if(!i.validatePassword(password.getText()))
						errorMsg.setText("Password must be greater than 4 letters");
					 
					else {
						long id = m.addPeople(namePair.getValue(), 
								Double.parseDouble(age.getText()), 
								gender.getText().toUpperCase().charAt(0), 
								shifts.getText(), password.getText(),
								selectedItem);
						try {
							db.addStaff(id, namePair.getValue(), 
								Double.parseDouble(age.getText()), 
								gender.getText().toUpperCase().charAt(0), 
								shifts.getText(), password.getText(),
								selectedItem);
							errorMsg.setTextFill(Color.GREEN);
							errorMsg.setText("Added successfully!");
							co.clearAllFields(name,age,gender,shifts,password);
						}catch(Exception exception) {
							errorMsg.setText(exception.toString());
						}
					}
						
				}
				
				else {
					if(cb.getSelectionModel().isEmpty())
						errorMsg.setText("Please select a post from the drop-down list!");
					else
						errorMsg.setText("Every field is mandatory!");
				}
					
				
			});
		});
		
		displayButton.setOnAction(e->{
			
			// Biggest wrapper containing drop-down and scroll
			VBox displayWrapper = new VBox(10);
			
			ComboBox<String> cb = new ComboBox<String>();
			
			ScrollPane sp = new ScrollPane();
			
			cb.getItems().addAll("Manager","Doctor","Nurse");
			
			
			cb.setOnAction(e2->{
				String selectedItem = cb.getSelectionModel().getSelectedItem();
				if(!selectedItem.isEmpty()) {
					
					if(selectedItem.equals("Manager")) {
						VBox displayBox = new VBox(10);
						for(Manager manager: m.retManagerList()) {
							GridPane displayCell = displayUsingGridPane(manager);
							displayBox.getChildren().add(displayCell);
						}
						sp.setContent(displayBox);
					}
					
					else if(selectedItem.equals("Doctor")) {
						VBox displayBox = new VBox(10);
						for(Doctor doctor: m.retDoctorList()) {
							GridPane displayCell = displayUsingGridPane(doctor);
							displayBox.getChildren().add(displayCell);
						}
						sp.setContent(displayBox);
					}
					
					else {
						VBox displayBox = new VBox(10);
						for(Nurse nurse: m.retNurseList()) {
							GridPane displayCell = displayUsingGridPane(nurse);
							displayBox.getChildren().add(displayCell);
						}
						sp.setContent(displayBox);
					}
				}
				
			});
			
			
			displayWrapper.getChildren().addAll(cb,sp);
			bp.setCenter(displayWrapper);
			bp.setRight(null);
			bp.setBottom(null);
			
			});
		
		// Displaying the actions performed
		displayActions.setOnAction(e->{
					ScrollPane sp = new ScrollPane();
					VBox actionBox = new VBox(10);
					
					for(Action a: m.retActionList()) {
						actionBox.getChildren().add(new Label("Employee with ID: "
								+a.retPerformerId()+" performed a/an "
								+a.retActionName()+" on Id: "
								+a.retReceiverId()+" on "+a.retDate()
								+" at "+a.retTime()));
					}
					
					sp.setContent(actionBox);
					bp.setCenter(sp);
					bp.setBottom(null);
					bp.setRight(null);
					
				});
		
		// Modifying Details
		modifyButton.setOnAction(e->{
			
			// Big Wrapper
			BorderPane wrapperPane = new BorderPane();
			wrapperPane.setPadding(new Insets(20,0,0,20));
			
			// Layouts
			ComboBox<String> personBox = new ComboBox<String>();
			GridPane searchGrid = new GridPane();
			Button idButton = new Button("Search by ID");
			Button nameButton = new Button("Search by Name");
			Button submitButton = new Button("Submit");
			TextField idField = new TextField();
			TextField nameField = new TextField();
			
			//Adding nodes in gridpane
			searchGrid.add(idButton, 1, 1);
			searchGrid.add(idField, 2, 1);
			searchGrid.add(nameButton, 1, 2);
			searchGrid.add(nameField, 2, 2);
			searchGrid.add(submitButton, 2, 3);
			searchGrid.add(co.addCancelButton(bp),3,3);
			
			// GridPane formatting
			searchGrid.setHgap(20);
			searchGrid.setVgap(20);
			idField.setVisible(false);
			nameField.setVisible(false);
			searchGrid.setPadding(new Insets(20,0,0,0));
			Label errorMsg = co.retErrorLabel();
			
			// Editing the ComboBox for post selection
			personBox.getItems().addAll("Patient","Manager","Doctor","Nurse");
			
			// errorMsg editing
			errorMsg.setFont(new Font("cambria",16));
			errorMsg.setTextFill(Color.RED);
			
			// Defining actions for the buttons
			
			idButton.setOnAction(e2->{
				idField.setVisible(true);
				nameField.setVisible(false);
				nameField.setText("");
			});
			
			nameButton.setOnAction(e2->{
				idField.setVisible(false);
				idField.setText("");
				nameField.setVisible(true);
			});
			
			
			submitButton.setOnAction(e2->{
				String selectedItem = personBox.getSelectionModel().getSelectedItem();
				
				if (!idField.isVisible() && !nameField.isVisible()){
					errorMsg.setText("Please select a search option.");
				}
				
				// Searching by ID
				else if(idField.isVisible() && selectedItem!=null) {
					if(!co.checkBlankFields(idField.getText()))
						errorMsg.setText("Please fill the ID field.");
					else if(m.modifyDetails(selectedItem, Long.parseLong(idField.getText()), "").retName()==null)
						errorMsg.setText(selectedItem.substring(0,1).toUpperCase()+selectedItem.substring(1)+" not found ");
					else {
						try {
							Employee emp = m.modifyDetails(selectedItem, Long.parseLong(idField.getText()), "");
							long id = Long.parseLong(idField.getText());
							managerModify(selectedItem,emp,wrapperPane,bp);							
						}catch(NumberFormatException exception) {
							errorMsg.setText("ID must contain only numbers!");
						}
					}
				} 
				
				// Searching by name
				else if(nameField.isVisible() && selectedItem!=null){
					Pair<Boolean,String> namePair = i.validateName(nameField.getText(),true);
					if(!co.checkBlankFields(nameField.getText()))
						errorMsg.setText("Please fill the name field.");
					else if(!namePair.getKey())
						errorMsg.setText("Incorrect name, enter again");
					else if(m.modifyDetails(selectedItem, -1, nameField.getText()).retName()==null)
						errorMsg.setText(selectedItem.substring(0,1).toUpperCase()+selectedItem.substring(1)+" not found ");
					else {
						Employee emp = m.modifyDetails(selectedItem,-1, namePair.getValue());
						managerModify(selectedItem,emp,wrapperPane,bp);
					}
				}
				
				else {
					errorMsg.setText("Please choose an option from drop-down.");
				}
					
			});
			
			// Assigning a drop-down for post selection
			wrapperPane.setTop(personBox);
			wrapperPane.setCenter(searchGrid);
			wrapperPane.setBottom(errorMsg);
			BorderPane.setAlignment(errorMsg, Pos.CENTER);
			bp.setCenter(wrapperPane);
			
		});
		
		managerStage.setScene(new Scene(bp,1000,450));
		managerStage.show();
	}
	
	
	private void managerModify(String selectedItem,Employee emp, BorderPane wrapperPane,BorderPane bp) {
		Label errorMsg = co.retErrorLabel();
		
		// Layout Items
		GridPane detailsPane = new GridPane();
		CheckBox nameBox = new CheckBox("Name");
		CheckBox ageBox = new CheckBox("Age");
		CheckBox genderBox = new CheckBox("Gender");
		CheckBox shiftsBox = new CheckBox("Shifts");
		CheckBox passwordBox = new CheckBox("Password");
		TextField nameModifyField = new TextField();
		TextField ageModifyField = new TextField();
		TextField genderModifyField = new TextField();
		Label shiftsModifyLabel = new Label();
		shiftsModifyLabel.setText("A new modification windows will appear after this!");
		TextField passwordModifyField = new PasswordField();
		Button submitButton2 = new Button("Submit");
		HashMap modifiedItems = new HashMap();
		
		detailsPane.add(nameBox, 1, 1);
		detailsPane.add(nameModifyField, 2, 1);
		detailsPane.add(ageBox, 1, 2);
		detailsPane.add(ageModifyField, 2, 2);
		detailsPane.add(genderBox, 1, 3);
		detailsPane.add(genderModifyField, 2, 3);
		detailsPane.add(shiftsBox, 1, 4);
		detailsPane.add(shiftsModifyLabel, 2, 4);
		detailsPane.add(passwordBox, 1, 5);
		detailsPane.add(passwordModifyField, 2, 5);
		detailsPane.add(submitButton2, 2, 6);
		detailsPane.add(co.addCancelButton(bp), 3, 6);
		
		nameModifyField.setVisible(false);
		ageModifyField.setVisible(false);
		genderModifyField.setVisible(false);
		shiftsModifyLabel.setVisible(false);
		passwordModifyField.setVisible(false);
		
		
		// Toggling visibility of textfields by checkbox selection
		nameBox.setOnAction(e3->{
			if(nameBox.isSelected())
				nameModifyField.setVisible(true);
			else
				nameModifyField.setVisible(false);
		});
		
		ageBox.setOnAction(e3->{
			if(ageBox.isSelected())
				ageModifyField.setVisible(true);
			else
				ageModifyField.setVisible(false);
		});
		 
		genderBox.setOnAction(e3->{
			if(genderBox.isSelected())
				genderModifyField.setVisible(true);
			else
				genderModifyField.setVisible(false);
		});
		
		shiftsBox.setOnAction(e3->{
			if(shiftsBox.isSelected())
				shiftsModifyLabel.setVisible(true);
			else
				shiftsModifyLabel.setVisible(false);
		});
		
		passwordBox.setOnAction(e3->{
			if(passwordBox.isSelected())
				passwordModifyField.setVisible(true);
			else
				passwordModifyField.setVisible(false);
		});
		
		// DetailsPane grid Formatting
		detailsPane.setHgap(20);
		detailsPane.setVgap(20);
		
		wrapperPane.setCenter(detailsPane);
		wrapperPane.setTop(null);
		wrapperPane.setRight(null);
		
		submitButton2.setOnAction(e3->{
			try {
				if(nameBox.isSelected()) {
					String newName = nameModifyField.getText();									
					if(newName!="") {
						Pair<Boolean,String> namePair = i.validateName(newName,false);
						if(!namePair.getKey())
							errorMsg.setText(namePair.getValue());
						else
							modifiedItems.put("Name", newName);
					}else
						modifiedItems.put("Name", "");
					
				}else
					modifiedItems.put("Name", "");
				
				if(ageBox.isSelected()) {
					String newAge = ageModifyField.getText();									
					if(newAge!="") {
						Double newAge2 = Double.parseDouble(newAge);
						Pair<Double,String> agePair = i.validateAge(newAge2);
						if(agePair.getValue().length()>0)
							errorMsg.setText(agePair.getValue());
						else
							modifiedItems.put("Age",newAge2);
					}
					
				}
				else
					modifiedItems.put("Age", -1);
				
				if(genderBox.isSelected()) {
					String newGender = genderModifyField.getText();									
					if(!newGender.equals("")) {
						String genderString = i.validateGender(newGender.toUpperCase().charAt(0));
						if(genderString.length()>0)
							errorMsg.setText(genderString);
						else 
							modifiedItems.put("Gender",newGender.toUpperCase());	
							
					}
					else {
						errorMsg.setText("Gender field cannot be empty while selected.");
						modifiedItems.put("Gender","");
					}
						
				}
				else
					modifiedItems.put("Gender","");
				
				if(passwordBox.isSelected()) {
					String newPass = passwordModifyField.getText();									
					if(!i.validatePassword(newPass))
						errorMsg.setText("Password must be more than 4 characters");
						
					else
						modifiedItems.put("Password",newPass);
				}
				else 	
					modifiedItems.put("Password","");
				
				m.changeDetails(emp, selectedItem, modifiedItems.get("Name").toString(),
						Double.parseDouble(modifiedItems.get("Age").toString())
						,modifiedItems.get("Gender").toString(),modifiedItems.get("Password").toString());
				
				if(shiftsBox.isSelected()) {
					// Layout 
					GridPane shiftPane = new GridPane();
					Button addShift = new Button("Add shifts");
					Button changeShift = new Button("Change shifts");
					Button deleteShift = new Button("Delete shift(s)");
					Button submitButton3 = new Button("Submit");
					TextField addShiftField = new TextField();
					TextField changeShiftField = new TextField();
					ComboBox<String> changeShiftBox = new ComboBox<String>();
					ComboBox<String> deleteShiftBox = new ComboBox<String>();
					
					// Setting Visibilities
					addShiftField.setVisible(false);
					changeShiftField.setVisible(false);
					changeShiftBox.setVisible(false);
					deleteShiftBox.setVisible(false);
					
					// Adding elements to combo boxes
					changeShiftBox.getItems().add("None");
					deleteShiftBox.getItems().add("None");
					
					// Adding elements to shiftPane grid pane
					shiftPane.add(addShift, 1, 1);
					shiftPane.add(addShiftField, 2, 1);
					shiftPane.add(changeShift, 1, 2);
					shiftPane.add(changeShiftBox, 2, 2);
					shiftPane.add(changeShiftField, 3, 2);
					shiftPane.add(deleteShift, 1, 3);
					shiftPane.add(deleteShiftBox, 2, 3);
					shiftPane.add(submitButton3, 2, 4);
					shiftPane.add(co.addCancelButton(bp), 3, 4);
					
					// Formatting grid pane
					shiftPane.setHgap(20);
					shiftPane.setVgap(20);
					
					// Adding actions on buttons
					addShift.setOnAction(e4->{
						addShiftField.setVisible(true);
						changeShiftField.setVisible(false);
						changeShiftBox.setVisible(false);
						deleteShiftBox.setVisible(false);
						co.clearAllFields(changeShiftField,addShiftField);
						errorMsg.setText("");
					});
					
					changeShift.setOnAction(e4->{
						// Adding shifts to drop-down
						// New changes will also be reflected here
						changeShiftBox.getItems().remove(0, changeShiftBox.getItems().size());
						for(String s: emp.retShifts())
							changeShiftBox.getItems().add(s);
						addShiftField.setVisible(false);
						changeShiftField.setVisible(true);
						changeShiftBox.setVisible(true);
						deleteShiftBox.setVisible(false);
						co.clearAllFields(changeShiftField,addShiftField);
						errorMsg.setText("");
					});
					
					deleteShift.setOnAction(e4->{
						deleteShiftBox.getItems().remove(0, deleteShiftBox.getItems().size());
						for(String s: emp.retShifts())
							deleteShiftBox.getItems().add(s);
						addShiftField.setVisible(false);
						changeShiftField.setVisible(false);
						changeShiftBox.setVisible(false);
						deleteShiftBox.setVisible(true);
						co.clearAllFields(changeShiftField,addShiftField);
						errorMsg.setText("");
					});
					
					submitButton3.setOnAction(e4->{
						errorMsg.setTextFill(Color.RED);
																	
						try {
							if(addShiftField.isVisible()) {
								Pair<Boolean,String> newShift = i.validateShifts(addShiftField.getText(),selectedItem);
								if(newShift.getKey()) {
									String returnValue2 = m.changeDetails(emp,selectedItem,
											-1,addShiftField.getText(),1 );
									if(returnValue2.length()==0) {
										errorMsg.setTextFill(Color.GREEN);
										errorMsg.setText("Added Successfully");
									}
									else
										errorMsg.setText(returnValue2);
								}
								else
									errorMsg.setText(newShift.getValue());
							} 
							
							else if(changeShiftField.isVisible()) {
								if(!changeShiftBox.getSelectionModel().isEmpty()) {
									Pair<Boolean,String> newShift = i.validateShifts(changeShiftField.getText(),selectedItem);
									int oldShiftIndex = emp.retShifts().indexOf(changeShiftBox.getSelectionModel().getSelectedItem());
									if(newShift.getKey()) {
										String returnValue2 = m.changeDetails(emp,selectedItem,oldShiftIndex
												,changeShiftField.getText(),2 );
										if(returnValue2.length()==0) {
											errorMsg.setTextFill(Color.GREEN);
											errorMsg.setText("Changed Successfully");
										}
										else
											errorMsg.setText(returnValue2);
									}
										
									else
										errorMsg.setText(newShift.getValue());
								}
								else {
									errorMsg.setText("Please select a shift from the drop-down.");
								}
							}
							
							else {
								String returnValue2 = m.changeDetails(emp,selectedItem,emp.retShifts().indexOf(
										deleteShiftBox.getSelectionModel().getSelectedItem())
										,"",3 );
								if(returnValue2.length()==0) {
									errorMsg.setTextFill(Color.GREEN);
									errorMsg.setText("Deleted Successfully");
								}
								else
									errorMsg.setText(returnValue2);
							}
							
							addShiftField.setVisible(false);
							changeShiftField.setVisible(false);
							changeShiftBox.setVisible(false);
							deleteShiftBox.setVisible(false);
								
						}catch(TooManyShiftsException exception) {
							errorMsg.setText("Too Many shifts");
							addShiftField.setVisible(false);
							changeShiftField.setVisible(false);
							changeShiftBox.setVisible(false);
							deleteShiftBox.setVisible(false);
						}
					});
					
					wrapperPane.setCenter(shiftPane);
					wrapperPane.setTop(null);
					wrapperPane.setRight(null);
				}
				
				
			}catch(NumberFormatException exception) {
				errorMsg.setText("Age must be in number");
			}
			
		});
	}
	
	
	private void doctorStart(Doctor d) {
		
		DoctorMain doctorMain = new DoctorMain();
		
		// Layout Elements
		Stage doctorStage = new Stage();
		BorderPane bp = new BorderPane();
		VBox vbox = new VBox(25);
		BorderPane topBar = new BorderPane();
		
		// VBox and HBox formatting
		topBar.setPadding(new Insets(15, 12, 15, 12));
	    topBar.setStyle("-fx-background-color: #4477aa;");
	    vbox.setPadding(new Insets(60,50,50,50));
	    
	    // Hi message
	    Label currentUser = new Label("Hi, "+d.retName());
	    currentUser.setFont(new Font("cambria",16));
	    currentUser.setStyle("-fx-font-weight: bold");
	    
	    //Buttons
	    Button addPrescriptionButton = new Button(dm.doctorMenu().get(0));
	    Button updatePrescriptionButton = new Button(dm.doctorMenu().get(1));
	    Button displayButton = new Button(dm.doctorMenu().get(2));
	    
	    // Top Bar hbox formatting
	    topBar.setLeft(currentUser);
	    topBar.setRight(addExitButton(doctorStage));
		
	    //Setting elements in Vbox
		vbox.setPadding(new Insets(10,0,0,50));
		vbox.getChildren().addAll(addPrescriptionButton,updatePrescriptionButton
				,displayButton);
			
		addPrescriptionButton.setOnAction(e->{
			doctorMain.patientSearch(d, bp, "add");
		});
		
		updatePrescriptionButton.setOnAction(e->{
			doctorMain.patientSearch(d,bp,"update");
		});
		
		displayButton.setOnAction(e->{
			doctorMain.patientSearch(d, bp, "x");
		});
			
		// Adding elements to the main border pane
		bp.setLeft(vbox);
		bp.setTop(topBar);
		
		doctorStage.setScene(new Scene(bp, 800, 300));
		doctorStage.show();
		
	}

	 
	private void nurseStart(Nurse n) {
		NurseMain nurseMain = new NurseMain();
		
		// Layout Elements
		Stage nurseStage = new Stage();
		BorderPane bp = new BorderPane();
		VBox vbox = new VBox(25);
		BorderPane topBar = new BorderPane();
		
		// VBox and HBox formatting
		topBar.setPadding(new Insets(15, 12, 15, 12));
	    topBar.setStyle("-fx-background-color: #4477aa;");
	    vbox.setPadding(new Insets(60,50,50,50));
	    
	    // Hi message
	    Label currentUser = new Label("Hi, "+n.retName());
	    currentUser.setFont(new Font("cambria",16));
	    currentUser.setStyle("-fx-font-weight: bold");
	    
	    //Buttons
	    Button administerButton = new Button(dm.nurseMenu().get(0));
	    Button changeAutoButton = new Button(dm.nurseMenu().get(1));
	    Button changeManualButton = new Button(dm.nurseMenu().get(2));
	    Button isolateButton = new Button(dm.nurseMenu().get(3));
	    Button displayButton = new Button(dm.nurseMenu().get(4));
	    
	    // Top Bar hbox formatting
	    topBar.setLeft(currentUser);
	    topBar.setRight(addExitButton(nurseStage));
		
	    //Setting elements in Vbox
		vbox.setPadding(new Insets(10,0,0,50));
		vbox.getChildren().addAll(administerButton,changeAutoButton
				,changeManualButton,displayButton);
			
		administerButton.setOnAction(e->{
			nurseMain.patientSearch(n, bp, "Administer");
		});
		
		changeManualButton.setOnAction(e->{
			nurseMain.patientSearch(n,bp,"ChangeBed");
		});
		
		changeAutoButton.setOnAction(e->{
			nurseMain.patientSearch(n,bp,"ChangeBedAuto");
		});
		
		isolateButton.setOnAction(e->{
			nurseMain.patientSearch(n,bp,"isolate");
		});
		
		displayButton.setOnAction(e->{
			nurseMain.patientSearch(n, bp, "x");
		});
			
		// Adding elements to the main border pane
		bp.setLeft(vbox);
		bp.setTop(topBar);
		
		nurseStage.setScene(new Scene(bp, 800, 400));
		nurseStage.show();
	}
	
	
}