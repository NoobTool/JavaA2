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
import java.util.*;
import javafx.util.Pair;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.text.*;
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
		int wrapperBoxHeight =800;
		int wrapperBoxWidth =800;
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
				(wardBoxPaddingBottom+wardBoxPaddingTop)*2))/10;
		int roomBoxWidth = (wardBoxWidth-(
				(wardBoxPaddingLeft+wardBoxPaddingRight)*2))/10;
		
		// Adding elements in wards
		HBox roomsBox1 = new HBox(20);
		
		
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
		
		// Adding beds in rooms
		roomsBox1.getChildren().addAll(roomBox1,roomBox2);
		
		// Adding elements in wrapper box
		wrapperBox.getChildren().addAll(wardBox1,wardBox2);
		
		// Formatting wrapperBox
		wrapperBox.setPadding(new Insets(wrapperBoxPaddingTop,wrapperBoxPaddingRight,
				wrapperBoxPaddingBottom,wrapperBoxPaddingLeft));
		
		primaryStage.setScene(new Scene(wrapperBox,wrapperBoxWidth,wrapperBoxHeight));
		primaryStage.show();
	}
}