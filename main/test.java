package main;

import java.time.*;
import java.time.format.DateTimeFormatter;
import CommonSnippets.CommonCodes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.time.temporal.ChronoUnit;

public class test {
	
	public static void print(Object o) {
		System.out.println(o);
	}
	
	public static void print() {
		System.out.println();
	}
	
	public static void main(String args[]) {
		LocalTime currentTime = LocalTime.parse("23:30");
		LocalTime shiftEnd = LocalTime.parse("05:00");
		
		if(currentTime.until(shiftEnd, ChronoUnit.MINUTES)+(24*60)>=300)
			print(currentTime.until(shiftEnd, ChronoUnit.MINUTES)+(24*60));
	}
}