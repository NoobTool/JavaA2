package ward;
import java.util.Objects;

import main.Patient;

public class Ward {
	private final int WARD_SIZE = 6;
	private Room rooms[] = new Room[WARD_SIZE];
	private WardDetails wd = new WardDetails();

	public Ward() {
		for(int i=0;i<WARD_SIZE;i++)
			rooms[i] = new Room();
	}

	public WardDetails addPatient(Patient p) {
		if(isFull()) {
			System.out.println(" No space in this ward! ");
			return null;
		}
		
		for(Room r: rooms) {
			WardDetails w = r.addPatient(p);
			if(w.retRoomNumber()!=-1)
				return w;
		}
		
		System.out.println("The patient can't be shifted to this ward! ");
		return null;
	}
	
	public void printWardStatus() {
		int i=0;
		for(Room r: rooms) {
			System.out.println("Room "+(i+1));
			r.printRoomStatus();
			i+=1;
		}
	}
	
	
	public boolean isFull() {
		for (Room r: rooms) {
			if(!r.isFull())
				return false;
		}	
		return true;
	}
}
