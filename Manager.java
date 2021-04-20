import java.util.*;


public class Manager extends Employee{
	
	CommonCodes c = new CommonCodes();
	Staff<Manager> managerList = new Staff<Manager>();
	Staff<Doctor> doctorList = new Staff<Doctor>();
	Staff<Nurse> nurseList = new Staff<Nurse>();
	
	Manager(){
		managerList.addStaff(new Manager(110,"Ram",21,'M',""));
		doctorList.addStaff(new Doctor(220,"Babu",16,'M',""));
		nurseList.addStaff(new Nurse(300,"Elisa",18,'F',""));
	}
	
	Manager(int id, String name, int age, char gender,String shifts){
		super(id,name,age,gender,shifts);
	}
	
	public void addStaff(String post){
		
		int id,age;
		String name,shifts;
		char gender;
		
		System.out.println("Enter the id of the employee!");
		id = c.inputInt();
		
		System.out.println("Enter the name of the employee!");
		name = c.inputString();
		
		System.out.println("Enter the age of the employee!");
		age = c.inputInt();
		
		System.out.println("Enter the gender of the employee");
		gender = c.inputChar();
		
		if (post=="Manager") {
			Manager m =  new Manager(id, name, age, gender, "");
			managerList.addStaff(m);
		}
		
		else if (post=="Doctor") {
			Doctor d =  new Doctor(id, name, age, gender, "");
			doctorList.addStaff(d);
		}
		
		else {
			Nurse n = new Nurse(id, name, age, gender, "");
			nurseList.addStaff(n);
		}
		
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
	
	
	public int retAge() {
		return super.retAge();
	}
	
	public char retGender() {
		return super.retGender();
	}
			
};
