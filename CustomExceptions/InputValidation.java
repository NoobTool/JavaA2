package CustomExceptions;
import java.lang.Math;
import ward.*;
import java.util.*;
import javafx.util.Pair;
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
	
	public Pair<Boolean,String> validateName(String name) {
		name = name.strip();
		String finalName="";
		if (name.length()<=1) {
			return new Pair<Boolean,String>(false,"Name must be 2 characters or more, enter again! ");
		}
		
		for(char s:name.toCharArray()) {
			if(!Character.isLetter(s) && s!=' ') {
				return new Pair<Boolean,String>(false,"Invalid Name, enter again! ");
			}
		}
		
		for(String s: name.split(" ")) {
			if(s.length()>0)
				finalName=finalName+" "+s;			
		}
		
		return new Pair<Boolean,String>(true,finalName);
	}
	 
	public Pair<Double,String> validateAge(double age) {
	
		if(age<0 || age>110) 
			return new Pair<Double,String>((double)-1,"Wrong age specified, enter again! ");
		
		if(age>1)
			return new Pair<Double,String>(Math.floor(age),"");
			
		return new Pair<Double,String>(age,"");	
	}
	
	public String validateGender(char gender) {
		if(gender != 'M' && gender!='F') {
			return "Wrong gender specified, enter again! ";
		}
		return "";
	}
	
	public Pair<Boolean,String> validateShifts(String shifts, String post) {
		
		final int MAX_HOURS = e.retHours(post);
		String errorMsg;
		try {
			LocalTime start_time,end_time;
			String[] shiftTimings = shifts.split("-");
			start_time = LocalTime.parse(shiftTimings[0]);
			end_time = LocalTime.parse(shiftTimings[1]);
			
			if(end_time.isBefore(start_time))
				throw new InvalidShiftTimingsException("End_time must be after starting time");
			
			if(post.equals("Doctor") && (start_time.until(end_time, ChronoUnit.MINUTES)<MAX_HOURS || 
					start_time.until(end_time, ChronoUnit.MINUTES)>MAX_HOURS))
				throw new InvalidShiftTimingsException("Shift duration must be equal to "+(MAX_HOURS/60)+" hour(s)!");
			
			if(post.equals("Manager") && (start_time.until(end_time, ChronoUnit.MINUTES)<MAX_HOURS || 
					start_time.until(end_time, ChronoUnit.MINUTES)>MAX_HOURS))
				throw new InvalidShiftTimingsException("Shift duration must be only 6 hours!");
			
			
			return new Pair<Boolean,String>(true,shifts);
			
		}catch(DateTimeParseException e) {
			return new Pair<Boolean,String>(false,"Please enter shifts in format XX:XX-YY:YY");
			
		}catch(InvalidShiftTimingsException e) {
			return new Pair<Boolean,String>(false,e.retMsg());
		
		}catch(ArrayIndexOutOfBoundsException e) {
			return new Pair<Boolean,String>(false,"Please enter shifts in format XX:XX-YY:YY");
		}
	} 
	
	public Boolean validatePassword(String password) {
		if(password.length()<4)
			return false;
		return true;
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
