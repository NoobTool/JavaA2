package main;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

class HeyException extends Exception {
	public HeyException(){}
}

public class test {
	
	public void trial(int n) {
		try {
			if(n>4) {
				System.out.println("Done");
				return;
			}
				
			else
				throw new HeyException();
		}catch(HeyException e) {
			System.out.println("n is "+n);
			trial(n+1);
		}
	}
	
	public static void main(String args[]) {
		test t = new test();
		t.trial(1);
	}
	
	
}