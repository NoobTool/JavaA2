package ward;
import main.Patient;

public class Room {
	final int ROOM_SIZE = 4;
	private Bed beds[] = new Bed[ROOM_SIZE];
	private char gender;
	private boolean empty;
	//private WardDetails wd = new WardDetails();
	
	public Room() {
		empty=true;
		for(int j=0;j<ROOM_SIZE;j++)
			beds[j] = new Bed();
	}
	
	public WardDetails addPatient(Patient p) {
		if(!isFull()) {
			for(int i=0;i<ROOM_SIZE;i++) {
				if(beds[i].retOccupied()==false) {
					System.out.println("Patient name"+p.retName()+"Room"+(i+1));
					// Check if room is fully empty or not 
					// in which case we don't require to check gender
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
						else {
							System.out.println("Sorry, the gender is not right! ");
							return new WardDetails();
						}	
					}
				}
			}
		}
		return new WardDetails();
	}
	
	public void printRoomStatus() {
		int c=0;
		for(Bed b: beds) {
			c+=1;
			if(b.retOccupied())
				System.out.println("Name: "+b.retName()+" Room No. "+c);
		}
	}
	
	public void setEmpty(Boolean empty) {
		this.empty=empty;
	}
	
	public void setGender(char gender) {
		this.gender = gender;
	}
	
	public void unoccupyBed(int n) {
		beds[n].unoccupyBed();
		empty = true;
	}
	
	// Check if even one bed is empty
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
