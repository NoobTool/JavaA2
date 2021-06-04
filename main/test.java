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

public class test {
	
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
	
	public static void main(String args[]) {
		LocalDate date = LocalDate.now();
		String d = date.format(DateTimeFormatter.ofPattern("dd-MM-YYYY"));
		String[] dList = d.split("-");
		
		print(date);
		
	}
}