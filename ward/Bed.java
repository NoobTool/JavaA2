package ward;
import main.Patient;

public class Bed {
	private boolean occupied;
	private Patient patient;
	private WardDetails wd = new WardDetails();
	
	public Bed() {
		occupied=false;
	}
	
	public Bed(boolean occupied, Patient p, int n) {
		this.occupied = occupied;
		this.patient = p;
		wd.setBedNumber(n);
		
	}
	
	
	public WardDetails addPatient(int n) {
		if(occupied==false) {
			this.occupied = true;
			wd.setBedNumber(n);
			return wd;
		}
		return null;
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

