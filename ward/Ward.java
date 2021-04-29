package ward;
import main.Patient;

public class Ward {
	private final int WARD_SIZE = 6;
	private Room rooms[] = new Room[WARD_SIZE];

	public Ward() {
		for(int i=0;i<WARD_SIZE;i++)
			rooms[i] = new Room();
	}
	
	public void addPatient(Patient p) {
		if(isFull()) {
			System.out.println(" No space in this ward! ");
			return;
		}
		
		for(Room r: rooms) {
			boolean flag = r.addPatient(p);
			if(flag==true)
				return;
		}
		
		System.out.println("The patient can't be shifted to this ward! ");
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
