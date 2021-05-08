package main;

import ward.*;
import java.util.Objects;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import CommonSnippets.CommonCodes;
import CustomExceptions.InputValidation;


public class Nurse extends Employee{
	CommonCodes c = new CommonCodes();
	final int MAX_HOURS = 1;
	Manager m = new Manager("Empty object");
	Employee e = new Employee();
	
	public static ArrayList<AdministerMedicine> administeredMedicines = new ArrayList<AdministerMedicine>();
	
	public Nurse(){}
	
	Nurse(long id, String name, double age, char gender,String shifts,String password){
		super(id,name,age,gender,shifts,password);
	}
	
	// Changing patient's bed
	
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
					removePatient(oldDetails.retWardNumber(),oldDetails.retRoomNumber(),oldDetails.retBedNumber());
				}
			}
		}
		
		else {
			System.out.println("Bed not available! ");
		}
	}
	
	public void changeWardAutomatically(Patient p) {
		Manager m = new Manager("Empty Object");
		Ward wards[] = m.retWardList();
		WardDetails wardDetails = new WardDetails();
		WardDetails oldDetails = p.retWardDetails();
		System.out.println("OldDetails: "+oldDetails.retWardNumber()+" "+oldDetails.retRoomNumber()+" "+oldDetails.retBedNumber());
		if(isWardFull()==false) {
			for(int i=0;i<m.retWards();i++) {
				System.out.println("Old details now become "+oldDetails.retBedNumber());
				wardDetails = wards[i].addPatient(p);
				if(wardDetails.retRoomNumber()!=-1) {
					wardDetails.setWardNumber((i+1));
					System.out.println("Old details before setting become "+oldDetails.retBedNumber());
					p.setWard(wardDetails);
					System.out.println("Ward Set Successfully");
					System.out.println("Old details now become "+oldDetails.retBedNumber());
					System.out.println("Patient: "+p.retName()+" is resting at "
							+"ward "+(i+1)+" in room "+p.retRoomNumber()
							+" in bed "+p.retBedNumber());
					removePatient(oldDetails.retWardNumber(),oldDetails.retRoomNumber(),oldDetails.retBedNumber());
					return;
				}
			}
		}
		System.out.println("Sorry, no space for you in the care centre! ");
		return;
	}
	
	// Removing patient from bed
	
	public void removePatient(int wardNumber, int roomNumber, int bedNumber) {
		System.out.println("Bed number in remove patient "+ bedNumber);
		Manager m = new Manager("Empty Object");
		Ward wards[] = m.retWardList();
		wards[wardNumber-1].unOccupyRoom(roomNumber-1,bedNumber-1);
	}
	
	
	
	// Preparations for administering medicine
	
	// Entering the patient's ward details
	public Patient retPatientInBed() {
		InputValidation i = new InputValidation();
		int wardNumber = i.validateWardNumber(c.inputInt("Enter patient's ward number. "));
		int roomNumber = i.validateRoomNumber(c.inputInt("Enter patient's room number. "));
		int bedNumber = i.validateBedNumber(c.inputInt("Enter patient's bed number. "));
		return retPatientInBed(bedNumber, roomNumber, wardNumber);
	}
	
	// Return a patient in specific bed number
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
	
	
	// Administer Medicine
	public void administerMedicine() {
		int choice,medSize=0;
		Patient p = enterPatientBed(true);
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
							LocalDate currentDate = LocalDate.now();
							if(Math.abs(time.until(currentTime,ChronoUnit.MINUTES))<5) {
								int timeIndex = medicine.retTimes().indexOf(time);
								boolean administered  = checkMedicineAdministered(p.retId(),timeIndex,medicine.retTimes().get(timeIndex),currentDate);
								if(administered==false) {
									administeredMedicines.add(new AdministerMedicine(p.retId(),
											this.retId(), medicine,timeIndex,currentDate,currentTime));
									System.out.println("Successfully administered "+medicine.retName());
									flag=true;
									break;
								}
								else {
									System.out.println("This medicine has already been administered");
									flag=true;
									break;
								}
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
			}else { System.out.println("No prescription added! ");}
		}else {System.out.println("No patient present");}
		
	}
	
	
	// Check if medicine is already administered or not
	public boolean checkMedicineAdministered(long id, int timeIndex, LocalTime time, LocalDate date) {
		if(administeredMedicines.size()>0) {
			System.out.println("Greater than 0 ");
			for(int i = administeredMedicines.size()-1;i>=0;i--) {
				AdministerMedicine a = administeredMedicines.get(i);
				if(a.retPatientId()==id) {
					System.out.println("Returned time is "+(a.retMedicine().retTimes().get(timeIndex)==time));
					System.out.println("Returned date is "+a.retDate().isBefore(date));
					if(a.retMedicine().retTimes().get(timeIndex)==time && !a.retDate().isBefore(date)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	// Print Administerations
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

