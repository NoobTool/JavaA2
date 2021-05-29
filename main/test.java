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
		LocalTime currentTime = LocalTime.now();
		LocalTime shiftEnd = LocalTime.parse("00:00");
		
		print(currentTime.until(shiftEnd, ChronoUnit.MINUTES)+(24*60)>=0);
		print(currentTime.until(shiftEnd, ChronoUnit.MINUTES)+(24*60)<=60);
		
	}
}