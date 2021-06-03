package main;
import prescription.*;
import ward.*;
import Actions.Action;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import CommonSnippets.CommonCodes;
import Actions.*;
import javafx.util.Pair;

public class Nurse extends Employee{
	CommonCodes c = new CommonCodes();
	Manager m = new Manager("Empty object");
	ActionList a = new ActionList();
	public static ArrayList<AdministerMedicine> administeredMedicines = new ArrayList<AdministerMedicine>();
	
	public Nurse(){}
	
	Nurse(long id, String name, double age, char gender,String shifts,String password){
		super(id,name,age,gender,shifts,password);
	}
	
	
	// Changing patient's bed manually
	public Pair<Boolean,String> changeBed(Patient p, int wardNumber, int roomNumber, int bedNumber) {
		WardDetails oldDetails = p.retWardDetails(); 	
		Ward ward = m.retWardList()[wardNumber-1];
		if(ward.retSingleRooms()>=roomNumber)
			return new Pair<Boolean,String>(false,"Isolation can't be provided through here,"
					+ " please choose 'Provide Isolation'");
		
		if(!ward.isFull()) {			
			
			if(ward.retDualRooms()>=roomNumber) {
				DualRoom room = ward.retDualRoomList()[roomNumber-1];
				Bed bed = room.retBedList()[bedNumber-1];
				
				if(bedNumber>room.retRooms())
					return new Pair<Boolean,String>(false,"Bed Number should be smaller"
							+ " than 3");
				else {
					if((!room.isFull() && p.retGender()==room.retGender()) || room.retEmpty()) {
						if(bed.retOccupied()==false) {
							bed.addPatient(p);
							room.setEmpty(false);
							if(room.retEmpty())
								room.setGender(p.retGender());
							p.setWard(new WardDetails(wardNumber,roomNumber,bedNumber));
							removePatient(oldDetails.retWardNumber(),oldDetails.retRoomNumber(),oldDetails.retBedNumber());
							a.addAction(new Action(this.retId(),p.retId(),"bed change",LocalDate.now(),LocalTime.now()));
							return new Pair<Boolean,String>(true,"Bed changed successfully! ");
						}
					}
				}
			} 
			
			else {
				Room room = ward.retRoomList()[roomNumber-1];
				Bed bed = room.retBedList()[bedNumber-1];
				if((!room.isFull() && p.retGender()==room.retGender()) || room.retEmpty()) {
					if(bed.retOccupied()==false) {
						bed.addPatient(p);
						room.setEmpty(false);
						if(room.retEmpty())
							room.setGender(p.retGender());
						p.setWard(new WardDetails(wardNumber,roomNumber,bedNumber));
						removePatient(oldDetails.retWardNumber(),oldDetails.retRoomNumber(),oldDetails.retBedNumber());
						a.addAction(new Action(this.retId(),p.retId(),"bed change",LocalDate.now(),LocalTime.now()));
						return new Pair<Boolean,String>(true,"Bed changed successfully! ");
					}
				}
			}
		}
		return new Pair<Boolean,String>(false,"Bed not available! ");
	}
	
	// Changing patient's bed automatically
	public Pair<Boolean,String> changeWardAutomatically(Patient p) {
		
		Manager m = new Manager("Empty Object");
		Ward wards[] = m.retWardList();
		WardDetails wardDetails = new WardDetails();
		WardDetails oldDetails = p.retWardDetails();
		if(isWardFull()==false) {
			for(int i=0;i<m.retWards();i++) {
				wardDetails = wards[i].addPatient(p);
				if(wardDetails.retRoomNumber()!=-1) {
					wardDetails.setWardNumber((i+1));
					p.setWard(wardDetails);
					removePatient(oldDetails.retWardNumber(),oldDetails.retRoomNumber(),oldDetails.retBedNumber());
					a.addAction(new Action(this.retId(),p.retId(),"bed changed",LocalDate.now(),LocalTime.now()));
					return new Pair<Boolean,String>(true,"Patient: "+p.retName()+" is resting at "
							+"ward "+(i+1)+" in room "+p.retRoomNumber()
							+" in bed "+p.retBedNumber());
					
				}
			}
		}
		return new Pair<Boolean,String>(false,"Sorry, no space for you in the care centre! ");
	}
	
	// Assign single room to a patient
	public void provideIsolation(Patient p) {
		WardDetails oldDetails=null;
		if(p.retWardDetails()!=null) {
			oldDetails = p.retWardDetails();
		}
			 
		
		int wardNumber = c.inputInt("Enter the new ward number. ");
		int roomNumber = c.inputInt("Enter the new room number. ");
		
		Ward ward = m.retWardList()[wardNumber-1];
		if(roomNumber>ward.retSingleRooms()) {
			System.out.println("This room is not used for isolation. ");
			return;
		}
		
		if(ward.isolationAvailable()) {
			SingleRoom room = ward.retSingleRoomsList()[roomNumber-1];
			if(!room.retOccupied()) {
				room.addPatient(p);
				p.setWard(new WardDetails(wardNumber,roomNumber,1));
				if(oldDetails!=null)
					removePatient(oldDetails.retWardNumber(),oldDetails.retRoomNumber(),oldDetails.retBedNumber());
				a.addAction(new Action(this.retId(),p.retId(),"isolation provided",LocalDate.now(),LocalTime.now()));
			}
		}
		
		else {
			System.out.println("Isolation can't be possible! ");
		}
	}
	
	public Patient nurseSearch(ArrayList<Patient> patientList, long id, String name) {
		
		if(id!=-1) {
			for (Patient p : patientList) {
				if (p.retId()==id)
					return p;
			}
		return new Patient();
		}
			 		
		else {
			for (Patient p: patientList) {
				if (p.retName().matches(name.strip()))
					return p;
			}
			return new Patient();
		}
	}
	
	
	
	// Removing patient from bed
	
	public void removePatient(int wardNumber, int roomNumber, int bedNumber) {
		Manager m = new Manager("Empty Object");
		Ward wards[] = m.retWardList();
		wards[wardNumber-1].unOccupyRoom(roomNumber-1,bedNumber-1);
	}
	
	// Administering Medicines
	// Index = index of the medicine chosen
	public String administerMedicine(Patient p, MedicineDose medicine) {
		for(LocalTime time: medicine.retTimes()) {
			LocalTime currentTime = LocalTime.now();
			LocalDate currentDate = LocalDate.now();
			if(Math.abs(time.until(currentTime,ChronoUnit.MINUTES))<5) {
				int timeIndex = medicine.retTimes().indexOf(time);
				boolean administered  = checkMedicineAdministered(p.retId(),timeIndex,medicine.retTimes().get(timeIndex),currentDate);
				if(administered==false) {
					administeredMedicines.add(new AdministerMedicine(p.retId(),
							this.retId(), medicine,timeIndex,currentDate,currentTime));
					a.addAction(new Action(this.retId(),p.retId(),"medicine administered",LocalDate.now(),LocalTime.now()));
					return "";
				}
				else
					return "This medicine has already been administered";
			}
		}
		return "This medicine is not administered for this time. ";
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
	
	// Print Administrations
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
		
		public ArrayList<String> retShifts() {
			return super.retShifts();
		}
	
}

