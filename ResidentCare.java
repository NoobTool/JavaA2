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
		int choice=1;
		ResidentCare rc = new ResidentCare();
		System.out.println("1. Login as Manager");
		System.out.println("Enter your choice!");
		//choice=rc.inputInt();
		switch(choice) {
			case 1: Manager m = new Manager(11,"Ram","Male");
					Staff<Manager> s = new Staff<Manager>();
					s.printStaff();
		}
	}
}
