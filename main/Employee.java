package main;
import java.time.LocalTime;

public class Employee extends Person{
	final int MAX_HOURS = 6 ;
	private long id;
	private String shifts;
	private String password;
	
	public Employee(){}
	
	Employee(long id, String name, double age, char gender, String shifts,String password){
		super(name,age,gender);
		this.id=id;
		this.shifts = shifts;
		this.password = password;
		
	}
	
	// Setter functions
	
	public void setName(String name) {
		super.setName(name);
	}
	
	public void setAge(double age) {
		super.setAge(age);
	}
	
	public void setGender(char gender) {
		super.setGender(gender);
	}
	
	public void setShifts(String shifts) {
		this.shifts = shifts;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	// Getter functions
	
	public long retId() {
		return this.id;
	}
	
	public String retShifts() {
		return this.shifts;
	}
	
	public String retPass() {
		return this.password;
	}
	
	public int retMaxHours() {
		return this.MAX_HOURS;
	}
	
}
