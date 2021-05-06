package main;
import CommonSnippets.CommonCodes;
import CustomExceptions.InputValidation;
import java.util.Objects;

import ward.Room;
import ward.Ward;

public class Employee extends Person{
	final int MAX_HOURS = 6 ;
	private long id;
	private String shifts;
	private String password;
	private CommonCodes c = new CommonCodes();
	
	public Employee(){}
	
	Employee(long id, String name, double age, char gender, String shifts,String password){
		super(name,age,gender);
		this.id=id;
		this.shifts = shifts;
		this.password = password;
		
	}
	
	public Patient enterPatientBed(boolean shouldReturn) {
		InputValidation i = new InputValidation();
		int wardNumber = i.validateWardNumber(c.inputInt("Enter patient's ward number. "));
		int roomNumber = i.validateRoomNumber(c.inputInt("Enter patient's room number. "));
		int bedNumber = i.validateBedNumber(c.inputInt("Enter patient's bed number. "));
		if(shouldReturn==false)
			return patientInBed(bedNumber, roomNumber, wardNumber,false);
		else
			return patientInBed(bedNumber, roomNumber, wardNumber,true);
			
	}
	
	private Patient patientInBed(int bedNumber,int roomNumber,int wardNumber,boolean shouldReturn) {
		Manager m = new Manager("Empty Object");
		Ward wards[] = m.retWardList();
		Ward w = wards[wardNumber-1];
		Room r = w.retRoom(roomNumber-1);
		Patient p = r.retPatient(bedNumber-1);
		if(!Objects.isNull(p)) {
			if(shouldReturn == false) {
				p.displayPatients();
				p.printPrescription();
				p.printWardDetails();
			}
			else
				return p;
		}
		else {
			System.out.println("No patient present at this bed! ");
		}
		return new Patient();
	}
	
	// Setter functions
	
	public void setName(String name) {
		super.setName(name);
	}
	
	public void setAge(double age) {
		super.setAge(age);
	}
	
	public void setGender(char gender) {
		super.setGender(gender);
	}
	
	public void setShifts(String shifts) {
		this.shifts = shifts;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	// Getter functions
	
	public long retId() {
		return this.id;
	}
	
	public String retShifts() {
		return this.shifts;
	}
	
	public String retPass() {
		return this.password;
	}
	
	public int retMaxHours() {
		return this.MAX_HOURS;
	}
	
}
