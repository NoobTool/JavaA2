package application;

import javafx.scene.control.*;

public class CommonOperations {
	
	public void clearLabels(TextField... fields) {
		for(TextField field: fields)
			field.setText("");
	}
	
}
