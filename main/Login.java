package main;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import CommonSnippets.CommonCodes;
import CustomExceptions.*;

public class Login {
	
	CommonCodes c = new CommonCodes();
	Manager manager = new Manager("Empty Object");
	
	public Login() {}
	
	public Manager managerLogin() {
		LocalTime currentTime = LocalTime.now();
		long id = c.inputLong("Enter your id. ");
		String password;
		for(Manager m: manager.retManagerList()) {
			
			if(m.retId()==id) {
				password = c.inputString("Enter password. ");
				for(String s: m.retShifts()){
					String[] shiftTimings = s.split("-");
					LocalTime start_time = LocalTime.parse(shiftTimings[0]);
					LocalTime end_time = LocalTime.parse(shiftTimings[1]);
					try {
						if(currentTime.isAfter(start_time.minusNanos(1)) && currentTime.isBefore(end_time.plusNanos(1))) {
							if(password.matches(m.retPass()))
								return m;
							else
								break;
								
						}
						else {
							throw new RestrictedTimingException();
						}
					}catch(RestrictedTimingException exception) {
						System.out.println("Not rostered for this shift. ");
						return new Manager("Exceptionally Correct");
					}
					
				}
			}
		}
		
		System.out.println("Id or password is wrong, try again! ");
		return new Manager();
	}
	
	public Doctor doctorLogin() {
		long id = c.inputLong("Enter your id. ");
		String password;
		for(Doctor d: manager.retDoctorList()) {
			if(d.retId()==id) {
				password = c.inputString("Enter password. ");
				if(password.matches(d.retPass()))
					return d;
				else
					break;
			}
		}
		
		System.out.println("Id or password is wrong, try again! ");
		return new Doctor();
	}
	
	public Nurse nurseLogin() {
		Manager m = new Manager("Object to return max shifts");
		long id = c.inputLong("Enter your id. ");
		String password;
		LocalTime shiftStart,shiftEnd;
		for(Nurse n: manager.retNurseList()) {
			if(n.retId()==id) {
				// Checking if login is within shift timings
				ArrayList<String> shiftTimings = n.retShifts();
				LocalTime currentTime = LocalTime.now();
				currentTime = LocalTime.parse(currentTime.format
						(DateTimeFormatter.ofPattern("HH:mm")));
				for(int i=0;i<m.retMaxShifts();i++) {
					shiftStart = LocalTime.parse(shiftTimings.get(i).split("-")[0]);
					shiftEnd = LocalTime.parse(shiftTimings.get(i).split("-")[1]);
					if(currentTime==shiftStart || currentTime==shiftEnd || 
							(currentTime.isAfter(shiftStart) && 
									currentTime.isBefore(shiftEnd))) {
						password = c.inputString("Enter password. ");
						if(password.matches(n.retPass()))
							return n;
						else
							break;
					}
					else {
						try {
							throw new RestrictedTimingException();
						}catch(RestrictedTimingException e) {
							System.out.println("You cannot go in this shift!");
						}
					}
				}
			}
		}
		
		System.out.println("Id or password is wrong, try again! ");
		return new Nurse();
	}
}
	
