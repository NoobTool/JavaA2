package main;
import CommonSnippets.CommonCodes;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import CustomExceptions.InputValidation;
import java.util.Objects;
import CustomExceptions.*;
import ward.Room;
import ward.Ward;

public class Employee extends Person{
	final int MAX_MINUTES = 360 ;
	final int DOC_MINUTES = 60;
	final int NURSE_MINUTES = 480;
	private long id;
	private ArrayList<String> shifts = new ArrayList<String>();
	private String password;
	private LocalDate lastShiftDate;
	private String chosenShiftTime;
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
	
	public String changeShifts(Employee e, String post, String shift, int choice) {
		Manager m = new Manager("Object to return MAX_SHIFTS");		
			switch(choice) {
				case 1: try{
					if(e.retShifts().size()!=m.retMaxShifts()) {
						boolean flag = checkShifts(e, shift, false);
						if(flag==true) {
							e.setShifts(shift);
							if(e.retShifts().size()>=2)
							sortTimes(e);
							return "";
						}
						else 
							return "Wrong shift!";							
					}	
					else
						throw new TooManyShiftsException("Cannot add more than 2 shifts. ");
				}catch(TooManyShiftsException exception) {
					System.out.println("Cannot enter more than 2 shifts.");
				}

				case 2: if(!post.equals("nurse"))
							alterShifts(e,post,false,shift);
						else
							return "Shifts can't be changed. ";
				
				case 3: if(!post.equals("nurse"))
							alterShifts(e,post,true,shift);
						else
							return "Nurse's shift can't be deleted. ";
						break;
			}
		return "Some problem must have occured";
	}
	
	public String alterShifts(Employee e, String post, boolean remove, String newShift) {
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
					boolean flag = checkShifts(e, newShift, true);
					if(flag==true) {
						e.retShifts().set(choice-1, newShift);
						if(e.retShifts().size()>=2)
							sortTimes(e);
						return "";
					}
					else {
						return "Wrong shifts! ";
					}
				}
				
				else {
					if(e.retShifts().size()==2) {
						e.retShifts().remove(choice-1);
						return "";
					}
					else {
						return "Shifts cannot be 0 ";
					}
				}
			}
				else if(choice==last_iteration+1){break;}
				
				else {
					return "Wrong choice, enter again! ";
				}
		}while(choice<=last_iteration);
		return "Something wrong happened!";		
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
				else if(LocalTime.parse(checkTimings[0]).isBefore(LocalTime.parse(shiftTimings[0]))
						&& LocalTime.parse(checkTimings[1]).isAfter(LocalTime.parse(shiftTimings[1])))
					return false;
				else
					return true;
			}
		}
		
		return false;
	}
	
	public void sortTimes(Employee e)
	{
	    int i, j,n=e.retShifts().size();
	    LocalTime key;
	    String keyString;
	    for (i=1;i<n;i++)
	    {
	    	keyString = e.retShifts().get(i);
	        key = LocalTime.parse(e.retShifts().get(i).split("-")[0]);
	        j = i-1;

	        while (j >= 0 && LocalTime.parse(e.retShifts().get(j).split("-")[0]).isAfter(key))
	        {
	            e.retShifts().set(j+1, e.retShifts().get(j));
	            j = j - 1;
	        }
	        e.retShifts().set(j+1,keyString);
	    }
	}
	
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	// Setter Functions
	
	public void setLastShiftDate(LocalDate lastShiftDate) {
		this.lastShiftDate = lastShiftDate;
	}
	
	public void setChosenShiftTime(String chosenShiftTime) {
		this.chosenShiftTime= chosenShiftTime;
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
	

	public int retHours(String post) {
		if(post.equals("Nurse"))
			return NURSE_MINUTES;
		else if(post.equals("Doctor"))
			return DOC_MINUTES;
		else
			return MAX_MINUTES;
	}
	
	public LocalDate retLastShiftDate() {
		return this.lastShiftDate;
	}
	
	public String retChosenShiftTime() {
		return this.chosenShiftTime;
	}
	
	
}
