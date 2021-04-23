package main;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class OptionSequence {
	
	public DisplayMenu dm = new DisplayMenu();
	public CommonCodes c = new CommonCodes();
	Manager m = new Manager();
	
	public void managerHires() {
		int choice=0;
		do {
			dm.managerMenuEmployeeSelection();
			choice = c.inputInt("");
			
			switch(choice) {
				case 1: m.addStaff("Manager");break;
				case 2: m.addStaff("Doctor");break;
				case 3: m.addStaff("Nurse");break;
				case 4: break;
				default: System.out.println("Wrong choice!");
			}
		}while(choice!=4);
		
	}
	
	public void managerDisplays() {
		int choice = 0;
		
		dm.managerMenuEmployeeSelection();
		choice = c.inputInt("");
		
		switch(choice) {
			case 1: m.displayManagers();break;
			case 2: m.displayDoctors();break;
			case 3: m.displayNurses();break;
			default: System.out.println("Wrong choice");break;
		}
	}
	
	
	public void managerSequence() {
		DisplayMenu dm = new DisplayMenu();
		int choice=0;
		do {
			dm.managerMenu();
			choice=c.inputInt("");
			
			switch(choice) {
				case 1: System.out.println("Admitting a patient!");break;
				case 2: managerHires();break;
				case 3: System.out.println("Modifying Staff details!");break;
				case 4: System.out.println("Changing shift timings.");break;
				case 5: managerDisplays();break;
			}
			
		}while(choice!=6);
		
		
	}
}
