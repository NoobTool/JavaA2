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
		this.id=id;
		
	}
	
	public long retId() {
		return this.id;
	}
	
	void checkTimings(LocalTime start_time,LocalTime end_time) throws InvalidShiftTimings{
		if (end_time.isBefore(start_time))
			throw new InvalidShiftTimings();
	}
}
