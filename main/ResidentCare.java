package main;
import java.util.Scanner;

public class ResidentCare {
	
	public String inputString() {
		Scanner scan = new Scanner(System.in);
		return scan.nextLine();
	}
	
	public int inputInt() {
		Scanner scan = new Scanner(System.in);
		return scan.nextInt();
	}
	
	
	
	public static void main(String args[]) {
		
		// Variable Initialization
		int choice=0;
		// Generating staff lists for all types of people
	
		
		// Objects for displaying options
		OptionSequence oq = new OptionSequence();
		
		ResidentCare rc = new ResidentCare();
		System.out.println("1. Login as Manager");
		System.out.println("2. Login as Doctor");
		System.out.println("3. Login as Nurse");
		System.out.println("Enter your choice!");
		choice=rc.inputInt();
		switch(choice) {
			case 1: 
					oq.managerSequence();
					break;
					
		}
	}
}
