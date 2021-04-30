package main;


import CommonSnippets.*;
import java.util.*;

public class Doctor extends Employee{
	DisplayMenu dm = new DisplayMenu();
	CommonCodes c = new CommonCodes();
	
	public Doctor(){}
	
	Doctor(long id, String name, double age, char gender,String shifts, String password){
		super(id,name,age,gender,shifts,password);
	}
	
	public void addPrescription(Patient p) {
		int choice;
		ArrayList<String> meds = new ArrayList<String>();		
		
		do {
			dm.doctorAddMedicines();
			choice = c.inputInt("");
			
			switch(choice) {
				case 1: meds.add(c.inputString("Enter the name of the medicine. "));
						break;
				case 2: choice=2;
						MedicineBlock mb = new MedicineBlock(meds);
						p.addPrescription(new Prescription(mb));
						break;
				default:
						System.out.println("Wrong choice, enter again! ");
			}
		}while(choice!=2);
	}

	public void doctorDisplayPatientInBed() {
		super.enterPatientBed();
	};
	
	public void doctorFunctions() {
		int choice;
		String msg = "Enter your choice";
		do {
			dm.doctorMenu();
			choice=c.inputInt(msg);
			 switch(choice) {
			 	case 1: 
			 			dm.searchOptions();
			 			Manager m = new Manager();
			 			Patient p = doctorSearch(c.inputInt(""),m.retPatientList());
			 			if (p.retName()==null) {
			 				System.out.println("Patient not found");
			 				msg="Enter your choice again! ";
			 				break;
			 			}
			 			this.addPrescription(p);
			 			break;
			 	case 2:
			 			System.out.println("Allotting nurse!");
			 			break;
			 			
			 	case 3:
			 			super.enterPatientBed();
			 			break;
			 	case 4: 
			 			System.out.println("Exiting");
			 			choice=3;
			 			break;
	 			default:
	 					System.out.println("Wrong choice, enter again! ");
	 					break; 					
			 }
		}while(choice!=4);
	}
	
	public Patient doctorSearch(int choice,ArrayList<Patient> patientList) {
		do {
			switch(choice) {
				case 1: long id = c.inputLong("Enter the id of the patient. ");
						for (Patient p: patientList) {
							if (p.retId()==id) {
								return p;
							}	
						}
						System.out.println("Patient not found ");
						break;
						
				case 2: String name = c.inputString("Enter the name of the patient. ");
						for (Patient p: patientList) {
							System.out.println("Patient name: "+p.retName());
							if (p.retName().matches(name)) {
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
		
		return new Patient();
	}
	
	// Getter functions
	
		public long retId() {
			return super.retId();
		}
		
		public String retName() {
			return super.retName();
		}

		public double retAge() {
			return super.retAge();
		}
		
		public char retGender() {
			return super.retGender();
		}
		
		public String retShifts() {
			return super.retShifts();
		}
	
}
