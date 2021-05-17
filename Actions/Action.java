package Actions;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Action {
	private long performerId;
	private long receiverId;
	private String actionName;
	private LocalDate date;
	private LocalTime time;
	
	public Action(){}
	
	public Action(long performerId, long receiverId, String actionName, LocalDate date, LocalTime time) {
		this.performerId = performerId;
		this.receiverId = receiverId;
		this.actionName = actionName;
		this.date = date;
		this.time = time;
	}
	
	public void printAction() {
		System.out.println("Employee with ID: "+this.retPerformerId()+" performed a/an "
				+this.retActionName()+" on Id: "+this.retReceiverId()+" on "+this.retDate()
				+" at "+this.retTime());
	}
	
	// Setters
	
	public void setPerformerId(long id) {
		performerId = id;
	}
	
	public void setReceiverId(long id) {
		receiverId = id;
	}
	
	public void setActionName(String s) {
		actionName = s;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public void setTime(LocalTime time) {
		this.time = time;
	}
	

	// Getters
	
	public long retPerformerId() {
		return performerId;
	}
	
	public long retReceiverId() {
		return receiverId;
	}
	
	public String retActionName() {
		return actionName;
	}
	
	public String retDate() {
		return date.format(DateTimeFormatter.ofPattern("dd-MM-YYYY"));
	}
	
	public String retTime() {
		return time.format(DateTimeFormatter.ofPattern("HH:mm"));
	}
	
}
