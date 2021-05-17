package application;
import main.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.fxml.FXMLLoader;
import javafx.geometry.*;

public class Main extends Application {
		
	Manager m = new Manager();
	
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
						Manager m = login.managerLogin(userId, passText);
						if(m.retName()!=null)
							errorMsg.setText("Hi, "+m.retName());
					}
					
					// Doctor's Login
					else if(userText.substring(0,2).equals("68")) {
						Doctor d = login.doctorLogin(userId, passText);
						if(d.retName()!=null)
							errorMsg.setText("Hi, Dr."+d.retName());
					}
					
					// Nurse's Login
					else if(userText.substring(0,2).equals("78")) {
						Nurse n = login.nurseLogin(userId, passText);
						if(n.retName()!=null)
							errorMsg.setText("Hi, "+n.retName());
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
	
}