package main;

import ward.*;
import java.util.Objects;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import CommonSnippets.CommonCodes;
import CustomExceptions.InputValidation;


public class Nurse extends Employee{
	CommonCodes c = new CommonCodes();
	final int MAX_HOURS = 1;
	Manager m = new Manager("Empty object");
	
	public static ArrayList<AdministerMedicine> administeredMedicines = new ArrayList<AdministerMedicine>();
	
	public Nurse(){}
	
	Nurse(long id, String name, double age, char gender,String shifts,String password){
		super(id,name,age,gender,shifts,password);
	}
	
	public void changeBed(Patient p) {
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
	
	// Preparations for administering medicine
	
	public Patient retPatientInBed() {
		InputValidation i = new InputValidation();
		int wardNumber = i.validateWardNumber(c.inputInt("Enter patient's ward number. "));
		int roomNumber = i.validateRoomNumber(c.inputInt("Enter patient's room number. "));
		int bedNumber = i.validateBedNumber(c.inputInt("Enter patient's bed number. "));
		return retPatientInBed(bedNumber, roomNumber, wardNumber);
	}
	
	private Patient retPatientInBed(int bedNumber,int roomNumber,int wardNumber) {
		Manager m = new Manager("Empty Object");
		Ward wards[] = m.retWardList();
		Ward w = wards[wardNumber-1];
		Room r = w.retRoom(roomNumber-1);
		Patient p = r.retPatient(bedNumber-1);
		if(!Objects.isNull(p)) {
			return p;
		}
		
		else {
			System.out.println("No patient present at this bed! ");
		}
		return new Patient();
	}
	
	
	
	public void administerMedicine() {
		int choice,medSize=0;
		Patient p = retPatientInBed();
		if(!Objects.isNull(p)) {
			Prescription prescription = p.retPrescription();
			if(!Objects.isNull(prescription)) {
				MedicineBlock medicineBlock = prescription.retMedicineBlock();
				ArrayList<MedicineDose> medicines = medicineBlock.retMedicines();
				medSize = medicines.size();
				do {
					for(int i=0;i<medSize;i++) {
						System.out.println((i+1)+". "+medicines.get(i).retName().toLowerCase());
					}
					System.out.println((medSize+1)+". Exit");
					choice = c.inputInt("Enter your choice!");
					if(choice<medSize+1) {
						boolean flag=false;
						MedicineDose medicine = medicines.get(choice-1);
						for(LocalTime time: medicine.retTimes()) {
							LocalTime currentTime = LocalTime.now();
							if(Math.abs(time.until(currentTime,ChronoUnit.MINUTES))<5) {
								administeredMedicines.add(new AdministerMedicine(p.retId(),
										this.retId(), medicine, medicine.retTimes().indexOf(time), currentTime));
								System.out.println("Successfully administered "+medicine.retName());
								flag=true;
								break;
							}
						}
						if(flag==false)
							System.out.println("This medicine is not administered for this time. ");
					}
					
					else if(choice == medSize+1) {
						System.out.println("Exiting...");
					}
					
					else {
						System.out.println("Wrong choice, enter again! ");
					}
				}while(choice!=medSize+1);
				
				//AdministerMedicine medGiven = new AdministerMedicine(p.retId(),
				//		this.retId());
			}else { System.out.println("No prescription added! ");}
		}else {System.out.println("No patient present");}
		
	}
	
	public void printAdministerations() {
		for(AdministerMedicine ad: administeredMedicines)
			ad.printMedicinesAdministered();
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

