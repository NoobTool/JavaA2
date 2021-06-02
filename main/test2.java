package main;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import CommonSnippets.CommonCodes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;
import java.time.temporal.ChronoUnit;
import javafx.event.EventHandler;
import javafx.util.Pair;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.text.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.geometry.*;
import CommonSnippets.*;
import CustomExceptions.*;
import Actions.Action;

public class test2 extends Application{
	
	final int wards = 2;
	final int rooms = 4;
	final int room_size=4;
	final int nDouble=1;
	final int dual_room_size=2;
	final int single_room=1;
	final int nSingle=1;
	
	ArrayList<VBox> roomList = new ArrayList<VBox>();
	ArrayList<Label> bedList = new ArrayList<Label>();
	
	public static void print(Object o) {
		System.out.println(o);
	}
	
	public static void print() {
		System.out.println();
	}
	
	public void start(Stage primaryStage) {
		
		// Wrapper
		HBox wrapperBox = new HBox(50);
		
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

		// roomsBox HBox Parameters
		int totalRooms = rooms+nDouble+nSingle;
		if(totalRooms%2!=0)
			totalRooms+=1;
		int no_of_rows = (totalRooms)/2;
		int totalLength = roomBoxHeight*no_of_rows;
		
		int roomsBoxPaddingTop = (wardBoxHeight-totalLength)/(no_of_rows+1);
		int roomsBoxPaddingRight = 20;
		int roomsBoxPaddingBottom = (wardBoxHeight-totalLength)/(no_of_rows+1);
		int roomsBoxPaddingLeft = 40;
		
		// Creating rooms		
		GridPane ward1Pane = new GridPane();

		for(int i=0;i<3;i++) {
			for(int j=0;j<2;j++) {
				VBox vbox = add4Rooms(roomBoxWidth,roomBoxHeight);
				ward1Pane.add(vbox, j, i);
				roomList.add(vbox);
			}
		}
		
		// Ward1Pane formatting 
		ward1Pane.setPadding(new Insets(roomsBoxPaddingTop,roomsBoxPaddingRight,
				roomsBoxPaddingBottom,roomsBoxPaddingLeft));
		ward1Pane.setHgap(100);
		ward1Pane.setVgap((wardBoxHeight-totalLength)/(no_of_rows+1));
		
		// Adding elements in wardBoxes
		wardBox1.getChildren().add(ward1Pane);
		
		GridPane ward2Pane = new GridPane();

		VBox vbox = new VBox();
		int k = 0;
		for(int i=0;i<3;i++) {
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
				roomList.add(vbox);
			}
		}
		
		// Ward1Pane formatting 
		ward2Pane.setPadding(new Insets(roomsBoxPaddingTop,roomsBoxPaddingRight,
				roomsBoxPaddingBottom,roomsBoxPaddingLeft));
		ward2Pane.setHgap(100);
		ward2Pane.setVgap((wardBoxHeight-totalLength)/(no_of_rows+1));
		
		// Adding elements in wardBoxes
		wardBox2.getChildren().add(ward2Pane);
		
		
		// Adding elements in wrapper box
		wrapperBox.getChildren().addAll(wardBox1,wardBox2);
		
		// Formatting wrapperBox
		wrapperBox.setPadding(new Insets(wrapperBoxPaddingTop,wrapperBoxPaddingRight,
				wrapperBoxPaddingBottom,wrapperBoxPaddingLeft));

		
		primaryStage.setScene(new Scene(wrapperBox,wrapperBoxWidth,wrapperBoxHeight));
		primaryStage.show();
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
//			v.setBorder(new Border(new BorderStroke(Color.RED,BorderStrokeStyle.SOLID
//					,null,new BorderWidths(3))));
			v.setStyle("-fx-background-color: red;");
			int temp = bedList.indexOf(v)+1;
		
		});
	}
	
	
	
	
}