package ward;
import main.Patient;
import java.util.*;


public class Room {
	final int ROOM_SIZE = 4;
	private Bed beds[] = new Bed[ROOM_SIZE];
	private char gender;
	private boolean empty;
	
	public Room() {
		empty=true;
		for(int i=0;i<ROOM_SIZE;i++)
			beds[i] = new Bed();
	}
	
	public void addPatient(Patient p) {
		boolean flag=false;
		for(int i=0;i<ROOM_SIZE;i++) {
			if(beds[i].retOccupied()==false) {
				if(empty==true) {
					beds[i]= new Bed(true,p);
					gender = p.retGender();
					flag=true;
					empty=false;
				}
				else {
					if(gender==p.retGender()) {
						flag=true;
						beds[i]= new Bed(true,p);
					}
					else {
						System.out.println("Sorry, the gender is not right! ");
						break;
					}
						
				}
				break;
			}
		}
		
		if(flag==false)
			System.out.println("No rooms left! ");
		
	}
	
	public void printRoomStatus() {
		for(Bed b: beds) {
			if(b.retOccupied())
				System.out.println("Name: "+b.retName());
		}
	}

}
