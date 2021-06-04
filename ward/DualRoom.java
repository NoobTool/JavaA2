package ward;

import main.Patient;

public class DualRoom {
	final int ROOM_SIZE = 2;
	private Bed beds[] = new Bed[ROOM_SIZE];
	private char gender;
	private boolean empty;
	
	public DualRoom() {
		empty=true;
		for(int j=0;j<ROOM_SIZE;j++)
			beds[j] = new Bed();
	}
	
	public WardDetails addPatient(Patient p) {
		if(!isFull()) {
			for(int i=0;i<ROOM_SIZE;i++) {
				if(beds[i].retOccupied()==false) {
					if(empty==true) {
						beds[i]= new Bed(true, p);
						gender = p.retGender();
						empty=false;
						WardDetails wd = new WardDetails();
						wd.setBedNumber((i+1));
						return wd;
					}
					else {
						if(gender==p.retGender()) {
							beds[i]= new Bed(true, p);
							WardDetails wd = new WardDetails();
							wd.setBedNumber((i+1));
							return wd;
						}
						else
							return new WardDetails();
					}
				}
			}
		}
		return new WardDetails();
	}
		
	public void setEmpty(boolean empty) {
		this.empty = empty;
	}
	
	public void setGender(char gender) {
		this.gender = gender;
	}
	
	public void setPatient(Patient p, int n) {
		this.beds[n].addPatient(p);
	}
	
	public void unoccupyBed(int n) {
		beds[n].unoccupyBed();
		empty = true;
	}
	
	public boolean isFull() {
		for(Bed b: beds) {
			if(b.retOccupied()==false)
				return false;
		}
		
		return true;
	}
	
	public Patient retPatient(int n) {
		return this.beds[n].retPatient();
	}
	
	public boolean retEmpty() {
		return this.empty;
	}
	
	public int retRooms() {
		return this.ROOM_SIZE;
	}
	
	public char retGender() {
		return this.gender;
	}
	
	public Bed[] retBedList() {
		return beds;
	}

}