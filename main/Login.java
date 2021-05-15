package main;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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
//				ArrayList<String> shiftTimings = n.retShifts();
//				LocalTime currentTime = LocalTime.now();
//				currentTime = LocalTime.parse(currentTime.format
//						(DateTimeFormatter.ofPattern("HH:mm")));
//				for(int i=0;i<m.retMaxShifts();i++) {
//					shiftStart = LocalTime.parse(shiftTimings.get(i).split("-")[0]);
//					shiftEnd = LocalTime.parse(shiftTimings.get(i).split("-")[1]);
//					if(currentTime==shiftStart || currentTime==shiftEnd || 
//							(currentTime.isAfter(shiftStart) && 
//									currentTime.isBefore(shiftEnd))) {
						boolean shouldLogin = verifySecondLogin((Employee)n);
						if(shouldLogin==true) {
							password = c.inputString("Enter password. ");
							if(password.matches(n.retPass()))
								return n;
							else
								break;
						}
						else {
							System.out.println("Sorry can't login.");
						}
					}
					else {
						try {
							throw new RestrictedTimingException();
						}catch(RestrictedTimingException e) {
							System.out.println("You cannot go in this shift!");
						}
					}
				}
		System.out.println("Id or password is wrong, try again! ");
		return new Nurse();
	}
	
	
	public boolean verifyShiftTimings() {
		return false;
	}
	
	
	// To verify if an employee is not logging in 
	// after taking 1 shift
	public boolean verifySecondLogin(Employee e) {
		Manager m = new Manager("Object to return max shifts");
		ArrayList<String> shiftTimings = e.retShifts();
		LocalTime shiftStart,shiftEnd;
		LocalTime currentTime = LocalTime.now();
		currentTime = LocalTime.parse(currentTime.format
				(DateTimeFormatter.ofPattern("HH:mm")));
		
		for(int i=0;i<m.retMaxShifts();i++) {
			shiftStart = LocalTime.parse(shiftTimings.get(i).split("-")[0]);
			shiftEnd = LocalTime.parse(shiftTimings.get(i).split("-")[1]);
			if(currentTime==shiftStart || currentTime==shiftEnd || 
					(currentTime.isAfter(shiftStart) && 
							currentTime.isBefore(shiftEnd))) {
				
				if(currentTime.until(shiftEnd, ChronoUnit.HOURS)<7) {
					if(e.retLastShiftDate()==null) {
						e.setLastShiftDate(LocalDate.now());
						e.setChosenShiftTime(shiftTimings.get(i));
						return true;
					}
					
					else if(e.retLastShiftDate()!=LocalDate.now()) {
						e.setLastShiftDate(LocalDate.now());
						e.setChosenShiftTime(shiftTimings.get(i));
						return true;
					}
					
					else if(e.retLastShiftDate()==LocalDate.now()) {
						if(e.retChosenShiftTime()==shiftTimings.get(i))
							return true;
						else
							return false;
					}
				}

				else {
					continue;
				}
				
				// Enter password here
			}
		}
		return false;
	}
	
	
	
}
	
