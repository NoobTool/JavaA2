import java.util.*;


public class Manager extends Person{
	
	Manager(int id, String name, int age, String gender){
		super(id,name,gender);
	}
	
	public void addStaff(int id, String name, int age, String gender, String post){
		if (post=="Manager") {
			Manager m =  new Manager(id, name, age, gender);
		}
		
	}
	
	public void displayManagers(ArrayList<Manager> staffList) {
		for(Manager m: staffList)
			System.out.println(m.retName());
	}
	
	
	public String retName() {
		return super.retName();
	}
	
	
	public int retAge() {
		return super.retAge();
	}
	
	public String retGender() {
		return super.retGender();
	}
			
};
