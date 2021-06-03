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
	
	public String validateId(long id,String post) {
		
		if(post == "Manager" && (id<7730000 || id>7739999))
			return "Id must be in between (7730000,7739999) ! ";
		
		else if(post == "Doctor" && (id<6830000 || id>6839999)) 
			return "Id must be in between (6830000,6839999) ! ";
		
		else if(post=="Nurse" && (id<7830000 || id>7839999)) 
			return "Id must be in between (7830000,7839999) ! ";
		
		else if(post=="Patient" && (id<8030000 || id>8039999))
			return "Id must be in between (8030000,8039999) ! ";
		
		return "";
	}
	
	public Pair<Boolean,String> validateName(String name, Boolean allowed) {
		name = name.strip();
		String finalName="";
		if (name.length()<=1) {
			return new Pair<Boolean,String>(false,"Name must be 2 characters or more, enter again! ");
		}
		
		if(!allowed) {
			for(char s:name.toCharArray()) {
				if(!Character.isLetter(s) && s!=' ') {
					return new Pair<Boolean,String>(false,"Invalid Name, enter again! ");
				}
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
		if(post=="Nurse")
			return new Pair<Boolean,String>(true,"");
		
		try {
			LocalTime start_time,end_time;
			String[] shiftTimings = shifts.split("-");
			start_time = LocalTime.parse(shiftTimings[0]);
			end_time = LocalTime.parse(shiftTimings[1]);
			
			if(post.equals("Doctor") && ((start_time.until(end_time, ChronoUnit.MINUTES)<MAX_HOURS || 
					start_time.until(end_time, ChronoUnit.MINUTES)>MAX_HOURS)) && 
					((start_time.until(end_time, ChronoUnit.MINUTES)+(24*60))>MAX_HOURS ||
							(start_time.until(end_time, ChronoUnit.MINUTES)+(24*60))<MAX_HOURS))
				throw new InvalidShiftTimingsException("Shift duration must be equal to "+(MAX_HOURS/60)+" hour(s)!");
			
			if(post.equals("Manager") && ((start_time.until(end_time, ChronoUnit.MINUTES)<MAX_HOURS || 
					start_time.until(end_time, ChronoUnit.MINUTES)>MAX_HOURS) && 
					((start_time.until(end_time, ChronoUnit.MINUTES)+(24*60))>MAX_HOURS ||
							(start_time.until(end_time, ChronoUnit.MINUTES)+(24*60))<MAX_HOURS)))
				throw new InvalidShiftTimingsException("Shift duration must be only "+(MAX_HOURS/60)+" hours!");
			
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
	
	public String validateWardNumber(int n) {
		Manager m = new Manager(this);
		if(n<=0 || n>m.retWards())
			return "Ward doesn't exist, enter again! ";
		return "";
	}
	
	public String validateRoomNumber(int n) {
		Ward w = new Ward(this);
		if(n<=0 || n>w.retWards())
			return "Room doesn't exist, enter again! ";
		return "";
	}
	
	public String validateBedNumber(int n) {
		Room r = new Room();
		if(n<=0 || n>r.retRooms())
			return "Bed doesn't exist, enter again! ";		
		return "";
	}
}
