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
	
	public Doctor(long id, String name, double age, char gender,String shifts, String password){
		super(id,name,age,gender,shifts,password);
	}

	public void addPrescription(Patient p, ArrayList<MedicineDose> meds) {
		MedicineBlock mb = new MedicineBlock(meds);
		p.addPrescription(new Prescription(mb));
		a.addAction(new Action(this.retId(),p.retId(),"prescription addition",LocalDate.now(),LocalTime.now()));
	}

	public void updatePrescription(int medicineIndex,
			String name, String time, Patient p) {
		if(p.retPrescription()!=null)
			updateMedicineBlock(medicineIndex, name, time, p);
		else 
			System.out.println("No prescription added");
	}
	
	
	public void updateMedicineBlock(int medicineIndex,
			String name, String time, Patient p) {
		
		ArrayList<MedicineDose> meds = p.retPrescription().retMedicineBlock()
										.retMedicines();
		
		if(name!="")
			meds.get(medicineIndex).setName(name);
		
		if(time!="") {
			LocalTime medicineTime = LocalTime.parse(time.split("#")[0]);
			int index = Integer.parseInt(time.split("#")[1]);
			meds.get(medicineIndex).setTime(index,medicineTime);
		}
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
