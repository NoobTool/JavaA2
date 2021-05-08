package CommonSnippets;
import main.Manager;
import main.Nurse;
import main.Patient;
import main.Doctor;
import java.util.Objects;
import java.util.ArrayList;

public class OptionSequence {
	
	DisplayMenu dm = new DisplayMenu();
	CommonCodes c = new CommonCodes();
	
	
	// Doctor
	public void doctorFunctions(Doctor d) {
		int choice;
		String msg = "Enter your choice";
		do {
			ArrayList<String> doctorMenu = dm.doctorMenu();
			for (String s: doctorMenu)
				System.out.println(s);
			choice = c.inputInt(msg);
			switch(choice) {
			 	case 1: 
			 			dm.searchOptions();
			 			Manager m = new Manager("Empty Object");
			 			Patient p = d.doctorSearch(c.inputInt(""),m.retPatientList());
			 			if (p.retName()==null) {
			 				System.out.println("Patient not found");
			 				msg="Enter your choice again! ";
			 				break;
			 			}
			 			d.addPrescription(p);
			 			break;
			 	case 2:
			 			System.out.println("Allotting nurse!");
			 			break;
			 			
			 	case 3: d.enterPatientBed(false);
			 			break;
			 			
			 	case 4: d.updatePrescription();
			 			break;
			 			
			 	case 5: System.out.println("Exiting");
			 			break;
	 			default:
	 					System.out.println("Wrong choice, enter again! ");
	 					break; 					
			 }
		}while(choice!=5);
	}
	
	
	// Nurse
	public void NurseMenu(Nurse n) {
		int choice;
		Patient p;
		do {
			for(String s : dm.NurseMenu())
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
