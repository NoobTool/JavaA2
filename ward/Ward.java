package ward;

import main.Patient;
import CustomExceptions.InputValidation;

public class Ward {
	private final int WARD_SIZE = 6;
	private Room rooms[] = new Room[WARD_SIZE];

	// Constructors
	public Ward(InputValidation i) {}
	
	public Ward(String s) {}
	
	public Ward() {
		for(int i=0;i<WARD_SIZE;i++)
			rooms[i] = new Room();
	}
	
	

	
	// Adding a patient
	public WardDetails addPatient(Patient p) {
		if(isFull()) {
			System.out.println(" No space in this ward! ");
			return new WardDetails();
		}
		
		for(int i=0;i<WARD_SIZE;i++) {
			WardDetails w = rooms[i].addPatient(p);
			System.out.println("Name: "+p.retName()+" "+w.retBedNumber());
			if(w.retBedNumber()!=-1) {
				w.setRoomNumber((i+1));
				return w;
			}
		}
		
		System.out.println("The patient can't be shifted to this ward! ");
		return null;
	}
	
	// Printing Ward Status
	public void printWardStatus() {
		int i=0;
		for(Room r: rooms) {
			System.out.println("Room "+(i+1));
			r.printRoomStatus();
			i+=1;
		}
	}
	
	
	// Unoccupy room
	public void unOccupyRoom(int roomNumber, int bedNumber) {
		this.rooms[roomNumber].unoccupyBed(bedNumber);
	}
	
	public boolean isFull() {
		for (Room r: rooms) {
			if(!r.isFull())
				return false;
		}	
		return true;
	}
	
	
	
	public Room retRoom(int n) {
		return this.rooms[n];
	}
	
	public Room[] retRoomList() {
		return rooms;
	}
	
	public int retWards() {
		return this.WARD_SIZE;
	}
	
}
