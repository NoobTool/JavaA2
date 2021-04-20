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
		Staff<Manager> managerList = new Staff<Manager>();
		
		ResidentCare rc = new ResidentCare();
		System.out.println("1. Login as Manager");
		System.out.println("Enter your choice!");
		//choice=rc.inputInt();
		switch(choice) {
			case 1: 
					Manager m1 = new Manager(11,"Ram",21,"Male");
					managerList.addStaff(m1);
					m1.displayManagers(managerList.retStaff());
		}
	}
}
