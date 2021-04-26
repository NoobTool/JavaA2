package main;

public class Patient extends Person{
	private long id;
	private Prescription prescription = new Prescription();
	
	public Patient(){
	}
	
	public Patient(long id, String name, double age, char gender){
		super(name,age,gender);
		this.id=id;
	}
	
	public void addPrescription(Prescription p) {
		prescription = p;
	}
	
	public void displayPatients() {
		System.out.println("\nId: "+this.retId()+"\nName: "+this.retName()+
				"\nAge: "+this.retAge()+"\nGender: "+this.retGender());
	}
	
	public void printPrescription() {
		System.out.println("Id: "+id);
		this.prescription.printPrescription();
	}
	
	// Getter Functions
	public String retName() {
		return super.retName();
	}
	
	public long retId() {
		return this.id;		
	}
	
	public double retAge() {
		return super.retAge();
	}
	
	public char retGender() {
		return super.retGender();
	}
	
}
