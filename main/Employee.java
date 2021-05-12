package main;
import CommonSnippets.CommonCodes;

import java.time.LocalTime;
import java.util.ArrayList;
import CustomExceptions.InputValidation;
import java.util.Objects;
import CustomExceptions.*;
import ward.Room;
import ward.Ward;

public class Employee extends Person{
	final int MAX_HOURS = 6 ;
	private long id;
	private ArrayList<String> shifts = new ArrayList<String>();
	private String password;
	private CommonCodes c = new CommonCodes();
	
	public Employee(){}
	
	Employee(long id, String name, double age, char gender, String shifts,String password){
		super(name,age,gender);
		this.id=id;
		this.shifts.add(shifts);
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
		this.shifts.add(shifts);
	}
	
	public void changeShifts(Employee e) {
		int choice;
		InputValidation i = new InputValidation();
		String msg = "Enter your choice";
		do {
			System.out.println("1. Add shift. ");
			System.out.println("2. Change shift timings. ");
			System.out.println("3. Delete a shift. ");
			System.out.println("4. Exit");
			choice = c.inputInt(msg);
			
			switch(choice) {
				case 1: try{
					if(e.retShifts().size()!=2) {
						String shift = i.validateShifts(c.inputString("Enter the new shift timings in the format "
								+ "XX:XX-YY:YY. "));
						boolean flag = checkShifts(e, shift, false);
						if(flag==true) {
							e.setShifts(shift);
							break;
						}
						else {
							System.out.println("Wrong shift! ");
							break;
						}
							
					}	
					else
						throw new TooManyShiftsException("Cannot add more than 2 shifts. ");
				}catch(TooManyShiftsException exception) {
					System.out.println("Cannot enter more than 2 shifts.");
					return;
				}

				case 2: alterShifts(e,false);
						break;
				case 3: alterShifts(e,true);
						break;
				default: System.out.println("Wrong choice, enter again! ");
			}
		}while(choice!=4);
	}
	
	
	public void alterShifts(Employee e, boolean remove) {
		InputValidation iv = new InputValidation();
		int choice, last_iteration=0;
		do {

			for(int i=0; i<e.retShifts().size();i++) {
				last_iteration+=1;
				System.out.println((i+1)+". "+e.retShifts().get(i));
			}
			System.out.println((last_iteration+1)+". Exit. ");
			choice = c.inputInt("Enter your choice! ");
			if(choice<=last_iteration && choice>0) {
				if(remove==false) {
					String newShift = iv.validateShifts(c.inputString("Enter new shift. "));
					boolean flag = checkShifts(e, newShift, true);
					if(flag==true) {
						e.retShifts().set(choice-1, newShift);
						return;
					}
					else {
						System.out.println("Wrong shifts! ");
						break;
					}
				}
				
				else {
					if(e.retShifts().size()==2) {
						e.retShifts().remove(choice-1);
						break;
					}
					else {
						System.out.println("Shifts cannot be 0 ");
						return;
					}
				}
			}
				else if(choice==last_iteration+1){break;}
				
				else {
					System.out.println("Wrong choice, enter again! ");
				}
			
		}while(choice<=last_iteration);
		
	}
	
	// Check if new shift start time is not equal to previous start time
	// Check if new start time is not in between another shift time
	// Same for end time
	public boolean checkShifts(Employee e, String shifts, boolean change) {
		if(e.retShifts().size()==0)
			return true;
		else if(e.retShifts().size()==1 && change==true)
			return true;
		else {
			String[] checkTimings = shifts.split("-");
			for(String s: e.retShifts()) {
				String[] shiftTimings = s.split("-");
				if(checkTimings[0].equals(shiftTimings[0]) || checkTimings[0].equals(shiftTimings[1]))
					return false;
				else if(checkTimings[1].equals(shiftTimings[0]) || checkTimings[1].equals(shiftTimings[1]))
					return false;
				else if(LocalTime.parse(checkTimings[0]).isAfter(LocalTime.parse(shiftTimings[0]))
						&& LocalTime.parse(checkTimings[0]).isBefore(LocalTime.parse(shiftTimings[1])))
						return false;
				else if(LocalTime.parse(checkTimings[1]).isAfter(LocalTime.parse(shiftTimings[0]))
						&& LocalTime.parse(checkTimings[1]).isBefore(LocalTime.parse(shiftTimings[1])))
					return false;
				else
					return true;
			}
		}
		
		return false;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	// Getter functions
	
	public long retId() {
		return this.id;
	}
	
	public ArrayList<String> retShifts() {
		return this.shifts;
	}
	
	public String retPass() {
		return this.password;
	}
	
	public int retMaxHours() {
		return this.MAX_HOURS;
	}
	
}
