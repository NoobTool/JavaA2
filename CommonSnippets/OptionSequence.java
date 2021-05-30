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
	
	// Manager
	
	// The manager's menu options
	public void managerSequence(Manager m) {
		int choice;
		do {
			for(String s: dm.managerMenu())
				System.out.println(s);
			
			choice=c.inputInt("");
			
			switch(choice) {
				case 1: m.addPeople("Patient");break;
				case 2: managerHires(m);break;
				case 3: modifyDetails(m);break;
				case 4: m.managerDisplays();break;
				case 5: m.printActionList();break;
				case 6: System.out.println("Exiting");break;
				default: System.out.println("Wrong choice, enter again. ");
			}
			
		}while(choice!=6);		
	}
	
	// Employing new staff
	public void managerHires(Manager m) {
		int choice;
		do {
			dm.managerMenuEmployeeSelection();
			System.out.println("4. Exit");
			choice = c.inputInt("");
			
			switch(choice) {
				case 1: m.addPeople("Manager");break;
				case 2: m.addPeople("Doctor");break;
				case 3: m.addPeople("Nurse");break;
				case 4: System.out.println("Exiting.");choice=4;break;
				default: System.out.println("Wrong choice!");
			}
		}while(choice!=4);
		
	}
	
	// Modify details of people
		public void modifyDetails(Manager m, String post, long id, String name) {
				
			if(post.equals("Manager"))
				managerSearch(post, m.retManagerList(), m, id, name);
			
			else if(post.equals("Doctor"))
				managerSearch(post, m.retDoctorList(), m, id, name);
			
			else if(post.equals("Nurse"))
				managerSearch(post, m.retNurseList(), m, id, name);
			
			else
				managerSearch(post, m.retPatientList(), m, id, name);
		}
		
		// Used to search people with either ID/Name
		public String managerSearch(String post, ArrayList list, Manager m, long id, String name) {
			
			if(id!=-1) {
				for (Object o : list) {
					Employee e = (Employee) o;
					if (e.retId()==id) {
						m.changeDetails(e, post);
						return "";
					}
				}
			return post.substring(0,1).toUpperCase()+post.substring(1)+" not found ";
			}
						
			else{
				for (Object o: list) {
					Employee e = (Employee) o;
					if (e.retName().matches(name)) {
						m.changeDetails(e, post);
						return "";
					}	
				}
				return post.substring(0,1).toUpperCase()+post.substring(1)+" not found ";
			}
		}
	
	
	
	// Doctor
		
	// Doctor's main menu
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
