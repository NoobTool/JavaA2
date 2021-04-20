import java.util.*;


public class Manager extends Employee{
	
	Manager(int id, String name, int age, char gender,String shifts){
		super(id,name,age,gender,shifts);
	}
	
	public void addStaff(int id, String name, int age, char gender,String shifts, String post){
		if (post=="Manager") {
			Manager m =  new Manager(id, name, age, gender, shifts);
			
		}
		
	}
	
	public String displayManagers(ArrayList<Manager> staffList) {
		for(Manager m: staffList)
			System.out.println(m.retName());
		return staffList.get(staffList.size()-1).retName();
	}
	
	public String displayDoctors(ArrayList<Doctor> staffList) {
		for (Doctor d: staffList) {
			System.out.println(d.retName());
		}
		
		return staffList.get(staffList.size()-1).retName();
			
	}
	
	
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
