package application;

import java.util.ArrayList;
import main.Manager;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.Patient;

public class WardMap extends Application{
	final int wards = 2;
	final int nRooms = 4;
	final int room_size=4;
	final int nDouble=1;
	final int dual_room_size=2;
	final int single_room=1;
	final int nSingle=1;
	final int totalBeds = nSingle + (2*nDouble)+ (4*nRooms);
	
	//static HBox wrapperBox = new HBox();
	
	int bedNumber;
	int roomNumber;
	int WardNumber;
	
	static ArrayList<Label> bedList = new ArrayList<Label>();
	static HBox wrapperBox;
	
	public static void print(Object o) {
		System.out.println(o);
	}
	
	public static void print() {
		System.out.println();
	}
	
	public WardMap() {
		start(new Stage());
	}
	
	public WardMap(ArrayList<Patient> patients) {
		int index;
		for(Patient p: patients) {
			print(p.retName());
			index = calculateIndex(p.retRoomNumber())+p.retBedNumber()-1;
			if(index!=-1) {
				char gender = p.retGender();
				changeBg(index, gender);
			}
		}
	}
	
	public WardMap(String s) {}
	
	public void start(Stage primaryStage) {
			
		wrapperBox = new HBox(50);
		
		// Layout params
		VBox vbox = new VBox();
		
		// Wrapper Box parameters
		int wrapperBoxHeight =1000;
		int wrapperBoxWidth =1000;
		int wrapperBoxPaddingTop=20;
		int wrapperBoxPaddingRight=20;
		int wrapperBoxPaddingBottom=20;
		int wrapperBoxPaddingLeft=20;
		
		
		// WardBox parameters
		int wardBoxHeight = wrapperBoxHeight-(
				(wrapperBoxPaddingBottom+wrapperBoxPaddingTop)*2);
		int wardBoxWidth = wrapperBoxWidth-(
				(wrapperBoxPaddingLeft+wrapperBoxPaddingRight)*2);
		int wardBoxPaddingTop = 10;
		int wardBoxPaddingRight = 10;
		int wardBoxPaddingBottom = 10;
		int wardBoxPaddingLeft = 10;
		
		
		//WardBox Creation
		VBox wardBox1 = new VBox();
		wardBox1.setPrefSize(wardBoxWidth, wardBoxHeight);
		wardBox1.setMaxHeight(wardBoxWidth);
		wardBox1.setMaxWidth(wardBoxHeight);
		wardBox1.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID
				,null,new BorderWidths(3))));
		
		VBox wardBox2 = new VBox();
		wardBox2.setPrefSize(wardBoxWidth, wardBoxHeight);
		wardBox2.setMaxHeight(wardBoxWidth);
		wardBox2.setMaxWidth(wardBoxHeight);
		wardBox2.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID
				,null,new BorderWidths(3))));
		

		// Room parameters
		int roomBoxHeight = (wardBoxHeight-(
				(wardBoxPaddingBottom+wardBoxPaddingTop)*2))/7;
		int roomBoxWidth = (wardBoxWidth-(
				(wardBoxPaddingLeft+wardBoxPaddingRight)*2))/7;

		// nRoomsBox HBox Parameters
		int totalRooms = nRooms+nDouble+nSingle;
		if(totalRooms%2!=0)
			totalRooms+=1;
		int no_of_rows = (totalRooms)/2;
		int totalLength = roomBoxHeight*no_of_rows;
		
		int nRoomsBoxPaddingTop = (wardBoxHeight-totalLength)/(no_of_rows+1);
		int nRoomsBoxPaddingRight = 20;
		int nRoomsBoxPaddingBottom = (wardBoxHeight-totalLength)/(no_of_rows+1);
		int nRoomsBoxPaddingLeft = 40;
		
		// Creating nRooms		
		GridPane ward1Pane = new GridPane();

		int k = 0;
		for(int i=0;i<totalRooms/2;i++) {
			for(int j=0;j<2;j++) {		
				if(k<nSingle) {
					vbox = add1Room(roomBoxWidth,roomBoxHeight);
					++k;
				}
				else if(k<nDouble+nSingle) {
					vbox = add2Rooms(roomBoxWidth,roomBoxHeight);
					++k;
				}
				else {
					vbox = add4Rooms(roomBoxWidth,roomBoxHeight);
					++k;
				}
				ward1Pane.add(vbox, j, i);
			}
		}
		
		// Ward1Pane formatting 
		ward1Pane.setPadding(new Insets(nRoomsBoxPaddingTop,nRoomsBoxPaddingRight,
				nRoomsBoxPaddingBottom,nRoomsBoxPaddingLeft));
		ward1Pane.setHgap(100);
		ward1Pane.setVgap((wardBoxHeight-totalLength)/(no_of_rows+1));
		
		// Adding elements in wardBoxes
		wardBox1.getChildren().add(ward1Pane);
		
		GridPane ward2Pane = new GridPane();

		k = 0;
		for(int i=0;i<totalRooms/2;i++) {
			for(int j=0;j<2;j++) {		
				if(k<nSingle) {
					vbox = add1Room(roomBoxWidth,roomBoxHeight);
					++k;
				}
				else if(k<nDouble+nSingle) {
					vbox = add2Rooms(roomBoxWidth,roomBoxHeight);
					++k;
				}
				else {
					vbox = add4Rooms(roomBoxWidth,roomBoxHeight);
					++k;
				}
				ward2Pane.add(vbox, j, i);
			}
		}
		
		// Ward1Pane formatting 
		ward2Pane.setPadding(new Insets(nRoomsBoxPaddingTop,nRoomsBoxPaddingRight,
				nRoomsBoxPaddingBottom,nRoomsBoxPaddingLeft));
		ward2Pane.setHgap(100);
		ward2Pane.setVgap((wardBoxHeight-totalLength)/(no_of_rows+1));
		
		// Adding elements in wardBoxes
		wardBox2.getChildren().add(ward2Pane);
		
		
		// Adding elements in wrapper box
		wrapperBox.getChildren().addAll(wardBox1,wardBox2);
		
		// Formatting wrapperBox
		wrapperBox.setPadding(new Insets(wrapperBoxPaddingTop,wrapperBoxPaddingRight,
				wrapperBoxPaddingBottom,wrapperBoxPaddingLeft));
		
//		primaryStage.setScene(new Scene(wrapperBox,wrapperBoxWidth,wrapperBoxHeight));
//		primaryStage.show();
	}
	
	
	public Label addBeds() {
		Label shape = new Label();
		shape.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID
				,null,new BorderWidths(3))));
		shape.setPrefSize(30,30);
		shape.setMaxHeight(30);
		shape.setMaxWidth(30);		
		return shape;
	}
	
	// Add a room with 1 bed
	public VBox add1Room(int width, int height) {
		
		VBox shape = new VBox();
		shape.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID
				,null,new BorderWidths(3))));
		shape.setPrefSize(width, height);
		shape.setMaxHeight(width);
		shape.setMaxWidth(height);	
		
		GridPane gp = new GridPane();
		Label bed = addBeds();
		bedList.add(bed);
		gp.add(bed,0,0);
		addEvents(bed);
		
		gp.setPadding(new Insets(15,15,15,15));
		
		shape.getChildren().add(gp);
		return shape;
	}
	
	// Add a room with 2 beds
	public VBox add2Rooms(int width, int height) {
		
		VBox shape = new VBox();
		shape.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID
				,null,new BorderWidths(3))));
		shape.setPrefSize(width, height);
		shape.setMaxHeight(width);
		shape.setMaxWidth(height);	
		
		GridPane gp = new GridPane();
		
		
		for (int j=0;j<2;j++) {
			Label bed = addBeds();
			bedList.add(bed);
			gp.add(bed, j, 0);
			addEvents(bed);
		}
		
		gp.setHgap(30);
		gp.setVgap(30);
		gp.setPadding(new Insets(15,0,0,15));
		
		shape.getChildren().add(gp);
		return shape;
}
	
	// Add a room wth 4 beds
	public VBox add4Rooms(int width, int height) {
		
		VBox shape = new VBox();
		shape.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID
				,null,new BorderWidths(3))));
		shape.setPrefSize(width, height);
		shape.setMaxHeight(width);
		shape.setMaxWidth(height);	
		
		GridPane gp = new GridPane();
		
		for(int i=0;i<2;i++) {
			for (int j=0;j<2;j++) {
				Label bed = addBeds();
				bedList.add(bed);
				gp.add(bed, i, j);
				addEvents(bed);
			}
		}
		
		gp.setHgap(30);
		gp.setVgap(30);
		gp.setPadding(new Insets(15,0,0,15));
		shape.getChildren().add(gp);
		return shape;
	}
	
	public void addEvents(Label v) {
		v.setOnMouseClicked(e->{
						
			int ward=1,room=0,operand=1,temp = bedList.indexOf(v)+1;
			
			if(temp>totalBeds) {
				ward=2;
				temp-=totalBeds;
			}
			
			
			for(int i=0;i<nSingle;i++) {
				temp-=1;
				if(temp==0)
					room=i+1;
			}
			
			if(temp>0) {
				for(int i=0;i<nDouble;i++) {
					temp-=2;
					if(temp<=0) {
						room=i+1+nSingle;
						operand=2;
						break;
					}
						
				}
			}
			
			if(temp>0) {
				for(int i=0;i<nRooms;i++) {
					temp-=4;
					if(temp<=0) {
						room=i+1+nSingle+nDouble;
						operand=4;
						break;
					}
				}
			}
			
			print("Ward Number is"+ward+" Room Number is "+room+" Bed Number is "+(temp+operand));			
		});
	}
	
	public void changeBg(int index, char gender) {
		if(gender=='M')
			((Label)bedList.get(index)).setStyle("-fx-background-color:blue");
		else
			((Label)bedList.get(index)).setStyle("-fx-background-color:red");
	}
	
	public int calculateIndex(int roomNumber) {
		
		int index=-1,k=0;
		
		while(roomNumber>0 && k<nSingle) {
			roomNumber-=1;
			index+=1;
			k+=1;
		}
		
		k=0;
		while(roomNumber>0 && k<nDouble) {
			roomNumber-=1;
			index+=2;
			k+=1;
		}
		index-=1;
		
		k=0;
		while(roomNumber>0 && k<nRooms) {
			roomNumber-=1;
			index+=4;
			k+=1;
		}
		index-=2;
		
		if(roomNumber==0)
			return index;
		else
			return -1;
	}
	
	
	// Getter Functions
	public HBox retMap() {
		return wrapperBox;
	}
	
}
