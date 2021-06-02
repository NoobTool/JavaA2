package CommonSnippets;
import java.util.ArrayList;

public class DisplayMenu {
	
	public ArrayList<String> managerMenu() {
		ArrayList<String> managerMenu = new ArrayList<String>();
		managerMenu.add("Admit a patient.");
		managerMenu.add("Hire new employees.");
		managerMenu.add("Modify staff details.");
		managerMenu.add("Display staff members.");
		managerMenu.add("Display the actions performed. ");
		return managerMenu;
	}
	
	public ArrayList<String > doctorMenu() {
		ArrayList<String> doctorMenu = new ArrayList<String>();
		doctorMenu.add("Add a prescription.");
		doctorMenu.add("Update prescription. ");
		doctorMenu.add("Display a patient's details. ");
		return doctorMenu;
	}	
	
	// Nurse 
	public ArrayList<String> nurseMenu() {
		ArrayList<String> nurseMenu = new ArrayList<String>();
		nurseMenu.add("Administer medicine. ");
		nurseMenu.add("Change bed automatically. ");
		nurseMenu.add("Change bed manually. ");
		nurseMenu.add("Display Patient Details. ");
		return nurseMenu;
	}
	
	
	// Medicine Dose
	public void timesMenu() {
		System.out.println("1. Change time of dose. ");
		System.out.println("2. Create new dose schedule. ");
		System.out.println("3. Exit. ");
		System.out.println("Enter your choice. ");
	}
	
	
	
}
