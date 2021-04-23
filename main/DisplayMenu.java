package main;

public class DisplayMenu {
	
	public void managerMenu() {
		System.out.println("1. Admit a patient.");
		System.out.println("2. Hire new employees.");
		System.out.println("3. Modify staff details.");
		System.out.println("4. Modify shift details.");
		System.out.println("5. Display staff members.");
		System.out.println("6. Exit");
		System.out.println("Enter your choice! ");
	}
	
	public void managerMenuEmployeeSelection() {
		System.out.println("1. Manager.");
		System.out.println("2. Doctor.");
		System.out.println("3. Nurse.");
		System.out.println("4. Exit");
		System.out.println("Enter your choice! ");
	}
	
	public void managerMenuStaffSelection() {
		System.out.println("1. Modify manager's details.");
		System.out.println("2. Modify doctor's details.");
		System.out.println("3. Modify nurse's details.");
		System.out.println("4. Exit");
		System.out.println("Enter your choice! ");
	}
	
	public void managerMenuStaffSelectionSearchOptions() {
		System.out.println("1. Search by id");
		System.out.println("2. Search by name");
		System.out.println("3. Exit");
		System.out.println("Enter your choice! ");
	}
}
