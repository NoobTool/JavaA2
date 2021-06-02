package CommonSnippets;
import main.Manager;
import main.Nurse;
import main.Patient;
import main.Doctor;
import main.Employee;

import java.util.Objects;
import java.util.ArrayList;

public class OptionSequence {
	
	DisplayMenu dm = new DisplayMenu();
	CommonCodes c = new CommonCodes();
	
	// Nurse's main menu
	public void NurseMenu(Nurse n) {
		int choice;
		Patient p;
		do {
			for(String s : dm.nurseMenu())
				System.out.println(s);
			choice = c.inputInt("");
			
			switch(choice) {
				case 1: n.administerMedicine();
						break;
				case 2: p = n.enterPatientBed(true);
						if(!Objects.isNull(p.retName()))
							n.changeWardAutomatically(p);
						break;
				case 3: p = n.enterPatientBed(true);
						if(!Objects.isNull(p.retName()))
							n.changeBed(p);
						break;
				case 4: System.out.println("Exiting");
						break;
				default: System.out.println("Wrong choice, enter again! ");
				
			}
			
		}while(choice!=4);
		
	}
	
}
