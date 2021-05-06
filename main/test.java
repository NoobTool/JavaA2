package main;

import java.time.LocalTime;
import CommonSnippets.CommonCodes;
import java.util.ArrayList;
import java.util.Collections;
import java.time.temporal.ChronoUnit;

public class test {
	
	public static void main(String args[]) {
		LocalTime ya = LocalTime.parse("16:20");
		System.out.println(Math.abs(ya.until(LocalTime.now(),ChronoUnit.MINUTES))<1);
	}	
}