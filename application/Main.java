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
	
	private void managerStart() {
		Stage managerStage = new Stage();
		BorderPane bp = new BorderPane();
		VBox vbox = new VBox();
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: #4477aa;");

	    Label currentUser = new Label("Ram Rattan Goyal");
	    
	    Button admitButton = new Button(dm.managerMenu().get(0));
	    Button hireButton = new Button(dm.managerMenu().get(1));
	    Button button3 = new Button(dm.managerMenu().get(2));
	    Button button4 = new Button(dm.managerMenu().get(3));
	    Button button5 = new Button(dm.managerMenu().get(4));
	    

	    hbox.getChildren().addAll(currentUser, addExitButton(managerStage));
		
		vbox.setPadding(new Insets(10,0,0,50));
		
		vbox.getChildren().addAll(admitButton,hireButton,button3,button4,button5);
		bp.setLeft(vbox);
		bp.setTop(hbox);
			
		admitButton.setOnAction(e->{
			Button submitButton = new Button("Submit");
			Label errorMsg = new Label();
			HBox errorBox = new HBox();
			errorMsg.setTextFill(Color.RED);
			errorMsg.setFont(new Font("Cambria",16));
			
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
			
			bp.setCenter(gp);bp.setBottom(errorBox);
			
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
			GridPane gp = new GridPane();	
			Label errorMsg = new Label();
			HBox errorBox = new HBox();
			errorBox.getChildren().add(errorMsg);
			errorBox.setAlignment(Pos.CENTER);
			gp.setPadding(new Insets(15,30,0,50));
			gp.setHgap(10);
	        gp.setVgap(10);
			ComboBox<String> cb = new ComboBox<String>();
			cb.getItems().addAll("Manager","Nurse","Doctor");
			String selectedItem = cb.getSelectionModel().getSelectedItem();
		
			Button submitButton = new Button("Submit");
			TextField name = new TextField();
			TextField age = new TextField();
			TextField gender = new TextField();
			TextField shifts = new TextField();
			TextField password = new TextField();
			
			gp.add(new Label("Name:"), 2, 1);
			gp.add(new Label("Age:"), 2, 2);
			gp.add(new Label("Gender:"), 2, 3);
			gp.add(new Label("Shifts:"), 2, 4);
			gp.add(new Label("Password:"), 2, 5);
			gp.add(name,4,1);
			gp.add(age,4,2);
			gp.add(gender,4,3);
			gp.add(shifts,4,4);
			gp.add(password,4,5);
			gp.add(submitButton, 4, 6);
			bp.setCenter(gp);
			
			submitButton.setOnAction(e2->{
				m.addPeople(name.getText(), Double.parseDouble(age.getText()), gender.getText().charAt(0), shifts.getText(), password.getText(),"manager");name.getText();
			});
		});
		
		
		managerStage.setScene(new Scene(bp,700,350));
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