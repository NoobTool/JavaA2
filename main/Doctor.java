package main;
import CommonSnippets.DisplayMenu;
import CommonSnippets.CommonCodes;
import java.util.*;

public class Doctor extends Employee{
	DisplayMenu dm = new DisplayMenu();
	CommonCodes c = new CommonCodes();
	
	Doctor(long id, String name, double age, char gender,String shifts){
		super(id,name,age,gender,shifts);
	}
	
	public void addPrescription(Patient p) {
		int choice;
		ArrayList<String> meds = new ArrayList<String>();		
		
		do {
			dm.doctorAddMedicines();
			choice = c.inputInt("");
			
			switch(choice) {
				case 1: meds.add(c.inputString("Enter the name of the medicine. "));
				case 2: choice=2;
						MedicineBlock mb = new MedicineBlock(meds);
						p.addPrescription(new Prescription(mb));
						break;
				default:
						System.out.println("Wrong choice, enter again! ");
			}
		}while(choice!=2);
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
