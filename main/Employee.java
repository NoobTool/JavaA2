package main;
import java.time.LocalTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import CustomExceptions.*;
import javafx.util.Pair;
import ward.*;

public class Employee extends Person{
	final int MAX_MINUTES = 360 ;
	final int DOC_MINUTES = 60;
	final int NURSE_MINUTES = 480;
	private long id;
	private ArrayList<String> shifts = new ArrayList<String>();
	private String password;
	private LocalDate lastShiftDate;
	private String chosenShiftTime;

	
	public Employee(){}
	
	Employee(long id, String name, double age, char gender, String shifts,String password){
		super(name,age,gender);
		this.id=id;
		this.shifts.add(shifts);
		this.password = password;
		
	}
	
	
	public Pair<String,Patient> patientInBed(int bedNumber,int roomNumber,int wardNumber) {
		
		Manager m = new Manager("Empty Object");
		Ward wards[] = m.retWardList();
		Ward w = wards[wardNumber-1];
		
		// Patient in singleRoom
		if(roomNumber<=w.retSingleRooms()) {
			SingleRoom r = w.retSingleRoomsList()[roomNumber-1];
			Patient p = r.retPatient();
			if(p!=null) {	
				return new Pair<String,Patient>("", p);
			}
			else {
				return new Pair<String,Patient>("No patient present at this bed! ", 
						new Patient());
			}
		}
		
		// Patient in dual rooms
		else if(roomNumber>w.retSingleRooms() && roomNumber<=w.retDualRooms()+w.retSingleRooms()) {
			DualRoom r = w.retDualRoomList()[roomNumber-1];
			Patient p = r.retPatient(bedNumber-1);
			System.out.println("p in employee is "+p);
			if(p!=null) {	
				return new Pair<String,Patient>("", p);
			}
			else {
				return new Pair<String,Patient>("No patient present at this bed! ", 
						new Patient());
			}
		}
		
		else {
			roomNumber -= w.retSingleRooms()+w.retDualRooms();
			Room r = w.retRoom(roomNumber-1);
			Patient p = r.retPatient(bedNumber-1);
			if(p!=null) {	
				return new Pair<String,Patient>("", p);
			}
			else {
				return new Pair<String,Patient>("No patient present at this bed! ", 
						new Patient());
			}
		}
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
	
	public String changeShifts(Employee e, String post, int oldShiftIndex, String newShift, int choice) 
			throws TooManyShiftsException {
		Manager m = new Manager("Object to return MAX_SHIFTS");		
		switch(choice) {
			case 1:
				if(e.retShifts().size()!=m.retMaxShifts()) {
					boolean flag = checkShifts(e, newShift, false, post);
					if(flag==true) {
						e.setShifts(newShift);
						if(e.retShifts().size()>=2)
						sortTimes(e);
						return "";
					}
					else 
						return "Wrong shift!";							
				}	
				else
					throw new TooManyShiftsException("Cannot add more than 2 shifts. ");

			case 2: if(!post.equals("Nurse"))
						return alterShifts(e,post,false,oldShiftIndex,newShift);
					else
						return "Shifts can't be changed. ";
			
			case 3: if(!post.equals("Nurse"))
						return alterShifts(e,post,true, oldShiftIndex, newShift);
					else
						return "Nurse's shift can't be deleted. ";
		}
		
		return "Suppressing errors!";
	}
	
	public String alterShifts(Employee e, String post, boolean remove, int oldShiftIndex, String newShift) {
		if(remove==false) {
			boolean flag = checkShifts(e, newShift, true, post);
			if(flag==true) {
				e.retShifts().set(oldShiftIndex, newShift);
				if(e.retShifts().size()>=2)
					sortTimes(e);
				return "";
			}
			else {
				return "Wrong shifts! ";
			}
		}
		
		else {
			if(e.retShifts().size()>=2) {
				e.retShifts().remove(oldShiftIndex);
				return "";
			}
			else {
				return "Shifts cannot be 0 ";
			}
		}	
	}
	
	// Check if new shift start time is not equal to previous start time
	// Check if new start time is not in between another shift time
	// Same for end time
	public boolean checkShifts(Employee e, String shifts, Boolean change, String post) {
			
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
