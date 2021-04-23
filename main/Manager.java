package main;
import java.util.*;
import java.time.*;
import CustomExceptions.InvalidShiftTimings;
import CustomExceptions.InputValidation;

public class Manager extends Employee{
	
	CommonCodes c = new CommonCodes();
	static Staff<Manager> managerList = new Staff<Manager>();
	static Staff<Doctor> doctorList = new Staff<Doctor>();
	static Staff<Nurse> nurseList = new Staff<Nurse>();
	InputValidation i = new InputValidation();
	
	Manager(){
		managerList.addStaff(new Manager(110,"Ram",21,'M',"09:00-05:00"));
		doctorList.addStaff(new Doctor(220,"Babu",16,'M',"12:00-03:00"));
		nurseList.addStaff(new Nurse(300,"Elisa",18,'F',"00:00-07:00"));
	}
	
	Manager(int id, String name, double age, char gender,String shifts){
		super(id,name,age,gender,shifts);
	}
	
	public void addStaff(String post){
		
		int id;
		double age;
		String name,shifts;
		char gender;
		
		System.out.println("Enter the id of the "+post.toLowerCase());
		id = c.inputInt();
		
		System.out.println("Enter the name of the employee!");
		name = c.inputString();
		
		System.out.println("Enter the age of "+name);
		age = c.inputDouble();
		age = i.validateAge(age);
		
		System.out.println("Enter the gender of the employee "+name);
		gender = c.inputChar();
		
		System.out.println("Enter the shift timings in the format XX:XX-YY:YY");
		shifts = c.inputString();
		
		
		if (post=="Manager") {
			Manager m =  new Manager(id, name, age, gender, shifts);
			managerList.addStaff(m);
		}
		
		else if (post=="Doctor") {
			Doctor d =  new Doctor(id, name, age, gender, shifts);
			doctorList.addStaff(d);
		}
		
		else {
			Nurse n = new Nurse(id, name, age, gender, shifts);
			nurseList.addStaff(n);
		}
		
	}
	
public void addStaff(Manager m){
		managerList.addStaff(m);
	}
	
	
	// Displaying the current employees present
	
	public String displayManagers() {
		for(Manager m: managerList.members)
			System.out.print(m.retName()+" ");
		
		System.out.println();
		
		return managerList.members.get(managerList.members.size()-1).retName();
	}
	
	public String displayDoctors() {
		for (Doctor d: doctorList.members)
			System.out.print(d.retName()+" ");
		
		System.out.println();
		
		return doctorList.members.get(doctorList.members.size()-1).retName();		
	}
	
	public String displayNurses() {
		for(Nurse n: nurseList.members)
			System.out.print(n.retName()+" ");
		
		System.out.println();
		
		return nurseList.members.get(nurseList.members.size()-1).retName();
	}
	
	
	// Getter functions
	
	public String retName() {
		return super.retName();
	}
	
	
	public double retAge() {
		return super.retAge();
	}
	
	public char retGender() {
		return super.retGender();
	}
			
};
