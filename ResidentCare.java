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
		int choice=1;
		// Generating staff lists for all types of people
		Staff<Manager> managerList = new Staff<Manager>();
		Staff<Doctor> doctorList = new Staff<Doctor>();
		
		ResidentCare rc = new ResidentCare();
		System.out.println("1. Login as Manager");
		System.out.println("2 Login as Doctor");
		System.out.println("2 Login as Nurse");
		System.out.println("Enter your choice!");
		//choice=rc.inputInt();
		switch(choice) {
			case 1: 
					Manager m1 = new Manager(11,"Ram",21,'M',"9-5");
					managerList.addStaff(m1);
					m1.displayManagers(managerList.retStaff());
					
			case 2:
					Doctor d1 = new Doctor(22,"Aman",35,'M',"10-12");
					doctorList.addStaff(d1);
					
		}
	}
}
