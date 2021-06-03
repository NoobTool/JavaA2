package application;

public class RetrieveValues {
	static int wardNumber;
	static int roomNumber;
	static int bedNumber;
	
	
	public static int retWard() {
		return wardNumber;
	}
	
	public static int retRoom() {
		return roomNumber;
	}
	
	public static int retBed() {
		return bedNumber;
	}
	
	public static void setWard(int n) {
		wardNumber=n;
	}
	
	public static void setRoom(int n) {
		roomNumber=n;
	}
	
	public static void setBed(int n) {
		bedNumber=n;
	}
	
}
