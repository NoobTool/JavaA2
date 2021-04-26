package CommonSnippets;
import main.Manager;

public class OptionSequence {
	
	public DisplayMenu dm = new DisplayMenu();
	public CommonCodes c = new CommonCodes();
	Manager m = new Manager();
	int choice=0;
	
	public void managerHires() {
		do {
			dm.managerMenuEmployeeSelection();
			choice = c.inputInt("");
			
			switch(choice) {
				case 1: m.addStaff("Manager");break;
				case 2: m.addStaff("Doctor");break;
				case 3: m.addStaff("Nurse");break;
				case 4: m.addStaff("Patient");break;
				case 5: System.out.println("Exiting.");choice=5;break;
				default: System.out.println("Wrong choice!");
			}
		}while(choice!=5);
		
	}
	
	public void managerDisplays() {
		dm.managerMenuEmployeeSelection();
		choice = c.inputInt("");
		
		switch(choice) {
			case 1: m.displayManagers();break;
			case 2: m.displayDoctors();break;
			case 3: m.displayNurses();break;
			case 4: m.displayPatients();break;
			default: System.out.println("Wrong choice");break;
		}
	}
	
	
	public void managerSequence() {
		do {
			dm.managerMenu();
			choice=c.inputInt("");
			
			switch(choice) {
				case 1: m.addStaff("Patient");break;
				case 2: managerHires();break;
				case 3: System.out.println("Modifying Staff details!");break;
				case 4: System.out.println("Changing shift timings.");break;
				case 5: managerDisplays();break;
			}
			
		}while(choice!=6);		
	}
		
}
