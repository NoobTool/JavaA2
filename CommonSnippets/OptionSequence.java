package CommonSnippets;
import main.Nurse;
import main.Patient;
import java.util.Objects;

public class OptionSequence {
	
	DisplayMenu dm = new DisplayMenu();
	CommonCodes c = new CommonCodes();
		
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
						if(!Objects.isNull(p))
							n.changeWardAutomatically(p);
						break;
				case 3: p = n.enterPatientBed(true);
						if(!Objects.isNull(p))
							n.changeBed(p);
						break;
				case 4: System.out.println("Exiting");
						break;
				default: System.out.println("Wrong choice, enter again! ");
				
			}
			
		}while(choice!=4);
		
	}
	
}
