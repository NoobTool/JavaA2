package ward;

public class WardDetails {
	private int wardNumber;
	private int roomNumber;
	private int bedNumber;
	
	
	public WardDetails() {
		wardNumber=-1;
		roomNumber=-1;
		bedNumber=-1;
	}
	
	public void setWardNumber(int n) {
		wardNumber=n;
	}
	
	public void setRoomNumber(int n) {
		roomNumber=n;
	}
	
	public void setBedNumber(int n) {
		bedNumber=n;
	}
	
	public int retWardNumber() {
		return this.wardNumber;
	}
	
	public int retRoomNumber() {
		return this.roomNumber;
	}
	
	public int retBedNumber() {
		return this.bedNumber;
	}
	
}