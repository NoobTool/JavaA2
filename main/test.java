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

public class test extends Application{
	
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
		HBox canvas = new HBox(30);
		
		// Canvas Parameters
		int canvasWidth = 800;
		int canvasHeight = 800;
		int canvasPaddingTop=100;
		int canvasPaddingRight=100;
		int canvasPaddingBottom=100;
		int canvasPaddingLeft=100;
		
		// Ward Padding Parameters
		// Canvas Parameters
		int wardPaddingTop=40;
		int wardPaddingRight=40;
		int wardPaddingBottom=40;
		int wardPaddingLeft=40;
		
		int wardRectangleWidth = (canvasWidth-(canvasPaddingRight+canvasPaddingLeft))/wards;
		int wardRectangleHeight= (canvasHeight-(canvasPaddingTop+canvasPaddingBottom));
		
		int roomRectangleWidth = (wardRectangleWidth-(wardPaddingRight+wardPaddingLeft))/wards;
		int roomRectangleHeight= (wardRectangleHeight-(wardPaddingTop+wardPaddingBottom));
		
		// Layout Elements
		Rectangle ward1 = new Rectangle(1,1,wardRectangleWidth,wardRectangleHeight);
		Rectangle ward2 = new Rectangle(1,1,wardRectangleWidth,wardRectangleHeight);
		Rectangle room1 = new Rectangle(1,1,roomRectangleWidth,roomRectangleHeight);
		Rectangle room2 = new Rectangle(1,1,roomRectangleWidth,roomRectangleHeight);
		
		
		// Adding elements to wrapper
		canvas.getChildren().addAll(ward1,ward2);
		
		
		// Formatting Wards
		ward1.setFill(Color.TRANSPARENT);
		ward1.setStroke(Color.BLACK);
		ward1.setStrokeWidth(3);
		
		ward2.setFill(Color.TRANSPARENT);
		ward2.setStroke(Color.BLACK);
		ward2.setStrokeWidth(3);
		
		
		//Formatting wrapper
		canvas.setPadding(new Insets(canvasPaddingTop,canvasPaddingRight,
				canvasPaddingBottom,canvasPaddingLeft));
		
		ward1.setOnMouseClicked(e->{
			print("Ward 1");
		});
		
		ward2.setOnMouseClicked(e->{
			print("Ward 2");
		});
		
		primaryStage.setScene(new Scene(canvas,canvasWidth,canvasHeight));
		primaryStage.show();
	}
}