package ward;
import main.Patient;

public class Bed {
	private boolean occupied;
	private Patient patient;
	
	public Bed() {
		occupied=false;
	}
	
	public Bed(boolean occupied, Patient p) {
		this.occupied = occupied;
		patient = p;
	}
	
	
	public void addPatient(Patient p) {
		if(occupied==false) {
			this.patient = p;
			this.occupied = true;
		}
			
	}
	
	public boolean retOccupied() {
		return this.occupied;
	}
	
	public char retGender() {
		return this.patient.retGender();
	}
	
	public String retName() {
		return this.patient.retName();
	}
}

