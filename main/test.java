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
		final int MAX_MINUTES=360;
		LocalTime shiftStart = LocalTime.parse("20:00");
		LocalTime currentTime = LocalTime.parse("20:30");
		LocalTime shiftEnd = LocalTime.parse("02:00");
		print(Math.abs(currentTime.until(shiftEnd,ChronoUnit.HOURS)+24));
	
	}
}