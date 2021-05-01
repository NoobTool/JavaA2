package main;

import ward.Ward;
import ward.WardDetails;


public class Nurse extends Employee{
	
	
	public Nurse(){}
	
	Nurse(long id, String name, double age, char gender,String shifts,String password){
		super(id,name,age,gender,shifts,password);
	}
	
	public void displayPatientInBed() {
		super.enterPatientBed();
	}
	
	public void addWard(Patient p) {
		Manager m = new Manager("Empty Object");
		Ward w = new Ward();
		Ward wards[] = m.retWardList();
		WardDetails wardDetails = new WardDetails();
		WardDetails oldDetails = p.retWardDetails();
		if(isWardFull()==false) {
			for(int i=0;i<m.retWards();i++) {
				w=wards[i];
				wardDetails = w.addPatient(p);
				if(wardDetails.retRoomNumber()!=-1) {
					wardDetails.setWardNumber((i+1));
					p.setWard(wardDetails);
					System.out.println("Ward Set Successfully");
					System.out.println("Patient: "+p.retName()+" is resting at "
							+"ward "+(i+1)+" in room "+wardDetails.retRoomNumber()
							+" in bed "+wardDetails.retBedNumber());
					wards[oldDetails.retWardNumber()-1].unOccupyRoom(oldDetails.retRoomNumber()-1);
					return;
				}
			}
		}
		System.out.println("Sorry, no space for you in the care centre! ");
		return;
	}
	
	// To check if ward is full or not
	public boolean isWardFull() {
		Ward w = new Ward();
		Manager m = new Manager("Empty Object");
		for(int i=0;i<m.retWards();i++) {
			if(w.isFull()==false)
				return false;
		}
		return true;
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

