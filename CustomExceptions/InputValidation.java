package CustomExceptions;
import java.lang.Math;
import ward.*;
import java.util.*;
import CommonSnippets.CommonCodes;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import main.Employee;
import main.Manager;

public class InputValidation {
	Scanner scan = new Scanner(System.in);
	CommonCodes c = new CommonCodes();
	Employee e = new Employee();
	final int MAX_HOURS = e.retMaxHours();
	Ward w = new Ward();
	Room r = new Room();
	Bed b = new Bed();
	
	public long validateId(long id,String post) {
		
		if(post == "Manager" && id<13000) {
			id = c.inputLong("Id must be greater than 1,30,000 , enter again! ");
			id = validateId(id,"Manager");
		}

		else if(post == "Doctor" && id<230000) {
			id = c.inputLong("Id must be greater than 2,30,000 , enter again! ");
			id = validateId(id,"Doctor");
		}
		
		else if(post=="Nurse" && id<330000) {
			id = c.inputLong("Id must be greater than 3,30,000 , enter again! ");
			id = validateId(id,"Doctor");
		}
		
		else {
			id = c.inputLong("Id must be greater than 43,00,000 , enter again! ");
			id = validateId(id,"Patient");
		}
		
		return id;
	}
	
	public String validateName(String name) {
		
		if (name.length()<=1) {
			name = c.inputString("Name must be 2 characters or more, enter again! ");
			name = validateName(name);
		}
		
		for(char s:name.toCharArray()) {
			if(!Character.isLetter(s)) {
				name = c.inputString("Invalid Name, enter again! ");
				name = validateName(name);
				break;
			}
				
		}
		
		return name;
	}
	public double validateAge(double age) {
		
		if(age<0 || age>110) {
			age = c.inputDouble("Wrong age specified, enter again! ");
			age = validateAge(age);
		}
		
		if(age>1)
			age = Math.floor(age);
		return age;	
	}
	
	public char validateGender(char gender) {
		if(gender != 'M' && gender!='F') {
			gender = c.inputChar("Wrong gender specified, enter again! ");
			gender = validateGender(gender);
		}
		return gender;
	}
	
	public String validateShifts(String shifts) {
		
		try {
			LocalTime start_time,end_time;
			String[] shiftTimings = shifts.split("-");
			start_time = LocalTime.parse(shiftTimings[0]);
			end_time = LocalTime.parse(shiftTimings[1]);
			
			if(end_time.isBefore(start_time))
				throw new InvalidShiftTimingsException("End_time must be after starting time");
			
			if(start_time.until(end_time, ChronoUnit.HOURS)>MAX_HOURS)
				throw new InvalidShiftTimingsException("Shift timings must be less than 6 hours!");
			
			return shifts;
			
		}catch(DateTimeParseException e) {
			String newShifts = c.inputString("Please enter shifts in format XX:XX-YY:YY");
			newShifts = validateShifts(newShifts);
			return newShifts;
			
		}catch(InvalidShiftTimingsException e) {
			String newShifts = c.inputString("Enter again!");
			newShifts = validateShifts(newShifts);
			return newShifts;
		}
	}
	
	public int validateWardNumber(int n) {
		Manager m = new Manager(this);
		if(n<=0 || n>m.retWards()) {
			n = c.inputInt("Ward doesn't exist, enter again! ");
			n = validateWardNumber(n);
		}		
		return n;
	}
	
	public int validateRoomNumber(int n) {
		Ward w = new Ward(this);
		if(n<=0 || n>w.retWards()) {
			n = c.inputInt("Room doesn't exist, enter again! ");
			n = validateRoomNumber(n);
		}		
		return n;
	}
	
	public int validateBedNumber(int n) {
		Room r = new Room();
		if(n<=0 || n>r.retRooms()) {
			n = c.inputInt("Bed doesn't exist, enter again! ");
			n = validateBedNumber(n);
		}		
		return n;
	}
}
