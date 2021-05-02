package main;

import ward.*;
import CommonSnippets.CommonCodes;


public class Nurse extends Employee{
	CommonCodes c = new CommonCodes();
	Manager m = new Manager("Empty object");
	
	public Nurse(){
		System.out.println(m.retWardList());
	}
	
	Nurse(long id, String name, double age, char gender,String shifts,String password){
		super(id,name,age,gender,shifts,password);
	}
	
	public void displayPatientInBed() {
		super.enterPatientBed();
	}
	
	
	public void changeBed(Patient p) {
		Room r = new Room();
		WardDetails oldDetails = p.retWardDetails(); 
		int wardNumber = c.inputInt("Enter the new ward number. ");
		int roomNumber = c.inputInt("Enter the new room number. ");
		int bedNumber = c.inputInt("Enter the new bed number. ");
		
		Ward ward = m.retWardList()[wardNumber-1];
		Room room = ward.retRoomList()[roomNumber-1];
		Bed bed = room.retBedList()[bedNumber-1];
		
		if(!ward.isFull()) {
			if((!room.isFull() && p.retGender()==room.retGender()) || room.retEmpty()) {
				if(bed.retOccupied()==false) {
					bed.addPatient(p);
					p.setWard(new WardDetails(wardNumber,roomNumber,bedNumber));
					m.retWardList()[oldDetails.retWardNumber()-1].unOccupyRoom(oldDetails.retRoomNumber()-1,oldDetails.retBedNumber()-1);
				}
			}
		}
		
		else {
			System.out.println("Bed not available! ");
		}
	}
	
	public void changeWardAutomatically(Patient p) {
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
					wards[oldDetails.retWardNumber()-1].unOccupyRoom(oldDetails.retRoomNumber()-1,oldDetails.retBedNumber()-1);
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

