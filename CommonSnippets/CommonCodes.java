package CommonSnippets;
import java.util.InputMismatchException;
import java.time.LocalTime;
import java.util.Scanner;
import java.time.format.DateTimeParseException;

public class CommonCodes {
	
	Scanner scan = new Scanner(System.in);
	
	public long inputLong(String msg) {
		while(true) {	
			try {
				System.out.println(msg);
				long a = scan.nextLong();
				return a;
			}catch(InputMismatchException e) {
				System.out.println("Expected numeric input, enter again!");
			}
		}
	}
	
	public int inputInt(String msg) {
		while(true) {	
			try {
				System.out.println(msg);
				int a = scan.nextInt();
				return a;
			}catch(InputMismatchException e) {
				inputInt("Expected numeric input, enter again! ");
			}
		}
	}
	
	public double inputDouble(String msg) {
		while(true) {	
			try {
				System.out.println(msg);
				double a = scan.nextDouble();
				return a;
			}catch(InputMismatchException e) {
				inputDouble("Expected numeric input, enter again! ");
			}
		}
	}
	
	public String inputString(String msg) {
		while(true) {	
			try {
				Scanner scan = new Scanner(System.in);
				System.out.println(msg);
				String a = scan.nextLine();
				return a;
			}catch(InputMismatchException e) {
				inputString("Expected alphabetical input, enter again!");
			}
		}
	}
	
	public char inputChar(String msg) {
		while(true) {	
			try {
				System.out.println(msg);
				char a = scan.next().toUpperCase().charAt(0);
				return a;
			}catch(InputMismatchException e) {
				inputChar("Expected a single character, enter again!");
			}
		}
	}
	
	public LocalTime inputTime(String msg) {
		while(true) {	
			try {
				String s = inputString(msg);
				LocalTime time = LocalTime.parse(s);
				return time;
			}catch(DateTimeParseException e) {
				inputTime("Invalid format, please enter in HH:MM! ");
			}
		}
	}
	
}
