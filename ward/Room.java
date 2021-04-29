package ward;
import main.Patient;

public class Room {
	final int ROOM_SIZE = 4;
	private int number;
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
		boolean flag=false;
		for(int i=0;i<ROOM_SIZE;i++) {
			if(beds[i].retOccupied()==false) {
				if(empty==true) {
					beds[i]= new Bed(true, p, (i+1));
					gender = p.retGender();
					flag=true;
					empty=false;
					wd.setRoomNumber((i+1));
					return wd;
				}
				else {
					if(gender==p.retGender()) {
						flag=true;
						beds[i]= new Bed(true, p, (i+1));
						wd.setRoomNumber((i+1));
						return wd;
					}
					else {
						System.out.println("Sorry, the gender is not right! ");
						break;
					}
						
				}
			}
		}
		return null;
	}
	
	public void printRoomStatus() {
		for(Bed b: beds) {
			if(b.retOccupied())
				System.out.println("Name: "+b.retName());
		}
	}
	
	public boolean isFull() {
		for(Bed b: beds) {
			if(b.retOccupied()==false)
				return false;
		}
		
		return true;
	}
	
	public int retNumber() {
		return this.number;
	}

}
