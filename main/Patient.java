package main;
import java.util.*;

public class Patient extends Person{
	private long id;
	private Prescription prescription = new Prescription();
	
	public Patient(){
		Patient p = new Patient(8030000,"PatientOne",34,'M');
	}
	
	public Patient(long id, String name, double age, char gender){
		super(name,age,gender);
		this.id=id;
	}
	
	public void addPrescription(Prescription p) {
		prescription = p;
	}
	
	public void displayPatients() {
		System.out.println("\nId: "+this.retID()+"\nName: "+this.retName()+
				"\nAge: "+this.retAge()+"\nGender: "+this.retGender());
	}
	
	
	public String retName() {
		return super.retName();
	}
	
	public long retID() {
		return this.id;		
	}
	
	public double retAge() {
		return super.retAge();
	}
	
	public char retGender() {
		return super.retGender();
	}
	
}
