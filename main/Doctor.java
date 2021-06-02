package main;
import prescription.*;
import CommonSnippets.*;
import java.util.*;
import Actions.*;
import java.time.*;

public class Doctor extends Employee{
	DisplayMenu dm = new DisplayMenu();
	CommonCodes c = new CommonCodes();
	ActionList a = new ActionList();
	
	public Doctor(){}
	
	Doctor(long id, String name, double age, char gender,String shifts, String password){
		super(id,name,age,gender,shifts,password);
	}
	
	public MedicineDose addMedicineDose(Patient p, String name, int doses, ArrayList<LocalTime> times, int choice) {		
		return new MedicineDose(name,doses,times);
	}
	
	public void addPrescription(Patient p, ArrayList<MedicineDose> meds) {
		MedicineBlock mb = new MedicineBlock(meds);
		p.addPrescription(new Prescription(mb));
		a.addAction(new Action(this.retId(),p.retId(),"prescription addition",LocalDate.now(),LocalTime.now()));
	}

	public void updatePrescription(String name, String time, Patient p) {
		if(p.retPrescription()!=null)
			updateMedicineBlock(name, time, p);
		else 
			System.out.println("No prescription added");
	}
	
	
	public void updateMedicineBlock(String name, String time,Patient p) {
		int choice=0;
		ArrayList<MedicineDose> meds = p.retPrescription().retMedicineBlock()
										.retMedicines();
		
		if(name!="") {
			String medicineName = name.split("#")[0];
			int index = Integer.parseInt(name.split("#")[1]);
			meds.get(index).setName(medicineName);
		}
		
		if(time!="") {
			LocalTime medicineTime = LocalTime.parse(time.split("#")[0]);
			int index = Integer.parseInt(time.split("#")[1]);
			meds.get(index).setTime(index,medicineTime);
		}
		
//		for (MedicineDose md: mb.retMedicines()) {
//			if (md.retName().equals(name)) {
//				do {
//					dm.doctorDoseMenu();
//					choice = c.inputInt("");
//					
//					switch(choice) {
//					case 1: String medicineName = c.inputString(" Enter the new name of the medicine! ");
//							md.setName(medicineName);
//							a.addAction(new Action(this.retId(),p.retId(),"medicine name updation",LocalDate.now(),LocalTime.now()));
//							break;							
//					case 2: int dose = c.inputInt("Enter the number of doses ");
//							md.setDose(dose);
//							a.addAction(new Action(this.retId(),p.retId(),"dose updation",LocalDate.now(),LocalTime.now()));
//							break;
//							
//					case 3: md.changeDoseTime();
//							a.addAction(new Action(this.retId(),p.retId(),"dose time updation",LocalDate.now(),LocalTime.now()));
//							break;
//							
//					case 4: System.out.println("Exiting...");
//							break;
//							
//					default: System.out.println("Wrong choice, enter again. ");
//						
//					}
//				}while(choice!=4);
//			}
//		}
	}	
	
	public Patient doctorSearch(ArrayList<Patient> patientList, long id, String name) {
			
			if(id!=-1) {
				for (Patient p : patientList) {
					if (p.retId()==id)
						return p;
				}
			return new Patient();
			}
				 		
			else {
				for (Patient p: patientList) {
					if (p.retName().matches(name))
						return p;
				}
				return new Patient();
			}
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
		
		public ArrayList<String> retShifts() {
			return super.retShifts();
		}
	
}
