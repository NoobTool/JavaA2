package main;

public class Employee extends Person{
	private long id;
	private String shifts;
	
	Employee(){}
	
	Employee(long id, String name, double age, char gender, String shifts){
		super(name,age,gender);
		this.id=id;
		this.shifts = shifts;
		
	}
	
	// Getter functions
	
			public long retId() {
				return this.id;
			}
			
			public String retShifts() {
				return this.shifts;
			}
}
