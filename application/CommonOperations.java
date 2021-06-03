package application;

import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;

public class CommonOperations {
	
	public void clearAllFields(TextField ...args) {
		for (TextField t:args)
			t.setText("");
	}
	
	public Button addCancelButton(BorderPane bp) {
		Button cancel = new Button("Cancel");
	    
	    cancel.setOnAction(e->{
	    	bp.setCenter(null);
	    	bp.setRight(null);
		});
	    
		return cancel;
	}
	
	public Boolean checkBlankFields(String ...args) {
		for (String s:args) {
			if(s.equals(""))
				return false;
		}		
		return true;
	}
	
	
	public Boolean checkBlankFields(TextField ...args) {
		for (TextField t:args) {
			if(t.getText().equals(""))
				return false;
		}		
		return true;
	}
	
	public HBox addButtonHolder(BorderPane bp) {
		HBox hbox = new HBox(10);
		Button submitButton = new Button("Submit");
		Button cancelButton = addCancelButton(bp);
		hbox.getChildren().addAll(submitButton,cancelButton);
		hbox.setAlignment(Pos.CENTER);
		hbox.setPrefSize(50,20);
		hbox.setMaxWidth(150);
		return hbox;
	}
	
	public void setVisibilityFalse(CheckBox...args) {
		for(CheckBox c: args)
			c.setVisible(false);
	}
	
	public Label retErrorLabel() {
		Label errorMsg = new Label();
		errorMsg.setTextFill(Color.RED);
		errorMsg.setFont(new Font("cambria",14));
		errorMsg.setText("");
		return errorMsg;
	}
	
	public Button retMapButton() {
		Button mapButton = new Button("M");
		mapButton.setTextFill(Color.BLUEVIOLET);
		return mapButton;
	}
	
}
