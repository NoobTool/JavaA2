package main;
import ward.WardDetails;
import prescription.Prescription;


public class Patient extends Person{
	private long id;
	private Prescription prescription = new Prescription();
	private WardDetails ward;
	
	public Patient(){
	}
	
	public Patient(long id, String name, double age, char gender){
		super(name,age,gender);
		this.id=id;
		ward=null;
		prescription = null;
	}
	
	public void addPrescription(Prescription p) {
		this.prescription = p;
	}
	
	public void displayPatients() {
		System.out.println("\nId: "+this.retId()+"\nName: "+this.retName()+
				"\nAge: "+this.retAge()+"\nGender: "+this.retGender());
	}
	
	public String printPrescription() {
		String returnValue="";
		if(prescription!=null) {
			returnValue+="Id: "+id;
			returnValue+="\n"+this.prescription.printPrescription();
		}
		return returnValue;
	}
	
	public void printWardDetails() {
		System.out.println("\n\n");
		System.out.println("Ward Number: "+retWardNumber());
		System.out.println("Room Number: "+retRoomNumber());
		System.out.println("Bed Number: "+retBedNumber());
	}
	
	// Setter functions
	public void setWard(WardDetails ward) {
		this.ward=ward;
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
	
	public Prescription retPrescription() {
		return this.prescription;
	}
	
	public int retWardNumber() {
		return ward.retWardNumber();
	}
	
	public int retRoomNumber() {
		return ward.retRoomNumber();
	}
	
	public int retBedNumber() {
		return ward.retBedNumber();
	}
	
	public WardDetails retWardDetails() {
		return ward;
	}
	
}
