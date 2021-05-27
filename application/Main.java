package application;
import main.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.fxml.FXMLLoader;
import javafx.geometry.*;
import CommonSnippets.*;
import CustomExceptions.*;

public class Main extends Application {
		
	Manager m = new Manager();
	DisplayMenu dm = new DisplayMenu();
	OptionSequence oq = new OptionSequence();
	
	
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
						}catch(RestrictedTimingException exception) {
							errorMsg.setText("Not Rostered for this shift!"+"\n\n\n");
						}
					}
					
					// Doctor's Login
					else if(userText.substring(0,2).equals("68")) {
						Doctor d = login.doctorLogin(userId, passText);
						if(d.retName()!=null) {
							errorMsg.setText("Hi, Dr."+d.retName()+"\n\n\n");
							primaryStage.close();
							doctorStart();
						}	
					}
					 
					// Nurse's Login
					else if(userText.substring(0,2).equals("78")) {
						Nurse n = login.nurseLogin(userId, passText);
						if(n.retName()!=null) {
							errorMsg.setText("Hi, "+n.retName()+"\n\n\n");
							primaryStage.close();
							nurseStart();
							
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
	    
	    Button button1 = new Button(dm.managerMenu().get(0));
	    Button button2 = new Button(dm.managerMenu().get(1));
	    Button button3 = new Button(dm.managerMenu().get(2));
	    Button button4 = new Button(dm.managerMenu().get(3));
	    Button button5 = new Button(dm.managerMenu().get(4));
	    

	    hbox.getChildren().addAll(currentUser, addExitButton(managerStage));
		
		vbox.setPadding(new Insets(10,0,0,50));
//		for(int i=0;i<dm.managerMenu().size();i++)
//			vbox.getChildren().add(new Button(dm.managerMenu().get(i)));
		vbox.getChildren().addAll(button1,button2,button3,button4,button5);
		bp.setLeft(vbox);
		bp.setTop(hbox);
			
		button1.setOnAction(e->{
			Button submitButton = new Button("Submit");
			GridPane gp = new GridPane();			
			TextField name = new TextField();
			TextField age = new TextField();
			TextField gender = new TextField();
			gp.add(new Label("Name:"), 1, 0);
			gp.add(new Label("Age:"), 1, 1);
			gp.add(new Label("Gender:"), 1, 2);
			gp.add(submitButton, 2, 4);
			gp.add(name,3,0);
			gp.add(age,3,1);
			gp.add(gender,3,2);
			bp.setCenter(gp);
			
			submitButton.setOnAction(e2->{
				m.admitPatient(name.getText(), Double.parseDouble(age.getText()), gender.getText().charAt(0), "patient");name.getText();
			});
		});
		
		
		button2.setOnAction(e->{
			GridPane gp = new GridPane();	
			ComboBox<String> cb = new ComboBox<String>();
			cb.getItems().addAll("Manager","Nurse","Doctor");
			Object selectedItem = cb.getSelectionModel().getSelectedItem();
		
			Button submitButton = new Button("Submit");
			TextField name = new TextField();
			TextField age = new TextField();
			TextField gender = new TextField();
			TextField shifts = new TextField();
			TextField password = new TextField();
			
			gp.add(new Label("Name:"), 1, 1);
			gp.add(new Label("Age:"), 1, 2);
			gp.add(new Label("Gender:"), 1, 3);
			gp.add(new Label("Shifts:"), 1, 4);
			gp.add(new Label("Password:"), 1, 5);
			gp.add(submitButton, 2, 4);
			gp.add(name,3,1);
			gp.add(age,3,2);
			gp.add(gender,3,3);
			gp.add(shifts,3,4);
			gp.add(password,3,5);
			bp.setCenter(gp);
			
			submitButton.setOnAction(e2->{
				m.addPeople(name.getText(), Double.parseDouble(age.getText()), gender.getText().charAt(0), shifts.getText(), password.getText(),"manager");name.getText();
			});
		});
		
		
		managerStage.setScene(new Scene(bp,600,400));
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