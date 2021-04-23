package main;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CommonCodes {
	public int inputInt() {
		while(true) {	
			try {
				Scanner scan =  new Scanner(System.in);
				int a = scan.nextInt();
				return a;
			}catch(InputMismatchException e) {
				System.out.println("Expected numeric input, enter again!");
			}
		}
	}
	
	public double inputDouble() {
		while(true) {	
			try {
				Scanner scan =  new Scanner(System.in);
				double a = scan.nextDouble();
				return a;
			}catch(InputMismatchException e) {
				System.out.println("Expected decimal input, enter again!");
			}
		}
	}
	
	public String inputString() {
		while(true) {	
			try {
				Scanner scan =  new Scanner(System.in);
				String a = scan.nextLine();
				return a;
			}catch(InputMismatchException e) {
				System.out.println("Expected alphabetical input, enter again!");
			}
		}
	}
	
	public char inputChar() {
		while(true) {	
			try {
				Scanner scan =  new Scanner(System.in);
				char a = scan.next().charAt(0);
				return a;
			}catch(InputMismatchException e) {
				System.out.println("Expected a single character, enter again!");
			}
		}
	}
}
