package ward;

import main.Patient;
import CustomExceptions.InputValidation;
import java.util.ArrayList;

public class Ward {
	private final int WARD_SIZE = 4;
	private final int DUAL_ROOMS = 1;
	private final int SINGLE_ROOMS = 1;
	private Room rooms[] = new Room[WARD_SIZE];
	private DualRoom dualRooms[] = new DualRoom[DUAL_ROOMS];
	private SingleRoom singleRooms[] = new SingleRoom[SINGLE_ROOMS];
	
	// Constructors
	public Ward(InputValidation i) {}
	
	public Ward(String s) {}
	
	public Ward() {
		
		for(int i=0;i<WARD_SIZE;i++)
			rooms[i] = new Room();
		
		for(int i=0;i<DUAL_ROOMS;i++)
			dualRooms[i] = new DualRoom();
		
		for(int i=0;i<SINGLE_ROOMS;i++)
			singleRooms[i] = new SingleRoom();
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
				w.setRoomNumber((SINGLE_ROOMS+DUAL_ROOMS+i+1));
				return w;
			}
		}
		
		for(int i=0;i<DUAL_ROOMS;i++) {
			System.out.println("In dual rooms");
			WardDetails w = dualRooms[i].addPatient(p);
			System.out.println("Name: "+p.retName()+" "+w.retBedNumber());
			if(w.retBedNumber()!=-1) {
				w.setRoomNumber((SINGLE_ROOMS+i+1));
				return w;
			}
		}
		
		System.out.println("The patient can't be shifted to this ward! ");
		return new WardDetails();
	}
	
	// Printing Ward Status (Polymorphism)
	public void printWardStatus() {
		int i=0;
		for(Room r: rooms) {
			System.out.println("Room "+(i+1));
			r.printRoomStatus();
			i+=1;
		}
		
		for(DualRoom r: dualRooms) {
			System.out.println("Room "+(i+1));
			r.printRoomStatus();
			i+=1;
		}
		
		for(SingleRoom r: singleRooms) {
			System.out.println("Room "+(i+1));
			r.printRoomStatus();
			i+=1;
		}
	}
	
	
	// Unoccupy room
	public void unOccupyRoom(int room, int bed) {
		rooms[room].unoccupyBed(bed);
	}
	
	public void unOccupyDualRoom(int room, int bed) {
		dualRooms[room].unoccupyBed(bed);
	}
	
	public boolean isFull() {
		boolean isFull;
		for (Room r: rooms) {
			if(!r.isFull())
				isFull = false;
		}
		
		for(DualRoom dr: dualRooms) {
			if(!dr.isFull())
				return false;
			else
				return true;
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
