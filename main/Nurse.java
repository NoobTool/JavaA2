package main;


public class Nurse extends Employee{
	
	Nurse(int id, String name, double age, char gender,String shifts){
		super(id,name,age,gender,shifts);
	}
	
	public void addPrescription() {
		System.out.println("Nurse Class");
	}
	
	public String retName() {
		return super.retName();
	}
	
}

