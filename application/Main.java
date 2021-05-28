package application;
import main.*;
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

public class Main extends Application {
		
	Manager m = new Manager();
	DisplayMenu dm = new DisplayMenu();
	OptionSequence oq = new OptionSequence();
	CommonOperations co = new CommonOperations();
	InputValidation i = new InputValidation();
	
	
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
								managerStart();
							}
							else {
								errorMsg.setText("Id or password is wrong!"+"\n\n\n");
								co.clearLabels(usernameField, passwordField);
							}
						}catch(RestrictedTimingException exception) {
							errorMsg.setText("Not Rostered for this shift!"+"\n\n\n");
							co.clearLabels(usernameField, passwordField);
						}catch(InvalidCredentialsException exception) {
							errorMsg.setText("Id or password is wrong!"+"\n\n\n");
							co.clearLabels(usernameField, passwordField);
						}
					}
					
					// Doctor's Login
					else if(userText.substring(0,2).equals("68")) {
						try {
							Doctor d = login.doctorLogin(userId, passText);
							if(d.retName()!=null) {
								errorMsg.setText("Hi, "+d.retName()+"\n\n\n");
								primaryStage.close();
								doctorStart();
							}
							else {
								errorMsg.setText("Id or password is wrong!"+"\n\n\n");
								co.clearLabels(usernameField, passwordField);
							}
						}catch(RestrictedTimingException exception) {
							errorMsg.setText("Not Rostered for this shift!"+"\n\n\n");
							co.clearLabels(usernameField, passwordField);
						}catch(InvalidCredentialsException exception) {
							errorMsg.setText("Id or password is wrong!"+"\n\n\n");
							co.clearLabels(usernameField, passwordField);
						}
					}	
					 
					// Nurse's Login
					else if(userText.substring(0,2).equals("78")) {
						try {
							Doctor d = login.doctorLogin(userId, passText);
							if(d.retName()!=null) {
								errorMsg.setText("Hi, "+d.retName()+"\n\n\n");
								primaryStage.close();
								nurseStart();
							}
							else {
								errorMsg.setText("Id or password is wrong!"+"\n\n\n");
								co.clearLabels(usernameField, passwordField);
							}
						}catch(RestrictedTimingException exception) {
							errorMsg.setText("Not Rostered for this shift!"+"\n\n\n");
							co.clearLabels(usernameField, passwordField);
						}catch(InvalidCredentialsException exception) {
							errorMsg.setText("Id or password is wrong!"+"\n\n\n");
							co.clearLabels(usernameField, passwordField);
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
	
	private Boolean checkBlankFields(String ...args) {
		for (String s:args) {
			if(s.contentEquals(""))
				return false;
		}		
		return true;
	}
	
	private void clearAllFields(TextField ...args) {
		for (TextField t:args)
			t.setText("");
	}
	
	private GridPane displayUsingGridPane(Employee e) {
		int rowIndex=1,columnIndex=1;
		
		GridPane displayCell = new GridPane();
		displayCell.add(new Label("Name:"),1,rowIndex);
		displayCell.add(new Label(e.retName()),2,rowIndex);
		
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
	
	private void managerStart() {
		Stage managerStage = new Stage();
		BorderPane bp = new BorderPane();
		VBox vbox = new VBox(15);
		HBox hbox = new HBox();
		
		// VBox and HBox formatting
		hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: #4477aa;");
	    vbox.setPadding(new Insets(50,50,50,50));
	    
	    Label currentUser = new Label(m.retName());
	    
	    Button admitButton = new Button(dm.managerMenu().get(0));
	    Button hireButton = new Button(dm.managerMenu().get(1));
	    Button button3 = new Button(dm.managerMenu().get(2));
	    Button displayButton = new Button(dm.managerMenu().get(3));
	    Button button5 = new Button(dm.managerMenu().get(4));
	    

	    hbox.getChildren().addAll(currentUser, addExitButton(managerStage));
		
		vbox.setPadding(new Insets(10,0,0,50));
		vbox.getChildren().addAll(admitButton,hireButton,button3,displayButton,button5);
		bp.setLeft(vbox);
		bp.setTop(hbox);
			
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
				String nameText = i.validateName(name.getText());
				Pair<Double,String> agePair = i.validateAge(Double.parseDouble(age.getText()));
				String genderString = i.validateGender(gender.getText().toUpperCase().charAt(0));
				
				if(nameText.length()>0) 
					errorMsg.setText(nameText);
					
				else if(agePair.getValue().length()>0)
					errorMsg.setText(agePair.getValue());
				
				else if(genderString.length()>0)
					errorMsg.setText(genderString);
				
				else {
					Pair<Boolean,String> returnValue = m.admitPatient(name.getText(), 
							agePair.getKey(),gender.getText().toUpperCase().charAt(0), "patient");
					if(returnValue.getKey()==true) {
						errorMsg.setTextFill(Color.GREEN);
						errorMsg.setText(returnValue.getValue());
					}
					
					else {
						errorMsg.setTextFill(Color.RED);
						errorMsg.setText(returnValue.getValue());
					}
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
			cb.getItems().addAll("Manager","Nurse","Doctor");
		
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
				
				if(checkBlankFields(name.getText(),age.getText(),
						gender.getText(),shifts.getText(),password.getText())
						&& cb.getSelectionModel().isEmpty()==false) {
					
					String nameText = i.validateName(name.getText());
					Pair<Double,String> agePair = i.validateAge(Double.parseDouble(age.getText()));
					String genderString = i.validateGender(gender.getText().toUpperCase().charAt(0));
					Pair<Boolean,String> shiftText = i.validateShifts(shifts.getText(), selectedItem);
					errorMsg.setTextFill(Color.RED);
					System.out.println(selectedItem);
					
					if(nameText.length()>0) 
						errorMsg.setText(nameText);
						
					else if(agePair.getValue().length()>0)
						errorMsg.setText(agePair.getValue());
					
					else if(genderString.length()>0)
						errorMsg.setText(genderString);
						
					else if(!shiftText.getKey()) {
						System.out.println(shiftText.getValue());
						errorMsg.setText(shiftText.getValue());
					}
						
					else if(!i.validatePassword(password.getText()))
						errorMsg.setText("Password must be greater than 4 letters");
					 
					else {
						m.addPeople(name.getText(), 
								Double.parseDouble(age.getText()), 
								gender.getText().toUpperCase().charAt(0), 
								shifts.getText(), password.getText(),
								selectedItem);
						errorMsg.setTextFill(Color.GREEN);
						errorMsg.setText("Added successfully!");
						clearAllFields(name,age,gender,shifts,password);
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
		
		
		managerStage.setScene(new Scene(bp,800,350));
		managerStage.show();
	}
	
	private void doctorStart() {
		Stage doctorStage = new Stage();
		BorderPane bp = new BorderPane();
		VBox vbox = new VBox();
		HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: #4477aa;");

	    Label currentUser = new Label("Ram Rattan Goyal");

	    hbox.getChildren().addAll(currentUser, addExitButton(doctorStage));
		
		vbox.setPadding(new Insets(10,0,0,50));
		for(int i=0;i<dm.doctorMenu().size();i++)
			vbox.getChildren().add(new Button(dm.doctorMenu().get(i)));
		bp.setLeft(vbox);
		bp.setTop(hbox);
		doctorStage.setScene(new Scene(bp,300,400));
		doctorStage.show();		
	}
	
	private void nurseStart() {
		Stage nurseStage = new Stage();
		BorderPane bp = new BorderPane();
		VBox vbox = new VBox();
		HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: #4477aa;");

	    Label currentUser = new Label("Elisa");

	    hbox.getChildren().addAll(currentUser, addExitButton(nurseStage));
		
		vbox.setPadding(new Insets(10,0,0,50));
		for(int i=0;i<dm.nurseMenu().size();i++)
			vbox.getChildren().add(new Button(dm.nurseMenu().get(i)));
		bp.setLeft(vbox);
		bp.setTop(hbox);
		nurseStage.setScene(new Scene(bp,300,400));
		nurseStage.show();		
	}
	
	
}