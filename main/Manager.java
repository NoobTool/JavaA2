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
	static ArrayList<Long> idList = new ArrayList<Long>();
	static ArrayList<Long> availableIdList = new ArrayList<Long>();
	//static ArrayList<Patient> patientList = new ArrayList<Patient>();
	InputValidation i = new InputValidation();
	
	public Manager(){
		idList.add((long)7730000);
		idList.add((long)6830000);
		idList.add((long)7830000);
		idList.add((long)8030000);
		managerList.addStaff(new Manager((long)7730000,"Ram",21,'M',"09:00-05:00"));
		doctorList.addStaff(new Doctor((long)6830000,"Babu",16,'M',"12:00-03:00"));
		nurseList.addStaff(new Nurse((long)7830000,"Elisa",18,'F',"00:00-07:00"));
		
		availableIdList.add((long)7830001);
		availableIdList.add((long)6830012);
	}
	
	public Manager(long id, String name, double age, char gender,String shifts){
		super(id,name,age,gender,shifts);
	}
	
	
	public long allotId(String post) {
		long id=0;
		int asciiCode = (int) post.charAt(0);
		long postId = asciiCode*100000;
		long postLastId = postId+100000-1;
		
		for(long x: availableIdList) {
			if(x>postId && x<postLastId)
				return x;
		}

		return id;
	}
	
	public void addStaff(String post){
		
		long id;
		double age;
		String name,shifts;
		char gender;
		
		id = allotId(post);
		
		name = c.inputString("Enter the name of the "+post.toLowerCase()+"! ");
		name = name.strip();
		name = i.validateName(name);
		
		age = c.inputDouble("Enter the age of "+name);
		age = i.validateAge(age);
		
		gender = c.inputChar("Enter the gender of the employee "+name);
		gender = i.validateGender(gender);
		
		shifts = c.inputString("Enter the shift timings in the format XX:XX-YY:YY");
		
		
		if (post=="Manager") {
			
			if(id==0) {
				Manager m =  new Manager(idList.get(0)+1, name, age, gender, shifts);
				idList.set(0,idList.get(0)+1);
				managerList.addStaff(m);
				
			}
			
			else {
				Manager m =  new Manager(id, name, age, gender, shifts);
				availableIdList.remove(availableIdList.indexOf(id));
				managerList.addStaff(m);
			}
			
			
		}
		
		else if (post=="Doctor") {
			if(id==0) {
				Doctor d =  new Doctor(idList.get(1)+1, name, age, gender, shifts);
				idList.set(1,idList.get(1)+1);
				doctorList.addStaff(d);
			}
			
			else {
				Doctor d =  new Doctor(id, name, age, gender, shifts);
				availableIdList.remove(availableIdList.indexOf(id));
				doctorList.addStaff(d);
			}
		}
		
		else {
			if(id==0) {
				Nurse n =  new Nurse(idList.get(2)+1, name, age, gender, shifts);
				idList.set(2,idList.get(2)+1);
				nurseList.addStaff(n);
			}
			
			else {
				Nurse n =  new Nurse(id, name, age, gender, shifts);
				availableIdList.remove(availableIdList.indexOf(id));
				nurseList.addStaff(n);
			}
		}
		
	}
	
public void addStaff(Manager m){
		managerList.addStaff(m);
	}
	
	
	// Displaying the current employees present
	
	public long displayManagers() {
		for(Manager m: managerList.members)
			System.out.print(m.retName()+" ");
		
		System.out.println();
		
		return managerList.members.get(managerList.members.size()-1).retId();
	}
	
	public long displayDoctors() {
		for (Doctor d: doctorList.members)
			System.out.print(d.retId()+" ");
		
		System.out.println();
		
		return doctorList.members.get(doctorList.members.size()-1).retId();		
	}
	
	public long displayNurses() {
		for(Nurse n: nurseList.members)
			System.out.print(n.retName()+" ");
		
		System.out.println();
		
		return nurseList.members.get(nurseList.members.size()-1).retId();
	}
	
	
	// Getter functions
	
	public long retId() {
		return super.retId();
	}
	
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
