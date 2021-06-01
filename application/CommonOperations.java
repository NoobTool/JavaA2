package application;

import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.*;

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
	
}
