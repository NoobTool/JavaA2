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
	final int rooms =4;
	final int room_size=4;
	final int dual_rooms=1;
	final int dual_room_size=2;
	final int single_room=1;
	final int single_room_size=1;
	
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
		
		// Adding elements in wards
		HBox roomsBox1 = new HBox(50);
		HBox roomsBox2 = new HBox(50);
		HBox roomsBox3 = new HBox(50);
		
		// Room parameters
		int roomBoxHeight = (wardBoxHeight-(
				(wardBoxPaddingBottom+wardBoxPaddingTop)*2))/7;
		int roomBoxWidth = (wardBoxWidth-(
				(wardBoxPaddingLeft+wardBoxPaddingRight)*2))/7;

		// roomsBox HBox Parameters
		int totalRooms = rooms+dual_rooms+single_room;
		if(totalRooms%2!=0)
			totalRooms+=1;
		int no_of_rows = (totalRooms)/2;
		int totalLength = roomBoxHeight*no_of_rows;
		
		int roomsBoxPaddingTop = (wardBoxHeight-totalLength)/(no_of_rows+1);
		int roomsBoxPaddingRight = 20;
		int roomsBoxPaddingBottom = 0;
		int roomsBoxPaddingLeft = 20;
		
		// Formatting RoomsBox HBox
		roomsBox1.setPadding(new Insets(roomsBoxPaddingTop,roomsBoxPaddingRight,
				roomsBoxPaddingBottom,roomsBoxPaddingLeft));
		roomsBox1.setAlignment(Pos.CENTER);
		
		roomsBox2.setPadding(new Insets(roomsBoxPaddingTop,roomsBoxPaddingRight,
				roomsBoxPaddingBottom,roomsBoxPaddingLeft));
		roomsBox2.setAlignment(Pos.CENTER);
		
		roomsBox3.setPadding(new Insets(roomsBoxPaddingTop,roomsBoxPaddingRight,
				roomsBoxPaddingBottom,roomsBoxPaddingLeft));
		roomsBox3.setAlignment(Pos.CENTER);
		
		
		
		// Creating Rooms
		VBox roomBox1 = new VBox();
		roomBox1.setPrefSize(roomBoxWidth, roomBoxHeight);
		roomBox1.setMaxHeight(roomBoxWidth);
		roomBox1.setMaxWidth(roomBoxHeight);
		roomBox1.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID
				,null,new BorderWidths(2))));
		
		VBox roomBox2 = new VBox();
		roomBox2.setPrefSize(roomBoxWidth, roomBoxHeight);
		roomBox2.setMaxHeight(roomBoxWidth);
		roomBox2.setMaxWidth(roomBoxHeight);
		roomBox2.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID
				,null,new BorderWidths(2))));
		
		VBox roomBox3 = new VBox();
		roomBox3.setPrefSize(roomBoxWidth, roomBoxHeight);
		roomBox3.setMaxHeight(roomBoxWidth);
		roomBox3.setMaxWidth(roomBoxHeight);
		roomBox3.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID
				,null,new BorderWidths(2))));
		
		VBox roomBox4 = new VBox();
		roomBox4.setPrefSize(roomBoxWidth, roomBoxHeight);
		roomBox4.setMaxHeight(roomBoxWidth);
		roomBox4.setMaxWidth(roomBoxHeight);
		roomBox4.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID
				,null,new BorderWidths(2))));
		
		
		VBox roomBox5 = new VBox();
		roomBox5.setPrefSize(roomBoxWidth, roomBoxHeight);
		roomBox5.setMaxHeight(roomBoxWidth);
		roomBox5.setMaxWidth(roomBoxHeight);
		roomBox5.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID
				,null,new BorderWidths(2))));
		
		VBox roomBox6 = new VBox();
		roomBox6.setPrefSize(roomBoxWidth, roomBoxHeight);
		roomBox6.setMaxHeight(roomBoxWidth);
		roomBox6.setMaxWidth(roomBoxHeight);
		roomBox6.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID
				,null,new BorderWidths(2))));
		
		
		// Adding beds in rooms
		roomsBox1.getChildren().addAll(roomBox1,roomBox2);
		roomsBox2.getChildren().addAll(roomBox3,roomBox4);
		roomsBox3.getChildren().addAll(roomBox5,roomBox6);
		
		// Adding rooms to wards
		wardBox1.getChildren().addAll(roomsBox1,roomsBox2,roomsBox3);
		
		// Adding elements in wrapper box
		wrapperBox.getChildren().addAll(wardBox1,wardBox2);
		
		// Formatting wrapperBox
		wrapperBox.setPadding(new Insets(wrapperBoxPaddingTop,wrapperBoxPaddingRight,
				wrapperBoxPaddingBottom,wrapperBoxPaddingLeft));
		
		VBox selectedRoom = ((VBox)((HBox)wardBox1.getChildren().get(2)).getChildren().get(0));
		
		selectedRoom.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

		     @Override
		     public void handle(MouseEvent event) {
		         System.out.println("Tile pressed ");
		         event.consume();
		     }
		});
		
		primaryStage.setScene(new Scene(wrapperBox,wrapperBoxWidth,wrapperBoxHeight));
		primaryStage.show();
	}
}