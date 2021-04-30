package CommonSnippets;

public class DisplayMenu {
	
	public void managerMenu() {
		System.out.println("1. Admit a patient.");
		System.out.println("2. Hire new employees.");
		System.out.println("3. Modify staff details.");
		System.out.println("4. Display staff members.");
		System.out.println("5. Exit");
		System.out.println("Enter your choice! ");
	}
	
	public void managerMenuEmployeeSelection() {
		System.out.println("1. Manager.");
		System.out.println("2. Doctor.");
		System.out.println("3. Nurse.");
	}
	
	public void searchOptions() {
		System.out.println("1. Search by id");
		System.out.println("2. Search by name");
		System.out.println("3. Exit");
		System.out.println("Enter your choice! ");
	}
	
	public void modificationOptions() {
		System.out.println("1. Name ");
		System.out.println("2. Age ");
		System.out.println("3. Gender");
		System.out.println("4. Shifts");
		System.out.println("5. Password");
		System.out.println("6. Exit");
	}
	
	public void doctorMenu() {
		System.out.println("1. Add a prescription.");
		System.out.println("2. Suggest Changes");
		System.out.println("3. Display a patient's details. ");
		System.out.println("4. Exit");
		System.out.println("Enter your choice!");
	}
	
	public void doctorAddMedicines() {
		System.out.println("1. Add medicine.");
		System.out.println("2. Exit");
		System.out.println("Enter your choice! ");
	}
}
