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
		ArrayList<LocalTime> times = new ArrayList<LocalTime>();
		times.add(LocalTime.parse("09:00"));
		times.add(LocalTime.parse("14:10"));
		times.add(LocalTime.parse("14:00"));
		for (int i=0;i<3;i++) {
			System.out.println(times.get(i));
			if(times.get(i).until(LocalTime.parse("16:00"),
					ChronoUnit.HOURS)<5)
			continue;
			else
				System.out.println("Ya");
			System.out.println(times.get(i));
		}
	}	
}