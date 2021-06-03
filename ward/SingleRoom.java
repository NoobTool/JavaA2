package ward;

import main.Patient;

public class SingleRoom {
	private Bed bed = new Bed();
	
	public SingleRoom() {
		bed = new Bed();
	}
	
	public WardDetails addPatient(Patient p) {
		if(bed.retOccupied()==false) {
			bed=new Bed(true, p);
			WardDetails wd = new WardDetails();
			wd.setBedNumber(1);
			return wd;
		}	
		return new WardDetails();
	}
	
	public void printRoomStatus() {
		if(bed.retOccupied())
			System.out.println("Name: "+bed.retName()+" Room No. 1");
	}
	
	public boolean retOccupied() {
		return bed.retOccupied();
	}
	
	public void unoccupyBed(int n) {
		bed.unoccupyBed();
	}
	
	public Patient retPatient() {
		return this.bed.retPatient();
	}

}
