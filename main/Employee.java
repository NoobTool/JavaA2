package main;
import java.time.LocalTime;

import CustomExceptions.InvalidShiftTimings;


public class Employee extends Person{
	private long id;
	private LocalTime start_time;
	private LocalTime end_time;
	
	Employee(){}
	
	Employee(long id, String name, double age, char gender, String shifts){
		super(name,age,gender);
		
		LocalTime start_time,end_time;
		this.id = id;
		String[] shiftTimings = shifts.split("-");
		start_time = LocalTime.parse(shiftTimings[0]);
		end_time = LocalTime.parse(shiftTimings[1]);
		this.start_time = LocalTime.parse(shiftTimings[0]);
		this.end_time = LocalTime.parse(shiftTimings[1]);
		
	}
	
	public long retId() {
		return this.id;
	}
	
	void checkTimings(LocalTime start_time,LocalTime end_time) throws InvalidShiftTimings{
		if (end_time.isBefore(start_time))
			throw new InvalidShiftTimings();
	}
}
