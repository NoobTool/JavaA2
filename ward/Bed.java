package ward;
import main.Patient;

public class Bed {
	private boolean occupied;
	private Patient patient;
	
	public Bed() {
		occupied=false;
		patient=null;
	}
	
	public Bed(boolean occupied, Patient p) {
		this.occupied = occupied;
		this.patient = p;		
	}
	
	public void addPatient(Patient p) {
		this.occupied = true;
		this.patient = p;
	}
	
	public void unoccupyBed() {
		this.occupied = false;
		this.patient = null;
	}
	
	public boolean retOccupied() {
		return this.occupied;
	}
	
	public Patient retPatient() {
		return this.patient;
	}
	
	public char retGender() {
		return this.patient.retGender();
	}
	
	public String retName() {
		return this.patient.retName();
	}
}

