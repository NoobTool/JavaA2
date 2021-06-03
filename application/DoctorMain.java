package application;
import java.time.LocalTime;
import java.time.format.*;
import prescription.MedicineDose;
import CustomExceptions.InputValidation;
import main.Doctor;
import main.Manager;
import main.Patient;
import java.util.*;
import javafx.util.Pair;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.geometry.*;

public class DoctorMain {
	
	CommonOperations co = new CommonOperations();
	Manager m = new Manager("Object to return patient list");
	InputValidation i = new InputValidation();
	ArrayList<MedicineDose> meds = new ArrayList<MedicineDose>();
	Patient p;
	Boolean timingsSet=false;
	ArrayList<LocalTime> removeTimeList = new ArrayList<LocalTime>();
	InputValidation iv = new InputValidation();
	static Scene scene=null;
	public int bedNum;
	public int wardNum;
	public int roomNum;
	
	
	public void patientSearch(Doctor d, BorderPane bp, String add) {
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
						p =  d.doctorSearch(m.retPatientList(),Long.parseLong(id)
								,"");
						if(p.retName()!=null)
							callFeatures(d,add,bp);
						else
							errorMsg.setText("Patient does not exist.");						
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
						p = d.doctorSearch(m.retPatientList(),-1,namePair.getValue());
						if(p.retName()!=null)
							callFeatures(d,add,bp);
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
			if(scene==null)
				scene = new Scene(map,1000,1000);
			
			scene.setOnMouseClicked(e2->{
				if(wm.retWardNumber()!=0) {
					bedNum = wm.retBedNumber();
					roomNum = wm.retRoomNumber();
					wardNum = wm.retWardNumber();
					Pair<String,Patient> patientPair = d.patientInBed(bedNum, roomNum, wardNum);
					primaryStage.close();
					if(patientPair.getKey()=="") {
						p = patientPair.getValue();
						callFeatures(d,add,bp);
					}
					else {
						errorMsg.setText("Patient not present!");
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
	
	
	private void callFeatures(Doctor d,String add, BorderPane bp) {
		Label errorMsg = co.retErrorLabel();
		if(add.contentEquals("add"))
			addPrescription(d,bp);
		else if(add.contentEquals("update")) {
			if(p.retPrescription()!=null)
				updatePrescription(d,bp);
			else
				errorMsg.setText("Add a prescription first! ");
		}
		else
			displayPatientDetails(p,bp);
	}
	
	private void addPrescription(Doctor d, BorderPane bp) {
		
		Label errorMsg = new Label();
		errorMsg.setTextFill(Color.RED);
		bp.setBottom(errorMsg);
		BorderPane.setAlignment(errorMsg, Pos.CENTER);

		if(p.retName()==null)
			errorMsg.setText("Patient not found ");
		else {
			
			// Big Wrapper
			BorderPane wrapperPane = new BorderPane();
			wrapperPane.setPadding(new Insets(20,0,0,20));
			
			
			// Layout Elements
			Button addMedicine = new Button("Add Medicine");
			ScrollPane sp = new ScrollPane();
			VBox prescriptionBox = new VBox(5);
			HBox prescriptionButtonHolder = co.addButtonHolder(bp);
			
			// Adding elements in prescription box and formatting
			errorMsg.setText("");
			prescriptionBox.getChildren().addAll(prescriptionButtonHolder,errorMsg);
			prescriptionBox.setPadding(new Insets(10,0,0,0));
			
			// Assigning elements to corresponding elements
			wrapperPane.setTop(null);
			wrapperPane.setLeft(addMedicine);
			wrapperPane.setCenter(sp);
			BorderPane.setMargin(sp, new Insets(0,0,0,20));
			wrapperPane.setBottom(prescriptionBox);
			BorderPane.setAlignment(prescriptionBox, Pos.CENTER);
			
			
			//Action Events
			addMedicine.setOnAction(e2->{
				addMedicine(d,p,sp);
			});
			
			((Button)prescriptionButtonHolder.getChildren().get(0)).setOnAction(e3->{
				d.addPrescription(p, meds);
			});
			bp.setCenter(wrapperPane);
		}
	}
	
	public void addMedicine(Doctor d, Patient p,ScrollPane sp) {
		Stage medicineStage = new Stage();
		FlowPane flowPane = new FlowPane(Orientation.VERTICAL,5,5);
		HBox buttonsBox = new HBox(10);
		
		// Labels
		Label errorMsg = new Label("\n\n\n");
		errorMsg.setTextFill(Color.RED);
		
		// Text fields
		TextField medicineNameField = new TextField();
		TextField medicineDoseField = new TextField();
		TextField medicineTimeField = new TextField();
		
		// Buttons
		Button submitButton = new Button("Submit");
		Button cancelButton = new Button("Cancel");
		
		// Adding buttons in buttonsBox
		buttonsBox.getChildren().addAll(submitButton,cancelButton);
		
		
		// FlowPane Config
		flowPane.getChildren().addAll(errorMsg,new Label("Medicine Name:"), 
				medicineNameField, new Label("Medicine Doses:"),medicineDoseField, 
				new Label("Dose Timings:"),medicineTimeField,new Label("\n\n"), 
				buttonsBox);
		flowPane.setPadding(new Insets(10,0,0,50));
		
		submitButton.setOnAction(e3->{
			errorMsg.setTextFill(Color.RED);
			
			Pair<Boolean,String> medNamePair = i.validateName(
					medicineNameField.getText(),true);
			String medTimesText = medicineTimeField.getText();
			if(!co.checkBlankFields(medTimesText))
				errorMsg.setText("All fields are manadatory!\n\n\n");
			else if (medNamePair.getKey()==false)
				errorMsg.setText(medNamePair.getValue());
			else {
				
				try {
					int medDose = Integer.parseInt(
							medicineDoseField.getText());
					
					String[] medTimesString = medTimesText.split(",");
					
					if(medTimesString.length==medDose) {
						ArrayList<LocalTime> medTimes = new ArrayList<LocalTime>();
						for(int i=0;i<medTimesString.length;i++) {
							medTimes.add(LocalTime.parse(medTimesString[i].strip()));
						}

						meds.add(new MedicineDose(medNamePair.getValue(), medDose, medTimes));
						displayMedicineBlock(sp);
						medicineStage.close();
					}
					
					else
						errorMsg.setText("Dose timings are less than"
								+ " number of doses.\n\n\n");
				}catch(NumberFormatException exception) {
					errorMsg.setText("Number of doses is incorrect.\n\n\n");
				}catch(DateTimeParseException exception) {
					errorMsg.setText("Please enter time in format HH:MM!\n\n\n");
				}
			}
		});
		
		cancelButton.setOnAction(e->{
			medicineStage.close();
		});
		
		medicineStage.setScene(new Scene(flowPane,300,500));
		medicineStage.show();
	}
		
	
	public void displayMedicineBlock(ScrollPane sp) {
		VBox medicineBox = new VBox(10);
		
		for(MedicineDose med: meds) {
			String label = "Medicine Name:"+med.retName()+"\nNumber of Doses: "
					+med.retDose();
			String medTimings = "";
			for(LocalTime time: med.retTimes())
				medTimings=medTimings+" "+time.toString();
			medicineBox.getChildren().add(new Label(label+"\nDose Timings: "+medTimings));
		}
		
		sp.setContent(medicineBox);
	}
	
	
	public void updatePrescription(Doctor d,BorderPane bp) {		
		// Big Wrapper
		BorderPane wrapperPane = new BorderPane();
		
		//Layout
		ComboBox<String> medicineName = new ComboBox<String>();
		
		// Adding elements to medicineName
		for(MedicineDose md: p.retPrescription().retMedicineBlock().retMedicines())
			medicineName.getItems().add(md.retName());
		
		
		// Common error message
		Label errorMsg = new Label();
		errorMsg.setTextFill(Color.RED);
		
		// Layout Items
		GridPane detailsPane = new GridPane();
		CheckBox nameBox = new CheckBox("Medicine Name");
		CheckBox doseBox = new CheckBox("Medicine Dose");
		CheckBox timeBox = new CheckBox("Medicine Time");
		TextField nameUpdateField = new TextField();
		TextField timeUpdateField = new TextField();
		TextField doseUpdateField = new TextField();
		HBox buttonHolder = co.addButtonHolder(bp);
		HashMap<String,String> updateItems = new HashMap<String,String>();
		ComboBox<String> timeDrop = new ComboBox<String>();
		
		detailsPane.add(nameBox, 1, 1);
		detailsPane.add(nameUpdateField, 2, 1);
		detailsPane.add(doseBox, 1, 2);
		detailsPane.add(doseUpdateField, 2, 2);
		detailsPane.add(timeBox, 1, 3);
		detailsPane.add(timeDrop, 2, 3);
		detailsPane.add(timeUpdateField, 3, 3);
		detailsPane.add(buttonHolder, 2, 4);
		
		nameBox.setVisible(false);
		doseBox.setVisible(false);
		timeBox.setVisible(false);
		nameUpdateField.setVisible(false);
		doseUpdateField.setVisible(false);
		timeUpdateField.setVisible(false);
		timeDrop.setVisible(false);
		
		// Toggling visibility of text fields by check box selection
		nameBox.setOnAction(e3->{
			if(nameBox.isSelected())
				nameUpdateField.setVisible(true);
			else
				nameUpdateField.setVisible(false);
		});
		
		doseBox.setOnAction(e3->{
			if(doseBox.isSelected())
				doseUpdateField.setVisible(true);
			else
				doseUpdateField.setVisible(false);
		});
		
		timeBox.setOnAction(e3->{
			if(timeBox.isSelected()) {
				timeUpdateField.setVisible(true);
				timeDrop.setVisible(true);
			}
				
			else {
				timeUpdateField.setVisible(false);
				timeDrop.setVisible(false);
			}
		});
		
		((Button) buttonHolder.getChildren().get(0)).setOnAction(e3->{
			int medicineIndex = medicineName.getSelectionModel()
					.getSelectedIndex();
			ArrayList<LocalTime> timeList = p.retPrescription().
					retMedicineBlock().retMedicines().get(medicineIndex).retTimes();
			
			if(medicineIndex==-1) {
				errorMsg.setText("Please select a medicine name first");
			}
			
			else if(!nameBox.isVisible()) {
				nameBox.setVisible(true);
				doseBox.setVisible(true);
				timeBox.setVisible(true);
				medicineName.setVisible(false);
				timeDrop.getItems().removeAll();
				for(LocalTime t: timeList) {
					timeDrop.getItems().add(t.toString());
				}
			}
			
			else {
				try {
					if(nameBox.isSelected()) {
						String newName = nameUpdateField.getText();									
						if(newName!="") {
							Pair<Boolean,String> namePair = i.validateName(newName,false);
							if(!namePair.getKey())
								errorMsg.setText(namePair.getValue());
							else
								updateItems.put("Name", newName+"");
						}else
							updateItems.put("Name", "");
						
					}else
						updateItems.put("Name", "");
					
					if(timeBox.isSelected()) {
						String newTime = timeUpdateField.getText();	
						Boolean flag=true;
						if(newTime!="") {
							LocalTime newTime2= LocalTime.parse(newTime);
							
							for (LocalTime t: timeList)
								if(newTime2==t) {
									errorMsg.setText("The new time is already present!");
									updateItems.put("Time","");
									flag=false;
								}
									
							if(flag)
								updateItems.put("Time", newTime+"#"+timeDrop.getSelectionModel()
								.getSelectedIndex());
						}
					}
					else
						updateItems.put("Time", "");	
					
					d.updatePrescription(medicineIndex,updateItems.get("Name"),updateItems.get("Time"),p);
					
					if(doseBox.isSelected()) {
						int newDoses = Integer.parseInt(doseUpdateField.getText().strip());
						
						if(newDoses>0) {
							int oldDoses = p.retPrescription().retMedicineBlock()
									.retMedicineDose(medicineIndex).retDose();
							if(oldDoses<newDoses) 
								changeDose(medicineIndex,oldDoses,newDoses,timeList,true,bp);
							if(oldDoses>newDoses)
								changeDose(medicineIndex,oldDoses,newDoses,timeList,false,bp);
						}
						
					}

					// Clearing fields after submission
					co.clearAllFields(nameUpdateField,timeUpdateField,doseUpdateField);
					timeBox.setSelected(false);
					nameBox.setSelected(false);
					doseBox.setSelected(false);
					timeDrop.setVisible(false);
					doseUpdateField.setVisible(false);
					
				}catch(NumberFormatException exception) {
					errorMsg.setText("Expected numeric characters in doses.");
				}catch(DateTimeParseException exception) {
					errorMsg.setText("Please enter time in format HH:MM");
				}
			}
		});
				
		// DetailsPane grid Formatting
		detailsPane.setHgap(20);
		detailsPane.setVgap(20);
		
		wrapperPane.setTop(medicineName);
		wrapperPane.setCenter(detailsPane);
		wrapperPane.setBottom(errorMsg);
		BorderPane.setAlignment(errorMsg, Pos.CENTER);
		
		bp.setCenter(wrapperPane);
		bp.setBottom(null);
		bp.setRight(null);		
	}
	
	//Function to update timings after dose updation
	private void changeDose(int index, int oldDose, int newDose, ArrayList<LocalTime> timeList,
			boolean increased, BorderPane bp) {
		// Initiliazing stage
		Stage primaryStage = new Stage();
		
		// Layout elements
		VBox doseBox = new VBox(30);
		HBox buttonHolder = new HBox(10);
		HBox inputHolder = new HBox(10);
		Button submit = new Button("Submit");
		Button cancel = new Button("Cancel");
		Label errorMsg = new Label();
		
		MedicineDose md = p.retPrescription().retMedicineBlock().
				retMedicineDose(index);
			
		TextField timeField = new TextField();
		ComboBox<String> timeBox = new ComboBox<String>();
		
		if(increased)
			inputHolder.getChildren().addAll(new Label("Add "+(newDose-oldDose)
					+"\nmore timings"),timeField);
		
		else {
			inputHolder.getChildren().addAll(new Label("Delete "+(oldDose-newDose)
					+"\nmore timings"),timeBox);
			for(LocalTime t: timeList)
				timeBox.getItems().add(t.toString());
		}
		
		// Adding elements in HBox
		buttonHolder.getChildren().addAll(submit,cancel);

		//Adding elements to VBox
		doseBox.getChildren().addAll(inputHolder,buttonHolder,errorMsg);
		
		((Button) buttonHolder.getChildren().get(0)).setOnAction(e->{
			errorMsg.setTextFill(Color.RED);
			if(increased) {
				try {
					boolean flag =true;
					String[] timings = timeField.getText().split(",");
					
					if(timings.length!=(newDose - oldDose)) {
						errorMsg.setText("Number of times is not equal to doses.");
					}
					
					else {
						ArrayList<LocalTime> newTime = new ArrayList<LocalTime>();
						for(int i=0;i<timings.length;i++) {
							LocalTime validatedTime = LocalTime.parse(timings[i]);
							for(LocalTime t: timeList)
								if(t==validatedTime) {
									errorMsg.setText(t+" is already present, enter again.");
									flag=false;
									break;
								}
							if(!flag)
								break;
							newTime.add(validatedTime);
						}
							
						if(flag && newTime.size()==(newDose - oldDose)) {
							md.addTime(newTime);
							md.setDose(newDose);
							errorMsg.setTextFill(Color.GREEN);
							primaryStage.close();
						}
					}
									
				}catch(DateTimeParseException exception) {
					errorMsg.setText("\n\n\n\nPlease enter time in format HH:MM");
				}
			}
			
			else {
				if(oldDose==newDose) {
					md.setDose(newDose);
					md.removeTime(removeTimeList);
					errorMsg.setTextFill(Color.GREEN);
					errorMsg.setText("Added Successfully!");
					primaryStage.close();
				}
				
				else {
					int selectedIndex = timeBox.getSelectionModel().getSelectedIndex();
					if(selectedIndex==-1) 
						errorMsg.setText("Please select a time to be deleted.");
					else {
						removeTimeList.add(timeList.get(selectedIndex));
						timeList.remove(selectedIndex);
						changeDose(index,newDose,oldDose-1,timeList,false,bp);
						primaryStage.close();
					}
					
				}
			}
		});
	
		// Formatting VBox
		doseBox.setAlignment(Pos.CENTER);
		doseBox.setPadding(new Insets(30,0,0,20));
		
		primaryStage.setScene(new Scene(doseBox,300,200));	
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
