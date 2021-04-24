package CommonSnippets;
import java.util.*;

import main.Doctor;
import main.Manager;
import main.MedicineBlock;
import main.Patient;

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
				case 4: break;
				default: System.out.println("Wrong choice!");
			}
		}while(choice!=4);
		
	}
	
	public void managerDisplays() {
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
	
	public void doctorSequence() {
		Doctor d =  new Doctor();
		do {
			dm.doctorMenu();
			choice=c.inputInt("");
			
			switch(choice) {
				case 1: d.addPrescription(p);
				case 2: managerHires();break;
				case 3: System.out.println("Exiting..");choice=3;break;
				default: System.out.println("Wrong choice, enter again! ");
			}
			
		}while(choice!=3);		
	}
	
	
	public Patient doctorSearch(int choice,ArrayList<Patient> patientList) {
		do {
			switch(choice) {
				case 1: long id = c.inputLong("Enter the id of the patient. ");
						for (Patient p: patientList) {
							if (p.retID()==id) {
								return p;
							}	
						}
						System.out.println("Patient not found ");
						return p;
						
				case 2: String name = c.inputString("Enter the name of the patient. ");
						for (Patient p: patientList) {
							if (p.retName()==name) {
								return p;
							}	
						}
						System.out.println("Patient not found ");
						break;
						
				case 3: choice=3;
						break;
				default: System.out.println(" Wrong choice, enter again! ");
						 break;
			}
			choice=c.inputInt("");
		}while(choice!=3);
	}
	
	public void doctorAddMedicines(ArrayList<String> meds) {
		do {
			dm.doctorMenu();
			choice=c.inputInt("");
			 switch(choice) {
			 	case 1: 
			 			dm.searchOptions();
			 			Manager m = new Manager(this);
			 			doctorSearch(c.inputInt(""),m.retPatientList());
			 }
		}while(choice!=3);
	}
}
