package ward;
import main.Patient;

public class Room {
	final int ROOM_SIZE = 4;
	private Bed beds[] = new Bed[ROOM_SIZE];
	private char gender;
	private boolean empty;
	private WardDetails wd = new WardDetails();
	
	public Room() {
		empty=true;
		for(int i=0;i<ROOM_SIZE;i++)
			beds[i] = new Bed();
	}
	
	public WardDetails addPatient(Patient p) {
		if(!isFull()) {
			for(int i=0;i<ROOM_SIZE;i++) {
				if(beds[i].retOccupied()==false) {
					if(empty==true) {
						beds[i]= new Bed(true, p, (i+1));
						gender = p.retGender();
						empty=false;
						wd.setBedNumber((i+1));
						return wd;
					}
					else {
						if(gender==p.retGender()) {
							beds[i]= new Bed(true, p, (i+1));
							wd.setBedNumber((i+1));
							return wd;
						}
						else {
							System.out.println("Sorry, the gender is not right! ");
							wd.setBedNumber(-1);
							break;
						}	
					}
				}
			}
			return wd;
		}
		return new WardDetails();
	}
	
	public void printRoomStatus() {
		for(Bed b: beds) {
			if(b.retOccupied())
				System.out.println("Name: "+b.retName());
		}
	}
	
	public void unoccupyBed(int n) {
		this.beds[n].unoccupyBed();
		this.empty = true;
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
	
	public int retRooms() {
		return this.ROOM_SIZE;
	}

}
