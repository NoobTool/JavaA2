package main;

import java.time.*;
import java.time.format.DateTimeFormatter;
import CommonSnippets.CommonCodes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.time.temporal.ChronoUnit;

public class test {
	
	public void functionCall() {}
	
	public static void main(String args[]) {
		LocalTime start_time = LocalTime.parse("09:00");
		LocalTime end_time = LocalTime.parse("09:00");
		
		System.out.println(start_time.format(DateTimeFormatter.ofPattern("HH:mm")));
	}	
}