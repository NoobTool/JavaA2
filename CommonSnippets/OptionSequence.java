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
			dm.managerMenu();
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
		public void modifyDetails(Manager m) {
			int choice;
			do {
				dm.managerMenuEmployeeSelection();
				System.out.println("4. Patient\n5. Exit ");
				choice = c.inputInt("Enter your choice. ");
				
				
				switch(choice) {
					case 1:	managerSearch("manager",m.retManagerList(),m);
							break;
							
					case 2:	managerSearch("doctor",m.retDoctorList(),m);
							break;
							
					case 3: managerSearch("nurse",m.retNurseList(),m);
							break;
							
					case 4: managerSearch("patient",m.retPatientList(),m);
							break;
							
					case 5: System.out.println("Exiting. ");
							choice=5;
							break;
					default:
						System.out.println("Wrong choice, enter again.");
				}
			}while(choice!=5);
			
		}
		
		// Used to search people with either ID/Name
		public void managerSearch(String post, ArrayList list, Manager m) {
			int choice=0;
			
			do {
				dm.searchOptions();
				boolean success=false;
				choice=c.inputInt("");
				switch(choice) {
					case 1: long id = c.inputLong("Enter the id of the "+post+". ");
							for (Object o : list) {
								Employee e = (Employee) o;
								if (e.retId()==id) {
									m.changeDetails(e, post);
									success=true;
									return;
								}	
							}
							if(success==false)
							System.out.println(post.substring(0,1).toUpperCase()+post.substring(1)+" not found ");
							break;
							
					case 2: String name = c.inputString("Enter the name of the  "+post+". ");
							for (Object o: list) {
								Employee e = (Employee) o;
								if (e.retName().matches(name)) {
									m.changeDetails(e, post);
									success=true;
									return;
								}	
							}
							if(success==false)
							System.out.println(post.substring(0,1).toUpperCase()+post.substring(1)+" not found ");
							break;
							
					case 3: choice=3;
							break;
					default: System.out.println(" Wrong choice, enter again! ");
							 break;
				}
			}while(choice!=3);
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
